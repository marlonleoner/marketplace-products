package br.com.ufsm.productapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.productapi.model.Product;
import lombok.Data;

@Data
public class ProductPriceDispDto {

	private Double price;
	private Integer disponibility;
	
	public ProductPriceDispDto(Product produto) {
		this.price = produto.getPrice();
		this.disponibility = produto.getDisponibility();
	}

	public static List<ProductPriceDispDto> converter(List<Product> produtos) {
		return produtos.stream().map(ProductPriceDispDto::new).collect(Collectors.toList());
	}
	
}
