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
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PRODUCT_ID")
	@TableGenerator(name = "PRODUCT_ID", table = "GENERATOR_TABLE", pkColumnName = "PRODUCT_KEY", valueColumnName = "PRODUCT_KEY_NEXT", pkColumnValue = "product", allocationSize = 1)
	@Column(name = "id")
	@Getter
	private Long id;

	@Column(name = "name")
	@Getter
	@Setter
	private String name;

	@Column(name = "description")
	@Getter
	@Setter
	private String description;

	@Column(name = "price")
	@Getter
	@Setter
	private Double price;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	@Getter
	@Setter
	private TypeEnum type;

	@Column(name = "amount")
	@Getter
	@Setter
	private Integer amount;

	@Column(name = "created_at")
	@Getter
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at")
	@Getter
	@Setter
	private LocalDateTime updatedAt = LocalDateTime.now();

	public Product(String name, String description, Double price, TypeEnum type, Integer amount) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.type = type;
		this.amount = amount;
	}

	public boolean isAvailable(Integer amount) {
		return amount > 0 && amount <= this.amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Product))
			return false;

		Product Produto = (Product) o;

		return Objects.equals(this.name, Produto.name);
	}

}
