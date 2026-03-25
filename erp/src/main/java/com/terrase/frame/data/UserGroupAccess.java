package com.terrase.frame.data;

import com.terrase.frame.data.base.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGroupAccess extends Entity {
	private static final long serialVersionUID = 1L;

	protected UserGroup userGroup;
	protected Module module;

	protected boolean viewRights;
	protected boolean addRights;
	protected boolean updateRights;
	protected boolean deleteRights;
	protected boolean printRights;
	protected boolean postRights;

	public UserGroupAccess() {
		super();
		userGroup = new UserGroup();
		module = new Module();
	}
}
