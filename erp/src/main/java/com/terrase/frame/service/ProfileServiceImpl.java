package com.terrase.frame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.ProfileDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.Profile;
import com.terrase.frame.data.User;
import com.terrase.frame.data.structure.ValidationException;
import com.terrase.frame.service.base.EntityServiceImpl;
import com.terrase.util.StringUtil;

@Service("com.terrase.frame.service.ProfileService")
public class ProfileServiceImpl extends EntityServiceImpl<Profile> implements ProfileService {

	@Autowired
	private ProfileDAO classDAO;

	@Override
	public EntityDAO<Profile> getClassDAO() {
		return classDAO;
	}

	@Override
	public void insert(Profile object, User user) throws Exception {
		validateInsert(object);

		classDAO.insert(object, user);
	}

	@Override
	protected void validateDelete(Profile object) throws Exception {

	}

	@Override
	protected void validateFormat(Profile object) throws Exception {
		if (StringUtil.isEmpty(object.getCode())) {
			throw new ValidationException("Please enter code");
		}

		if (StringUtil.isEmpty(object.getName())) {
			throw new ValidationException("Please enter name");
		}
	}
}
