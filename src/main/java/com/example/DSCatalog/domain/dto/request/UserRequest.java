package com.example.DSCatalog.domain.dto.request;

import com.example.DSCatalog.domain.validacao.UserInsertValid;

import jakarta.validation.constraints.NotBlank;
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
	private String email;
	
	@NotBlank
	private String password;
}
