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

//import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CartItemService implements I_CartItemService {

    private final CartItemRepository cartItemRepository;

    private final CartService cartService;

    private final UserService userService;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository, CartService cartService, UserService userService) {
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
        this.userService = userService;
    }

    @Transactional
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

    @Transactional
    @Override
    public CartItem saveCartItem_By_Email_Or_PhoneNumber(String emailOrPhoneNumber, CartItem cartItem) throws JpaSystemException{
        Long userId = userService.getIdByEmailOrPhoneNumber(emailOrPhoneNumber.trim());
        if(userId != null){
            return saveCartItem(userId, cartItem);
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
    public List<CartItem> getListCartItem_By_Email_Or_PhoneNumber(String emailOrPhoneNumber) throws JpaSystemException {
        Long userId = userService.getIdByEmailOrPhoneNumber(emailOrPhoneNumber.trim());
        List<CartItem> cartItems;
        if(userId != null){
            cartItems = getListCartItem_By_UserId(userId);
            return cartItems;
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
    public void deleteCartItem_By_UserId(Long id) throws JpaSystemException {
        List<CartItem> cartItems = getListCartItem_By_UserId(id);
        if(!cartItems.isEmpty()){
            for(CartItem cartItem: cartItems) {
                deleteCartItem_By_CartItemId(cartItem.getCartItemId());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }

    @Override
    public void updateQuantityBuy_By_CartItemId(Long cartItemId, int quantityBuy) throws JpaSystemException {
        CartItem cartItemFound = getCartItemById(cartItemId);
        if(cartItemFound != null){
            cartItemFound.setQuantityBuy(quantityBuy);
            cartItemRepository.save(cartItemFound);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found");
        }
    }

    @Override
    public CartItem findCartItemBy_ProductId_Size_Color(String emailOrPhoneNumber, Long productId, String size, String color) throws JpaSystemException {
        List<CartItem> cartItems = getListCartItem_By_Email_Or_PhoneNumber(emailOrPhoneNumber.trim());
        for(CartItem cartItem: cartItems){
            if(Objects.equals(cartItem.getProductId(), productId) &&
               cartItem.getSize().equalsIgnoreCase(size) &&
              cartItem.getColor().equalsIgnoreCase(color)
            ){
                return cartItem;
            }
        }
        return null;
    }

    @Override
    public CartItem findCartItemBy_ProductId_Size(String emailOrPhoneNumber, Long productId, String size) {
        List<CartItem> cartItems = getListCartItem_By_Email_Or_PhoneNumber(emailOrPhoneNumber.trim());
        for(CartItem cartItem: cartItems){
            if(Objects.equals(cartItem.getProductId(), productId) &&
                    cartItem.getSize().equalsIgnoreCase(size)
            ){
                return cartItem;
            }
        }
        return null;
    }

    @Override
    public CartItem findCartItemBy_ProductId_Color(String emailOrPhoneNumber, Long productId, String color) {
        List<CartItem> cartItems = getListCartItem_By_Email_Or_PhoneNumber(emailOrPhoneNumber.trim());
        if(!cartItems.isEmpty()){
            for(CartItem cartItem: cartItems){
                if(Objects.equals(cartItem.getProductId(), productId) &&
                        cartItem.getColor().equalsIgnoreCase(color)
                ){
                    return cartItem;
                }
            }
        }
        return null;
    }

    @Override
    public CartItem findCartItemBy_ProductId_If_Size_And_Color_Null(String emailOrPhoneNumber, Long productId) {
        List<CartItem> cartItems = getListCartItem_By_Email_Or_PhoneNumber(emailOrPhoneNumber.trim());
        if(!cartItems.isEmpty()){
            for(CartItem cartItem: cartItems){
                if(Objects.equals(cartItem.getProductId(), productId)
                ){
                    return cartItem;
                }
            }
        }
        return null;
    }
}
