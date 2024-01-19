package com.example.DSCatalog.domain.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.DSCatalog.domain.entities.Product;

@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	@DisplayName("testa se id de produto foi apagado , teste de repository")
	public void testaSeIDFoiDeletado() {
		
		productRepository.deleteById(1L);
		Optional<Product> buscaId = productRepository.findById(1L);
		assertTrue(buscaId.isEmpty());
	}
}
