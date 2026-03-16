package com.terrase.frame.data;

import com.terrase.frame.data.base.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroupBranchAccess extends Entity {
	private static final long serialVersionUID = 1L;

	protected UserGroupBranch userGroupBranch;
	protected Module module;
	
	protected boolean viewRights;
	protected boolean addRights;
	protected boolean updateRights;
	protected boolean deleteRights;
	protected boolean printRights;
	protected boolean postRights;

	public UserGroupBranchAccess() {
		super();
		userGroupBranch = new UserGroupBranch();
		module = new Module();
	}
}
