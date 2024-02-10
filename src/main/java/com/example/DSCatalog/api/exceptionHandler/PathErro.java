package com.example.DSCatalog.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum PathErro {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada" , "entidade nao encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso" , "violação de integridade"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro Inválido"),
	EMAIL_INVALIDO("/email invalido" , "Email invalido"),
	TOKEN_INVALIDO("/token-invalido" , "Token invalido");

	private String Url;
	private String title;
	
	PathErro(String Url, String title) {
		this.Url = "https//kaique.com.br" + Url;
		this.title = title;
	}
	
	
}
