package com.example.DSCatalog.domain.exception;

public class UserNaoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UserNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public UserNaoEncontradoException(Long id) {
		this("'ID' de user com código " + id + " não encontrado");
	}
}
