package com.terrase.autocount.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountPurchaseInvoice {
	public static final String JOURNAL_TYPE_PURCHASE = "PURCHASE";
	
	private String key;
	
	private String creditorCode;

	private String docNo;
	private String docDate;
	
	private String supplierInvoiceNo;
	
	private String journalType;

	private List<AutoCountPurchaseInvoiceDetail> details;

	public AutoCountPurchaseInvoice() {
		details = new ArrayList<>();
	}
}
