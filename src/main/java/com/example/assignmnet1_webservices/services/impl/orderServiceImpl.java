package com.example.assignmnet1_webservices.services.impl;

import com.example.assignmnet1_webservices.dto.orderDTO;
import com.example.assignmnet1_webservices.dto.productDTO;
import com.example.assignmnet1_webservices.dto.productOrderDTO;
import com.example.assignmnet1_webservices.entity.orderEntity;
import com.example.assignmnet1_webservices.entity.product;
import com.example.assignmnet1_webservices.entity.productOrder;
import com.example.assignmnet1_webservices.exception.BadRequestException;
import com.example.assignmnet1_webservices.repository.orderRepository;
import com.example.assignmnet1_webservices.repository.productRepository;
import com.example.assignmnet1_webservices.services.orderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class orderServiceImpl implements orderService {
    
    private final orderRepository orderRepository;
    private final productRepository productRepository;

    public orderServiceImpl(orderRepository orderRepository,productRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public orderDTO createOrder(orderDTO orderDTO) {
        orderEntity order = mapToEntity(orderDTO);
        orderEntity newOrder = orderRepository.save(order);
        return mapToDTO(newOrder);
    }

    @Override
    public List<orderDTO> getAllOrders() {
        List<orderEntity> queryResult = orderRepository.findAll();
        List<orderDTO> orders = new ArrayList<>();
        if (queryResult.isEmpty()) {
            throw new BadRequestException.NotFoundException("No orders found in the database.");
        }

        for (orderEntity order : queryResult) {
            orderDTO orderDTO = mapToDTO(order);
            orders.add(orderDTO);
        }
        return orders;
    }

    @Override
    public orderDTO getOrderById(int id) {
        orderEntity order = orderRepository.findById(id);
        if (order == null) {
            throw new BadRequestException.NotFoundException("No order found with id: " + id);
        }
        return mapToDTO(order);
    }

    @Override
    public orderDTO updateOrder(int id, orderDTO orderDTO) {
        orderEntity order=orderRepository.findById(id);

        if(order!=null){
            order.setDate(orderDTO.getDate());
            order.setStatus(orderDTO.getStatus());
            order.setPrice(orderDTO.getPrice());
            return mapToDTO(order);
        }
        else {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }
    }

    @Override
    public void deleteOrderById(int id) {
        orderEntity order = orderRepository.findById(id);
        if (order == null) {
            throw new BadRequestException.NotFoundException("No order found with id: " + id);
        }
        orderRepository.delete(order);
    }

    @Override
    public orderDTO addProductToOrder(productOrderDTO request ) {
        orderEntity order = orderRepository.findById(request.getOrder_id());
        if(order==null){
            throw new IllegalArgumentException("Order not found");
        }
        product product = productRepository.findById(request.getProduct_id());

        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        productOrder productOrder = new productOrder();
        productOrder.setOrderEntity(order);
        productOrder.setProduct(product);
        productOrder.setQuantityOrdered(request.getQuantity_ordered());
        order.setPrice(order.getPrice()+product.getPrice()*request.getQuantity_ordered());
        order.getProductsOrdered().add(productOrder);
        orderRepository.save(order);
        return mapToDTO(order);
    }

    @Override
    public productOrderDTO updateProductInOrder(int orderId, int productId, productOrderDTO productOrderDTO) {
        orderEntity order = orderRepository.findById(orderId);
        if(order==null){
            throw new IllegalArgumentException("Order not found");
        }
        boolean productFound = false;
        for (productOrder product : order.getProductsOrdered()) {
            if (product.getId() == productId) {
                productFound = true;
                 product.setQuantityOrdered(productOrderDTO.getQuantity_ordered());
                orderRepository.save(order);
                break;
            }
        }

        if (!productFound) {
            throw new IllegalArgumentException("Product not found in the order");
        }

        return productOrderDTO;
    }

    public List<productDTO> retrieveProductsForSpecificOrder(int id){
        orderEntity order = orderRepository.findById(id);
        if (order == null) {
            throw new BadRequestException.NotFoundException("No order found with id: " + id);
        }
        List<productOrder>items=order.getProductsOrdered();
        if (items==null) {
            throw new IllegalArgumentException("Product not found in the order");
        }

        return items.stream().map(item -> mapProductOrderToproductDTO(item)).collect(Collectors.toList());

    }

    private orderDTO mapToDTO(orderEntity order) {
        orderDTO orderDTO = new orderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setDate(order.getDate());
        return orderDTO;
    }

    private orderEntity mapToEntity(orderDTO orderDTO) {
        orderEntity order = new orderEntity();
        order.setId(orderDTO.getId());
        order.setStatus(orderDTO.getStatus());
        order.setPrice(orderDTO.getPrice());
        order.setDate(orderDTO.getDate());
        return order;
    }

    private productDTO mapProductOrderToproductDTO(productOrder productOrder) {
        productDTO productOrderDTO = new productDTO();
        productOrderDTO.setId(productOrder.getId());
        productOrderDTO.setQuantity(productOrder.getQuantityOrdered());
        productOrderDTO.setName(productOrder.getProduct().getName());
        productOrderDTO.setPrice(productOrder.getProduct().getPrice()*productOrder.getQuantityOrdered());
        productOrderDTO.setDescription(productOrder.getProduct().getDescription());
        productOrderDTO.setWarehouse_id(productOrder.getProduct().getWareHouse().getId());
        productOrderDTO.setSupplier_id(productOrder.getProduct().getSupplier().getId());
        return productOrderDTO;
    }



}
