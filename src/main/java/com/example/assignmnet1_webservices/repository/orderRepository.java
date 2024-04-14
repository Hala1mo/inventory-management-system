package com.example.assignmnet1_webservices.repository;


import com.example.assignmnet1_webservices.entity.orderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface orderRepository extends JpaRepository<orderEntity, Long> {
    List<orderEntity> findAll(); // Method to retrieve all products
    orderEntity findById(long id);
}
