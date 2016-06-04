package com.fse.currencyconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileRateSupplier implements RateSupplier {

	private static final String RATE_FILE = "com/fse/currencyconverter/FileRates.csv";

	private final Map<String, Double> rateMap;

	public FileRateSupplier() {
		this.rateMap = new HashMap<>();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						this.getClass().getClassLoader().getResourceAsStream(RATE_FILE)))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				final String[] rateLine = line.split(",");
				this.rateMap.put(rateLine[2].trim(), Double.valueOf(rateLine[3].trim()));
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		ensureRelativeCurrencyIsSupported();
	}

	private void ensureRelativeCurrencyIsSupported() {
		this.rateMap.put("GBP", 1.0);
	}

	@Override
	public double forCodes(String fromCode, String toCode) {
		return this.rateMap.get(toCode) / this.rateMap.get(fromCode);
	}

}
