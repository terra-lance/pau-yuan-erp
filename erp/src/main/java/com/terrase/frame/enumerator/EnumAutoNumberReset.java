package com.terrase.frame.enumerator;

public enum EnumAutoNumberReset {
	NEVER(0, "Never"), DAILY(1, "Daily"), MONTHLY(2, "Monthly"), YEARLY(3, "Yearly");

	private int value;
	private String description;

	private EnumAutoNumberReset(int value, String description) {
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
