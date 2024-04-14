package com.example.assignmnet1_webservices.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor //automatically generates a constructor with a parameter for each field in the class
@Entity
@Table
public class wareHouse {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name="address",nullable = false)
    private String address;

    @Column(name="capacity")
    private int capacity;

    @OneToMany(mappedBy ="wareHouse",
            cascade={CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH,CascadeType.REFRESH})
    private List<product> products;
}
