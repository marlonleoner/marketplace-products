package br.com.ufsm.product.api.controller.form;

import java.util.List;

import lombok.Data;

@Data
public class VerifyDisponibilityForm {

	private List<ProductDisponibilityForm> products;

}
