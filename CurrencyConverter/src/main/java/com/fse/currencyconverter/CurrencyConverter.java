package com.fse.currencyconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConverter {

	private final RateSupplier rateSupplier;

	public CurrencyConverter(RateSupplier rateSupplier) {
		this.rateSupplier = rateSupplier;
	}

	public double convert(String amount, String fromCode, String toCode) {
		final double rawConverted = Double.valueOf(amount) * this.rateSupplier.forCodes(fromCode, toCode);
		return BigDecimal.valueOf(rawConverted).setScale(2, RoundingMode.UP).doubleValue();
	}

}
