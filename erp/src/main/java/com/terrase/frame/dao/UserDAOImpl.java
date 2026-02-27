package com.terrase.frame.dao;

import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.User;

@Repository("com.terrase.frame.dao.UserDAO")
public class UserDAOImpl extends EntityDAOImpl<User> implements UserDAO {

	@Override
	public Class<User> getClassParam() {
		return User.class;
	}
}
