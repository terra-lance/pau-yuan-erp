package com.terrase.frame.service.base;

import com.terrase.frame.data.User;
import com.terrase.frame.data.base.Entity;

public abstract class EntityServiceImpl<T extends Entity> extends ServiceImpl<T> implements EntityService<T> {

	@Override
	public void insert(T object, User user) throws Exception {
		validateInsert(object);
		getClassDAO().insert(object, user);
	}

	protected void validateInsert(T object) throws Exception {
		validateFormat(object);
	}

	@Override
	public void update(T object, User user) throws Exception {
		validateUpdate(object);
		getClassDAO().update(object, user);
	}

	protected void validateUpdate(T object) throws Exception {
		validateFormat(object);
	}

	@Override
	public void delete(T object, User user) throws Exception {
		validateDelete(object);
		getClassDAO().delete(object, user);
	}

	protected void validateDelete(T object) throws Exception {

	}

	protected void validateFormat(T object) throws Exception {

	}
}
