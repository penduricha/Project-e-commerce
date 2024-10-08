package com.example.project_e_commerce_backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductViewDto {
    private Long productId;

    private String name;

    private String status;

    private String typeProduct;

    public ProductViewDto(Long productId, String name, String status, String typeProduct) {
        this.productId = productId;
        this.name = name;
        this.status = status;
        this.typeProduct = typeProduct;
    }
}
