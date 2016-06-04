package com.fse.currencyconverter;

public class CurrencyConverter {

	private final RateSupplier rateSupplier;

	public CurrencyConverter(RateSupplier rateSupplier) {
		this.rateSupplier = rateSupplier;
	}

	public double convert(String amount, String fromCode, String toCode) {
		return Double.valueOf(amount) * this.rateSupplier.forCodes(fromCode, toCode);
	}

}
