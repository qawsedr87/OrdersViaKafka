package com.example.ordersviakafka.kafka.producers;

import com.example.ordersviakafka.kafka.entity.ProductMessage;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Slf4j
@NoArgsConstructor
@Component
public class ProductProducer {
//    final String productTopic = "product";
//
//    private KafkaTemplate<String, Serializable> kafkaTemplate;
//
//    @Autowired
//    public ProductProducer(KafkaTemplate<String, Serializable> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void send(ProductMessage message) {
//        CompletableFuture<SendResult<String, Serializable>> future = kafkaTemplate.send(productTopic, message);
//
//        // Throwable ex
//        // SendResult<String, Serializable> result
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                log.info("Message sent successfully with offset = {}", result.getRecordMetadata().offset());
//            }
//            else {
//                log.error("Unable to send message = {} dut to: {}", message.toString(), ex.getMessage());
//            }
//        });
//
////        future.addCallback(new ListenableFutureCallback<SendResult<String, Serializable>>() {
////            @Override
////            public void onFailure(Throwable ex) {
////                log.error("Unable to send message = {} dut to: {}", message.toString(), ex.getMessage());
////            }
////
////            @Override
////            public void onSuccess(SendResult<String, Serializable> result) {
////                log.info("Message sent successfully with offset = {}", result.getRecordMetadata().offset());
////            }
////        });
//    }

}
