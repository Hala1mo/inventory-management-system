package com.example.assignmnet1_webservices.dto;


import lombok.Data;
import lombok.NonNull;

@Data
public class productDTO {
 private int id;
 private String name;
 private double price;
 private int quantity;
 private String description;
 private int supplier_id;
 private int warehouse_id;


}
