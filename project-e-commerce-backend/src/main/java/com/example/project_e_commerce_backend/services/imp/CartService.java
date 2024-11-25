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
    public Cart getCartByUserId(Long id) throws JpaSystemException{
        return cartRepository.findByUser_Id(id);
    }
}
