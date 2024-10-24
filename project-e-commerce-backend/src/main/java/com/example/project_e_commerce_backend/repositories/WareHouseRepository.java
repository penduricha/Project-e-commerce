package com.example.project_e_commerce_backend.repositories;

import com.example.project_e_commerce_backend.dtos.WareHouseDto;

import com.example.project_e_commerce_backend.models.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WareHouseRepository extends JpaRepository<WareHouse,Long> {

    @Query("select " +
            "new com.example.project_e_commerce_backend.dtos.WareHouseDto(w.wareHouseId,w.size, w.color, w.quantity, w.image, w.price, coalesce(d.numberOfDiscounts, 0) )" +
            "from WareHouse w " +
            "left join Discount d on w.wareHouseId = d.wareHouse.wareHouseId where w.product.productId = :productId")
    List<WareHouseDto> getWareHousesByProduct_ProductId(@Param("productId") Long productId);

}
