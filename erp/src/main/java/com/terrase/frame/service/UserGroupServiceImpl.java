package com.terrase.frame.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.UserGroupDAO;
import com.terrase.frame.dao.UserRoleDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.UserGroup;
import com.terrase.frame.data.structure.ValidationException;
import com.terrase.frame.service.base.EntityServiceImpl;
import com.terrase.util.StringUtil;

@Service("com.terrase.frame.service.UserGroupService")
public class UserGroupServiceImpl extends EntityServiceImpl<UserGroup> implements UserGroupService {

	@Autowired
	private UserGroupDAO classDAO;
	@Autowired
	private UserRoleDAO userRoleDAO;

	@Override
	public EntityDAO<UserGroup> getClassDAO() {
		return classDAO;
	}

	@Override
	protected void validateDelete(UserGroup object) throws Exception {
		HashMap<String, Object> filters = new LinkedHashMap<>();
		filters.put("userGroup.id", object.getId());
		if (userRoleDAO.exists(filters)) {
			throw new ValidationException("User group is in use and cannot be deleted");
		}
	}

	@Override
	protected void validateFormat(UserGroup object) throws Exception {
		if (StringUtil.isEmpty(object.getCode())) {
			throw new ValidationException("Please enter code");
		}

		if (StringUtil.isEmpty(object.getName())) {
			throw new ValidationException("Please enter name");
		}
	}

	@Override
	public List<UserGroup> findSelected(long userId) {
		return classDAO.findSelected(userId);
	}
}
