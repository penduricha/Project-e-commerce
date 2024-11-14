package com.example.project_e_commerce_backend.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
    private Long productId;

    private String name;

    private String size;

    private String color;

    private int quantity;

    public CartDto(Long productId, String name, String size, String color, int quantity) {
        this.productId = productId;
        this.name = name;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
    }
}
