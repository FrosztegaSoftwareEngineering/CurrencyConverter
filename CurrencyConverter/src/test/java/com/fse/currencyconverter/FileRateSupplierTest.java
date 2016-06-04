package com.fse.currencyconverter;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FileRateSupplierTest {

	private final FileRateSupplier testSubject = new FileRateSupplier();

	@DataProvider
	public Object[][] rateData() {
		return new Object[][] {
				{ "GBP", "AED", 7.2104 },
				{ "GBP", "AUD", 1.51239 },
				{ "GBP", "BAM", 2.60565 },
		};
	}

	@Test(dataProvider = "rateData")
	public void shouldReturnCorrectRate(String fromCode, String toCode, double expectedRate) {
		assertEquals(expectedRate, this.testSubject.forCodes(fromCode, toCode));
	}
}
