package com.example.DSCatalog.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.DSCatalog.domain.entities.Product;
import com.example.DSCatalog.domain.frabricaOBJ.CriaProduct;


//@DataJpaTest configura um ambiente de teste para integração com JPA, facilitando a escrita de testes de persistência.
//Geralmente usado em conjunto com anotações como @Autowired e @MockBean para configurar dependências e o ambiente de teste.
@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	private Product product;

	// @BeforeEach é uma anotação do JUnit que indica que o método será executado antes de cada teste.
	// É comumente usado para configurar o estado inicial necessário para os testes.
	@BeforeEach
	public void setUp() {
		this.product = CriaProduct.objCriado();
	}

	@Test
	@DisplayName("testa se id de produto foi apagado , teste de repository")
	public void testaSeIDFoiDeletado() {

		productRepository.deleteById(1L);
		Optional<Product> buscaId = productRepository.findById(1L);
		assertTrue(buscaId.isEmpty());
	}

	@Test
	@DisplayName("testa se o auto incremente e valido")
	public void testaSeIdEAutoIncrementoNoBancoDeDados() {

	     Product entity = productRepository.save(product);
		
		assertNotNull(product.getId());
		assertTrue(entity.getId() == 26L);
	}
	
	@Test
	@DisplayName("testa se o optional é retornado vazio quando for passado um id invalido")
	private void testaSeFindByIdRetornaUmOptionalVazioQuandoIdInexistente() {
	
		 Optional<Product> inexistente = productRepository.findById(29L);
		 
		 assertTrue(inexistente.isEmpty());
	}
	
	@Test
	@DisplayName("testa se contem um OBJ presente no findById quando passado um id valido")
	private void testaSeOptionalRetornaUmIdExistenteQuandoPassardoUmIdValido() {
	
		 Optional<Product> inexistente = productRepository.findById(25L);
		 
		 assertTrue(inexistente.isPresent());
	}
}
 