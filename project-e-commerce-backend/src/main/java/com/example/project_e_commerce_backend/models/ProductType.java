package com.example.project_e_commerce_backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import java.io.Serializable;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long productTypeId;

    @Column(columnDefinition = "nvarchar(50)", nullable = false, unique = true)
    private String typeProduct;

    private Long parentId;

    //mapping with Product
    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public ProductType(Long productTypeId,String typeProduct, Long parentId) {
        this.productTypeId = productTypeId;
        this.typeProduct = typeProduct;
        this.parentId = parentId;
    }
}
