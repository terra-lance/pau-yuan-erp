package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.UserGroupAccess;

@Repository("com.terrase.frame.dao.UserGroupAccessDAO")
public class UserGroupAccessDAOImpl extends EntityDAOImpl<UserGroupAccess> implements UserGroupAccessDAO {

	@Override
	public Class<UserGroupAccess> getClassParam() {
		return UserGroupAccess.class;
	}
}
