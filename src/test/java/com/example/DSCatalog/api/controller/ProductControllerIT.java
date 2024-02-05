package com.example.DSCatalog.api.controller;

import com.example.DSCatalog.domain.frabricaOBJ.CriaProduct;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Anotação indicando que este é um teste de integração e será executado em um ambiente real com uma porta aleatória.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//Anotação que configura o ciclo de vida da instância de teste para ser reutilizada para todos os métodos de teste na classe.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class ProductControllerIT {

	@LocalServerPort
	private int porta;
	
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {

		RestAssured.port = porta;
		RestAssured.basePath = "/products";
	}

	@Test
	@DisplayName("testa se o retorno do codigo é o 200 e se o total de elementos e 25")
	public void testaSeCodigoDeListaE200() {

		// Configuração da requisição
		int totalAtual = RestAssured.given()
					.accept(ContentType.JSON) // Aceita resposta no formato JSON
				.when()//é parte da construção de uma requisição HTTP e é usado para iniciar a seção em que você especifica a ação a ser executada (como GET, POST, PUT, etc.)
					.contentType(ContentType.JSON)
					.get()
				.then() // é usado para iniciar a seção de validação de uma resposta HTTP
					.statusCode(HttpStatus.OK.value())
				.extract()
						.path("size()"); // Extrai o tamanho da resposta JSON
					
		assertEquals(25 , totalAtual);
	}
	
	@Test
	@DisplayName("testa se atualiza um id existente")
	public void testaSeOCodigoDeatualizacaoEO200() throws JsonProcessingException {
		
		String atualiza = objectMapper.writeValueAsString(CriaProduct.ObtParaAtualizar());

		RestAssured.given()
			.accept(ContentType.JSON)
			.pathParam("id", 2)
			.body(atualiza)// prepara um corpo para o request
			.contentType(ContentType.JSON)
			.header("NomeDoCabecalho", "ValorDoCabecalho") // Passando um cabeçalho
		.when()
			.put("{id}")
		.then().statusCode(HttpStatus.OK.value());
	}
	
	@Test
	@DisplayName("testa se retorna o 404 quando o id for inexistente")
	public void testaSeOCodigoDeAtualizacaoEO404QuandoPassarUmIdInexistente() throws JsonProcessingException {
		
		String atualiza = objectMapper.writeValueAsString(CriaProduct.ObtParaAtualizar());// crai um json com base no obj

		RestAssured.given()
			.accept(ContentType.JSON)
			.pathParam("id", 20000)
			.body(atualiza)// prepara um corpo para o request
			.contentType(ContentType.JSON)
		.when()
			.put("{id}")
		.then().statusCode(HttpStatus.NOT_FOUND.value())
		;
	}
}
