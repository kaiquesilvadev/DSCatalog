package com.example.DSCatalog.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.DSCatalog.domain.dto.conversor.ProductConversor;
import com.example.DSCatalog.domain.dto.request.ProductRequest;
import com.example.DSCatalog.domain.entities.Product;
import com.example.DSCatalog.domain.exception.EntidadeEmUsoException;
import com.example.DSCatalog.domain.exception.ProductNaoEncontradoException;
import com.example.DSCatalog.domain.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ProductConversor conversor;

	@Transactional(readOnly = true)
	public List<Product> lista() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Product buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new ProductNaoEncontradoException(id));
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Product salvar(ProductRequest dto) {

		Product entidade = conversor.converteDto(dto);
		return repository.save(entidade);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Product atualiza(Long id, ProductRequest dto) {

		Product entidade = buscaPorId(id);
		conversor.copia(dto, entidade);
		return entidade;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleta(Long id) {

		try {
			buscaPorId(id);
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(id);
		}
	}

}