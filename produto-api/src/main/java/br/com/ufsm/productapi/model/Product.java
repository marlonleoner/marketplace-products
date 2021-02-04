package br.com.ufsm.productapi.model;

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
@NoArgsConstructor
public class Product {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	@Getter
	private Long idProduct;
	
	@Column(name = "name_product")
	@Getter
	@Setter
	private String name;
	
	@Column(name = "price")
	@Getter
	@Setter
	private Double price;
	
	@Column(name = "created_at")
	@Getter
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	@Getter
	@Setter
	private TypeEnum typeEnum;
	
	@Column(name = "disponibility")
	@Getter
	@Setter
	private Integer disponibility;
	
	public Product(String nome, Double preco, TypeEnum tipo, Integer disponibilidade) {
		this.name = nome;
		this.price = preco;
		this.typeEnum = tipo;
		this.disponibility = disponibilidade;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product Produto = (Product) o;
		return Objects.equals(this.name, Produto.name);
	}

}
