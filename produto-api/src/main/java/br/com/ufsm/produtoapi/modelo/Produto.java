package br.com.ufsm.produtoapi.modelo;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
public class Produto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long idProduto;
	
	@Column(name = "nome_produto")
	private String nome;
	
	@Column(name = "preco")
	private Double preco;
	
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private TipoProduto tipo;
	
	@Column(name = "disponibilidade")
	private Integer disponibilidade;
	
	public Produto(String nome, Double preco, TipoProduto tipo, Integer disponibilidade) {
		this.nome = nome;
		this.preco = preco;
		this.tipo = tipo;
		this.disponibilidade = disponibilidade;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Produto)) return false;
		Produto Produto = (Produto) o;
		return Objects.equals(this.nome, Produto.nome);
	}

}
