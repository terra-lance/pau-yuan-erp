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
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String phone;
	private String fax;

	public Company() {
		super();
	}

	public String getContactInfo() {
		String address = StringUtil.EMPTY;
		List<String> strings = new ArrayList<String>();

		if (!StringUtil.isEmpty(address1)) {
			strings.add(address1);
		}

		if (!StringUtil.isEmpty(address2)) {
			strings.add(address2);
		}

		if (!StringUtil.isEmpty(address3)) {
			strings.add(address3);
		}

		if (!StringUtil.isEmpty(address4)) {
			strings.add(address4);
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
