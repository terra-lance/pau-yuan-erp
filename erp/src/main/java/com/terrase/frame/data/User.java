package com.terrase.frame.data;

import java.util.LinkedHashSet;
import java.util.Set;

import com.terrase.frame.data.base.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Entity {
	private static final long serialVersionUID = 1L;

	protected String username;
	protected String password;

	protected String name;
	protected String email;

	protected boolean active;

	protected Set<UserRole> userRoles;

	public User() {
		super();
		active = true;

		userRoles = new LinkedHashSet<>();
	}
}
