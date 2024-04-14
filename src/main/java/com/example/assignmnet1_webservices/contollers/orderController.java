package com.example.assignmnet1_webservices.contollers;


import com.example.assignmnet1_webservices.dto.orderDTO;
import com.example.assignmnet1_webservices.dto.productDTO;
import com.example.assignmnet1_webservices.dto.productOrderDTO;
import com.example.assignmnet1_webservices.dto.supplierDTO;
import com.example.assignmnet1_webservices.entity.orderEntity;
import com.example.assignmnet1_webservices.services.orderService;
import com.example.assignmnet1_webservices.services.productService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class orderController {


    private final orderService orderService;

    @Autowired
    public orderController(orderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<orderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        orderDTO order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public  orderDTO saveNewOrder(@Valid @RequestBody  orderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    @PostMapping("/products")
    public ResponseEntity<orderDTO> addProductToOrder(

            @RequestBody productOrderDTO request) {

        orderDTO order = orderService.addProductToOrder(request);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(order);
    }
    @PutMapping("/{id}")
    public  orderDTO updateOrder(@PathVariable int id,@RequestBody  orderDTO orderDTO) {
        return orderService.updateOrder(id,orderDTO);
    }
    @GetMapping("/{id}/products")
    public List<productDTO> retrieveProductsForSpecificOrder(@PathVariable int id) {
        return orderService.retrieveProductsForSpecificOrder(id);
    }

    @PutMapping("/{order_id}/products/{product_id}")
    public  productOrderDTO updateProductInOrder(@PathVariable int order_id,
            @PathVariable int product_id,@RequestBody  productOrderDTO productOrderDTO) {
        return orderService.updateProductInOrder(order_id,product_id,productOrderDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteOrder(@PathVariable int id) {

        orderDTO order = orderService.getOrderById(id);
        if(order!=null){
            orderService.deleteOrderById(id);
            return ResponseEntity.ok("Deleted successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }




}
