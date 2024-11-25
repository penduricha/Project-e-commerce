package com.example.project_e_commerce_backend.repositories;

import com.example.project_e_commerce_backend.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> findByCart_CartIdOrderByCartItemIdAsc(Long cartId);

    CartItem findCartItemByCartItemId(Long cartItemId);

    //void deleteByCartItemId(Long cartItemId);

    @Modifying
    @Query("delete from CartItem where cartItemId = :cartItemId")
    void removeCartItemsByCartItemId(@Param("cartItemId") Long cartItemId);
}
