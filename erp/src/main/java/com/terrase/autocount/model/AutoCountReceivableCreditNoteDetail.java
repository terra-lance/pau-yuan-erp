package com.terrase.autocount.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountReceivableCreditNoteDetail {

	private String accNo;
	private String description;
	private BigDecimal amount;

	public AutoCountReceivableCreditNoteDetail() {
		amount = BigDecimal.ZERO;
	}
}
