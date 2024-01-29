package com.example.DSCatalog.domain.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.DSCatalog.api.exceptionHandler.ApiErro.Field;
import com.example.DSCatalog.domain.dto.request.UserRequest;
import com.example.DSCatalog.domain.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class UserInsertValidator implements  ConstraintValidator<UserInsertValid, UserRequest> {
	
	@Autowired
	private UserRepository repository;
	
	
	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserRequest dto, ConstraintValidatorContext context) {
		
		List<Field> list = new ArrayList<>();
		
		// Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista
		if(repository.existsByEmail(dto.getEmail()))
			list.add( Field.builder()
					.nome("email")
					.Message("email ja existente")
					.build());
		
		for (Field e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNome())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}