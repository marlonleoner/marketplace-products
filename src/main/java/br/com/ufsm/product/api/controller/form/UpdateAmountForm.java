package br.com.ufsm.product.api.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAmountForm {

	@NotNull
	@Positive
	private Integer amount;

}
