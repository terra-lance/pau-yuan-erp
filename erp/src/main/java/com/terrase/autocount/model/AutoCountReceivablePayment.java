package com.terrase.autocount.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountReceivablePayment {

	private String key;
	
	private String debtorCode;

	private String docNo;
	private String docDate;
	
	private String description;

	private List<AutoCountReceivablePaymentDetail> details;
	private List<AutoCountReceivablePaymentKnockOff> knockOffs;

	public AutoCountReceivablePayment() {
		details = new ArrayList<>();
		knockOffs = new ArrayList<>();
	}
}