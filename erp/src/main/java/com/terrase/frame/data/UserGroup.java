package com.terrase.frame.data;

import java.util.LinkedHashSet;
import java.util.Set;

import com.terrase.frame.data.base.MasterEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroup extends MasterEntity {
	private static final long serialVersionUID = 1L;

	protected Set<UserGroupAccess> userGroupAccesses;
	protected Set<UserRole> userRoles;

	public UserGroup() {
		super();
		userGroupAccesses = new LinkedHashSet<>();
		userRoles = new LinkedHashSet<>();
	}
}
