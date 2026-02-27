package com.terrase.autocount.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountPurchaseOrder {
	
	private String key;
	
	private String creditorCode;
	private String creditorName;
	
	private String docNo;
	private String docDate;
	
	private String invAddr1;
	private String invAddr2;
	private String invAddr3;
	private String invAddr4;
	
	private List<AutoCountPurchaseOrderDetail> details;

	public AutoCountPurchaseOrder() {
		details = new ArrayList<>();
	}
}
