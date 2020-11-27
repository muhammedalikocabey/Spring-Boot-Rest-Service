package com.makocabey.rest.Exceptions;



public class ChangeModelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public ChangeModelNotFoundException(String changeModel) {
		super("Yield Change Model {" + changeModel + "} Not Found. "
				+ "Try absolute, relative or logaritmic method.");

	}
}
