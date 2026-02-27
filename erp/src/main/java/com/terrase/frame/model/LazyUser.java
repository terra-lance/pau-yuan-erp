package com.terrase.frame.model;

import java.util.Map;

import com.terrase.frame.data.User;
import com.terrase.frame.model.base.LazyEntity;
import com.terrase.frame.service.UserService;
import com.terrase.frame.service.base.EntityService;

public class LazyUser extends LazyEntity<User> {
	private static final long serialVersionUID = 1L;

	public LazyUser() {
		super();
	}

	public LazyUser(Map<String, Object> predicates) {
		super(predicates);
	}

	@Override
	public EntityService<User> getService() {
		return getService(UserService.class);
	}
}
