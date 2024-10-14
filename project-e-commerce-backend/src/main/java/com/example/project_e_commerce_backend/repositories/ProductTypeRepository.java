package com.example.project_e_commerce_backend.repositories;

import com.example.project_e_commerce_backend.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType,Long> {

    ProductType findByProductTypeId(Long productTypeId);

    ProductType findByTypeProduct(String typeProduct);

    @Query("select new ProductType(p.productTypeId,p.typeProduct,p.parentId) from ProductType p where p.parentId is null")
    List<ProductType> findProductTypesByParentIdNull();

    List<ProductType> findProductTypesByParentId(Long parentId);
}
