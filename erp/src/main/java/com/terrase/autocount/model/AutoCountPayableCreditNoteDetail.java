package com.terrase.autocount.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountPayableCreditNoteDetail {

	private String accNo;
	private String description;
	private BigDecimal amount;

	public AutoCountPayableCreditNoteDetail() {
		amount = BigDecimal.ZERO;
	}
}
