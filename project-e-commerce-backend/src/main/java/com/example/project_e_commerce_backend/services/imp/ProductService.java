package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.dtos.ProductDto;
import com.example.project_e_commerce_backend.models.Product;
import com.example.project_e_commerce_backend.repositories.*;
import com.example.project_e_commerce_backend.services.I_ProductService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements I_ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) throws JpaSystemException {
        return productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts() throws JpaSystemException{
        return productRepository.getAllProductAndType();
    }
}
