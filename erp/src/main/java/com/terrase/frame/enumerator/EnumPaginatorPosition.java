package com.terrase.frame.enumerator;

public enum EnumPaginatorPosition {
	BOTH(0, "both"), TOP(1, "top"), BOTTOM(2, "bottom");

	private int value;
	private String description;

	private EnumPaginatorPosition(int value, String description) {
		this.setValue(value);
		this.setDescription(description);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
