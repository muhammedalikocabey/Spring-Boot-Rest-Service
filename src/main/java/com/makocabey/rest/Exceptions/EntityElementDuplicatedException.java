package com.makocabey.rest.Exceptions;


import java.time.LocalDate;



public class EntityElementDuplicatedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	
	public EntityElementDuplicatedException(String parityCode, LocalDate date) {
		super("There is more than one {" + String.valueOf(date) + 
				"} dated, and {" + parityCode + "} coded data.");
	}
}
