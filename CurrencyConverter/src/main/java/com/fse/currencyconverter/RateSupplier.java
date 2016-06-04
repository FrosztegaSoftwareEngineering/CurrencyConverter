package com.fse.currencyconverter;

public interface RateSupplier {

	double forCodes(String fromCode, String toCode);

}
