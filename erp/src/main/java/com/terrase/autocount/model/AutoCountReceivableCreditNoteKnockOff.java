package com.terrase.autocount.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountReceivableCreditNoteKnockOff {
	private String knockOffDocumentType;
	private String knockOffDocument;
	private BigDecimal knockOffAmount;

	public AutoCountReceivableCreditNoteKnockOff() {
		knockOffAmount = BigDecimal.ZERO;
	}
}
