package com.example.DSCatalog.domain.frabricaOBJ;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.DSCatalog.domain.dto.responce.ProductResponce;
import com.example.DSCatalog.domain.entities.Category;
import com.example.DSCatalog.domain.entities.Product;

public class CriaProduct {

	public static Product objCriado() {

		Product product = new Product(1L , "PC Gamer asus", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
				300.50,
				"https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/7-big.jpg",
				OffsetDateTime.now(), new ArrayList<>());

		product.getCategories().add(new Category(2L, "Eletrônicos", OffsetDateTime.now()));

		return product;
	}

	public static ProductResponce convertProduc(Product product) {
		ProductResponce responce = new ProductResponce();

		responce.setId(product.getId());
		responce.setName(product.getName());
		responce.setDescription(product.getDescription());
		responce.setImgUrl(product.getImgUrl());
		responce.setPrice(product.getPrice());
		product.getCategories().forEach(cat -> {
			responce.getCategories().add(CriaCategory.convertCategory(cat));
		});

		return responce;
	}

	public static List<ProductResponce> convertLista(List<Product> list) {
		return list.stream().map(P -> convertProduc(P)).toList();
	}
}
