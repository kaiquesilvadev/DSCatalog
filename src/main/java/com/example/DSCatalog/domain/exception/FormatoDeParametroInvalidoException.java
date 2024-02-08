package com.example.DSCatalog.domain.exception;

public class FormatoDeParametroInvalidoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public FormatoDeParametroInvalidoException(String msg) {
		super(msg);
	}
	
	public FormatoDeParametroInvalidoException() {
		this("O formato de parâmetro passado na URL é inválido. Por favor, corrija.");
	}
}
