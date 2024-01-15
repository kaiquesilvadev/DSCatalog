package com.example.DSCatalog.domain.dto.responce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponce {

	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
}
