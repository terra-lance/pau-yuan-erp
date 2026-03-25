package com.terrase.frame.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.terrase.frame.dao.base.EntityDAOImpl;
import com.terrase.frame.data.User;
import com.terrase.frame.data.UserGroup;
import com.terrase.frame.data.UserGroupAccess;
import com.terrase.frame.util.EntityUtil;

@Repository("com.terrase.frame.dao.UserGroupDAO")
public class UserGroupDAOImpl extends EntityDAOImpl<UserGroup> implements UserGroupDAO {

	@Override
	public Class<UserGroup> getClassParam() {
		return UserGroup.class;
	}

	@Override
	public void delete(UserGroup object, User user) {
		EntityUtil.markDelete(object, user);

		for (UserGroupAccess access : object.getAccesses()) {
			EntityUtil.markDelete(access, user);
		}

		currentSession().update(object);
	}

	@Override
	public List<UserGroup> findSelected(long userId) {
		String query = "select o.userGroup from com.terrase.frame.data.UserRole o " + "where o.user.id = :param";

		Query<UserGroup> command = currentSession().createQuery(query, getClassParam());
		command.setParameter("param", userId);
		return command.list();
	}
}
