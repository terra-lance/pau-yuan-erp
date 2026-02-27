package com.terrase.frame.data.base;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.SerializationUtils;

import com.terrase.frame.data.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	protected long id;
	protected int version;

	protected User userCreated;
	protected Date dateCreated;

	protected User userModified;
	protected Date dateModified;

	protected boolean deleted;
	protected User userDeleted;
	protected Date dateDeleted;

	// Non-database Field
	protected boolean selected;

	protected boolean autoNumber;

	public Entity() {

	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj.getClass() != this.getClass()) {
			return false;
		}

		if (!(obj instanceof Entity)) {
			return false;
		}

		if (this.getId() == 0 && ((Entity) obj).getId() == 0) {
			return super.equals(obj);
		}

		return this.getId() == ((Entity) obj).getId();
	}

	@Override
	public Entity clone() {
		try {
			return (Entity) super.clone();
		} catch (Exception e) {
			return (Entity) SerializationUtils.clone(this);
		}
	}

	public Entity copy() {
		return (Entity) SerializationUtils.clone(this);
	}
}
