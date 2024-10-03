package com.example.project_e_commerce_backend.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import java.io.Serializable;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long productTypeId;

    @Column(name = "typeProduct",columnDefinition = "nvarchar(50)",nullable = false)
    private String typeProduct;

    private Long parentId;

    @ToString.Exclude
    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}
