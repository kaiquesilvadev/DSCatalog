package com.example.DSCatalog.domain.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.example.DSCatalog.domain.dto.referencias.CategoryRef;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
	private String imgUrl;
	
	@NotNull
	@Valid
	@Size(min = 1)
	private List<CategoryRef> categories = new ArrayList<>();
}
