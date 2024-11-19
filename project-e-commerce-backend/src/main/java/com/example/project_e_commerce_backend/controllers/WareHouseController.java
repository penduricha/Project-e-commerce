package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.dtos.CartDto;
import com.example.project_e_commerce_backend.dtos.WareHouseDto;
import com.example.project_e_commerce_backend.models.WareHouse;
import com.example.project_e_commerce_backend.repositories.WareHouseRepository;
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

    private final WareHouseRepository wareHouseRepository;

    @Autowired
    public WareHouseController(WareHouseService wareHouseService, WareHouseRepository wareHouseRepository) {
        this.wareHouseService = wareHouseService;
        this.wareHouseRepository = wareHouseRepository;
    }

    @GetMapping("/warehouses/productId/{productId}")
    public List<WareHouseDto> getWareHousesByProduct_ProductId(@PathVariable Long productId) throws JpaSystemException {
        return wareHouseService.getWareHousesByProduct_ProductId(productId);
    }

    @GetMapping("/cart/productId-size-color/{productId}/{size}/{color}")
    public CartDto getCart_By_ProductId_Size_Color(@PathVariable Long productId,@PathVariable String size,@PathVariable String color) throws JpaSystemException {
        String colorCode = '#' + color;
        return wareHouseRepository.getCart_By_ProductId_Size_Color(productId, size, colorCode);
    }

    @GetMapping("/cart/productId-size/{productId}/{size}")
    public CartDto getCart_By_ProductId_Size(@PathVariable Long productId,@PathVariable String size) throws JpaSystemException {
        return wareHouseRepository.getCart_By_ProductId_Size(productId, size);
    }

    @GetMapping("/cart/productId-color/{productId}/{color}")
    public CartDto getCart_By_ProductId_Color(@PathVariable Long productId,@PathVariable String color) throws JpaSystemException {
        String colorCode = '#' + color;
        return wareHouseRepository.getCart_By_ProductId_Color(productId, colorCode);
    }

    @GetMapping("/cart/productId-size-and-color-null/{productId}")
    public CartDto getCart_By_ProductId_If_Size_And_Color_NullCartDto(@PathVariable Long productId){
        return wareHouseRepository.getCart_By_ProductId_If_Size_And_Color_Null(productId);
    }
}
