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
//    @SequenceGenerator(
//            name = "order_generator",
//            sequenceName = "order_sequence",
//            initialValue = 1000
//    )
    private Long id;

    @Column(name="price", columnDefinition="Decimal(10,2) default '0.00'")
    private double totalPrice;

    @Column(columnDefinition = "TEXT")
    private String memo;
}