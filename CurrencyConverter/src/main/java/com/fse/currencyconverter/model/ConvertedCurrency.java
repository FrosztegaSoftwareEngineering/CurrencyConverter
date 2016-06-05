package com.fse.currencyconverter.model;

public class ConvertedCurrency {

	private final double amount;
	private final String countryName;
	private final String currencyName;

	public ConvertedCurrency(double amount, String countryName, String currencyName) {
		this.amount = amount;
		this.countryName = countryName;
		this.currencyName = currencyName;
	}

	public double amount() {
		return this.amount;
	}

	public String countryName() {
		return this.countryName;
	}

	public String currencyName() {
		return this.currencyName;
	}
}
