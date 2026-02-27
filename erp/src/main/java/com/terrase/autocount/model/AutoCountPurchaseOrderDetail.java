package com.terrase.autocount.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoCountPurchaseOrderDetail {

	private String itemCode;
	private String description;
	private BigDecimal qty;
	private String uom;
	private BigDecimal unitPrice;
	private String discount;
	private String taxCode;
	private BigDecimal taxRate;
	private BigDecimal taxableAmt;
	private BigDecimal subTotal;
	
	public AutoCountPurchaseOrderDetail() {
		qty = BigDecimal.ZERO;
		unitPrice = BigDecimal.ZERO;
		
		taxRate = BigDecimal.ZERO;
		taxableAmt = BigDecimal.ZERO;
		subTotal = BigDecimal.ZERO;
	}
}
