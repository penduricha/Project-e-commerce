package com.example.project_e_commerce_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount discount)) return false;
        return Objects.equals(getWareHouse(), discount.getWareHouse());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getWareHouse());
    }
}
