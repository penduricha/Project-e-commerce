package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.models.WareHouse;
import com.example.project_e_commerce_backend.repositories.WareHouseRepository;
import com.example.project_e_commerce_backend.services.I_WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareHouseService implements I_WarehouseService {

    private final WareHouseRepository wareHouseRepository;

    public WareHouseService(WareHouseRepository wareHouseRepository) {
        this.wareHouseRepository = wareHouseRepository;
    }

    @Override
    public List<WareHouse> getAllWarehouses() {
        return List.of();
        //return userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }
}
