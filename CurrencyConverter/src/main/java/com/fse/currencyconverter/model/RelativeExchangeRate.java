package com.fse.currencyconverter.model;

public class RelativeExchangeRate {

	private final double rate;
	private final String countryName;
	private final String currencyName;

	public RelativeExchangeRate(double rate, String countryName, String currencyName) {
		this.rate = rate;
		this.countryName = countryName;
		this.currencyName = currencyName;
	}

	public double rate() {
		return this.rate;
	}

	public String countryName() {
		return this.countryName;
	}

	public String currencyName() {
		return this.currencyName;
	}
}
