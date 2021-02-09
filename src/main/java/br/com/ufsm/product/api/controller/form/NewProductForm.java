package br.com.ufsm.product.api.controller.form;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.ufsm.product.api.model.Product;
import br.com.ufsm.product.api.model.TypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewProductForm {

	@NotNull
	@NotEmpty
	private String name;

	@NotNull
	@NotEmpty
	private String description;

	@NotNull
	@Positive
	private Double price;

	@NotNull
	@Positive
	private Integer amount;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeEnum type;

	public Product converter() {
		return new Product(this.name, this.description, this.price, this.type, this.amount);
	}

}
