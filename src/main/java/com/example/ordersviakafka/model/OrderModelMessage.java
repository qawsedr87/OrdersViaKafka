package com.example.ordersviakafka.model;

import com.example.ordersviakafka.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OrderModelMessage extends Order implements Serializable {
    String action;

    public OrderModelMessage() {

    }

    public OrderModelMessage(Order order, String action) {
        this.setId(order.getId());
        this.setTotalPrice(order.getTotalPrice());
        this.setMemo(order.getMemo());
        this.setUpdatedAt(order.getUpdatedAt());
        this.setCreatedAt(order.getCreatedAt());
        this.action = action;
    }
}
