package com.terrase.frame.dao.base;

import com.terrase.frame.data.User;
import com.terrase.frame.data.base.Entity;
import com.terrase.frame.util.EntityUtil;

public abstract class EntityDAOImpl<T extends Entity> extends DAOImpl<T> implements EntityDAO<T> {

	@Override
	public void insert(T object, User user) {
		EntityUtil.markInsert(object, user);
		currentSession().save(object);
	}

	@Override
	public void update(T object, User user) {
		EntityUtil.markUpdate(object, user);
		currentSession().saveOrUpdate(object);
	}

	@Override
	public void delete(T object, User user) {
		EntityUtil.markDelete(object, user);
		currentSession().update(object);
	}
}
