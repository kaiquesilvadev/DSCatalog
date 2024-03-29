package com.example.DSCatalog.domain.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.DSCatalog.domain.entities.Product;
import com.example.DSCatalog.domain.exception.EntidadeEmUsoException;
import com.example.DSCatalog.domain.exception.ProductNaoEncontradoException;
import com.example.DSCatalog.domain.repositories.ProductRepository;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductService service;
	
	@Mock
	private  ProductRepository repository;
	private  Long idExistente;
	private Long idInexistente;
	private Long idAssociado;
	
	@BeforeEach
	private void setUp() throws Exception{
		idExistente = 2L;
		idInexistente = 100L;
		idAssociado = 3L;
		// Configuração do mock para o método findById
		// Quando o método findById do mock repository for chamado com o argumento idExistente,
		// então retorne um Optional contendo um objeto Product simulado.
		when(repository.findById(idExistente)).thenReturn(Optional.of( new Product()));
		when(repository.findById(idAssociado)).thenReturn(Optional.of( new Product()));
		
		// Configuração do mock para o método deleteById
		// Quando o método deleteById do mock repository for chamado com o argumento idExistente,
		// não faça nada (doNothing), pois é um método void (não retorna valor).
		doNothing().when(repository).deleteById(idAssociado);
		
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(idAssociado);

		when(repository.findById(idInexistente)).thenThrow(new ProductNaoEncontradoException(idInexistente));
	}
	
	@Test
	@DisplayName("testa se o id existente é deletado")
	public void testaSeEstaApagandoIdExistente() {
		assertDoesNotThrow(() -> {
			service.deleta(idExistente);
		});
		
		// Verifica se o método deleteById foi chamado exatamente 1 vez com o argumento idExistente.
		Mockito.verify(repository ,Mockito.times(1)).deleteById(idExistente);
		
		// Verifica se o método findById foi chamado exatamente 1 vez com o argumento idExistente.
		Mockito.verify(repository ,Mockito.times(1)).findById(idExistente);
	}
	
	@Test
	@DisplayName("testa se retorna uma exception se passar um id inexistente em delete")
	public void testaSeRetornaUmaExceptionQuandoPassadoUmIdInexistente() {

		assertThrows(ProductNaoEncontradoException.class, () -> {
			service.deleta(idInexistente);
		});
	}
	
	@Test
	@DisplayName("testa se retorna uma exception se passar um id que esteja associado a outra tabela")
	public void testaSeGeraUmaExceptionQuandoOIdEstiverAssociadoAOutraTabela() {

		assertThrows(EntidadeEmUsoException.class, () -> {
			service.deleta(idAssociado);
		});
	}
}
