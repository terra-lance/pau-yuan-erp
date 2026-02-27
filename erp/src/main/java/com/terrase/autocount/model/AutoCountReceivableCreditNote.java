package com.terrase.autocount.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountReceivableCreditNote {
	public static final String CN_TYPE_DISCOUNT = "DISCOUNT";
	public static final String CN_TYPE_RETURN = "RETURN";

	private String key;
	
	private String debtorCode;

	private String docNo;
	private String docDate;
	private String description;
	private String reason;
	private String ourInvoiceNo;
	private String journalType;
	private String cNType;

	private List<AutoCountReceivableCreditNoteDetail> details;
	private List<AutoCountReceivableCreditNoteKnockOff> knockOffs;

	public AutoCountReceivableCreditNote() {
		details = new ArrayList<>();
		knockOffs = new ArrayList<>();
	}
}