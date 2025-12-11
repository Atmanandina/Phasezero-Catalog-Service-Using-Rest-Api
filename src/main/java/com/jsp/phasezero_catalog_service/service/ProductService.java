package com.jsp.phasezero_catalog_service.service;

import java.util.Map;

import org.apache.coyote.BadRequestException;

import com.jsp.phasezero_catalog_service.entity.Product;


public interface ProductService {

	public Map<String, Object> saveProduct(Product product) throws BadRequestException;

	public Map<String, Object> fetchAll();

	public Map<String, Object> fetchByPartName(String partName);

	public Map<String, Object> filterByCategory(String category);

	public Map<String, Object> sortByPrice();

	public void deleteById(Integer id);

	Map<String, Object> inventoryValue();

	

	

}
