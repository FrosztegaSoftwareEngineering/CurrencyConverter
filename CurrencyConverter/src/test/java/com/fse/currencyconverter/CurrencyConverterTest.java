package com.fse.currencyconverter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class CurrencyConverterTest {

	private final CurrencyConverter testSubject = new CurrencyConverter();

	@Test
	public void shouldConvertSterlingToSterling() {
		// given
		final String amount = "1.00";
		final String fromCode = "GBP";
		final String toCode = "GBP";
		// when
		final double converted = this.testSubject.convert(amount, fromCode, toCode);
		// then
		assertEquals(converted, 1.00);
	}
}
