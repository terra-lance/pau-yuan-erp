package com.terrase.autocount.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountReceivablePaymentDetail {

	private String chequeNo;
	private String paymentMethod;
	private BigDecimal paymentAmount;

	public AutoCountReceivablePaymentDetail() {
		paymentAmount = BigDecimal.ZERO;
	}
}
