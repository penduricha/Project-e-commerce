package com.example.project_e_commerce_backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MappingEventPurchasingProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long mappingEventPurchasingProduct;

    @ManyToOne
    @JoinColumn(name = "eventPurchasingId")
    private EventPurchasing eventPurchasing;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;
}
