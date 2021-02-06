package br.com.ufsm.productapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateProductForm {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @Positive
	private Double preco;
	
	@NotNull @Positive
	private Integer disponibilidade;
	
}
