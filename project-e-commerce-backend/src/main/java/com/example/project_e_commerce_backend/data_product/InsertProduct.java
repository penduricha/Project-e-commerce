package com.example.project_e_commerce_backend.data_product;

import com.example.project_e_commerce_backend.repositories.ProductRepository;
import com.example.project_e_commerce_backend.services.imp.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsertProduct {
    private final ProductRepository productRepository;

    @Autowired
    public InsertProduct(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String insert() throws JpaSystemException {
        return "Insert success";
    }
}
