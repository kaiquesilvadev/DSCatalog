package com.example.DSCatalog.domain.dto.conversor;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.DSCatalog.domain.dto.request.UserRequest;
import com.example.DSCatalog.domain.dto.responce.CategoryResponce;
import com.example.DSCatalog.domain.dto.responce.UserResponce;
import com.example.DSCatalog.domain.entities.Category;
import com.example.DSCatalog.domain.entities.User;

@Component
public class UserConversor {

	@Autowired
	private ModelMapper mapper;

	public User converteDto(UserRequest dto) {
		return mapper.map(dto, User.class);
	}
	
	public UserResponce converteEntidade(User entidade) {
		return mapper.map(entidade , UserResponce.class);
	}

	public void copia(UserRequest dto, User entidade) {
		mapper.map(dto, entidade);
	}
	
	public List<UserResponce> converteLista(List<User> lista) {
		return lista.stream().map(cat -> converteEntidade(cat)).toList();
	}
}
