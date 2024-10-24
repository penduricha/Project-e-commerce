package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.dtos.WareHouseDto;
import com.example.project_e_commerce_backend.models.WareHouse;
import com.example.project_e_commerce_backend.services.imp.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WareHouseController {
    private final WareHouseService wareHouseService;

    @Autowired
    public WareHouseController(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    @GetMapping("/warehouses/productId/{productId}")
    public List<WareHouseDto> getWareHousesByProduct_ProductId(@PathVariable Long productId) throws JpaSystemException {
        return wareHouseService.getWareHousesByProduct_ProductId(productId);
    }
}
