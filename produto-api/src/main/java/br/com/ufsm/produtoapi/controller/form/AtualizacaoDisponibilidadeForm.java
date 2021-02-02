package br.com.ufsm.produtoapi.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AtualizacaoDisponibilidadeForm {

	@NotNull @Positive
	private Integer disponibilidade;
	
}
