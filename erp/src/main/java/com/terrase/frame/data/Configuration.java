package com.terrase.frame.data;

import java.math.BigDecimal;

import com.terrase.frame.data.base.Entity;
import com.terrase.util.StringUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Configuration extends Entity {
	private static final long serialVersionUID = 1L;

	// Format
	protected String currencySymbol;

	protected int quantityDecimal;
	protected int monetaryDecimal;

	protected String datePattern;
	protected String timePattern;
	protected String timestampPattern;

	// Encoding character
	protected String character0;
	protected String character1;
	protected String character2;
	protected String character3;
	protected String character4;
	protected String character5;
	protected String character6;
	protected String character7;
	protected String character8;
	protected String character9;
	
	// Accounting
	protected String accountingUrl;
	protected String accountingServer;
	protected String accountingDatabase;
	protected String accountingUsername;
	protected String accountingPassword;
	
	protected String accountingDebtorControlAccount;
	protected String accountingCreditorControlAccount;
	
	protected String accountingSalesAccount;
	protected String accountingPurchaseAccount;
	protected String accountingPurchaseReturnAccount;
	protected String accountingTaxCode;
	protected BigDecimal accountingTaxRate;
	
	protected String operationDisclaimer;
	protected String depositDisclaimer;
	protected String depositSlipFooterDisclaimer;

	// Printer
	protected String cashRegisterPrinterUrl;
	protected String cashRegisterPrinterName;
	
	// Environment
	protected String cachePath;

	public Configuration() {
		super();
	}

	public String encode(String number) {
		try {
			String result = StringUtil.EMPTY;

			for (int i = 0; i < number.length(); i++) {
				switch (number.charAt(i)) {
				case '0':
					result = result + (character0 == null ? "" : character0);
					break;

				case '1':
					result = result + (character1 == null ? "" : character1);
					break;

				case '2':
					result = result + (character2 == null ? "" : character2);
					break;

				case '3':
					result = result + (character3 == null ? "" : character3);
					break;

				case '4':
					result = result + (character4 == null ? "" : character4);
					break;

				case '5':
					result = result + (character5 == null ? "" : character5);
					break;

				case '6':
					result = result + (character6 == null ? "" : character6);
					break;

				case '7':
					result = result + (character7 == null ? "" : character7);
					break;

				case '8':
					result = result + (character8 == null ? "" : character8);
					break;

				case '9':
					result = result + (character9 == null ? "" : character9);
					break;

				default:
					return null;
				}
			}

			return result;
		} catch (Exception ex) {
			return null;
		}
	}
}
