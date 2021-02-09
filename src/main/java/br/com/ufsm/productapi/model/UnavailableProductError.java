package br.com.ufsm.productapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnavailableProductError {

	private String error;

	private String message;

	private Long id;

}
