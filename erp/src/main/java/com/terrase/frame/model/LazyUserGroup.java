package com.terrase.frame.model;

import java.util.Map;

import com.terrase.frame.data.UserGroup;
import com.terrase.frame.model.base.LazyEntity;
import com.terrase.frame.service.UserGroupService;
import com.terrase.frame.service.base.EntityService;

public class LazyUserGroup extends LazyEntity<UserGroup> {
	private static final long serialVersionUID = 1L;

	public LazyUserGroup() {
		super();
	}

	public LazyUserGroup(Map<String, Object> predicates) {
		super(predicates);
	}

	@Override
	public EntityService<UserGroup> getService() {
		return getService(UserGroupService.class);
	}
}
