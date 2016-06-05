package com.fse.currencyconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

	public static void main(String[] args) throws IOException {
		final String amount = readLine("A source currency amount in the format x.yy: ");
		final String fromCode = readLine("A source ISO 4217 currency code: ");
		final String toCode = readLine("A target ISO 4217 currency code: ");

		final CurrencyConverter currencyConverter = buildApplication();
		System.out.println(currencyConverter.convert(amount, fromCode, toCode));
	}

	private static String readLine(String message) throws IOException {
		System.out.print(message);
		return new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	private static CurrencyConverter buildApplication() {
		return new CurrencyConverter(new FileRateSupplier());
	}
}
