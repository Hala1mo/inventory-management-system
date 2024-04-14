package com.example.assignmnet1_webservices.services.impl;

import com.example.assignmnet1_webservices.dto.productDTO;
import com.example.assignmnet1_webservices.entity.product;
import com.example.assignmnet1_webservices.entity.supplier;
import com.example.assignmnet1_webservices.entity.wareHouse;
import com.example.assignmnet1_webservices.exception.BadRequestException;
import com.example.assignmnet1_webservices.repository.productRepository;
import com.example.assignmnet1_webservices.repository.supplierRepository;
import com.example.assignmnet1_webservices.repository.wareHouseRepository;
import com.example.assignmnet1_webservices.services.productService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class productServiceImpl implements productService {

    private final productRepository productRepository;
    private final supplierRepository supplierRepository;

    private final wareHouseRepository wareHouseRepository;

    public productServiceImpl(productRepository productRepository, supplierRepository supplierRepository, com.example.assignmnet1_webservices.repository.wareHouseRepository wareHouseRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.wareHouseRepository = wareHouseRepository;
    }

    @Override
    public productDTO createProduct(productDTO productDTO) {
        product product = mapToEntity(productDTO);
        product newProduct = productRepository.save(product);

        // convert entity to DTO
        return mapToDTO(newProduct);
    }

    @Override
    public List<productDTO> getAllProducts() {
        System.out.println("Dddd");
        List<product> queryResult = productRepository.findAll();
        List<productDTO> products = new ArrayList<>();
        if (queryResult.isEmpty()) {
            throw new BadRequestException.NotFoundException("No products found in the database.");
        }

        for (product product : queryResult) {
            productDTO productDTO = mapToDTO(product);
            products.add(productDTO);
        }
        return products;
    }

    @Override
    public productDTO getProductById(int id) {
        product product = productRepository.findById(id);
        if (product == null) {
            throw new BadRequestException.NotFoundException("No product found with id: " + id);
        }
        return mapToDTO(product);
    }

    @Override
    public productDTO updateProduct(int id,productDTO productDTO) {
        product product = productRepository.findById(id);
        if(product!=null){
            product.setName(productDTO.getName());
            product.setDescription(productDTO.getDescription());
            product.setQuantity(productDTO.getQuantity());
            product.setPrice(productDTO.getPrice());
            product updatedProduct = productRepository.save(product);
            return mapToDTO(updatedProduct);
        }
    else {
        throw new EntityNotFoundException("Product not found with id: " + id);
    }
    }


    @Override
    public void deleteProductById(int id) {
        product product = productRepository.findById(id);
        if (product == null) {
            throw new BadRequestException.NotFoundException("No product found with id: " + id);
        }
        productRepository.delete(product);
    }

    @Override
    public List<productDTO> getProductsforSpecificSupplier(int id) {
        List<product> queryResult = productRepository.getProdcutsForSpecificSupplier(id);
        List<productDTO> products = new ArrayList<>();

        if (queryResult == null) {
            throw new BadRequestException.NotFoundException("No products found for this supplier id: " + id);
        }
        for (product product : queryResult) {
            productDTO productDTO = mapToDTO(product);
            products.add(productDTO);
        }
        return products;
    }

    @Override
    public List<productDTO> getProductsforSpecificWareHouse(int id) {
        List<product> queryResult = productRepository.getProdcutsForSpecificWareHouse(id);
        List<productDTO> products = new ArrayList<>();
        if (queryResult == null) {
            throw new BadRequestException.NotFoundException("No products found for this warehouse id: " + id);
        }
        for (product product : queryResult) {
            productDTO productDTO = mapToDTO(product);
            products.add(productDTO);
        }
        return products;
    }


    private productDTO mapToDTO(product product) {
        productDTO productDTO = new productDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setDescription(product.getDescription());
        productDTO.setSupplier_id(product.getSupplier().getId());
        productDTO.setWarehouse_id(product.getWareHouse().getId());
        return productDTO;
    }

    // convert DTO to entity
    private product mapToEntity(productDTO productDTO) {
        product product = new product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        supplier supplier = supplierRepository.findById(productDTO.getSupplier_id());
        if (supplier == null) {
            throw new EntityNotFoundException("Supplier not found with id: " + productDTO.getSupplier_id());
        }
        product.setSupplier(supplier);

        wareHouse wareHouse = wareHouseRepository.findById(productDTO.getWarehouse_id());
        if (wareHouse == null) {
            throw new EntityNotFoundException("WareHouse not found with id: " + productDTO.getWarehouse_id());
        }
        product.setWareHouse(wareHouse);
        return product;
    }
}
