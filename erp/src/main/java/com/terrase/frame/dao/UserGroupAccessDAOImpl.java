package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.UserGroupBranchAccess;

@Repository("com.terrase.frame.dao.UserGroupAccessDAO")
public class UserGroupAccessDAOImpl extends EntityDAOImpl<UserGroupBranchAccess> implements UserGroupAccessDAO {

	@Override
	public Class<UserGroupBranchAccess> getClassParam() {
		return UserGroupBranchAccess.class;
	}
}
