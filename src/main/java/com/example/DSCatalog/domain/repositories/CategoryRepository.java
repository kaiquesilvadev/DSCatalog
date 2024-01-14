package com.example.DSCatalog.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DSCatalog.domain.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
