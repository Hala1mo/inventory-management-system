package com.example.assignmnet1_webservices.repository;

import com.example.assignmnet1_webservices.entity.wareHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface wareHouseRepository extends JpaRepository<wareHouse, Long> {

    List<wareHouse> findAll(); // Method to retrieve all products
    wareHouse findById(long id);
}
