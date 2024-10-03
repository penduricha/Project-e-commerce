package com.example.project_e_commerce_backend.services;

import com.example.project_e_commerce_backend.models.ProductType;

import java.util.List;

public interface I_ProductTypeService {

    public ProductType saveProductType(ProductType productType);

    public List<ProductType> getAllProductTypes();
}
