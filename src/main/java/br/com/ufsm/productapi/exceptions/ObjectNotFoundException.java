package br.com.ufsm.productapi.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4565656577695416170L;

	public ObjectNotFoundException(String message) {
		super(message);
	}
}
