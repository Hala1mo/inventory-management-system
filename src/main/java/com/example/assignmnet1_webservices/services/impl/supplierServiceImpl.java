package com.example.assignmnet1_webservices.services.impl;


import com.example.assignmnet1_webservices.dto.supplierDTO;
import com.example.assignmnet1_webservices.entity.supplier;
import com.example.assignmnet1_webservices.exception.BadRequestException;
import com.example.assignmnet1_webservices.services.supplierService;
import com.example.assignmnet1_webservices.repository.supplierRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class supplierServiceImpl implements supplierService {

   private final supplierRepository  supplierRepository;

    public supplierServiceImpl(supplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public supplierDTO createSupplier(supplierDTO supplierDTO) {
        supplier supplier = mapToEntity(supplierDTO);
        supplier newSupplier = supplierRepository.save(supplier);

        // convert entity to DTO
        return mapToDTO(newSupplier);
    }

    @Override
    public List<supplierDTO> getAllSuppliers() {
        List<supplier> queryResult = supplierRepository.findAll();
        List<supplierDTO> suppliers = new ArrayList<>();
        if (queryResult.isEmpty()) {
            throw new BadRequestException.NotFoundException("No suppliers found in the database.");
        }

        for (supplier supplier : queryResult) {
            supplierDTO supplierDTO = mapToDTO(supplier);
            suppliers.add(supplierDTO);
        }
        return suppliers;
    }

    @Override
    public supplierDTO getSupplierById(int id) {
        supplier supplier = supplierRepository.findById(id);

        if (supplier==null) {
            throw new BadRequestException.NotFoundException("Supplier not found with id: "+id);
        }
        return mapToDTO(supplier);
    }

    @Override
    public supplierDTO updateSupplier(int id, supplierDTO supplierDTO) {
        supplier supplier = supplierRepository.findById(id);
        if(supplier!=null){
            supplier.setName(supplierDTO.getName());
            supplier.setPhone_number(supplierDTO.getPhone_number());
            supplier updatedProduct = supplierRepository.save(supplier);
            return mapToDTO(updatedProduct);
        }
        else {
            // Handle the case where the supplier with the given id is not found
            throw new EntityNotFoundException("Supplier not found with id: " + id);
        }
    }

    @Override
    public void deleteSupplierById(int id) {
        supplier supplier = supplierRepository.findById(id);
        if (supplier==null) {
            throw new BadRequestException.NotFoundException("Supplier not found with id: "+id);
        }
        supplierRepository.delete(supplier);
    }


    private supplierDTO mapToDTO(supplier supplier) {
        supplierDTO supplierDTO = new supplierDTO();
        supplierDTO.setId(supplier.getId());
        supplierDTO.setName(supplier.getName());
        supplierDTO.setPhone_number(supplier.getPhone_number());
       
        return supplierDTO;
    }

    // convert DTO to entity
    private supplier mapToEntity(supplierDTO supplierDTO) {
        supplier supplier = new supplier();
        supplier.setName(supplierDTO.getName());
        supplier.setPhone_number(supplierDTO.getPhone_number());
        return supplier;
    }
}
