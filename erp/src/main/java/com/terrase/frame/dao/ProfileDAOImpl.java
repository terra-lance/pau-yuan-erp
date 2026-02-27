package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.Profile;

@Repository("com.terrase.frame.dao.ProfileDAO")
public class ProfileDAOImpl extends EntityDAOImpl<Profile> implements ProfileDAO {

	@Override
	public Class<Profile> getClassParam() {
		return Profile.class;
	}
}
