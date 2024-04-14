package com.example.assignmnet1_webservices.services.impl;

import com.example.assignmnet1_webservices.dto.supplierDTO;
import com.example.assignmnet1_webservices.dto.wareHouseDTO;
import com.example.assignmnet1_webservices.entity.supplier;
import com.example.assignmnet1_webservices.entity.wareHouse;
import com.example.assignmnet1_webservices.exception.BadRequestException;
import com.example.assignmnet1_webservices.repository.wareHouseRepository;
import com.example.assignmnet1_webservices.services.wareHouseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class wareHouseServiceImpl implements wareHouseService {
    private final wareHouseRepository wareHouseRepository;

    public wareHouseServiceImpl(wareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }

    @Override
    public wareHouseDTO createWareHouse(wareHouseDTO wareHouseDTO) {
        wareHouse wareHouse = mapToEntity(wareHouseDTO);
        wareHouse newWareHouse = wareHouseRepository.save(wareHouse);

        return mapToDTO(newWareHouse);
    }

    @Override
    public List<wareHouseDTO> getAllWareHouses() {
        List<wareHouse> queryResult = wareHouseRepository.findAll();
        List<wareHouseDTO> wareHouses = new ArrayList<>();

        for (wareHouse wareHouse : queryResult) {
            wareHouseDTO wareHouseDTO = mapToDTO(wareHouse);
            wareHouses.add(wareHouseDTO);
        }
        return wareHouses;
    }

    @Override
    public wareHouseDTO getWareHouseById(int id) {
        wareHouse wareHouse = wareHouseRepository.findById(id);
        if (wareHouse==null) {
            throw new BadRequestException.NotFoundException("WareHouse not found with id: "+id);
        }
        return mapToDTO(wareHouse);
    }

    @Override
    public wareHouseDTO updateWareHouse(int id, wareHouseDTO wareHouseDTO) {
        wareHouse wareHouse = wareHouseRepository.findById(id);
        if(wareHouse!=null){
            wareHouse.setName(wareHouseDTO.getName());
            wareHouse.setAddress(wareHouseDTO.getAddress());
            wareHouse.setCapacity(wareHouseDTO.getCapacity());
            wareHouse updatedProduct = wareHouseRepository.save(wareHouse);
            return mapToDTO(updatedProduct);
        }
        else {
            // Handle the case where the supplier with the given id is not found
            throw new EntityNotFoundException("WareHouse not found with id: " + id);
        }
    }

    @Override
    public void deleteWareHouseById(int id) {
        wareHouse wareHouse = wareHouseRepository.findById(id);
        if (wareHouse==null) {
            throw new BadRequestException.NotFoundException("WareHouse not found with id: "+id);
        }
        wareHouseRepository.delete(wareHouse);
    }

    private wareHouseDTO mapToDTO(wareHouse wareHouse) {
        wareHouseDTO wareHouseDTO = new wareHouseDTO();
        wareHouseDTO.setId(wareHouse.getId());
        wareHouseDTO.setName(wareHouse.getName());
        wareHouseDTO.setAddress(wareHouse.getAddress());
        wareHouseDTO.setCapacity(wareHouse.getCapacity());
        return wareHouseDTO;
    }

    // convert DTO to entity
    private wareHouse mapToEntity(wareHouseDTO wareHouseDTO) {
        wareHouse wareHouse = new wareHouse();
        wareHouse.setName(wareHouseDTO.getName());
        wareHouse.setCapacity(wareHouseDTO.getCapacity());
        wareHouse.setAddress(wareHouseDTO.getAddress());
        return wareHouse;
    }
}
