package com.example.DSCatalog.domain.dto.referencias;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRef {

	@NotNull
	private Long id;
}
