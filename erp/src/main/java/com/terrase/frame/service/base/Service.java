package com.terrase.frame.service.base;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;

import com.terrase.frame.dao.base.DAO;

public interface Service<T> {

	public DAO<T> getClassDAO();

	public List<T> find();

	public List<T> findList(Map<String, Object> filters, String ordering);

	public List<T> findList(Map<String, Object> filters, Map<String, Object> predicates, String ordering);

	public T find(long id);

	public T findSingle(Map<String, Object> filters, String ordering);

	public T findSingle(Map<String, Object> filters, Map<String, Object> predicates, String ordering);

	public T findFirst();

	public long count(Map<String, Object> filters);

	public long count(Map<String, Object> filters, Map<String, Object> predicates);

	public long identify(Map<String, Object> filters);

	public boolean exists(Map<String, Object> filters);

	public boolean exists(Map<String, Object> filters, Map<String, Object> predicates);

	public List<T> findLazy(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, Object> filters);

	public List<T> findLazy(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, Object> filters,
			Map<String, Object> predicates);

	public long countLazy(Map<String, Object> filters);

	public long countLazy(Map<String, Object> filters, Map<String, Object> predicates);
}
