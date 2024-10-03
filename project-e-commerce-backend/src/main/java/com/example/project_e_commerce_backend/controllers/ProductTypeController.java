package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.models.ProductType;
import com.example.project_e_commerce_backend.services.imp.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping("/productType")
    public ProductType saveProduct(ProductType productType) {
        return productTypeService.saveProductType(productType);
    }
}
