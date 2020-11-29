package com.makocabey.rest.Methods;



public class RelativeChangeModel implements IChangeModelStrategy {

	@Override
	public Double CalculateChangeModel(Double todaysPrice, Double yesterdaysPrice) {
		return ((todaysPrice - yesterdaysPrice) / yesterdaysPrice);
	}
}
