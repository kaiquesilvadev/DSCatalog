package com.example.DSCatalog.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.DSCatalog.domain.dto.request.EmailRequest;
import com.example.DSCatalog.domain.dto.request.NovaSenhaRequest;
import com.example.DSCatalog.domain.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class EmailAuthController {
	
	@Autowired
	private AuthService service;

	@PostMapping("/recover-token")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void criaToken(@Valid @RequestBody EmailRequest emailRequest) {
		service.criaToken(emailRequest);
	}
	
	@PostMapping("/new-password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void salvaNovaSenha(@Valid @RequestBody NovaSenhaRequest novaSenhaRequest) {
		service.salvaNovaSenha(novaSenhaRequest);
	}
}
