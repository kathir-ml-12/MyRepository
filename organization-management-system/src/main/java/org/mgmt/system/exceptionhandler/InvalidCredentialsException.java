package org.mgmt.system.exceptionhandler;

public class InvalidCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCredentialsException(String s) {
		super(s);
	}
}
