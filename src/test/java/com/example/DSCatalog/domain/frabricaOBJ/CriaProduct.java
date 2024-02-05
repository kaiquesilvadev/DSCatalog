package com.example.DSCatalog.domain.frabricaOBJ;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.DSCatalog.domain.dto.referencias.CategoryRef;
import com.example.DSCatalog.domain.dto.request.ProductRequest;
import com.example.DSCatalog.domain.dto.responce.ProductMinResponce;
import com.example.DSCatalog.domain.dto.responce.ProductResponce;
import com.example.DSCatalog.domain.entities.Category;
import com.example.DSCatalog.domain.entities.Product;

public class CriaProduct {

	public static ProductRequest ObtParaAtualizar() {

		CategoryRef ref = new CategoryRef();
		ref.setId(1L);

		ProductRequest productRequest = new ProductRequest();
		productRequest.setName("Smart TV");
		productRequest.setDescription("Descrição da Smart TV");
		productRequest.setPrice(2190.0);
		productRequest.setImgUrl("https://caminho/da/imagem");
		productRequest.getCategories().add(ref);

		return productRequest;
	}

	public static Product testeSalva() {

		Product product = new Product(null, "PC Gamer asus", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
				300.50,
				"https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/7-big.jpg",
				OffsetDateTime.now(), new ArrayList<>());

		product.getCategories().add(new Category(2L, "Eletrônicos", OffsetDateTime.now()));

		return product;
	}

	public static Product objCriado() {

		Product product = new Product(1L, "PC Gamer asus", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
				300.50,
				"https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/7-big.jpg",
				OffsetDateTime.now(), new ArrayList<>());

		product.getCategories().add(new Category(2L, "Eletrônicos", OffsetDateTime.now()));

		return product;
	}

	public static ProductResponce convertProduct(Product product) {
		ProductResponce responce = new ProductResponce();

		responce.setId(1L);
		responce.setName(product.getName());
		responce.setDescription(product.getDescription());
		responce.setImgUrl(product.getImgUrl());
		responce.setPrice(product.getPrice());
		product.getCategories().forEach(cat -> {
			responce.getCategories().add(CriaCategory.convertCategory(cat));
		});

		return responce;
	}
	
	public static ProductMinResponce convertProductMin(Product product) {
		ProductMinResponce responce = new ProductMinResponce();

		responce.setId(1L);
		responce.setName(product.getName());
		responce.setImgUrl(product.getImgUrl());
		responce.setPrice(product.getPrice());

		return responce;
	}

	public static ProductRequest convertParaRequest(Product product) {
		ProductRequest request = new ProductRequest();

		request.setName(product.getName());
		request.setDescription(product.getDescription());
		request.setImgUrl(product.getImgUrl());
		request.setPrice(product.getPrice());
		product.getCategories().forEach(cat -> {
			request.getCategories().add(CriaCategory.convertCatParaRequest(cat));
		});

		return request;
	}

	public static List<ProductMinResponce> convertLista(List<Product> list) {
		return list.stream().map(P -> convertProductMin(P)).toList();
	}
}
