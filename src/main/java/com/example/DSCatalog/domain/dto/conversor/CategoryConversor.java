package com.example.DSCatalog.domain.dto.conversor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DSCatalog.domain.dto.request.CategoryRequest;
import com.example.DSCatalog.domain.dto.responce.CategoryResponce;
import com.example.DSCatalog.domain.entities.Category;

@Component
public class CategoryConversor {

	@Autowired
	private ModelMapper mapper;

	public Category converteDto(CategoryRequest dto) {
		return mapper.map(dto, Category.class);
	}
	
	public CategoryResponce converteEntidade(Category entidade) {
		return mapper.map(entidade , CategoryResponce.class);
	}

	public void copia(CategoryRequest dto, Category entidade) {
		mapper.map(dto, entidade);
	}
	
	public List<CategoryResponce> converteLista(List<Category> lista) {
		return lista.stream().map(cat -> converteEntidade(cat)).toList();
	}
}
