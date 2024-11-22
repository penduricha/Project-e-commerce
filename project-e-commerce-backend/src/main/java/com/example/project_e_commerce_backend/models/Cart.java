package com.example.project_e_commerce_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.io.Serializable;

@Getter
@Entity
@Setter
@ToString
@NoArgsConstructor
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long cartId;

    @OneToOne
    @JoinColumn(name = "id")
    //one to one mapping with id in table User
    private User user;

    public Cart(User user) {
        this.user = user;
    }
}
