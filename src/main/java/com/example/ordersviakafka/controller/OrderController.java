package com.example.ordersviakafka.controller;

import com.example.ordersviakafka.entity.Delivery;
import com.example.ordersviakafka.entity.Order;
import com.example.ordersviakafka.exception.ResourceNotFoundException;
import com.example.ordersviakafka.repository.DeliveryRepository;
import com.example.ordersviakafka.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }


    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderRepository.save(order);
    }


    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        return orderRepository.findById(orderId)
                .map(delivery -> {
                    orderRepository.delete(delivery);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Delivery not found with id " + orderId));
    }
}