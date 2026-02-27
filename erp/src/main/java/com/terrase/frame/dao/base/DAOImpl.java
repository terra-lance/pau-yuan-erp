package com.terrase.frame.dao.base;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.terrase.frame.data.structure.ValidationException;
import com.terrase.util.CommonUtil;
import com.terrase.util.DateUtil;

public abstract class DAOImpl<T> extends HibernateDaoSupport implements DAO<T> {
	private static Map<String, Class<?>> resolvedTypes = new HashMap<>();

	protected Logger getLogger() {
		return Logger.getLogger(getClass());
	}

	@Autowired
	public void init(SessionFactory factory) {
		setSessionFactory(factory);
	}

	@Override
	public <R> List<R> findHql(Class<R> clazz, String hql, Map<String, Object> parameters) {
		Query<R> query = currentSession().createQuery(hql, clazz);

		if (parameters != null) {
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}

		return query.list();
	}

	@Override
	public <R> List<R> findHql(Class<R> clazz, String hql) {
		return findHql(clazz, hql, null);
	}

	@Override
	public List<T> find() {
		String hql = "select o from " + getClassParam().getName() + " o";
		return findHql(getClassParam(), hql);
	}

	@Override
	public List<T> findList(Map<String, Object> filters, String ordering) {
		return findList(filters, null, ordering);
	}

	@Override
	public List<T> findList(Map<String, Object> filters, Map<String, Object> predicates, String ordering) {
		String hql = "select o from " + getClassParam().getName() + " o ";
		Query<T> query = prepareQuery(getClassParam(), hql, filters, predicates, ordering);
		return query.list();
	}

	@Override
	public T find(long id) {
		String hql = "select o from " + getClassParam().getName() + " o where id = :id";
		Query<T> query = currentSession().createQuery(hql, getClassParam());
		query.setParameter("id", id);
		query.setMaxResults(1);
		return query.uniqueResult();
	}

	@Override
	public T findSingle(Map<String, Object> filters, String ordering) {
		return findSingle(filters, null, ordering);
	}

	@Override
	public T findSingle(Map<String, Object> filters, Map<String, Object> predicates, String ordering) {
		String hql = "select o from " + getClassParam().getName() + " o";
		Query<T> query = prepareQuery(getClassParam(), hql, filters, predicates, ordering);
		query.setMaxResults(1);
		return query.uniqueResult();
	}

	@Override
	public long count(Map<String, Object> filters) {
		return count(filters, null);
	}

	@Override
	public long count(Map<String, Object> filters, Map<String, Object> predicates) {
		String hql = "select count(o) from " + getClassParam().getName() + " o";
		Query<Long> query = prepareQuery(Long.class, hql, filters, predicates, null);
		return query.uniqueResult();
	}

	@Override
	public long identify(Map<String, Object> filters) {
		String hql = "select o.id from " + getClassParam().getName() + " o";
		Query<Long> query = prepareQuery(Long.class, hql, filters, null, null);
		query.setMaxResults(1);
		Long result = query.uniqueResult();
		return result == null ? 0L : result;
	}

	@Override
	public T findFirst() {
		return findSingle(null, null, null);
	}

	@Override
	public boolean exists(Map<String, Object> filters) {
		return exists(filters, null);
	}

	@Override
	public boolean exists(Map<String, Object> filters, Map<String, Object> predicates) {
		String hql = "select 1L from " + getClassParam().getName() + " o";
		Query<Long> query = prepareQuery(Long.class, hql, filters, predicates, null);
		query.setMaxResults(1);
		return query.uniqueResult() != null;
	}

	@Override
	public List<T> findLazy(int first, int pageSize, Map<String, SortMeta> sortings, Map<String, Object> filters) {
		return findLazy(first, pageSize, sortings, filters, null);
	}

	@Override
	public List<T> findLazy(int first, int pageSize, Map<String, SortMeta> sortings, Map<String, Object> filters,
			Map<String, Object> predicates) {
		String hql = "select o from " + getClassParam().getName() + " o";
		Query<T> query = prepareLazyQuery(getClassParam(), hql, filters, predicates, sortings);
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public long countLazy(Map<String, Object> filters) {
		return countLazy(filters, null);
	}

	@Override
	public long countLazy(Map<String, Object> filters, Map<String, Object> predicates) {
		String hql = "select count(o) from " + getClassParam().getName() + " o";
		Query<Long> query = prepareLazyQuery(Long.class, hql, filters, predicates, null);
		return query.uniqueResult();
	}

	protected <Q> Query<Q> prepareQuery(Class<Q> clazz, String hql, Map<String, Object> filters,
			Map<String, Object> predicates, String ordering) {
		List<String> wheres = new ArrayList<>();
		Map<String, Object> parameters = new LinkedHashMap<>();

		processFilters(wheres, parameters, filters);
		processPredicates(wheres, parameters, predicates);

		if (wheres.size() > 0) {
			hql += " where " + String.join(" and ", wheres);
		}

		if (StringUtils.isNotEmpty(ordering)) {
			hql += " " + ordering.trim();
		}

		Query<Q> query = currentSession().createQuery(hql, clazz);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value instanceof List) {
				query.setParameterList(key, (List<?>) value);
			} else {
				query.setParameter(key, value);
			}
		}

