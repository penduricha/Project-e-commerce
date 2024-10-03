package com.example.project_e_commerce_backend.services;

import com.example.project_e_commerce_backend.models.*;

import java.util.List;

public interface I_ProductService {
    public Product saveProduct(Product product);

    public List<Product> getAllProducts();
}
