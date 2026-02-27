package com.terrase.frame.service;

import java.util.List;

import com.terrase.frame.data.UserGroup;
import com.terrase.frame.service.base.EntityService;

public interface UserGroupService extends EntityService<UserGroup> {

	public List<UserGroup> findSelected(long userId);
}