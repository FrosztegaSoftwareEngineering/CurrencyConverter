package com.fse.currencyconverter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fse.currencyconverter.model.ConvertedCurrency;
import com.fse.currencyconverter.model.RelativeExchangeRate;

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
		when(this.rateSupplier.forCodes(anyString(), anyString())).thenReturn(rate(1.00));
		final String amount = "1.00";
		final String fromCode = "GBP";
		final String toCode = "GBP";
		// when
		final ConvertedCurrency converted = this.testSubject.convert(amount, fromCode, toCode);
		// then
		assertEquals(converted.amount(), 1.00);
	}

	@Test
	public void shouldConvertToOtherCurrencies() {
		// given
		when(this.rateSupplier.forCodes(anyString(), anyString())).thenReturn(rate(2.60));
		final String amount = "2.00";
		final String fromCode = "GBP";
		final String toCode = "JPY";
		// when
		final ConvertedCurrency converted = this.testSubject.convert(amount, fromCode, toCode);
		// then
		assertEquals(converted.amount(), 5.20);
		verify(this.rateSupplier).forCodes(fromCode, toCode);
	}

	@Test
	public void shouldRoundUpTo2DecimalPlaces() {
		// given
		when(this.rateSupplier.forCodes(anyString(), anyString())).thenReturn(rate(1.50000000001));
		// when
		final ConvertedCurrency converted = this.testSubject.convert("1.00", null, null);
		// then
		assertEquals(converted.amount(), 1.51);
	}

	@Test
	public void shouldReturnCountryNameOfTheTargetCurrencyCode() {
		// given
		final String countryName = "countryName";
		when(this.rateSupplier.forCodes(anyString(), anyString())).thenReturn(rate(1.0, countryName, null));
		// when
		final ConvertedCurrency converted = this.testSubject.convert("1.00", null, null);
		// then
		assertEquals(converted.countryName(), countryName);
	}

	@Test
	public void shouldReturnCurrencyNameOfTheTargetCurrencyCode() {
		// given
		final String currencyName = "currencyName";
		when(this.rateSupplier.forCodes(anyString(), anyString())).thenReturn(rate(1.0, null, currencyName));
		// when
		final ConvertedCurrency converted = this.testSubject.convert("1.00", null, null);
		// then
		assertEquals(converted.currencyName(), currencyName);
	}

	private RelativeExchangeRate rate(double rateValue) {
		return rate(rateValue, null, null);
	}

	private RelativeExchangeRate rate(double rateValue, String countryName, String currencyName) {
		return new RelativeExchangeRate(rateValue, countryName, currencyName);
	}
}
