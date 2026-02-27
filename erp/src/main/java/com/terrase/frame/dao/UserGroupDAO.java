package com.terrase.frame.dao;

import java.util.List;

import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.UserGroup;

public interface UserGroupDAO extends EntityDAO<UserGroup> {

	public List<UserGroup> findSelected(long userId);
}