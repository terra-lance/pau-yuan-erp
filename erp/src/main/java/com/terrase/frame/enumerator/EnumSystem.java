package com.terrase.frame.enumerator;

public enum EnumSystem {
	GENERAL(0, "General"), DISTRIBUTION(1, "Sales & Distribution"), WAREHOUSE(2, "Storage Service");

	private int value;
	private String description;

	private EnumSystem(int value, String description) {
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
