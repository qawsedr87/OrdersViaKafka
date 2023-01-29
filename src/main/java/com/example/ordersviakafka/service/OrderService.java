package com.example.ordersviakafka.service;

import com.example.ordersviakafka.entity.Delivery;
import com.example.ordersviakafka.entity.DeliveryStatus;
import com.example.ordersviakafka.entity.Order;
import com.example.ordersviakafka.kafka.producers.OrderProducer;
import com.example.ordersviakafka.model.OrderModelMessage;
import com.example.ordersviakafka.repository.DeliveryRepository;
import com.example.ordersviakafka.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderService {
    private OrderProducer orderProducer;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    public OrderService(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    public void sendMessage(OrderModelMessage message) {
        log.info("[OrderService] send order to 'order' topic");
        orderProducer.send(message);
    }

    public Order createOrderAndDefaultDeliveryStatus(Order order) {
        // order
        Order savedOrder = orderRepository.save(order);

        // delivery
        Delivery defaultDelivery = new Delivery(savedOrder, DeliveryStatus.TODO);
        deliveryRepository.save(defaultDelivery);

        return savedOrder;
    }
}