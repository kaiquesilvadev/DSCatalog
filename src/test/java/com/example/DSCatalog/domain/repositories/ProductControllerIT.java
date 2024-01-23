package com.example.DSCatalog.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
public class ProductControllerIT {

	@LocalServerPort
	private int porta;

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
}
