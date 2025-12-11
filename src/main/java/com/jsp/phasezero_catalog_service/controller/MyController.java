package com.jsp.phasezero_catalog_service.controller;

import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.phasezero_catalog_service.entity.Product;
import com.jsp.phasezero_catalog_service.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/products")
public class MyController {
	
	private final ProductService productservice;
	
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Map<String, Object> saveProduct(@RequestBody  Product product) throws BadRequestException{
	return productservice.saveProduct(product);
}

@GetMapping
@ResponseStatus(HttpStatus.OK)
public Map<String, Object> fetchProducts() {
	return productservice.fetchAll();
}
@GetMapping("/partName/{partName}")
public Map<String, Object> findByPartName(@PathVariable String partName){
	
	return productservice.fetchByPartName(partName);
}
@GetMapping("{category}")
public Map<String, Object> findByCategory(@PathVariable String category){
	
	return productservice.filterByCategory(category);
}

@GetMapping("/sortprice")
public Map<String, Object> sortByPrice(){
	
	return productservice.sortByPrice();
}
@DeleteMapping("/delete/{id}")
@ResponseStatus(value=HttpStatus.NO_CONTENT)
public void deleteById(@PathVariable Integer id){
	
  productservice.deleteById(id);
}
@GetMapping("/inventory/value")
public Map<String, Object> inventenaryValue(){
	
	return productservice.inventoryValue();
}

}
