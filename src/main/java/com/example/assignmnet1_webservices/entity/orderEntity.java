package com.example.assignmnet1_webservices.entity;

import com.example.assignmnet1_webservices.entity.enums.orderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class orderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="date",nullable = false)
    Date date;
    @Column(name="price",nullable = false)
    double price=0.0;
    @Column(name="status",nullable = false)
    orderStatus status;

    @OneToMany(mappedBy ="orderEntity",
            cascade={CascadeType.ALL})
    private List<productOrder> productsOrdered;


}
