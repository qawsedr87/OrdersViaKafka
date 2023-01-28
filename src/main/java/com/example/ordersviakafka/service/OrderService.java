package com.example.ordersviakafka.service;

import com.example.ordersviakafka.kafka.producers.OrderProducer;
import com.example.ordersviakafka.model.OrderModelMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderService {
    private OrderProducer orderProducer;

    @Autowired
    public OrderService(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    public void sendMessage(OrderModelMessage message) {
        log.info("[OrderService] send order to 'order' topic");
        orderProducer.send(message);
    }
}