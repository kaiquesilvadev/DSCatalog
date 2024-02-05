package com.example.DSCatalog.domain.dto.responce;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductMinResponce {

	private Long id;
	private String name;
	private Double price;
	private String imgUrl;
}
