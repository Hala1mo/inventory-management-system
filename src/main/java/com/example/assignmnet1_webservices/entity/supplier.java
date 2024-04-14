package com.example.assignmnet1_webservices.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor //automatically generates a constructor with a parameter for each field in the class
@Entity
@Table
public class supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="phone_number",nullable = false)
    private String phone_number;
    @OneToMany(mappedBy ="supplier",
               cascade={CascadeType.ALL})
    private List<product> products;




}
