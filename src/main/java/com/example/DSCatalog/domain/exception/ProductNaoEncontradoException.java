package com.example.DSCatalog.domain.exception;

public class ProductNaoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ProductNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ProductNaoEncontradoException(Long id) {
		this("'ID' de product com código " + id + " não foi encontrado");
	}
}
