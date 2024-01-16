package com.example.DSCatalog.domain.dto.conversor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DSCatalog.domain.dto.request.ProductRequest;
import com.example.DSCatalog.domain.dto.responce.ProductResponce;
import com.example.DSCatalog.domain.entities.Product;

@Component
public class ProductConversor {
	
	@Autowired
	private ModelMapper mapper;

	public Product converteDto(ProductRequest dto) {
		return mapper.map(dto, Product.class);
	}
	
	public ProductResponce converteEntidade(Product entidade) {
		return mapper.map(entidade , ProductResponce.class);
	}

	public void copia(ProductRequest dto, Product entidade) {
		mapper.map(dto, entidade);
	}
	
	public List<ProductResponce> converteLista(List<Product> lista) {
		return lista.stream().map(cat -> converteEntidade(cat)).toList();
	}
}
