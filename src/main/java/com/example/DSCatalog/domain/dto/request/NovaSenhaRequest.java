package com.example.DSCatalog.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovaSenhaRequest {

	@NotBlank
	private String token;
	
	@NotBlank
	@Size(min = 8, message = "A senha deve conter pelo menos 8 caracteres para garantir segurança.")
	private String password;
}
