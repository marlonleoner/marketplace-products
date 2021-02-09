package br.com.ufsm.product.api.exceptions;

import java.util.List;

import br.com.ufsm.product.api.model.UnavailableProductError;
import lombok.Getter;
import lombok.Setter;

public class UnavailableProductException extends RuntimeException {

	private static final long serialVersionUID = 865814450275921702L;

	@Getter
	@Setter
	List<UnavailableProductError> errors;

	public UnavailableProductException(String message) {
		super(message);
	}

}
