package com.example.DSCatalog.domain.exception;

public class CategoryNaoEncontradaException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CategoryNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CategoryNaoEncontradaException(Long id) {
		this("'ID' de category com código " + id + " não encontrado");
	}
}
