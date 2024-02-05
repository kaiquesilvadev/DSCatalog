package com.example.DSCatalog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.example.DSCatalog.domain.dto.conversor.ProductConversor;
import com.example.DSCatalog.domain.dto.request.ProductRequest;
import com.example.DSCatalog.domain.dto.responce.ProductMinResponce;
import com.example.DSCatalog.domain.dto.responce.ProductResponce;
import com.example.DSCatalog.domain.entities.Product;
import com.example.DSCatalog.domain.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@Autowired
	private ProductConversor conversor;

	@GetMapping
	public Page<ProductMinResponce> lista(Pageable pageable) {
		Page<Product> page = service.lista(pageable);
		List<ProductMinResponce> pageDto = conversor.converteLista(page.getContent());
		return new PageImpl<>(pageDto, pageable, page.getTotalElements());
	}

	@GetMapping("/{id}")
	public ProductResponce buscaPorId(@PathVariable Long id) {
		return conversor.converteEntidade(service.buscaPorId(id));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ProductResponce salva(@Valid @RequestBody ProductRequest dto) {
		return conversor.converteEntidade(service.salvar(dto));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutExchange("/{id}")
	public ProductResponce atualizar(@PathVariable Long id, @Valid @RequestBody ProductRequest dto) {
		return conversor.converteEntidade(service.atualiza(id, dto));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleta(id);
	}
}
