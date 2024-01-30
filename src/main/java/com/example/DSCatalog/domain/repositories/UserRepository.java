package com.example.DSCatalog.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DSCatalog.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email);
}
