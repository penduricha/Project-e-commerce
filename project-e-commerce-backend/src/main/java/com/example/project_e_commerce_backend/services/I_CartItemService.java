package com.example.project_e_commerce_backend.services;

import com.example.project_e_commerce_backend.models.CartItem;

import java.util.List;

public interface I_CartItemService {
    public CartItem saveCartItem(Long id, CartItem cartItem);

    public List<CartItem> getListCartItem();

    public List<CartItem> getListCartItem_By_UserId(Long id);

    public CartItem getCartItemById(Long cartItemId);

    //public CartItem deleteCartItem_By_CartItemId(Long cartItemId);

    public void deleteCartItem_By_CartItemId(Long cartItemId);

    public void deleteCartItem_By_CartId(Long cartId);

    public boolean updateQuantity_By_CartItemId(Long cartItemId, int quantity);


}
