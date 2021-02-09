package br.com.ufsm.product.api.controller.dto;

import br.com.ufsm.product.api.model.Product;
import lombok.Data;

@Data
public class DisponibiltyDto {

	private Long id;

	private Double price;

	private Integer amount;

	private Double total;

	public DisponibiltyDto(Product product, int amount) {
		this.id = product.getId();
		this.price = product.getPrice();
		this.amount = amount;
		this.total = product.getPrice() * amount;
	}

}
