package com.example.DSCatalog.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DSCatalog.domain.entities.Role;
import com.example.DSCatalog.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
	
	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email =:email")
	User validaEmail(String email);
	
	@Query("SELECT r FROM Role r WHERE r.authority = :role")
	Role buscaRole(String role);
}
