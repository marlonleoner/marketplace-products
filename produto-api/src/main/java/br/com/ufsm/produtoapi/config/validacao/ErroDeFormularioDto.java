package br.com.ufsm.produtoapi.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ErroDeFormularioDto {
	
	private String campo;
	private String erro;
	
}
