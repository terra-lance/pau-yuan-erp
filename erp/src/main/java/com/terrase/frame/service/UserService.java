package com.terrase.frame.service;

import com.terrase.frame.data.User;
import com.terrase.frame.service.base.EntityService;

public interface UserService extends EntityService<User> {
	public User findByUsername(String username);
}