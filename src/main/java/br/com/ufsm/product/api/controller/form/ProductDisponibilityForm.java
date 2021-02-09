package br.com.ufsm.productapi.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDisponibilityForm {

	private Long id;

	private Integer amount;

}