		return query;
	}

	protected void processFilters(List<String> wheres, Map<String, Object> parameters, Map<String, Object> filters) {

		if (filters == null || filters.isEmpty()) {
			return;
		}

		int filterIndex = 0;

		// Prepare parameters
		for (HashMap.Entry<String, Object> entry : filters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			// Preparing predicates
			if (value == null) {
				// For null values
				wheres.add("o." + key + " is null");
			} else if (value instanceof CharSequence) {
				// For string values
				CharSequence v = (CharSequence) value;

				if (StringUtils.startsWith(v, "%") || StringUtils.endsWith(v, "%")) {
					filterIndex++;
					wheres.add("o." + key + " like :daoprm_" + filterIndex);
					parameters.put("daoprm_" + filterIndex, value);
				} else {
					filterIndex++;
					wheres.add("o." + key + " = :daoprm_" + filterIndex);
					parameters.put("daoprm_" + filterIndex, value);
				}
			} else if (value.getClass().isArray()) {
				// For array values
				List<Object> values = CommonUtil.arrayToList(value);
				filterIndex++;
				wheres.add("o." + key + " in (:daoprm_" + filterIndex + ")");
				parameters.put("daoprm_" + filterIndex, values);
			} else if (value instanceof List) {
				// For list values
				filterIndex++;
				wheres.add("o." + key + " in (:daoprm_" + filterIndex + ")");
				parameters.put("daoprm_" + filterIndex, value);
			} else {
				// For general
				filterIndex++;
				wheres.add("o." + key + " = :daoprm_" + filterIndex);
				parameters.put("daoprm_" + filterIndex, value);
			}
		}
	}

	protected void processPredicates(List<String> wheres, Map<String, Object> parameters,
			Map<String, Object> predicates) {
		if (predicates == null || predicates.isEmpty()) {
			return;
		}

		List<String> keys = predicates.entrySet().stream().map(x -> x.getKey()).filter(x -> !StringUtils.isEmpty(x))
				.collect(Collectors.toList());
		List<String> params = findParams(String.join(" ", keys));
		List<Object> values = predicates.entrySet().stream().map(x -> x.getValue()).filter(x -> x != null)
				.collect(Collectors.toList());

		if (params.size() != values.size()) {
			throw new ValidationException("Predicate parameters and values count does not match");
		}

		wheres.addAll(keys);

		for (int i = 0; i < params.size(); i++) {
			Object value = values.get(i);
			if (value.getClass().isArray()) {
				List<Object> list = CommonUtil.arrayToList(value);
				parameters.put(params.get(i), list);
			} else {
				parameters.put(params.get(i), value);
			}
		}
	}

	protected <Q> Query<Q> prepareLazyQuery(Class<Q> clazz, String hql, Map<String, Object> filters,
			Map<String, Object> predicates, Map<String, SortMeta> sortings) {
		List<String> wheres = new ArrayList<>();
		Map<String, Object> parameters = new LinkedHashMap<>();

		processLazyFilters(wheres, parameters, filters);
		processLazyPredicates(wheres, parameters, predicates);

		if (wheres.size() > 0) {
			hql += " where " + String.join(" and ", wheres);
		}

		if (sortings != null && sortings.size() > 0) {
			hql += " order by ";

			boolean firstSort = true;
			for (Map.Entry<String, SortMeta> entry : sortings.entrySet()) {
				if (entry.getValue().getField() != null) {
					if (!firstSort) {
						hql += ",";
					}

					firstSort = false;

					hql += entry.getValue().getField();

					if (entry.getValue().getOrder() == SortOrder.DESCENDING) {
						hql += " desc ";
					}
				}
			}
		}

		Query<Q> query = currentSession().createQuery(hql, clazz);

		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value instanceof List) {
				query.setParameterList(key, (List<?>) value);
			} else {
				query.setParameter(key, value);
			}
		}

		return query;
	}

	protected void processLazyFilters(List<String> wheres, Map<String, Object> parameters,
			Map<String, Object> filters) {

		if (filters == null || filters.isEmpty()) {
			return;
		}

		int filterIndex = 0;

		for (Map.Entry<String, Object> entry : filters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (CharSequence.class.isAssignableFrom(resolveType(getClassParam(), key))) {
				if (value != null && value.getClass().isArray()) {
					List<Object> values = CommonUtil.arrayToList(value);
					filterIndex++;
					wheres.add("o." + key + " in (:daoprm_" + filterIndex + ")");
					parameters.put("daoprm" + filterIndex, values);
				} else {
					filterIndex++;
					wheres.add("o." + key + " like :daoprm_" + filterIndex);
					parameters.put("daoprm_" + filterIndex, "%" + value + "%");
				}
			} else if (BigDecimal.class.isAssignableFrom(resolveType(getClassParam(), key))) {
				if (value != null && value.getClass().isArray()) {
					throw new UnsupportedOperationException();
				} else {
					filterIndex++;
					wheres.add("o." + key + " = :daoprm_" + filterIndex);
					Object converted = ConvertUtils.convert(value, BigDecimal.class);
					parameters.put("daoprm_" + filterIndex, converted);
				}
			} else if (resolveType(getClassParam(), key) == int.class
					|| resolveType(getClassParam(), key) == Integer.class) {
				filterIndex++;
				wheres.add("o." + key + " = :daoprm_" + filterIndex);
				parameters.put("daoprm_" + filterIndex, Integer.valueOf((String) value));
			} else if (resolveType(getClassParam(), key) == long.class
					|| resolveType(getClassParam(), key) == Long.class) {
				filterIndex++;
				wheres.add("o." + key + " = :daoprm_" + filterIndex);
				parameters.put("daoprm_" + filterIndex, Long.valueOf((String) value));
			} else if (resolveType(getClassParam(), key) == Date.class) {
				filterIndex++;
				wheres.add("o." + key + " = :daoprm_" + filterIndex);
				parameters.put("daoprm_" + filterIndex, DateUtil.toDate((LocalDate) value));
			} else {
				filterIndex++;
				wheres.add("o." + key + " = :daoprm_" + filterIndex);
				parameters.put("daoprm_" + filterIndex, value);
			}
		}
	}

	protected void processLazyPredicates(List<String> wheres, Map<String, Object> parameters,
			Map<String, Object> predicates) {

		if (predicates == null || predicates.isEmpty()) {
			return;
		}

		if (predicates == null || predicates.isEmpty()) {
			return;
		}

		List<String> keys = predicates.entrySet().stream().map(x -> x.getKey()).filter(x -> !StringUtils.isEmpty(x))
				.collect(Collectors.toList());
		List<String> params = findParams(String.join(" ", keys));
		List<Object> values = predicates.entrySet().stream().map(x -> x.getValue()).filter(x -> x != null)
				.collect(Collectors.toList());

		if (params.size() != values.size()) {
			throw new ValidationException("Predicate parameters and values count does not match");
		}

		wheres.addAll(keys);

		for (int i = 0; i < params.size(); i++) {
			Object value = values.get(i);
			if (value.getClass().isArray()) {
				List<Object> list = CommonUtil.arrayToList(value);
				parameters.put(params.get(i), list);
			} else {
				parameters.put(params.get(i), value);
			}
		}
	}

	protected Class<?> resolveType(Class<?> clazz, String key) {
		key = key.intern();
		Class<?> resolved;
		synchronized (resolvedTypes) {
			resolved = resolvedTypes.get(key);
		}

		if (resolved != null) {
			return resolved;
		}

		String[] hierarchy = key.split("\\.");
		Class<?> tmpClazz = clazz;
		try {
			for (int i = 0; i < hierarchy.length; i++) {
				while (true) {
					try {
						Field field = tmpClazz.getDeclaredField(hierarchy[i]);
						tmpClazz = field.getType();
						break;
					} catch (NoSuchFieldException e) {
						tmpClazz = tmpClazz.getSuperclass();

						if (tmpClazz == null) {
							throw e;
						}
					}
				}
			}
		} catch (Exception e) {
			getLogger().error(e.getMessage(), e);
			return null;
		}

		synchronized (resolvedTypes) {
			resolvedTypes.put(key, tmpClazz);
		}

		return tmpClazz;
	}

	protected List<String> findParams(String key) {
		Pattern pattern = Pattern.compile(":[a-zA-Z0-9]+");
		Matcher matcher = pattern.matcher(key);
		List<String> params = new ArrayList<>();

		while (matcher.find()) {
			params.add(matcher.group().replaceAll(":", ""));
		}

		return params;
	}
}
