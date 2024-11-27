package com.example.project_e_commerce_backend.services;

import com.example.project_e_commerce_backend.models.CartItem;

import java.util.List;

public interface I_CartItemService {
    public CartItem saveCartItem(Long id, CartItem cartItem);

    public CartItem saveCartItem_By_Email_Or_PhoneNumber(String emailOrPhoneNumber, CartItem cartItem);

    public List<CartItem> getListCartItem();

    public List<CartItem> getListCartItem_By_UserId(Long id);

    public List<CartItem> getListCartItem_By_Email_Or_PhoneNumber(String emailOrPhoneNumber);

    public CartItem getCartItemById(Long cartItemId);

    //public CartItem deleteCartItem_By_CartItemId(Long cartItemId);

    public void deleteCartItem_By_CartItemId(Long cartItemId);

    public void deleteCartItem_By_UserId(Long id);

    public void updateQuantityBuy_By_CartItemId(Long cartItemId, int quantityBuy);

    public CartItem findCartItemBy_ProductId_Size_Color(String emailOrPhoneNumber,Long productId, String size, String color);
}
