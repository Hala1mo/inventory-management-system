package com.example.assignmnet1_webservices.services;

import com.example.assignmnet1_webservices.dto.wareHouseDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface wareHouseService {

    wareHouseDTO createWareHouse(wareHouseDTO wareHouseDTO);

    List<wareHouseDTO> getAllWareHouses();

    wareHouseDTO getWareHouseById(int id);

    wareHouseDTO updateWareHouse(int id,wareHouseDTO wareHouseDTO);

    void deleteWareHouseById(int id);
}
