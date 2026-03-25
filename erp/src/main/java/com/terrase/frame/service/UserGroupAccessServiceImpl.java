package com.terrase.frame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.UserGroupAccessDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.UserGroupAccess;
import com.terrase.frame.service.base.EntityServiceImpl;

@Service("com.terrase.frame.service.UserGroupAccessService")
public class UserGroupAccessServiceImpl extends EntityServiceImpl<UserGroupAccess> implements UserGroupAccessService {

	@Autowired
	private UserGroupAccessDAO classDAO;

	@Override
	public EntityDAO<UserGroupAccess> getClassDAO() {
		return classDAO;
	}
}
