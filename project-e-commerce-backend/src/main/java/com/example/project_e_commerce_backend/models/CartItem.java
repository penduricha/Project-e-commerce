package com.example.project_e_commerce_backend.models;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//
import java.io.Serializable;
//
@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long cartItemId;

    //bigint(20)
    @Column(columnDefinition = "bigint",nullable = false)
    private Long productId;

    @Column(columnDefinition = "nvarchar(200)")
    private String image;

    @Column(columnDefinition = "nvarchar(100)", nullable = false)
    private String name;

    @Column(columnDefinition = "nvarchar(20)")
    private String size;

    @Column(columnDefinition = "nvarchar(20)")
    private String color;

    @Column(columnDefinition = "double", nullable=false)
    private double price;

    @Column(columnDefinition = "bigint", nullable=false)
    private int quantityBuy;

//    @Column(columnDefinition = "double", nullable=false)
//    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "cartId")
    @JsonIgnore

    private Cart cart;
}
