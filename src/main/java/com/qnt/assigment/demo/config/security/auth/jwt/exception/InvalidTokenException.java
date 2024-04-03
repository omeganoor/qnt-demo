package com.qnt.assigment.demo.config.security.auth.jwt.exception;

public class InvalidTokenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {
	}

	public InvalidTokenException(String message) {
		
		super(message);
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return null;
	}
	
}