package com.terrase.frame.data;

import java.util.LinkedHashSet;
import java.util.Set;

import com.terrase.frame.data.base.MasterEntity;
import com.terrase.frame.enumerator.EnumSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroup extends MasterEntity {
	private static final long serialVersionUID = 1L;

	protected EnumSystem system;
	protected Company company;

	protected Set<UserRole> userRoles;
	protected Set<UserGroupAccess> accesses;

	public UserGroup() {
		super();
		company = new Company();

		userRoles = new LinkedHashSet<>();
		accesses = new LinkedHashSet<>();
	}
}
