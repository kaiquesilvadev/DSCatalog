package com.example.DSCatalog.domain.dto.responce;

import java.util.ArrayList;
import java.util.List;

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
	private List<CategoryResponce> categories = new ArrayList<>();
}
