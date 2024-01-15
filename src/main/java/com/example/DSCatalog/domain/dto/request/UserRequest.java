package com.example.DSCatalog.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

	@NotBlank
	private String FirstName;
	
	@NotBlank
	private String vlastName;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
}