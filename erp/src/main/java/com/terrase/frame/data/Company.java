package com.terrase.frame.data;

import java.util.ArrayList;
import java.util.List;

import com.terrase.frame.data.base.MasterEntity;
import com.terrase.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company extends MasterEntity {
	private static final long serialVersionUID = 1L;

	// Company info
	private String companyName;
	private String registrationNo;
	private String sstRegistrationNo;
	private String taxIdentificationNo;

	// Contact Info
	private String line1;
	private String line2;
	private String line3;
	private String line4;
	private String phone;
	private String fax;

	public Company() {
		super();
	}

	public String getContactInfo() {
		String address = StringUtil.EMPTY;
		List<String> strings = new ArrayList<String>();

		if (!StringUtil.isEmpty(line1)) {
			strings.add(line1);
		}

		if (!StringUtil.isEmpty(line2)) {
			strings.add(line2);
		}

		if (!StringUtil.isEmpty(line3)) {
			strings.add(line3);
		}

		if (!StringUtil.isEmpty(line4)) {
			strings.add(line4);
		}

		String contact = StringUtil.EMPTY;
		if (!StringUtil.isEmpty(phone)) {
			contact += ("Phone: " + phone + "  ");
		}

		if (!StringUtil.isEmpty(fax)) {
			contact += ("Fax: " + fax + "  ");
		}

		if (!StringUtil.isEmpty(taxIdentificationNo)) {
			contact += ("TIN: " + taxIdentificationNo);
		}

		if (!StringUtil.isEmpty(contact)) {
			strings.add(contact);
		}

		address = String.join(StringUtil.NEWLINE, strings);

		return address;
	}
}
