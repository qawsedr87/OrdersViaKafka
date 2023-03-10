package com.example.ordersviakafka.controller;

import com.example.ordersviakafka.entity.Order;
import com.example.ordersviakafka.exception.ResourceNotFoundException;
import com.example.ordersviakafka.model.OrderModelMessage;
import com.example.ordersviakafka.repository.OrderRepository;
import com.example.ordersviakafka.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Service
@RequestMapping("api/v1")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public Page<Order> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }


    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order newOrder) {
        // database
        Order savedOrder = this.orderService.createOrderAndDefaultDeliveryStatus(newOrder);

        // kafka
        log.info("[OrderController]: add new order = " + savedOrder.toString());
        this.orderService.sendMessage(new OrderModelMessage(savedOrder, "add"));

        return savedOrder;
    }


    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer orderId) {
        // kafka
        log.info("[OrderController]: delete order by id = " + orderId);
        Order order = new Order();
        order.setId(orderId);
        this.orderService.sendMessage(new OrderModelMessage(order, "delete"));


        // database
        orderRepository.findById(orderId)
                .map(delivery -> {
                    orderRepository.delete(delivery);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));

        return new ResponseEntity<>(
                "Delete Order by Id = " + orderId,
                HttpStatus.OK);
    }
}