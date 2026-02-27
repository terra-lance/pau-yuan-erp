package com.terrase.frame.dao.base;

import java.util.List;

import org.hibernate.query.Query;

import com.terrase.frame.data.User;
import com.terrase.frame.data.base.MasterEntity;
import com.terrase.frame.util.EntityUtil;
import com.terrase.util.StringUtil;

public abstract class MasterEntityDAOImpl<T extends MasterEntity> extends EntityDAOImpl<T>
		implements MasterEntityDAO<T> {

	@Override
	public void insert(T object, User user) {
		if (StringUtil.isEmpty(object.getName())) {
			object.setName(object.getCode());
		}

		EntityUtil.markInsert(object, user);
		currentSession().save(object);
	}

	@Override
	public List<T> findByActive(boolean active) {
		String hql = "select o from " + getClassParam().getName()
				+ " o where o.active = :param and o.deleted = :param1";
		Query<T> query = currentSession().createQuery(hql, getClassParam());
		query.setParameter("param", active);
		query.setParameter("param1", false);

		return query.list();
	}
}
