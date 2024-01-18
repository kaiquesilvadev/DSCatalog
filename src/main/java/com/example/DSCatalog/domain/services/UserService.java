package com.example.DSCatalog.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.DSCatalog.domain.dto.conversor.UserConversor;
import com.example.DSCatalog.domain.dto.request.UserRequest;
import com.example.DSCatalog.domain.entities.User;
import com.example.DSCatalog.domain.exception.UserNaoEncontradoException;
import com.example.DSCatalog.domain.exception.EntidadeEmUsoException;
import com.example.DSCatalog.domain.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserConversor conversor;

	@Transactional(readOnly = true)
	public List<User> lista() {
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public User buscaPorId(Long id) {
		return repository.findById(id).orElseThrow(() -> new UserNaoEncontradoException(id));
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public User salvar(UserRequest dto) {

		User entidade = conversor.converteDto(dto);
		return repository.save(entidade);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public User atualiza(Long id, UserRequest dto) {

		User entidade = buscaPorId(id);
		conversor.copia(dto, entidade);
		return repository.save(entidade);	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void deleta(Long id) {

		try {
			buscaPorId(id);
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(id);
		}
	}

}
