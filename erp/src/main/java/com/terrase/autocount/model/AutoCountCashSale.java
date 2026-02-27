package com.terrase.autocount.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountCashSale {
	public static final String JOURNAL_TYPE_SALES = "SALES";
	
	private String key;
	
	private String debtorCode;
	private String debtorName;
	
	private String docNo;
	private String docDate;
	
	private String invAddr1;
	private String invAddr2;
	private String invAddr3;
	private String invAddr4;
	
	private boolean consolidatedEInvoice;
	
	private List<AutoCountCashSaleDetail> details;

	public AutoCountCashSale() {
		details = new ArrayList<>();
	}
}
