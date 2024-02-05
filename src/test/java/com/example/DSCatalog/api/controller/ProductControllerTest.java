package com.example.DSCatalog.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.DSCatalog.domain.dto.conversor.ProductConversor;
import com.example.DSCatalog.domain.entities.Product;
import com.example.DSCatalog.domain.exception.ProductNaoEncontradoException;
import com.example.DSCatalog.domain.frabricaOBJ.CriaProduct;
import com.example.DSCatalog.domain.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

//Anotação utilizada para testes específicos do controlador (controller) ProductController.
//Esta anotação configura um contexto de teste do Spring MVC, focando apenas no controlador
//especificado, e não carrega todo o contexto da aplicação. Ideal para testes unitários de controladores.
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductConversor conversor;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ProductService service;
	private List<Product> listProducts = new ArrayList<>();
	private Long idExistente;
	private Long idInexistente;
	private Product entity;

	@BeforeEach
	public void setUp() throws Exception {
		idExistente = 1L;
		idInexistente = 2L;
		entity = CriaProduct.objCriado();
		listProducts.add(entity);
		Page<Product> pageProducts = new PageImpl<>(listProducts);

		// Criando um objeto Pageable para passar durante a simulação
		Pageable pageable = Mockito.mock(Pageable.class);

		//mock de lista
		when(service.lista(pageable)).thenReturn(pageProducts);
		
		//Mokc conversor
		when(conversor.converteEntidade(entity)).thenReturn(CriaProduct.convertProduct(entity));
		when(conversor.converteLista(listProducts)).thenReturn(CriaProduct.convertLista(listProducts));
		
		//mock BuscaPorId
		when(service.buscaPorId(idExistente)).thenReturn(CriaProduct.objCriado());	
		when(service.buscaPorId(idInexistente)).thenThrow(new ProductNaoEncontradoException(idInexistente));
		
		when(service.atualiza(eq(idExistente), any())).thenReturn(CriaProduct.objCriado());	
		when(service.atualiza(eq(idInexistente), any())).thenThrow(new ProductNaoEncontradoException(idInexistente));
	}

	@Test
	@DisplayName("testa se retorna código 200 em lista de product")
	public void testaSeRetornaCodigoOkEmListaDeProduct() throws Exception {
		mockMvc.perform(get("/products")).andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("testa se retorna código 200 quando o id for existente")
	public void testaSeRetornaCodigoOkquandoIdExistente() throws Exception {
		// Realiza uma requisição GET para o endpoint "/products/{id}" com um ID existente.
		// O método perform() inicia a execução da requisição, e o valor do ID (idExistente) é passado como um parâmetro na URL.
		mockMvc.perform(get("/products/{id}", idExistente))

		// Executa uma expectativa (assertion) para garantir que um campo "id" exista no corpo da resposta JSON.
		.andExpect(jsonPath("$.id").exists())

		// Executa outra expectativa para garantir que o status da resposta seja "OK" (200).
		.andExpect(status().isOk());	}
	
	@Test
	@DisplayName("testa se retorna código 404 quando o id for inexistente")
	public void testaSeRetornaCodigo404quandoIdExistente() throws Exception {
		// Realiza uma requisição GET para o endpoint "/products/{id}" com um ID inexistente.
		// O método perform() inicia a execução da requisição e aceita um MediaType JSON.
		// O valor do ID (idInexistente) é passado como um parâmetro na URL.
		mockMvc.perform(get("/products/{id}" , idInexistente )
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("testa se retirna 404 condo o id d atualização for passado errado")
	public void testaSeRetornaCodigo404quandotentarAtualizar() throws Exception {
		String body = objectMapper.writeValueAsString(CriaProduct.convertParaRequest(entity));
		
		mockMvc.perform(put("/products/{id}" , idInexistente )
				.content(body)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("testa se retirna 200 condo o id d atualização for passado serto")
	public void testaSeRetornaCodigoOKquandotentarAtualizar() throws Exception {
		String body = objectMapper.writeValueAsString(CriaProduct.convertParaRequest(entity));
		
		mockMvc.perform(put("/products/{id}" , idExistente )
				.content(body)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
