package com.example.DSCatalog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.DSCatalog.domain.dto.conversor.UserConversor;
import com.example.DSCatalog.domain.dto.request.UserRequest;
import com.example.DSCatalog.domain.dto.responce.UserResponce;
import com.example.DSCatalog.domain.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private UserConversor conversor;

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping
	public List<UserResponce> lista() {
		return conversor.converteLista(service.lista());
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public UserResponce buscaPorId(@PathVariable Long id) {
		return conversor.converteEntidade(service.buscaPorId(id));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public UserResponce salva(@Valid @RequestBody UserRequest dto) {
		return conversor.converteEntidade(service.salvar(dto));
	}

	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PutExchange("/{id}")
	public UserResponce atualizar(@PathVariable Long id, @Valid @RequestBody UserRequest dto) {
		return conversor.converteEntidade(service.atualiza(id, dto));
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		service.deleta(id);
	}
}
