package com.fse.currencyconverter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CurrencyConverterTest {

	private CurrencyConverter testSubject;

	@Mock
	private RateSupplier rateSupplier;

	@BeforeMethod
	public void createTestSubject() {
		MockitoAnnotations.initMocks(this);
		this.testSubject = new CurrencyConverter(this.rateSupplier);
	}

	@Test
	public void shouldConvertSterlingToSterling() {
		// given
		when(this.rateSupplier.forCodes(anyString(), anyString())).thenReturn(1.00);
		final String amount = "1.00";
		final String fromCode = "GBP";
		final String toCode = "GBP";
		// when
		final double converted = this.testSubject.convert(amount, fromCode, toCode);
		// then
		assertEquals(converted, 1.00);
	}

	@Test
	public void shouldConvertToOtherCurrencies() {
		// given
		when(this.rateSupplier.forCodes(anyString(), anyString())).thenReturn(2.60);
		final String amount = "2.00";
		final String fromCode = "GBP";
		final String toCode = "JPY";
		// when
		final double converted = this.testSubject.convert(amount, fromCode, toCode);
		// then
		assertEquals(converted, 5.20);
		verify(this.rateSupplier).forCodes(fromCode, toCode);
	}
}
