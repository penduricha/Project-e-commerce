package com.example.project_e_commerce_backend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long productId;

    @Column(columnDefinition = "nvarchar(100)",nullable = false)
    private String name;

    @Column(columnDefinition = "nvarchar(300)")
    private String description;

    @Column(columnDefinition = "nvarchar(50)")
    private String status;

    @Column(columnDefinition = "DATETIME", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "DATETIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @Column(columnDefinition = "DATETIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime removedAt;

    @PrePersist
    protected void onCreated(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdated(){
        updatedAt = LocalDateTime.now();
    }

    @PreRemove
    protected void onDeleted(){
        removedAt = LocalDateTime.now();
    }

    //mapping from ProductType;
    @ManyToOne
    @JoinColumn(name = "productTypeId")
    private ProductType productType;

    //mapping with WareHouse
    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<WareHouse> wareHouseList = new ArrayList<>();

    public Product(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
