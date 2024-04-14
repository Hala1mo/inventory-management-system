package com.example.assignmnet1_webservices.repository;

import com.example.assignmnet1_webservices.entity.supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface supplierRepository extends JpaRepository<supplier, Long> {
    List<supplier> findAll(); // Method to retrieve all products
     supplier findById(long id);


}
