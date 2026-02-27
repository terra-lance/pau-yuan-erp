package com.terrase.autocount.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountReceivablePaymentKnockOff {
	public static final String TYPE_AR_INVOICE = "ARInvoice";
	public static final String TYPE_CASH_SALE = "CashSale";
	
	private String knockOffDocumentType;
	private String knockOffDocument;
	private BigDecimal knockOffAmount;

	public AutoCountReceivablePaymentKnockOff() {
		knockOffAmount = BigDecimal.ZERO;
	}
}
