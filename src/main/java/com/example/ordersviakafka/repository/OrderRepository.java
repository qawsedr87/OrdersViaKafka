package com.example.ordersviakafka.repository;


import com.example.ordersviakafka.entity.Delivery;
import com.example.ordersviakafka.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}