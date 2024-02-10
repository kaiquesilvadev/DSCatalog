package com.example.DSCatalog.domain.exception;

public class TokenInvalidoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public TokenInvalidoException(String token){
		super("Desculpe, o token '" + token + "' é inválido ou expirou. Por favor, verifique novamente.");
	}
	
}
