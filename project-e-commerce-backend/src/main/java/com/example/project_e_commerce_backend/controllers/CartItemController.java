package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.models.CartItem;

import com.example.project_e_commerce_backend.repositories.CartItemRepository;
import com.example.project_e_commerce_backend.services.imp.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartItemController {
    private final CartItemService cartItemService;

    private final CartItemRepository cartItemRepository;

    public CartItemController(CartItemService cartItemService, CartItemRepository cartItemRepository) {
        this.cartItemService = cartItemService;
        this.cartItemRepository = cartItemRepository;
    }

    @PostMapping("/cart-item/{id}")
    public CartItem saveCartItem(@PathVariable Long id, @RequestBody CartItem cartItem) throws JpaSystemException {
        return cartItemService.saveCartItem(id, cartItem);
    }

    @GetMapping("/cart-item-by-user-id/{id}")
    public List<CartItem> getListCartItem_By_UserId(@PathVariable Long id) throws JpaSystemException {
        return cartItemService.getListCartItem_By_UserId(id);
    }

    @GetMapping("/cart-item-by-cart-item-id/{cartItemId}")
    public CartItem getCartItemById(@PathVariable Long cartItemId) throws JpaSystemException {
        return cartItemService.getCartItemById(cartItemId);
    }

    @DeleteMapping("/cart-item-by-cart-item-id/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem_By_CartItemId(@PathVariable Long cartItemId) throws JpaSystemException{
        try {
            CartItem cartItem = cartItemService.getCartItemById(cartItemId);
            if (cartItem != null) {
                cartItemService.deleteCartItem_By_CartItemId(cartItemId);
                // Use service method to delete
                return ResponseEntity.ok().build();
                // Return 200 No Content
            } else {
                return ResponseEntity.notFound().build();
                // Return 404 Not Found
            }
        } catch (JpaSystemException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            // Handle server error
        }
    }
}
