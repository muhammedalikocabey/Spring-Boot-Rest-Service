package com.makocabey.rest.Exceptions;


import java.time.LocalDate;



public class CurrencyLessThanZeroException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	
	public CurrencyLessThanZeroException(LocalDate date, String parityCode) {
		super("Currency with {" + parityCode + "} coded on {" + String.valueOf(date) + 
				"} dated is less than zero.");

	}
}
