package com.example.DSCatalog.domain.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.example.DSCatalog.api.exceptionHandler.ApiErro.Field;
import com.example.DSCatalog.domain.dto.request.UserRequest;
import com.example.DSCatalog.domain.entities.User;
import com.example.DSCatalog.domain.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserRequest> {

	@Autowired
	private UserRepository repository;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserRequest dto, ConstraintValidatorContext context) {

	
		var url = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long userId = null;
		String id = url.get("id");
		
		
		if (id != null && id != "") {
			userId = Long.parseLong(id);
		}

		List<Field> list = new ArrayList<>();
		User user = repository.findByEmail(dto.getEmail());

		if (user != null) {
			list.add(Field.builder().nome("email").Message("email ja existente").build());
			if (user.getId().equals(userId)) {
				list.clear();
			}
		}

		for (Field e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNome())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}