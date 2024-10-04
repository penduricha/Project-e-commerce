package com.example.project_e_commerce_backend.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private String name;

    private String description;

    private String status;

    private String typeProduct;

    public ProductDto(String name, String description, String status, String typeProduct) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.typeProduct = typeProduct;
    }
}
