package com.terrase.frame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.UserRoleDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.UserRole;
import com.terrase.frame.service.base.EntityServiceImpl;

@Service("com.terrase.frame.service.UserRoleService")
public class UserRoleServiceImpl extends EntityServiceImpl<UserRole> implements UserRoleService {

	@Autowired
	private UserRoleDAO classDAO;

	@Override
	public EntityDAO<UserRole> getClassDAO() {
		return classDAO;
	}
}
