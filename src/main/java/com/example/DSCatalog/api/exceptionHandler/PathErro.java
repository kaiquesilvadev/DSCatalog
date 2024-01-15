package com.example.DSCatalog.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum PathErro {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-nncontrada" , "entidade nao encontrada");

	private String Url;
	private String title;
	
	PathErro(String Url, String title) {
		this.Url = "https//kaique.com.br" + Url;
		this.title = title;
	}
	
	
}
