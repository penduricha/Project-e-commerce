package com.example.project_e_commerce_backend.services;

import com.example.project_e_commerce_backend.models.CartItem;

import java.util.List;

public interface I_CartItem {
    public CartItem saveCartItem(CartItem cartItem);

    public List<CartItem> getListCartItem();

    public List<CartItem> getListCartItem_By_CartId(Long cartId);

    public CartItem getCartItemById(Long cartItemId);

    public boolean deleteCartItem_By_CartItemId(CartItem cartItem);

    public boolean deleteCartItem_By_CartId(Long cartId);

    public boolean updateQuantity_By_CartItemId(Long cartItemId, int quantity);


}
