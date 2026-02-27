package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.UserRole;

@Repository("com.terrase.frame.dao.UserRoleDAO")
public class UserRoleDAOImpl extends EntityDAOImpl<UserRole> implements UserRoleDAO {

	@Override
	public Class<UserRole> getClassParam() {
		return UserRole.class;
	}
}
