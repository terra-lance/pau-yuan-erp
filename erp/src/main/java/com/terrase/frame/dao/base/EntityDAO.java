package com.terrase.frame.dao.base;

import com.terrase.frame.data.User;
import com.terrase.frame.data.base.Entity;

public interface EntityDAO<T extends Entity> extends DAO<T> {

	public void insert(T object, User user);

	public void update(T object, User user);

	public void delete(T object, User user);
}
