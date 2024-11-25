package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.models.Cart;
import com.example.project_e_commerce_backend.services.imp.CartService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart/user-id/{id}")
    public Cart getCartByUserId(@PathVariable Long id) throws JpaSystemException {
        return cartService.getCartByUserId(id);
    }
}
