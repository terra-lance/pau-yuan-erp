package com.terrase.frame.util;

import java.util.Date;

import com.terrase.frame.data.User;
import com.terrase.frame.data.base.Entity;

public class EntityUtil {
	public static boolean isEmpty(Entity entity) {
		if (entity == null) {
			return true;
		} else if (entity.getId() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void markInsert(Entity entity, User user) {
		entity.setDateCreated(new Date());
		entity.setUserCreated(user);
	}

	public static void markUpdate(Entity entity, User user) {
		entity.setDateModified(new Date());
		entity.setUserModified(user);
	}

	public static void markDelete(Entity entity, User user) {
		entity.setDeleted(true);
		entity.setDateDeleted(new Date());
		entity.setUserDeleted(user);
	}
}
