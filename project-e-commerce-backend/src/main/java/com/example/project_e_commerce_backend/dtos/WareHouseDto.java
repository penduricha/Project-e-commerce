package com.example.project_e_commerce_backend.dtos;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WareHouseDto {
    private Long wareHouseId;

    private String size;

    private String color;

    private int quantity;

    private String image;

    private double price;

    private double numberOfDiscount;

    public WareHouseDto(Long wareHouseId, String size, String color, int quantity, String image, double price, double numberOfDiscount) {
        this.wareHouseId = wareHouseId;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.numberOfDiscount = numberOfDiscount;
    }
}
