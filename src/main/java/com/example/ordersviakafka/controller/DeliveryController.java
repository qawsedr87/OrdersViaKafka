package com.example.ordersviakafka.controller;

import com.example.ordersviakafka.entity.Delivery;
import com.example.ordersviakafka.exception.ResourceNotFoundException;
import com.example.ordersviakafka.repository.DeliveryRepository;
import com.example.ordersviakafka.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class DeliveryController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @GetMapping("/orders/{orderId}/deliveries")
    public List<Delivery> getOrdersByDeliveryId(@PathVariable Integer orderId) {
        return deliveryRepository.findByOrderId(orderId);
    }


    @PostMapping("/orders/{orderId}/deliveries")
    public Delivery addDeliveries(@PathVariable Integer orderId,
                            @Valid @RequestBody Delivery delivery) {
        return orderRepository.findById(orderId)
                .map(order -> {
                    delivery.setOrder(order);
                    return deliveryRepository.save(delivery);
                }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
    }

    @DeleteMapping("/orders/{orderId}/deliveries/{deliveryId}")
    public ResponseEntity<?> deleteDelivery(@PathVariable Integer orderId,
                                          @PathVariable Integer deliveryId) {
        if(!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("Order not found with id " + orderId);
        }

        deliveryRepository.findById(deliveryId)
                .map(delivery -> {
                    deliveryRepository.delete(delivery);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Delivery not found with id " + deliveryId));

        return new ResponseEntity<>(
                "Delete Delivery by Id = " + deliveryId + " of Order Id is = " + orderId,
                HttpStatus.OK);

    }
}