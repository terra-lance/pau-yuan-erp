package com.terrase.autocount.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountPurchaseInvoiceDetail {

	private String accNo;
	private String description;
	private String taxCode;
	private BigDecimal amount;
	
	public AutoCountPurchaseInvoiceDetail() {
		amount = BigDecimal.ZERO;
	}
}
