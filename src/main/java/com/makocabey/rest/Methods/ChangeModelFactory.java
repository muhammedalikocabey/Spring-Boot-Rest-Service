package com.makocabey.rest.Methods;


import static java.lang.Math.log;



public enum ChangeModelFactory implements IChangeModelStrategy {
	ABSOLUTE("absolute") {
		public Double CalculateChangeModel(Double todaysPrice, Double yesterdaysPrice) {
			return (todaysPrice - yesterdaysPrice);			
		}
	},
	
	RELATIVE("relative") {
		public Double CalculateChangeModel(Double todaysPrice, Double yesterdaysPrice) {
			return ((todaysPrice - yesterdaysPrice) / yesterdaysPrice);
		}
	},
	
	LOGARITMIC("logaritmic") {
		public Double CalculateChangeModel(Double todaysPrice, Double yesterdaysPrice) {
			return (log(todaysPrice - yesterdaysPrice));
		}
	};

	private String changeModelName;
	
	private ChangeModelFactory(String changeModelName) {
		this.changeModelName = changeModelName;
	}
	
	public String modelType() {
		return changeModelName;
	}
}