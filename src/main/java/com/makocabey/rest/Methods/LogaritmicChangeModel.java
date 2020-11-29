package com.makocabey.rest.Methods;


import static java.lang.Math.log;



public class LogaritmicChangeModel implements IChangeModelStrategy {

	@Override
	public Double CalculateChangeModel(Double todaysPrice, Double yesterdaysPrice) {
		return (log(todaysPrice - yesterdaysPrice));
	}
}
