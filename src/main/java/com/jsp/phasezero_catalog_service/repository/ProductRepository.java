package com.jsp.phasezero_catalog_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.phasezero_catalog_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{


	boolean existsByPartNumber(String partNumber);

	List<Product> findByPartName(String partName);

	List<Product> findByCategoryIgnoreCase(String category);

	
	

}
