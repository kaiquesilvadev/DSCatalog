package com.example.DSCatalog.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailRequest {

	@Email(message = "O email tem que ser válido ")
	@NotBlank(message = "email não pode estar em branco")
	private String email;
}
