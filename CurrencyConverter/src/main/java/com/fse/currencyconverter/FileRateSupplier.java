package com.fse.currencyconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.fse.currencyconverter.model.RelativeExchangeRate;

public class FileRateSupplier implements RateSupplier {

	private static final String RATE_FILE = "com/fse/currencyconverter/FileRates.csv";
	private static final String RELATIVE_CURRENCY_CODE = "GBP";

	private final Map<String, RelativeExchangeRate> rateMap;

	public FileRateSupplier() {
		this.rateMap = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						this.getClass().getClassLoader().getResourceAsStream(RATE_FILE)))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				final String[] rateLine = line.split(",");
				final RelativeExchangeRate rate = new RelativeExchangeRate(
						Double.valueOf(rateLine[3].trim()),
						rateLine[0].trim(),
						rateLine[1].trim());
				this.rateMap.put(rateLine[2].trim(), rate);
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		ensureRelativeCurrencyIsSupported();
	}

	private void ensureRelativeCurrencyIsSupported() {
		this.rateMap.put(RELATIVE_CURRENCY_CODE, new RelativeExchangeRate(
				1.0,
				"United Kingdom",
				"British Pound"));
	}

	@Override
	public RelativeExchangeRate forCodes(String fromCode, String toCode) {
		final RelativeExchangeRate targetExchangeRate = this.rateMap.get(toCode);
		final RelativeExchangeRate sourceExchangeRate = this.rateMap.get(fromCode);
		return new RelativeExchangeRate(
				targetExchangeRate.rate() / sourceExchangeRate.rate(),
				targetExchangeRate.countryName(),
				targetExchangeRate.currencyName());
	}

}
