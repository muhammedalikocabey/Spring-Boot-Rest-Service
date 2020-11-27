package com.makocabey.rest.Methods;


import com.makocabey.rest.Exceptions.ChangeModelNotFoundException;



public class ChangeModelFactory {

	public static ChangeModelStrategy getChangeModelStrategy(String changeModel) {
		if(changeModel.equalsIgnoreCase("absolute")) {
			return new AbsoluteChangeModel();
		}
		
		else if (changeModel.equalsIgnoreCase("relative")) { 
			return new RelativeChangeModel();
		}
		
		else if(changeModel.equalsIgnoreCase("logaritmic")) {
			return new LogaritmicChangeModel();
		}
		
		else {
			throw new ChangeModelNotFoundException(changeModel);
		}
	}
}
