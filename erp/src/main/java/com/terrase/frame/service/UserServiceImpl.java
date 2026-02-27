package com.terrase.frame.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.UserDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.User;
import com.terrase.frame.data.structure.ValidationException;
import com.terrase.frame.service.base.EntityServiceImpl;
import com.terrase.util.StringUtil;

@Service("com.terrase.frame.service.UserService")
public class UserServiceImpl extends EntityServiceImpl<User> implements UserService {

	@Autowired
	private UserDAO classDAO;

	@Override
	public EntityDAO<User> getClassDAO() {
		return classDAO;
	}

	@Override
	protected void validateDelete(User object) throws Exception {
		HashMap<String, Object> filters = new LinkedHashMap<>();
		filters.put("user.id", object.getId());
	}

	@Override
	protected void validateFormat(User object) throws Exception {
		if (StringUtil.isEmpty(object.getUsername())) {
			throw new ValidationException("Please enter username");
		}

		if (StringUtil.isEmpty(object.getPassword())) {
			throw new ValidationException("Please enter password");
		}

		if (StringUtil.isEmpty(object.getName())) {
			throw new ValidationException("Please enter name");
		}

		if (object.getUserRoles().size() == 0) {
			throw new ValidationException("Please assign user to at least one user group");
		}
	}

	@Override
	public User findByUsername(String username) {
		Map<String, Object> filters = new LinkedHashMap<>();
		filters.put("username", username);
		return findSingle(filters, null);
	}
}
