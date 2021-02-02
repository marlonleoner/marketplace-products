package br.com.ufsm.produtoapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.produtoapi.modelo.Produto;
import br.com.ufsm.produtoapi.modelo.TipoProduto;
import lombok.Data;

@Data
public class ProdutoDto {

	private Long id;
	private String nome;
	private Double preco;
	private TipoProduto tipo;
	private Integer dispoinibilidade;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getIdProduto();
		this.preco = produto.getPreco();
		this.tipo = produto.getTipo();
		this.nome = produto.getNome();
		this.dispoinibilidade = produto.getDisponibilidade();
	}

	public static List<ProdutoDto> converter(List<Produto> produtos) {
		return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
	}

}
