package com.fse.currencyconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.fse.currencyconverter.model.ConvertedCurrency;

public class App {

	public static void main(String[] args) throws IOException {
		final String amount = readLine("A source currency amount in the format x.yy: ");
		final String fromCode = readLine("A source ISO 4217 currency code: ");
		final String toCode = readLine("A target ISO 4217 currency code: ");

		final CurrencyConverter currencyConverter = buildApplication();
		final ConvertedCurrency convertedCurrency = currencyConverter.convert(amount, fromCode, toCode);
		System.out.println(
				String.format("%s %s, %s",
						convertedCurrency.amount(),
						convertedCurrency.currencyName(),
						convertedCurrency.countryName()));
	}

	private static String readLine(String message) throws IOException {
		System.out.print(message);
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	private static CurrencyConverter buildApplication() {
		return new CurrencyConverter(new FileRateSupplier());
	}
}
