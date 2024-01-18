package com.example.DSCatalog.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DSCatalog.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT P FROM Product P JOIN FETCH P.categories")
	List<Product> findAll();
	
	@Query("SELECT P FROM Product P JOIN FETCH P.categories WHERE P.id = :id")
    Optional<Product> findById(Long id);
}
