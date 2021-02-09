package br.com.ufsm.product.api.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.product.api.model.Product;
import br.com.ufsm.product.api.model.TypeEnum;
import lombok.Data;

@Data
public class ProductDto {

	private Long id;

	private String name;

	private String description;

	private Double price;

	private TypeEnum type;

	private Integer amount;

	public ProductDto(Product produto) {
		this.id = produto.getId();
		this.name = produto.getName();
		this.description = produto.getDescription();
		this.price = produto.getPrice();
		this.type = produto.getType();
		this.amount = produto.getAmount();
	}

	public static List<ProductDto> converter(List<Product> produtos) {
		return produtos.stream().map(ProductDto::new).collect(Collectors.toList());
	}

}
