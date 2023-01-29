package com.example.ordersviakafka.kafka.listeners;

import com.example.ordersviakafka.model.OrderModelMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Configuration
public class OrderListener {
    @KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void newOrderListener(OrderModelMessage order) {
        log.info("Get request from Front 'save order' " + order.toString());
    }
}
