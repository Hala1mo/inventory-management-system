package com.example.assignmnet1_webservices.services;

import com.example.assignmnet1_webservices.dto.orderDTO;
import com.example.assignmnet1_webservices.dto.productDTO;
import com.example.assignmnet1_webservices.dto.productOrderDTO;

import java.util.List;



public interface orderService {

    orderDTO createOrder(orderDTO orderDTO);

    List<orderDTO> getAllOrders();

    orderDTO getOrderById(int id);

    orderDTO updateOrder(int id,orderDTO orderDTO);

    void deleteOrderById(int id);

    List<productDTO> retrieveProductsForSpecificOrder(int id);
    orderDTO addProductToOrder(productOrderDTO request);

    productOrderDTO updateProductInOrder(int orderId, int productId, productOrderDTO productOrderDTO);
}
