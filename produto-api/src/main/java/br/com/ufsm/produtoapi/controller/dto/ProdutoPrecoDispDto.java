package br.com.ufsm.produtoapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.produtoapi.modelo.Produto;
import lombok.Data;

@Data
public class ProdutoPrecoDispDto {

	private Double preco;
	private Integer dispoinibilidade;
	
	public ProdutoPrecoDispDto(Produto produto) {
		this.preco = produto.getPreco();
		this.dispoinibilidade = produto.getDisponibilidade();
	}

	public static List<ProdutoPrecoDispDto> converter(List<Produto> produtos) {
		return produtos.stream().map(ProdutoPrecoDispDto::new).collect(Collectors.toList());
	}
	
}
