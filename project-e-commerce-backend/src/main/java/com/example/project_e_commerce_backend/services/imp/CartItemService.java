package com.example.project_e_commerce_backend.services.imp;

import com.example.project_e_commerce_backend.models.Cart;
import com.example.project_e_commerce_backend.models.CartItem;
import com.example.project_e_commerce_backend.repositories.CartItemRepository;
import com.example.project_e_commerce_backend.services.I_CartItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CartItemService implements I_CartItemService {

    private final CartItemRepository cartItemRepository;

    private final CartService cartService;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, CartService cartService) {
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
    }

    @Override
    public CartItem saveCartItem(Long id, CartItem cartItem) throws JpaSystemException {

        Cart cartFound = cartService.getCartByUserId(id);

        if(cartFound != null){
            cartItem.setCart(cartFound);
            return cartItemRepository.save(cartItem);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }

    @Override
    public List<CartItem> getListCartItem() throws JpaSystemException {
        return cartItemRepository.findAll(Sort.by(Sort.Direction.ASC, "cartItemId"));
    }

    @Override
    public List<CartItem> getListCartItem_By_UserId(Long id) throws JpaSystemException {

        Cart cartFound = cartService.getCartByUserId(id);

        if(cartFound != null){
            return cartItemRepository.findByCart_CartIdOrderByCartItemIdAsc(cartFound.getCartId());
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }

    @Override
    public CartItem getCartItemById(Long cartItemId) throws JpaSystemException {
        return cartItemRepository.findCartItemByCartItemId(cartItemId);
    }

    @Override
    public void deleteCartItem_By_CartItemId(Long cartItemId) throws JpaSystemException {
        cartItemRepository.removeCartItemsByCartItemId(cartItemId);
    }

    @Override
    public void deleteCartItem_By_CartId(Long cartId) {

    }

    @Override
    public boolean updateQuantity_By_CartItemId(Long cartItemId, int quantity) {
        return false;
    }
}
