package com.terrase.frame.model;

import java.util.Map;

import com.terrase.frame.data.Company;
import com.terrase.frame.model.base.LazyEntity;
import com.terrase.frame.service.CompanyService;
import com.terrase.frame.service.base.EntityService;

public class LazyCompany extends LazyEntity<Company> {
	private static final long serialVersionUID = 1L;

	public LazyCompany() {
		super();
	}

	public LazyCompany(Map<String, Object> predicates) {
		super(predicates);
	}

	@Override
	public EntityService<Company> getService() {
		return getService(CompanyService.class);
	}
}
