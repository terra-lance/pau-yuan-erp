package com.terrase.frame.data;

import com.terrase.frame.data.base.MasterEntity;
import com.terrase.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile extends MasterEntity {
	private static final long serialVersionUID = 1L;

	protected String registrationNo;
	protected String taxNo;

	protected String address1;
	protected String address2;
	protected String address3;
	protected String address4;

	protected String operationDescription;

	protected String phone;
	protected String fax;

	public Profile() {
		super();
	}

	public String getCompanyInfo() {
		String summary = address1;

		if (!StringUtil.isEmpty(address2)) {
			summary += "," + address2;
		}

		if (!StringUtil.isEmpty(address3)) {
			summary += "," + address3;
		}

		if (!StringUtil.isEmpty(address4)) {
			summary += "," + address4;
		}

		if (!StringUtil.isEmpty(phone)) {
			summary += StringUtil.NEWLINE + phone;
		}

		if (!StringUtil.isEmpty(operationDescription)) {
			summary += StringUtil.NEWLINE + operationDescription;
		}

		return summary;
	}
	
	public String getCompanyAddressWithTel() {
		String summary = address1;

		if (!StringUtil.isEmpty(address2)) {
			summary += "," + address2;
		}

		if (!StringUtil.isEmpty(address3)) {
			summary += "," + address3;
		}

		if (!StringUtil.isEmpty(address4)) {
			summary += "," + address4;
		}

		if (!StringUtil.isEmpty(phone)) {
			summary += StringUtil.NEWLINE + phone;
		}

		return summary;
	}
}
