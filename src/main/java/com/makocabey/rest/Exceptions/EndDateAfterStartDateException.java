package com.makocabey.rest.Exceptions;


import java.time.LocalDate;



public class EndDateAfterStartDateException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	
	public EndDateAfterStartDateException(LocalDate startDate, LocalDate endDate) {
		super("The StartDay:{"+ String.valueOf(startDate) + "} cannot be "
				+ "after the EndDay:{" + String.valueOf(endDate) + "}");
	}

}
