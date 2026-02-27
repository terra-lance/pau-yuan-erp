package com.terrase.frame.service.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;

public abstract class ServiceImpl<T> implements Service<T> {

	@Override
	public List<T> find() {
		return getClassDAO().find();
	}

	@Override
	public List<T> findList(Map<String, Object> filters, String ordering) {
		return getClassDAO().findList(filters, ordering);
	}

	@Override
	public List<T> findList(Map<String, Object> filters, Map<String, Object> predicates, String ordering) {
		return getClassDAO().findList(filters, predicates, ordering);
	}

	@Override
	public T find(long id) {
		return getClassDAO().find(id);
	}

	@Override
	public T findSingle(Map<String, Object> filters, String ordering) {
		return getClassDAO().findSingle(filters, ordering);
	}

	@Override
	public T findSingle(Map<String, Object> filters, Map<String, Object> predicates, String ordering) {
		return getClassDAO().findSingle(filters, predicates, ordering);
	}

	@Override
	public T findFirst() {
		return getClassDAO().findFirst();
	}

	@Override
	public long count(Map<String, Object> filters) {
		return getClassDAO().count(filters);
	}

	@Override
	public long count(Map<String, Object> filters, Map<String, Object> predicates) {
		return getClassDAO().count(filters, predicates);
	}

	@Override
	public long identify(Map<String, Object> filters) {
		return getClassDAO().identify(filters);
	}
	
	@Override
	public boolean exists(Map<String, Object> filters) {
		return getClassDAO().exists(filters);
	}

	@Override
	public boolean exists(Map<String, Object> filters, Map<String, Object> predicates) {
		return getClassDAO().exists(filters, predicates);
	}

	@Override
	public List<T> findLazy(int first, int pageSize, Map<String, SortMeta> sortings, Map<String, Object> filters) {
		return getClassDAO().findLazy(first, pageSize, sortings, filters);
	}

	@Override
	public List<T> findLazy(int first, int pageSize, Map<String, SortMeta> sortings, Map<String, Object> filters,
			Map<String, Object> predicates) {
		return getClassDAO().findLazy(first, pageSize, sortings, filters, predicates);
	}

	@Override
	public long countLazy(Map<String, Object> filters) {
		return getClassDAO().countLazy(filters);
	}

	@Override
	public long countLazy(Map<String, Object> filters, Map<String, Object> predicates) {
		return getClassDAO().countLazy(filters, predicates);
	}
}
