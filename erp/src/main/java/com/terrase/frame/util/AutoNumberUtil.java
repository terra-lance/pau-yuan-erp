package com.terrase.frame.util;

import java.util.Calendar;

import com.terrase.frame.data.AutoNumber;
import com.terrase.frame.enumerator.EnumAutoNumberReset;

public class AutoNumberUtil {
	public static AutoNumber increment(AutoNumber autoNumber) {
		Calendar calendar = Calendar.getInstance();

		if (autoNumber.getResetBy() == EnumAutoNumberReset.YEARLY) {
			if (calendar.get(Calendar.YEAR) != autoNumber.getYear()) {
				autoNumber.setSequence(0);
				autoNumber.setYear(calendar.get(Calendar.YEAR));
			}
		} else if (autoNumber.getResetBy() == EnumAutoNumberReset.MONTHLY) {
			if (calendar.get(Calendar.YEAR) != autoNumber.getYear()
					|| calendar.get(Calendar.MONTH) + 1 != autoNumber.getMonth()) {
				autoNumber.setSequence(0);
				autoNumber.setYear(calendar.get(Calendar.YEAR));
				autoNumber.setMonth(calendar.get(Calendar.MONTH) + 1);
			}
		} else if (autoNumber.getResetBy() == EnumAutoNumberReset.DAILY) {
			if (calendar.get(Calendar.YEAR) != autoNumber.getYear()
					|| calendar.get(Calendar.MONTH) + 1 != autoNumber.getMonth()
					|| calendar.get(Calendar.DATE) != autoNumber.getDay()) {
				autoNumber.setSequence(0);
				autoNumber.setYear(calendar.get(Calendar.YEAR));
				autoNumber.setMonth(calendar.get(Calendar.MONTH) + 1);
				autoNumber.setDay(calendar.get(Calendar.DATE));
			}
		}

		autoNumber.setSequence(autoNumber.getSequence() + 1);

		return autoNumber;
	}

	public static String generate(AutoNumber autoNumber) {
		StringBuilder numberBuilder = new StringBuilder();

		numberBuilder = numberBuilder.append(autoNumber.getPrefix());

		StringBuilder sequence = new StringBuilder().append(autoNumber.getSequence());
		while (sequence.length() < autoNumber.getLength()) {
			sequence = sequence.insert(0, "0");
		}

		numberBuilder = numberBuilder.append(sequence);
		numberBuilder = numberBuilder.append(autoNumber.getPostfix());

		String number = numberBuilder.toString();

		Calendar calendar = Calendar.getInstance();

		if (number.contains(AutoNumber.LONG_YEAR_PLACER)) {
			if (autoNumber.getResetBy() == EnumAutoNumberReset.NEVER) {
				number = number.replace(AutoNumber.LONG_YEAR_PLACER, String.valueOf(calendar.get(Calendar.YEAR)));
			} else {
				number = number.replace(AutoNumber.LONG_YEAR_PLACER, String.valueOf(autoNumber.getYear()));
			}
		}

		if (number.contains(AutoNumber.SHORT_YEAR_PLACER)) {
			if (autoNumber.getResetBy() == EnumAutoNumberReset.NEVER) {
				number = number.replace(AutoNumber.SHORT_YEAR_PLACER,
						String.valueOf(calendar.get(Calendar.YEAR)).substring(2));
			} else {
				number = number.replace(AutoNumber.SHORT_YEAR_PLACER,
						String.valueOf(autoNumber.getYear()).substring(2));
			}
		}

		if (number.contains(AutoNumber.MONTH_PLACER)) {
			String month = new String();

			if (autoNumber.getResetBy() == EnumAutoNumberReset.NEVER) {
				month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
			} else {
				month = String.valueOf(autoNumber.getMonth());
			}
			number = number.replace(AutoNumber.MONTH_PLACER, month.length() == 2 ? month : ("0" + month));
		}

		if (number.contains(AutoNumber.DAY_PLACER)) {
			String day = new String();

			if (autoNumber.getResetBy() == EnumAutoNumberReset.NEVER) {
				day = String.valueOf(calendar.get(Calendar.DATE));
			} else {
				day = String.valueOf(autoNumber.getDay());
			}
			number = number.replace(AutoNumber.DAY_PLACER, day.length() == 2 ? day : ("0" + day));
		}

		return number;
	}
}