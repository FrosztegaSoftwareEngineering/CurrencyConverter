package com.fse.currencyconverter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.fse.currencyconverter.model.ConvertedCurrency;
import com.fse.currencyconverter.model.RelativeExchangeRate;

public class CurrencyConverter {

	private final RateSupplier rateSupplier;

	public CurrencyConverter(RateSupplier rateSupplier) {
		this.rateSupplier = rateSupplier;
	}

	public ConvertedCurrency convert(String amount, String fromCode, String toCode) {
		final RelativeExchangeRate exchangeRate = this.rateSupplier.forCodes(fromCode, toCode);
		final double rawConverted = Double.valueOf(amount) * exchangeRate.rate();
		final double rounded = BigDecimal.valueOf(rawConverted).setScale(2, RoundingMode.UP).doubleValue();
		return new ConvertedCurrency(rounded, exchangeRate.countryName(), exchangeRate.currencyName());
	}

}
