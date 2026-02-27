package com.terrase.frame.data.base;

import com.terrase.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address extends Entity {
	private static final long serialVersionUID = 1L;

	protected String line1;
	protected String line2;

	protected String postcode;
	protected String city;
	protected String state;
	protected String country;

	public Address() {
		super();
	}

	public String getFullLine() {
		String fullLine = (StringUtil.isNotEmpty(line1) ? line1 : "")
				+ (StringUtil.isNotEmpty(line2) ? StringUtil.NEWLINE + line2 : "");
		return fullLine;
	}
}
