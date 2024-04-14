package com.example.assignmnet1_webservices.repository;

import com.example.assignmnet1_webservices.entity.productOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface productOrderRepository extends JpaRepository<productOrder, Long> {
    List<productOrder> findAll(); // Method to retrieve all products
//    product findById(long id);

}
