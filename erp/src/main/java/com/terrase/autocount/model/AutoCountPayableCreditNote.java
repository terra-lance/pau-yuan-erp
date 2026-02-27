package com.terrase.autocount.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountPayableCreditNote {
	public static final String CN_TYPE_DISCOUNT = "DISCOUNT";
	public static final String CN_TYPE_RETURN = "RETURN";
	
	private String key;
	
	private String creditorCode;

	private String docNo;
	private String docDate;
	private String description;
	private String supplierCNNo;
	private String supplierInvoiceNo;
	private String journalType;
	private String cNType;

	private List<AutoCountPayableCreditNoteDetail> details;
	private List<AutoCountPayableCreditNoteKnockOff> knockOffs;

	public AutoCountPayableCreditNote() {
		details = new ArrayList<>();
		knockOffs = new ArrayList<>();
	}
}