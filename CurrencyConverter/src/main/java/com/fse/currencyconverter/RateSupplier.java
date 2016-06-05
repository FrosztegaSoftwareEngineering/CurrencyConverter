package com.fse.currencyconverter;

import com.fse.currencyconverter.model.RelativeExchangeRate;

public interface RateSupplier {

	/**
	 * Return an exchange rate relative to the source currency code (fromCode)
	 * and target currency code (toCode)
	 * 
	 * @param fromCode
	 *            source currency code
	 * @param toCode
	 *            target currency code
	 * @return Relative Exchange rate from one code to another
	 */
	RelativeExchangeRate forCodes(String fromCode, String toCode);

}
