package com.terrase.frame.data;

import com.terrase.frame.data.base.MasterEntity;
import com.terrase.frame.enumerator.EnumAutoNumberReset;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutoNumber extends MasterEntity {
	private static final long serialVersionUID = 1L;

	public static final String SHORT_YEAR_PLACER = "[yy]";
	public static final String LONG_YEAR_PLACER = "[yyyy]";
	public static final String MONTH_PLACER = "[MM]";
	public static final String DAY_PLACER = "[dd]";

	protected String prefix;
	protected String postfix;
	protected int length;

	protected int sequence;
	protected int year;
	protected int month;
	protected int day;

	protected EnumAutoNumberReset resetBy;

	protected boolean systemSequence;

	public AutoNumber() {
		super();
	}
}
