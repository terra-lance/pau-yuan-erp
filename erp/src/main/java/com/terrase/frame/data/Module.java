package com.terrase.frame.data;

import com.terrase.frame.data.base.MasterEntity;
import com.terrase.frame.enumerator.EnumSystem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Module extends MasterEntity {
	private static final long serialVersionUID = 1L;

	protected EnumSystem system;
	
	public Module() {
		super();
	}
}
