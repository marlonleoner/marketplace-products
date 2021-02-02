package br.com.ufsm.produtoapi.controller.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.ufsm.produtoapi.modelo.Produto;
import br.com.ufsm.produtoapi.modelo.TipoProduto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProdutoForm {

	@NotNull @NotEmpty
	private String nome;
	
	@NotNull @Positive
	private Double preco;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoProduto tipo;
	
	@NotNull @Positive
	private Integer disponibilidade;

	public Produto converter() {
		return new Produto(nome, preco, tipo, disponibilidade);
	}
	
}
