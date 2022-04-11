package org.mgmt.system.exceptionhandler;

public class EmptyListFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyListFoundException(String s) {
		super(s);
	}
}
