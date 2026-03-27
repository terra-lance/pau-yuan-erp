package com.terrase.frame.enumerator;

public enum EnumSystem {
	COMMON(0, "General"), ERP(1, "Resources Planning"), WMS(2, "Warehouse Management");

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
