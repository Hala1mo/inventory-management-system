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
public class product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;
    @Column(name="name", nullable = false, unique = true)
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="quantity")
    private int quantity;
    @Column(name="price")
    private double price;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
                CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "warehouse_id")
    private wareHouse wareHouse;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE,
                        CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "supplier_id")
    private supplier supplier;

    @OneToMany(mappedBy ="product",
            cascade={CascadeType.ALL})
    private List<productOrder> productsOrdered;

    @Override
    public String toString() {
        return "product{" +
                "productID=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
