package com.example.DSCatalog.domain.dto.conversor;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DSCatalog.domain.dto.request.ProductRequest;
import com.example.DSCatalog.domain.dto.responce.ProductMinResponce;
import com.example.DSCatalog.domain.dto.responce.ProductResponce;
import com.example.DSCatalog.domain.entities.Category;
import com.example.DSCatalog.domain.entities.Product;

@Component
public class ProductConversor {

	@Autowired
	private ModelMapper mapper;

	public Product converteDto(ProductRequest dto) {
		return mapper.map(dto, Product.class);
	}

	public ProductResponce converteEntidade(Product entidade) {
		return mapper.map(entidade, ProductResponce.class);
	}

	public ProductMinResponce converteParaPage(Product entidade) {
		return mapper.map(entidade, ProductMinResponce.class);
	}

	public void copia(ProductRequest dto, Product entidade) {

		entidade.getCategories().clear();
		entidade.getCategories().addAll(
				dto.getCategories().stream().map(categoryId -> new Category(categoryId)).collect(Collectors.toSet()));

		mapper.map(dto, entidade);
	}

	public List<ProductMinResponce> converteLista(List<Product> lista) {
		return lista.stream().map(cat -> converteParaPage(cat)).toList();
	}
}
