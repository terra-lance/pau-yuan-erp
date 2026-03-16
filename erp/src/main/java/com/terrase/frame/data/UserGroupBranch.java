package com.terrase.frame.data;

import java.util.LinkedHashSet;
import java.util.Set;

import com.terrase.frame.data.base.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroupBranch extends Entity {
	private static final long serialVersionUID = 1L;

	protected UserGroup userGroup;
	protected Branch branch;

	protected Set<UserGroupBranchAccess> accesses;

	public UserGroupBranch() {
		super();
		userGroup = new UserGroup();
		branch = new Branch();
		
		accesses = new LinkedHashSet<>();
	}
}
