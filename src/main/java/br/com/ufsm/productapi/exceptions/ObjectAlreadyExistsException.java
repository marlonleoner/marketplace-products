package br.com.ufsm.productapi.exceptions;

public class ObjectAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -2511846370137131828L;

	public ObjectAlreadyExistsException(String message) {
		super(message);
	}

}
