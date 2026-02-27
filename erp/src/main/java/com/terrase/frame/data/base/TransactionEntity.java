package com.terrase.frame.data.base;

import java.util.Date;

import com.terrase.util.DateUtil;
import com.terrase.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class TransactionEntity extends Entity {
	private static final long serialVersionUID = 1L;

	protected String documentNo;
	protected Date documentDate;

	public TransactionEntity() {
		documentDate = DateUtil.today();
		autoNumber = true;
		documentNo = "<<Auto Number>>";
	}

	public void toggleAutoNumber() {
		if (isAutoNumber()) {
			documentNo = "<<Auto Number>>";
		} else {
			documentNo = StringUtil.EMPTY;
		}
	}
}
