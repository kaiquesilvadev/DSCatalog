package com.example.DSCatalog.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public EntidadeNaoEncontradaException(Long id) {
		this("'ID' de código " + id + " não encontrado" );
	}
}
