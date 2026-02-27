package com.terrase.frame.model.base;

import org.primefaces.model.LazyDataModel;

import com.terrase.spring.util.SpringUtil;

public abstract class LazyModel<T> extends LazyDataModel<T> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public <S> S getService(Class<S> classParam) {
		return (S) SpringUtil.lookup(classParam.getName());
	}
}
