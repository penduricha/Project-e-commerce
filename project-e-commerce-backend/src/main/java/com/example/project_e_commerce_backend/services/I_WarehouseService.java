package com.example.project_e_commerce_backend.services;

import com.example.project_e_commerce_backend.dtos.WareHouseDto;
import com.example.project_e_commerce_backend.models.WareHouse;

import java.util.List;

public interface I_WarehouseService {
    public List<WareHouse> getAllWarehouses();

    public List<WareHouseDto> getWareHousesByProduct_ProductId(Long productId);
}
