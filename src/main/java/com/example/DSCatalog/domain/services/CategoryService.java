package com.example.DSCatalog.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.DSCatalog.domain.dto.conversor.CategoryConversor;
import com.example.DSCatalog.domain.dto.request.CategoryRequest;
import com.example.DSCatalog.domain.entities.Category;
import com.example.DSCatalog.domain.exception.CategoryNaoEncontradaException;
import com.example.DSCatalog.domain.exception.EntidadeEmUsoException;
import com.example.DSCatalog.domain.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Autowired
	private CategoryConversor conversor;

	@Transactional(readOnly = true)
	public Page<Category> lista(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Category buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new CategoryNaoEncontradaException(id));
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Category salvar(CategoryRequest dto) {

		Category entidade = conversor.converteDto(dto);
		return repository.save(entidade);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public Category atualiza(Long id, CategoryRequest dto) {

		Category entidade = buscaPorId(id);
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
	
	public void testaSeIdENulo() {
		
		
		
		repository.deleteById(null);
	}

}
