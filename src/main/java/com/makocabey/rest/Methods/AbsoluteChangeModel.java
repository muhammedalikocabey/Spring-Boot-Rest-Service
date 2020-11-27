package com.makocabey.rest.Methods;



public class AbsoluteChangeModel implements ChangeModelStrategy {

	@Override
	public Double CalculateChangeModel(Double todaysPrice, Double yesterdaysPrice) {
		return (todaysPrice - yesterdaysPrice);
	}
}
