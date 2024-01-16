package com.example.DSCatalog.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum PathErro {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada" , "entidade nao encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso" , "violação de integridade");

	private String Url;
	private String title;
	
	PathErro(String Url, String title) {
		this.Url = "https//kaique.com.br" + Url;
		this.title = title;
	}
	
	
}
