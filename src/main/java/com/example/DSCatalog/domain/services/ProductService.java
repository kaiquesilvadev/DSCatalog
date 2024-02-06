package com.example.DSCatalog.domain.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Product> lista(Pageable pageable, String categoryIds, String name) {
		List<Long> categoriesId = new ArrayList<>();

		if (!"0".equals(categoryIds))
			categoriesId = Arrays.asList(categoryIds.split(",")).stream().map(Long::parseLong).toList();
		System.out.println(categoriesId);
		
		return repository.listaProuct(name , categoriesId, pageable);
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

	@Transactional(propagation = Propagation.REQUIRED)
	public Product atualiza(Long id, ProductRequest dto) {

		Product entidade = buscaPorId(id);
		entidade.getCategories();
		conversor.copia(dto, entidade);
		return repository.save(entidade);
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
