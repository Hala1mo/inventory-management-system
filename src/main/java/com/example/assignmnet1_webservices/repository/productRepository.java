package com.example.assignmnet1_webservices.repository;

import com.example.assignmnet1_webservices.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  productRepository  extends JpaRepository<product, Long> {
    List<product> findAll(); // Method to retrieve all products
    product findById(long id);


    @Query("SELECT a from product a " +
            "WHERE a.supplier.id = :supplierID")
    List<product> getProdcutsForSpecificSupplier(@Param("supplierID")int supplierID);


    @Query("SELECT a from product a " +
            "WHERE a.wareHouse.id = :wareHouseID")
    List<product> getProdcutsForSpecificWareHouse(@Param("wareHouseID")int wareHouseID);
}