package com.example.project_e_commerce_backend.repositories;

import com.example.project_e_commerce_backend.dtos.ProductViewDto;
import com.example.project_e_commerce_backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
//    @Query("select new com.example.project_e_commerce_backend.dtos.ProductViewDto(p.productId,p.name, p.status, pt.typeProduct) " +
//            "from Product p " +
//            "left join p.productType pt " +
//            "order by p.productId")
//    List<ProductViewDto> getAllProductAndType();
}
