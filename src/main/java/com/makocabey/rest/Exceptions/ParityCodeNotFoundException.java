package com.makocabey.rest.Exceptions;



public class ParityCodeNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	
	public ParityCodeNotFoundException(String parityCode) {
		super("No Parity Code found for {" + parityCode + "} among the existing data.");
	}
}
