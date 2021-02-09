package br.com.ufsm.product.api.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDisponibilityForm {

	private Long id;

	private Integer amount;

}
