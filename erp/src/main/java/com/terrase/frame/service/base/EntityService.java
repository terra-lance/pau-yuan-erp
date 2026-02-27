package com.terrase.frame.service.base;

import com.terrase.frame.dao.base.EntityDAO;
import com.terrase.frame.data.User;
import com.terrase.frame.data.base.Entity;

public interface EntityService<T extends Entity> extends Service<T> {

	public EntityDAO<T> getClassDAO();

	public void insert(T object, User user) throws Exception;

	public void update(T object, User user) throws Exception;

	public void delete(T object, User user) throws Exception;
}
