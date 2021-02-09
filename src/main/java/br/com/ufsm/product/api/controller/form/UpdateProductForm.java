package br.com.ufsm.product.api.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductForm {

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

}
