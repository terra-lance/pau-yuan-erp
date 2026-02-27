package com.terrase.frame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terrase.frame.dao.UserPreferenceDAO;
import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.UserPreference;
import com.terrase.frame.service.base.EntityServiceImpl;

@Service("com.terrase.frame.service.UserPreferenceService")
public class UserPreferenceServiceImpl extends EntityServiceImpl<UserPreference> implements UserPreferenceService {

	@Autowired
	private UserPreferenceDAO classDAO;

	@Override
	public EntityDAO<UserPreference> getClassDAO() {
		return classDAO;
	}
}
