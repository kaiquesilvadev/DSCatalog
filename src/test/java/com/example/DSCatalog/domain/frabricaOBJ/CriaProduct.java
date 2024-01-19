package com.example.DSCatalog.domain.frabricaOBJ;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.example.DSCatalog.domain.entities.Category;
import com.example.DSCatalog.domain.entities.Product;

public class CriaProduct {

	public static Product objCriado() {
		
		Product product = new Product(null, "PC Gamer asus" ,
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit," , 
				300.50 ,
				"https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/7-big.jpg" , 
				OffsetDateTime.now() 
				, new ArrayList<>());
		
		product.getCategories().add(new Category( 2L , "Eletrônicos" , OffsetDateTime.now()));
		
		return product;
	}
}
