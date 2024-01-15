package com.example.DSCatalog.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DSCatalog.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
