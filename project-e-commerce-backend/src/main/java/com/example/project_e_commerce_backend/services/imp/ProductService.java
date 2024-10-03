package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.models.Product;
import com.example.project_e_commerce_backend.repositories.*;
import com.example.project_e_commerce_backend.services.I_ProductService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements I_ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        //return productRepository.findAll(Sort.by(Sort.Direction.ASC, "productId"));
        return null;
    }
}
