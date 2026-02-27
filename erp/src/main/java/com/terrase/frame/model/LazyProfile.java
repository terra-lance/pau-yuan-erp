package com.terrase.frame.model;

import java.util.Map;

import com.terrase.frame.data.Profile;
import com.terrase.frame.model.base.LazyEntity;
import com.terrase.frame.service.ProfileService;
import com.terrase.frame.service.base.EntityService;

public class LazyProfile extends LazyEntity<Profile> {
	private static final long serialVersionUID = 1L;

	public LazyProfile() {
		super();
	}

	public LazyProfile(Map<String, Object> predicates) {
		super(predicates);
	}

	@Override
	public EntityService<Profile> getService() {
		return getService(ProfileService.class);
	}
}
