package com.example.order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.model.Order;
import com.example.order.model.OrderService;
import com.example.order.model.ProductService;

@Service
public class OrderServiceImpl implements OrderService {

    private final Map<Long, Order> orders = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();
    
    @Autowired
    private ProductService productService;
    
    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order findById(Long id) {
        return orders.get(id);
    }

    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            order.setId(idCounter.incrementAndGet());
        }
        
        order.setProductService(productService);
        
        orders.put(order.getId(), order);
        return order;
    }

}