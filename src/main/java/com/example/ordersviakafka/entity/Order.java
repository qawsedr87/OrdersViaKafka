package com.example.ordersviakafka.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Setter
@Getter
@Entity
@Table(name = "orders")
public class Order extends DateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="price", columnDefinition="Decimal(10,2) default '0.00'")
    private double totalPrice;

    @Column(columnDefinition = "TEXT")
    private String memo;

    public Order() {}

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + '\"' +
                ", \"total price\":\"" + totalPrice + '\"' +
                ", \"memo\":\"" + memo + '\"' +
                "}";
    }

}