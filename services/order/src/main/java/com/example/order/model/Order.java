package com.example.order.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private List<Long> productIds;
    private ProductService productService;
    
    public Order() {
        this.productIds = new ArrayList<>();
    }
    
    public Order(Long id, List<Long> productIds, int quantity) {
        this.id = id;
        this.productIds = productIds;
        
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public List<Object> getProducts() {
        if (this.productIds == null || this.productIds.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Object> products = new ArrayList<>();
        for (Long productId : this.productIds) {
            Object product = productService.getProductById(productId);
            if (product != null) {
                products.add(product);
            }
        }
        
        return products;
    }

    public void addProductId(Long productId) {
        if (this.productIds == null) {
            this.productIds = new ArrayList<>();
        }
        this.productIds.add(productId);
    }
    
    public void removeProductId(Long productId) {
        if (this.productIds != null) {
            this.productIds.remove(productId);
        }
    }
    
}