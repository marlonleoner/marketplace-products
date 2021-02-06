package br.com.ufsm.productapi.controller.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.ufsm.productapi.model.Product;
import br.com.ufsm.productapi.model.TypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductForm {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @Positive
	private Double preco;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeEnum tipo;
	
	@NotNull @Positive
	private Integer disponibilidade;

	public Product converter() {
		return new Product(nome, preco, tipo, disponibilidade);
	}
	
}
