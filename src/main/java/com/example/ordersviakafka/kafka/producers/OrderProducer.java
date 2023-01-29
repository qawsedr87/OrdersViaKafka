package com.example.ordersviakafka.kafka.producers;

import com.example.ordersviakafka.model.OrderModelMessage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@NoArgsConstructor
@Component
@Configuration
public class OrderProducer {
    @Value(value = "${spring.kafka.topic}")
    private String orderTopic;
    private KafkaTemplate<String, Serializable> kafkaTemplate;

    @Autowired
    public OrderProducer(KafkaTemplate<String, Serializable> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(OrderModelMessage message) {
        CompletableFuture<SendResult<String, Serializable>> future = kafkaTemplate.send(orderTopic, message);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message sent successfully with offset = {}", result.getRecordMetadata().offset());
            }
            else {
                log.error("Unable to send message = {} dut to: {}", message.toString(), ex.getMessage());
            }
        });
    }

}
