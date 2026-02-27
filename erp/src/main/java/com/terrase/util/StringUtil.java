package com.terrase.util;

import java.math.BigDecimal;

public class StringUtil extends org.apache.commons.lang3.StringUtils {

	public static final String EMPTY = "";
	public static final String NEWLINE = System.lineSeparator();
	public static final String TAB = "\t";

	public static String appendLine(String parent, String newLine) {
		if (isEmpty(parent)) {
			return parent + newLine;
		} else {
			return parent + System.lineSeparator() + newLine;
		}
	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static boolean isNumber(String value) {
		try {
			new BigDecimal(value);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static String toSignedNumberString(String value) {
		try {
			if (!StringUtil.isEmpty(value)) {
				if (isNumber(value)) {
					if (value.charAt(0) == '-' || value.charAt(0) == '+') {
						return value;
					} else {
						return "+" + value;
					}
				}
			}
		} catch (Exception ex) {
			return value;
		}

		return value;
	}

	public static String toSignedDecimalString(String value, int decimalPlaces) {
		try {
			String sign = null;
			String number = null;

			if (!StringUtil.isEmpty(value)) {
				if (value.charAt(0) == '-' || value.charAt(0) == '+') {
					sign = value.charAt(0) + "";
					number = value.substring(1);
				} else {
					sign = "+";
					number = value;
				}

				if (isNumber(number)) {
					if (number.contains(".")) {
						return sign + number;
					} else {
						number = new StringBuilder(number).insert(number.length() - decimalPlaces, ".").toString();

						while (number.length() < (decimalPlaces + 1)) {
							number = "0" + number;
						}

						return sign + number;
					}
				}
			}
		} catch (Exception ex) {
			return value;
		}

		return value;
	}
}
