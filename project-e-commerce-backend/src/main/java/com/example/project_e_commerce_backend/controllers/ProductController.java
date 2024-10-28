package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.dtos.ProductViewDto;
import com.example.project_e_commerce_backend.models.Product;
import com.example.project_e_commerce_backend.repositories.ProductRepository;
import com.example.project_e_commerce_backend.services.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/products-explore-our-products")
    public List<Map<String, Object>> getExploreProducts() throws JpaSystemException {
        return productRepository.getProducts_By_event_Explore_Product();
    }

    @GetMapping("/products-best-selling")
    public List<Map<String, Object>> getBestSellingProducts() throws JpaSystemException {
        return productRepository.getProducts_By_Event("Best Selling Product");
    }

    @GetMapping("/products/productId/{productId}")
    public ProductViewDto findProductByProductId(@PathVariable Long productId) throws JpaSystemException {
        return productService.findProductByProductId(productId);
    }

    @GetMapping("/products/related/{productTypeId}/{productId}")
    public List<Map<String, Object>> getProducts_Related_By_ProductTypeId(@PathVariable Long productTypeId, @PathVariable Long productId) throws JpaSystemException {
        return productRepository.getProducts_Related_By_ProductTypeId(productTypeId,productId);
    }
}
