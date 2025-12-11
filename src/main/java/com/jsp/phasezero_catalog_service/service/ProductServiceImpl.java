package com.jsp.phasezero_catalog_service.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;


import com.jsp.phasezero_catalog_service.entity.Product;
import com.jsp.phasezero_catalog_service.exception.NoDataFoundException;
import com.jsp.phasezero_catalog_service.exception.ResourceConflictException;
import com.jsp.phasezero_catalog_service.repository.ProductRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@ControllerAdvice
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;

	@Override
	public Map<String, Object> saveProduct(Product product) throws BadRequestException  {
		
		if(productRepository.existsByPartNumber(product.getPartNumber())) {
			throw new ResourceConflictException(product.getPartNumber()+"alreadyexists");
		}
		if(product.getPrice()<0) {
			throw new BadRequestException("prize should be greater than zero"+product.getPrice());
			
		}if(product.getStock()<0) {
			throw new BadRequestException("stock value should be greater than zero");
			
		}
		String normalizedPartName = product.getPartName();
        if (normalizedPartName == null || !StringUtils.hasText(normalizedPartName)) {
            throw new BadRequestException("partName is required");
        }
        String normalizedPartNumber = product.getPartNumber();
        if (normalizedPartNumber == null || !StringUtils.hasText(normalizedPartNumber)) {
            throw new BadRequestException("partNumber is required");
        }
        String normalizedCategory = product.getCategory();
        if (normalizedCategory == null || !StringUtils.hasText(normalizedCategory)) {
            throw new BadRequestException("category is required");
        }
        normalizedPartName = normalizedPartName.trim().toLowerCase(Locale.ROOT);
		double value=(product.getPrice()*product.getStock());
		product.setValue(value);
		String partName=product.getPartName().toLowerCase();
		product.setPartName(partName);
		Product saved=productRepository.save(product);
		Map<String, Object> map=new LinkedHashMap<>();
		map.put("message", "data saved sucess");
		map.put("data", saved);
		return map;
	}

	@Override
	public Map<String, Object> fetchAll() {
		List<Product> list=productRepository.findAll();
		if(list.isEmpty()) {
			throw new NoDataFoundException("No data present");
		}else {
			Map<String, Object> map=new LinkedHashMap<>();
			map.put("message", "data Found");
			map.put("data", list);
			return map;
		}
		
		
	}

	@Override
	public Map<String, Object> fetchByPartName(String partName) {
		
		List<Product> list=productRepository.findByPartName(partName);
		if(list.isEmpty()) {
			throw new NoDataFoundException("no records found");
		}else {
			Map<String, Object> map=new LinkedHashMap<>();
			map.put("message", "data Found");
			map.put("data", list);
			return map;
		}
	}

	  @Override
	    public Map<String, Object> filterByCategory(String category) {

	        List<Product> list = productRepository.findByCategoryIgnoreCase(category);

	        if (list.isEmpty()) {
	            throw new NoDataFoundException("No products found in category: " + category);
	        }else {

	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("message", "Category results found");
	        map.put("data", list);

	        return map;
	        }
	  }
	  @Override
	    public Map<String, Object> sortByPrice() {

	        List<Product> list = productRepository.findAll();

	        if (list.isEmpty()) {
	            throw new NoDataFoundException("No products to sort");
	        }

	        // Sorting using normal Java Comparator
	        Collections.sort(list, new Comparator<Product>() {
	            @Override
	            public int compare(Product p1, Product p2) {
	                return Double.compare(p1.getPrice(), p2.getPrice());
	            }
	        });

	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("message", "Products sorted by price");
	        map.put("data", list);

	        return map;
	    }

	  @Override
	  public void deleteById(Integer id) {
		
		  productRepository.deleteById(id);
		  
	  }
	  
	  @Override
	    public Map<String, Object> inventoryValue() {

	        List<Product> list = productRepository.findAll();

	        if (list.isEmpty()) {
	            throw new NoDataFoundException("No inventory data available");
	        }

	        double total = 0;

	        // simple for-loop calculation
	        for (Product p : list) {
	            total += p.getPrice() * p.getStock();
	        }

	        Map<String, Object> map = new LinkedHashMap<>();
	        map.put("message", "Total inventory value calculated");
	        map.put("totalInventoryValue", total);

	        return map;
	    }

	  	  
}
