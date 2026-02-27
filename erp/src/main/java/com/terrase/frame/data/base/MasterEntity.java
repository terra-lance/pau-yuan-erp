package com.terrase.frame.data.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MasterEntity extends Entity {
	private static final long serialVersionUID = 1L;

	protected String code;
	protected String name;
	protected String description;
	protected boolean active;

	public MasterEntity() {
		super();
		active = true;
	}
}
