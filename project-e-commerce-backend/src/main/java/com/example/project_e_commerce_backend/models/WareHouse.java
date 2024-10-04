package com.example.project_e_commerce_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long wareHouseId;

    @Column(columnDefinition = "nvarchar(20)")
    private String size;

    @Column(columnDefinition = "nvarchar(20)")
    private String color;

    @Column(columnDefinition = "bigint", nullable=false)
    private int quantity;

    @Column(columnDefinition = "nvarchar(200)", nullable=false)
    private String image;

    @Column(columnDefinition = "double", nullable=false)
    private double price;

    //mapping from Product
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @OneToOne(mappedBy = "wareHouse", cascade = CascadeType.ALL)
    private Discount discount;

    public WareHouse(String size, String color, int quantity, String image, double price) {
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
    }
}
