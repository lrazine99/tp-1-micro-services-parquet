package com.example.order.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.example.order.model.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductServiceImpl implements ProductService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private static final String API_BASE_URL = "http://localhost:3000/api/products/";
    
    private final RestTemplate restTemplate;
    
    public ProductServiceImpl() {
        this.restTemplate = new RestTemplate();
    }
    
    @Override
    public Object getProductById(Long id) {
        try {
            String apiUrl = API_BASE_URL + id;
            
            ResponseEntity<Object> response = restTemplate.getForEntity(apiUrl, Object.class);
            
            return response.getBody();
            
        } catch (HttpClientErrorException.NotFound e) {
            logger.warn("Product with ID {} not found", id);
            return null;
        } catch (Exception e) {
            logger.error("Error retrieving product with ID {}: {}", id, e.getMessage());
            return null;
        }
    }
}