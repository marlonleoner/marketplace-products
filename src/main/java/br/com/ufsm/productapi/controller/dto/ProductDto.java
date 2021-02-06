package br.com.ufsm.productapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ufsm.productapi.model.Product;
import br.com.ufsm.productapi.model.TypeEnum;
import lombok.Data;

@Data
public class ProductDto {

	private Long id;
	private String name;
	private Double price;
	private TypeEnum type;
	private Integer dispoinibility;
	
	public ProductDto(Product produto) {
		this.id = produto.getIdProduct();
		this.price = produto.getPrice();
		this.type = produto.getTypeEnum();
		this.name = produto.getName();
		this.dispoinibility = produto.getDisponibility();
	}

	public static List<ProductDto> converter(List<Product> produtos) {
		return produtos.stream().map(ProductDto::new).collect(Collectors.toList());
	}

}
