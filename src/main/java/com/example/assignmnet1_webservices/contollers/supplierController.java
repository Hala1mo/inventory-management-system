package com.example.assignmnet1_webservices.contollers;

import com.example.assignmnet1_webservices.dto.supplierDTO;
import com.example.assignmnet1_webservices.dto.supplierDTO;
import com.example.assignmnet1_webservices.services.supplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class supplierController {
    
    private supplierService supplierService;

    @Autowired
    public supplierController(supplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("")
    public List<supplierDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable int id) {
        supplierDTO supplier = supplierService.getSupplierById(id);
        if (supplier != null) {
            return ResponseEntity.ok(supplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public  supplierDTO saveNewSupplier(@Valid @RequestBody  supplierDTO supplierDTO) {
        return supplierService.createSupplier(supplierDTO);
    }

    @PutMapping("/{id}")
    public  supplierDTO updateSupplier(@PathVariable int id,@RequestBody  supplierDTO supplierDTO) {
        return supplierService.updateSupplier(id,supplierDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteSupplier(@PathVariable int id) {

        supplierDTO supplier = supplierService.getSupplierById(id);
        if(supplier!=null){
            supplierService.deleteSupplierById(id);
            return ResponseEntity.ok("Deleted successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
