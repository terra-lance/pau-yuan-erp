package com.terrase.frame.model;

import java.util.Map;

import com.terrase.frame.data.AutoNumber;
import com.terrase.frame.model.base.LazyEntity;
import com.terrase.frame.service.AutoNumberService;
import com.terrase.frame.service.base.EntityService;

public class LazyAutoNumber extends LazyEntity<AutoNumber> {
	private static final long serialVersionUID = 1L;

	public LazyAutoNumber() {
		super();
	}

	public LazyAutoNumber(Map<String, Object> predicates) {
		super(predicates);
	}

	@Override
	public EntityService<AutoNumber> getService() {
		return getService(AutoNumberService.class);
	}
}
