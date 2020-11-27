package com.makocabey.rest.Exceptions;


import java.time.LocalDate;



public class IncorrectParityCodeFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public IncorrectParityCodeFormatException(String parityCode, LocalDate date) {
		
		super("{" + String.valueOf(date) + "} dated {" + 
				parityCode + "} parity code in wrong format.");
	}
	
	public IncorrectParityCodeFormatException(String parityCode) {
		
		super("{" + parityCode + "} parity code in wrong format.");
	}
}
