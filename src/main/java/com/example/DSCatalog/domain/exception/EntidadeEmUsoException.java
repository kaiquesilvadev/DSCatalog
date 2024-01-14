package com.example.DSCatalog.domain.exception;

public class EntidadeEmUsoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public EntidadeEmUsoException(String msg) {
		super(msg);
	}
	
	public EntidadeEmUsoException(Long id) {
		this("Não é possível excluir a entidade de código " + id + " pois está atualmente em uso.");
	}
}
