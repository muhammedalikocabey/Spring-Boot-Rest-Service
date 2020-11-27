package com.makocabey.rest.Exceptions;



public class DatabaseEmptyException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	
	public DatabaseEmptyException() {
		super("No data found in the database");
	}
}
