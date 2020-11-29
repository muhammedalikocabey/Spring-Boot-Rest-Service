package com.makocabey.rest.Methods;



public class AbsoluteChangeModel implements IChangeModelStrategy {

	@Override
	public Double CalculateChangeModel(Double todaysPrice, Double yesterdaysPrice) {
		return (todaysPrice - yesterdaysPrice);
	}
}
