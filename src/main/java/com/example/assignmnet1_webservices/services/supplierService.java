package com.example.assignmnet1_webservices.services;

import com.example.assignmnet1_webservices.dto.supplierDTO;

import java.util.List;

public interface supplierService {

    supplierDTO createSupplier(supplierDTO supplierDTO);

    List<supplierDTO> getAllSuppliers();

    supplierDTO getSupplierById(int id);

    supplierDTO updateSupplier(int id,supplierDTO supplierDTO);

    void deleteSupplierById(int id);
}
