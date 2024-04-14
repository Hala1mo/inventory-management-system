package com.example.assignmnet1_webservices.services;

import com.example.assignmnet1_webservices.dto.productDTO;

import java.util.List;

public interface productService {

    productDTO createProduct(productDTO productDTO);

    List<productDTO> getAllProducts();

    productDTO getProductById(int id);

    productDTO updateProduct(int id,productDTO productDTO);

    void deleteProductById(int id);

    List<productDTO> getProductsforSpecificSupplier(int id);
    List<productDTO> getProductsforSpecificWareHouse(int id);

}
