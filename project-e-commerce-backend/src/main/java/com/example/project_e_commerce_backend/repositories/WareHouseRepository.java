package com.example.project_e_commerce_backend.repositories;

import com.example.project_e_commerce_backend.dtos.CartDto;
import com.example.project_e_commerce_backend.dtos.WareHouseDto;

import com.example.project_e_commerce_backend.models.WareHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WareHouseRepository extends JpaRepository<WareHouse,Long> {

    @Query("select " +
            "new com.example.project_e_commerce_backend.dtos.WareHouseDto(w.wareHouseId,w.size, w.color, w.quantity, w.image, w.price, " +
            "coalesce(d.numberOfDiscounts, 0) )" +
            "from WareHouse w " +
            "left join Discount d on w.wareHouseId = d.wareHouse.wareHouseId where w.product.productId = :productId order by w.wareHouseId")
    List<WareHouseDto> getWareHousesByProduct_ProductId(@Param("productId") Long productId);

    @Query("select new com.example.project_e_commerce_backend.dtos.CartDto(p.productId, p.name, wh.size, wh.color, wh.quantity) " +
            "from Product p left join p.wareHouseList wh " +
            "where p.productId = :productId and wh.size = :size and wh.color = :color")
    CartDto getCart_By_ProductId_Size_Color(
            @Param("productId") Long productId,
            @Param("size") String size,
            @Param("color") String color
    );

    @Query("select new com.example.project_e_commerce_backend.dtos.CartDto(p.productId, p.name, wh.size, wh.color, wh.quantity) " +
            "from Product p left join p.wareHouseList wh " +
            "where p.productId = :productId and wh.size = :size")
    CartDto getCart_By_ProductId_Size(
            @Param("productId") Long productId,
            @Param("size") String size
    );

    @Query("select new com.example.project_e_commerce_backend.dtos.CartDto(p.productId, p.name, wh.size, wh.color, wh.quantity) " +
            "from Product p left join p.wareHouseList wh " +
            "where p.productId = :productId and wh.color = :color")
    CartDto getCart_By_ProductId_Color(
            @Param("productId") Long productId,
            @Param("color") String color
    );

    @Query("select new com.example.project_e_commerce_backend.dtos.CartDto(p.productId, p.name, wh.size, wh.color, wh.quantity) " +
            "from Product p left join p.wareHouseList wh " +
            "where p.productId = :productId and wh.color is null and wh.size is null")
    CartDto getCart_By_ProductId_If_Size_And_Color_Null(
            @Param("productId") Long productId
    );
}
