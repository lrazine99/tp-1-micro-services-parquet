package com.example.order.model;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    
    Order findById(Long id);
    
    Order save(Order order);
}