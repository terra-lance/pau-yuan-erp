package com.terrase.autocount.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountPayableCreditNoteKnockOff {
	public static final String TYPE_AP_INVOICE = "APInvoice";
	
	private String knockOffDocumentType;
	private String knockOffDocument;
	private BigDecimal knockOffAmount;

	public AutoCountPayableCreditNoteKnockOff() {
		knockOffAmount = BigDecimal.ZERO;
	}
}
