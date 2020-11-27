package com.makocabey.rest.Exceptions;


import java.time.LocalDate;



public class DateNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	
	public DateNotFoundException(LocalDate date1) {
		super("No data found on day of {" + String.valueOf(date1) + "}");
	}
	
	public DateNotFoundException(LocalDate date1,  LocalDate date2) {
		super("No data found between StartDay:{" + String.valueOf(date1) + "} and EndDate:{" + 
				String.valueOf(date2) + "} dates.");
	}
	
	public DateNotFoundException(LocalDate date,  String parityCode) {
		super("Calculation cannot be performed because the parity code with {"
				+ parityCode + "} code on the previous day of {" + String.valueOf(date) +
				"} date cannot be found.");
	}
}
