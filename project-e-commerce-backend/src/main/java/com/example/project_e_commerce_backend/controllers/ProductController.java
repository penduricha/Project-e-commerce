package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.models.Product;
import com.example.project_e_commerce_backend.repositories.ProductRepository;
import com.example.project_e_commerce_backend.services.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @PostMapping("/products")
    public Product saveProduct(Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/products-flash-sales")
    public List<Map<String, Object>> getFlashSalesProducts() throws JpaSystemException {
        return productRepository.getProducts_By_Event("Flash Sales");
    }
}
