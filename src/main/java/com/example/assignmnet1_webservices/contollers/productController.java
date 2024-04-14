package com.example.assignmnet1_webservices.contollers;

import com.example.assignmnet1_webservices.dto.productDTO;
import com.example.assignmnet1_webservices.services.productService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class productController {

    private final productService productService;

    @Autowired
    public productController(productService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<productDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getProduct(@PathVariable int id) {
        productDTO product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public  productDTO saveNewProduct(@Valid @RequestBody  productDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @PutMapping("/{id}")
    public  productDTO updateProduct(@PathVariable int id,@RequestBody  productDTO productDTO) {
        return productService.updateProduct(id,productDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteProduct(@PathVariable int id) {

        productDTO product = productService.getProductById(id);
        if(product!=null){
            productService.deleteProductById(id);
            return ResponseEntity.ok("Deleted successfully");
        }
     else {
        return ResponseEntity.notFound().build();
    }
}


    @GetMapping("supplier/{id}")
    public  ResponseEntity<?> getProductsforSpeificSupplier(@PathVariable int id) {
        List<productDTO> products= productService.getProductsforSpecificSupplier(id);
        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("wareHouse/{id}")
    public  ResponseEntity<?> getProductsforSpeificWareHouse(@PathVariable int id) {
        List<productDTO> products= productService.getProductsforSpecificWareHouse(id);
        if (products != null) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
