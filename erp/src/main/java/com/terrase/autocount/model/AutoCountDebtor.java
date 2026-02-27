package com.terrase.autocount.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountDebtor {

	private String key;
	
	private String controlAccount;
	private String accNo;

	private String companyName;

	private String addr1;
	private String addr2;
	private String addr3;
	private String addr4;

	private String phone;
	private String mobile;

	private String attention;
	private String email;

	public AutoCountDebtor() {

	}
}
