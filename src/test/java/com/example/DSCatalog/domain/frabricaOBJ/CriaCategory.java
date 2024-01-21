package com.example.DSCatalog.domain.frabricaOBJ;

import com.example.DSCatalog.domain.dto.responce.CategoryResponce;
import com.example.DSCatalog.domain.entities.Category;

public class CriaCategory {

	public static CategoryResponce convertCategory(Category category) {
		CategoryResponce responce = new CategoryResponce();

		responce.setId(category.getId());
		responce.setName(category.getName());

		return responce;
	}
}
