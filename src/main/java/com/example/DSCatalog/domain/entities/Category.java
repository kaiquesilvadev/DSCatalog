package com.example.DSCatalog.domain.entities;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.example.DSCatalog.domain.dto.referencias.CategoryRef;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_category")
public class Category {

	

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@CreationTimestamp
	private OffsetDateTime createdAt;
	
	public Category(CategoryRef categoryId) {
		this.id = categoryId.getId();
	}
}
