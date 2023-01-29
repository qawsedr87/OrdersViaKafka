package com.example.ordersviakafka.repository;


import com.example.ordersviakafka.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findByOrderId(Integer orderId);
}