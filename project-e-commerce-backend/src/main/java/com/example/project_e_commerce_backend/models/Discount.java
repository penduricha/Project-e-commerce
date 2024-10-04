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
public class Discount implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "wareHouseId")
    private WareHouse wareHouse;

    @Column(columnDefinition = "double")
    private double numberOfDiscounts;

    public Discount(double numberOfDiscounts) {
        this.numberOfDiscounts = numberOfDiscounts;
    }
}
