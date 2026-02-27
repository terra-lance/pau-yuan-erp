package com.terrase.frame.data;

import com.terrase.frame.data.base.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRole extends Entity {
	private static final long serialVersionUID = 1L;

	protected User user;
	protected UserGroup userGroup;

	public UserRole() {
		super();
		user = new User();
		userGroup = new UserGroup();
	}
}
