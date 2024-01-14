package com.example.DSCatalog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.DSCatalog.domain.dto.conversor.CategoryConversor;
import com.example.DSCatalog.domain.dto.request.CategoryRequest;
import com.example.DSCatalog.domain.dto.responce.CategoryResponce;
import com.example.DSCatalog.domain.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@Autowired
	private CategoryConversor conversor;

	@GetMapping
	public List<CategoryResponce> lista() {
		return conversor.converteLista(service.lista());
	}

	@GetMapping("/{id}")
	public CategoryResponce buscaPorId(@PathVariable Long id) {
		return conversor.converteEntidade(service.buscaPorId(id));
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CategoryResponce salva(@Valid @RequestBody CategoryRequest dto) {
		return conversor.converteEntidade(service.salvar(dto));
	}

	@PutExchange("/{id}")
	public CategoryResponce atualizar(@PathVariable Long id, @Valid @RequestBody CategoryRequest dto) {
		return conversor.converteEntidade(service.atualiza(id, dto));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleta(id);
	}
}
