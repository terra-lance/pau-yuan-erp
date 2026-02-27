package com.terrase.frame.model.base;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import com.terrase.frame.data.base.Entity;
import com.terrase.frame.service.base.EntityService;

public abstract class LazyEntity<T extends Entity> extends LazyModel<T> {
	private static final long serialVersionUID = 1L;
	protected Map<String, Object> predicates;
	protected List<T> objects;

	public LazyEntity() {
	}

	public LazyEntity(Map<String, Object> predicates) {
		this.predicates = predicates;
	}

	public abstract EntityService<T> getService();

	@Override
	public List<T> load(int first, int pageSize, Map<String, SortMeta> sortings, Map<String, FilterMeta> filterMetas) {
		long rowCount = 0;

		HashMap<String, Object> filters = new LinkedHashMap<>();
		for (Map.Entry<String, FilterMeta> entry : filterMetas.entrySet()) {
			filters.put(entry.getValue().getField(), entry.getValue().getFilterValue());
		}

		if (predicates != null && predicates.size() > 0) {
			objects = getService().findLazy(first, pageSize, sortings, filters, predicates);
			rowCount = getService().countLazy(filters, predicates);
		} else {
			objects = getService().findLazy(first, pageSize, sortings, filters);
			rowCount = getService().countLazy(filters);
		}

		this.setRowCount((int) rowCount);
		return objects;
	}

	@Override
	public String getRowKey(T type) {
		return type.getId() + "";
	}

	@Override
	public T getRowData(String key) {
		Long id = Long.parseLong(key);
		return objects.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
	
	@Override
	public int count(Map<String, FilterMeta> filterMetas) {
		HashMap<String, Object> filters = new LinkedHashMap<>();
		for (Map.Entry<String, FilterMeta> entry : filterMetas.entrySet()) {
			filters.put(entry.getValue().getField(), entry.getValue().getFilterValue());
		}
		
		if (predicates != null && predicates.size() > 0) {
			return (int)getService().countLazy(filters, predicates);
		} else {
			return (int)getService().countLazy(filters);
		}
	}
}
