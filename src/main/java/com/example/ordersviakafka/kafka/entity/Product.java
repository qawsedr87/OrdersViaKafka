package com.example.ordersviakafka.kafka.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product {
    private String id;
    private String name;
    private Double price;

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"name\":\"" + name + '\"' +
                ", \"price\":\"" + price + '\"' +
                "}";
    }
}
