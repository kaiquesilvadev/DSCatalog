package com.example.DSCatalog.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.DSCatalog.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("SELECT P FROM Product P JOIN FETCH P.categories")
	List<Product> findAll();
	
	@Query("SELECT P FROM Product P JOIN FETCH P.categories WHERE P.id = :id")
    Optional<Product> findById(Long id);
	
	// Esta consulta representa a mesma lógica da consulta "listaProduct", porém está escrita em SQL nativo. para fins didaticos
	
	/*
	@Query(nativeQuery = true, value = """
			SELECT DISTINCT tb_product.id, tb_product.name
			FROM tb_product
			INNER JOIN tb_product_category ON tb_product_category.product_id = tb_product.id
			WHERE (:categoryIds IS NULL OR tb_product_category.category_id IN :categoryIds)
			AND (LOWER(tb_product.name) LIKE LOWER(CONCAT('%',:name,'%')))
			ORDER BY tb_product.name
			""",
			countQuery = """
			SELECT COUNT(*) FROM (
			SELECT DISTINCT tb_product.id, tb_product.name
			FROM tb_product
			INNER JOIN tb_product_category ON tb_product_category.product_id = tb_product.id
			WHERE (:categoryIds IS NULL OR tb_product_category.category_id IN :categoryIds)
			AND (LOWER(tb_product.name) LIKE LOWER(CONCAT('%',:name,'%')))
			) AS tb_result
			""")
		Page<ProductProjection> searchProducts(List<Long> categoryIds, String name, Pageable pageable);
*/
	    @Query("SELECT DISTINCT p FROM Product p " +
		       "INNER JOIN p.categories c " +
		       "WHERE (:categoryIds IS NULL OR c.id IN :categoryIds) " +
		       "AND LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
		       "ORDER BY p.name")
		Page<Product> listaProuct(String name, List<Long> categoryIds, Pageable pageable);


		@Query("SELECT obj FROM Product obj JOIN FETCH obj.categories "
				+ "WHERE obj.id IN :productIds ORDER BY obj.name")
		List<Product> searchProductsWithCategories(List<Long> productIds);

}
