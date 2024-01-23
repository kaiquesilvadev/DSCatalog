package com.example.DSCatalog.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.DSCatalog.domain.exception.ProductNaoEncontradoException;
import com.example.DSCatalog.domain.repositories.ProductRepository;

//@SpringBootTest: Inicializa a aplicação Spring Boot para testes integrados, simulando o ambiente de produção.
//@Transactional: Garante atomicidade nas transações do banco de dados durante os testes, revertendo as mudanças ao final de cada teste.
@SpringBootTest
public class ProductServiceIT {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductRepository repository;
	private Long idExistente;
	private Long totalProduct;
	private Long idInexistente;

	@BeforeEach
	public void setUp() {
		idExistente = 10L;
		totalProduct = 25L;
		idInexistente = 1000L;
	}
	
	// teste de delete de product

	@Test
	@DisplayName("testa de o id de product e apagado com sucesso")
	public void testaSeIdEApagadoComSucesso() {

		service.deleta(idExistente);

		assertEquals(totalProduct - 1, repository.count());
	}

	@Test
	@DisplayName("testa se método deleta de product retorna uma exception quando id for inexistente")
	public void testaSeRetornaUmaExceptionQuandoIdForInexistente() {

		assertThrows(ProductNaoEncontradoException.class, () -> {
			service.deleta(idInexistente);
		});
	}
	
}
