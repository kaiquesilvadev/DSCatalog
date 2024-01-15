package com.example.DSCatalog.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {

	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@Positive
	@NotNull
	private Double price;
	
	@NotBlank
	private String imgUrl;
}
