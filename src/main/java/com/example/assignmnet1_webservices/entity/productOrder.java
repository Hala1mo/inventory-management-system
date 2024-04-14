package com.example.assignmnet1_webservices.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class productOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private orderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private product product;

    @Column(name = "quantity_ordered")
    private int quantityOrdered;
}
