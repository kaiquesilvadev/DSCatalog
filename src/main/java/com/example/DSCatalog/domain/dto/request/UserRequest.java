package com.example.DSCatalog.domain.dto.request;

import com.example.DSCatalog.domain.validacao.UserInsertValid;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@UserInsertValid
public class UserRequest {

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@Email
	private String email;

	@NotBlank
	@Size(min = 8, message = "A senha deve conter pelo menos 8 caracteres para garantir segurança.")
	private String password;
}
