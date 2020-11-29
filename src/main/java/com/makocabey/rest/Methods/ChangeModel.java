package com.makocabey.rest.Methods;

public class ChangeModel {

	private IChangeModelStrategy changeModelStrategy;
	
	public ChangeModel(String changeModel) {
		changeModelStrategy = ChangeModelFactory.getChangeModelStrategy(changeModel);
	}
	
	public Double calculate(Double todaysPrice, Double yesterdaysPrice) {
		return changeModelStrategy.CalculateChangeModel(todaysPrice, yesterdaysPrice);
	}
}
