package com.example.assignmnet1_webservices.contollers;

import com.example.assignmnet1_webservices.dto.supplierDTO;
import com.example.assignmnet1_webservices.dto.wareHouseDTO;
import com.example.assignmnet1_webservices.services.wareHouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/wareHouse")
public class wareHouseController {

   private final wareHouseService wareHouseService;

    @Autowired
    public wareHouseController(wareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    @GetMapping("")
    public List<wareHouseDTO> getAllWareHouses() {
        return wareHouseService.getAllWareHouses();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getWareHouse(@PathVariable int id) {
        wareHouseDTO wareHouse = wareHouseService.getWareHouseById(id);
        if (wareHouse != null) {
            return ResponseEntity.ok(wareHouse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public  wareHouseDTO saveNewWareHouse(@Valid @RequestBody wareHouseDTO wareHouseDTO) {
        return wareHouseService.createWareHouse(wareHouseDTO);
    }

    @PutMapping("/{id}")
    public  wareHouseDTO updateWareHouse(@PathVariable int id,@RequestBody  wareHouseDTO wareHouseDTO) {
        return wareHouseService.updateWareHouse(id,wareHouseDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteWareHouse(@PathVariable int id) {

        wareHouseDTO wareHouse = wareHouseService.getWareHouseById(id);
        if(wareHouse!=null){
            wareHouseService.deleteWareHouseById(id);
            return ResponseEntity.ok("Deleted successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
