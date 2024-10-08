package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.dtos.ProductViewDto;
import com.example.project_e_commerce_backend.models.Product;
import com.example.project_e_commerce_backend.services.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product saveProduct(Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("/products")
    public List<ProductViewDto> getAllProducts() {
        //return productRepository.findAll(Sort.by(Sort.Direction.ASC, "productId"));
        return productService.getAllProducts();
    }
}
