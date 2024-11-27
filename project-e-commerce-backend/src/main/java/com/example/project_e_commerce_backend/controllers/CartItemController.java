package com.example.project_e_commerce_backend.controllers;

import com.example.project_e_commerce_backend.models.CartItem;

//import com.example.project_e_commerce_backend.repositories.CartItemRepository;
import com.example.project_e_commerce_backend.services.imp.CartItemService;

//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/api")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

//    private final CartItemRepository cartItemRepository;
//
//    public CartItemController(CartItemService cartItemService, CartItemRepository cartItemRepository) {
//        this.cartItemService = cartItemService;
//        this.cartItemRepository = cartItemRepository;
//    }

    @PostMapping("/cart-item/{id}")
    public CartItem saveCartItem(@PathVariable Long id,@RequestBody CartItem cartItem) throws JpaSystemException {
        return cartItemService.saveCartItem(id, cartItem);
    }

    @PostMapping("/post-cart-item-by-email-or-phone-number/{emailOrPhoneNumber}")
    public CartItem saveCartItem_By_Email_Or_PhoneNumber(@PathVariable String emailOrPhoneNumber,@RequestBody CartItem cartItem) throws JpaSystemException {
        return cartItemService.saveCartItem_By_Email_Or_PhoneNumber(emailOrPhoneNumber,cartItem);
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

    }

    @DeleteMapping("/carts-by-user-id/{id}")
    public ResponseEntity<Void> deleteCartItem_By_UserId(@PathVariable Long id) throws JpaSystemException {
        if(cartItemService.getListCartItem_By_UserId(id) != null){
            cartItemService.deleteCartItem_By_UserId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-quantityBuy-by-cartItemId/{cartItemId}/{quantityBuy}")
    public ResponseEntity<Void> updateQuantityBuy_By_CartItemId(@PathVariable Long cartItemId,@PathVariable int quantityBuy) throws JpaSystemException {
        CartItem cartItemFound = getCartItemById(cartItemId);
        if(cartItemFound != null){
            cartItemService.updateQuantityBuy_By_CartItemId(cartItemId,quantityBuy);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cart-item-by-user-email-or-phoneNumber/{emailPhoneNumber}")
    public List<CartItem> getListCartItem_By_Email_Or_PhoneNumber(@PathVariable String emailPhoneNumber) throws JpaSystemException {
        return cartItemService.getListCartItem_By_Email_Or_PhoneNumber(emailPhoneNumber);
    }

    @GetMapping("/find-cart-item-by-productId-size-color/{emailOrPhoneNumber}/{productId}/{size}/{color}")
    public CartItem findCartItemBy_ProductId_Size_Color(@PathVariable String emailOrPhoneNumber,@PathVariable Long productId,@PathVariable String size,@PathVariable String color) throws JpaSystemException {
        String colorCode = "#" + color;
        return cartItemService.findCartItemBy_ProductId_Size_Color(emailOrPhoneNumber, productId, size, colorCode);
    }
}
