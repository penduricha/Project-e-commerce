package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.models.Cart;
import com.example.project_e_commerce_backend.repositories.CartRepository;
import com.example.project_e_commerce_backend.services.I_CartService;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

@Service
public class CartService implements I_CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart saveCart(Long id) throws JpaSystemException {
        Cart cart = new Cart();
        cart.getUser().setId(id);
        return cartRepository.save(cart);
    }
}
