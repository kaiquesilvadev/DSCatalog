package com.example.DSCatalog.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.DSCatalog.domain.dto.conversor.ProductConversor;
import com.example.DSCatalog.domain.entities.Product;
import com.example.DSCatalog.domain.frabricaOBJ.CriaProduct;
import com.example.DSCatalog.domain.services.ProductService;

//Anotação utilizada para testes específicos do controlador (controller) ProductController.
//Esta anotação configura um contexto de teste do Spring MVC, focando apenas no controlador
//especificado, e não carrega todo o contexto da aplicação. Ideal para testes unitários de controladores.
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductConversor conversor;

	@MockBean
	private ProductService service;
	private List<Product> listProducts = new ArrayList<>();

	@BeforeEach
	public void setUp() throws Exception {
		listProducts.add(CriaProduct.objCriado());
		when(service.lista()).thenReturn(listProducts);
		when(conversor.converteLista(listProducts)).thenReturn(CriaProduct.convertLista(listProducts));
	}

	@Test
	@DisplayName("testa se retorna código 200 em lista de product")
	public void testaSeRetornaCodigoOkEmListaDeProduct() throws Exception {

		mockMvc.perform(get("/products")).andExpect(status().isOk());
	}
}
