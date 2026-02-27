package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.UserPreference;

@Repository("com.terrase.frame.dao.UserPreferenceDAO")
public class UserPreferenceDAOImpl extends EntityDAOImpl<UserPreference> implements UserPreferenceDAO {

	@Override
	public Class<UserPreference> getClassParam() {
		return UserPreference.class;
	}
}
