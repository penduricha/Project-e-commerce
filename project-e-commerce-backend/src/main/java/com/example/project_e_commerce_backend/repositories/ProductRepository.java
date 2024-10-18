package com.example.project_e_commerce_backend.repositories;


import com.example.project_e_commerce_backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product,Long> {
//    @Query("select new com.example.project_e_commerce_backend.dtos.ProductViewDto(p.productId,p.name, p.status, pt.typeProduct) " +
//            "from Product p " +
//            "left join p.productType pt " +
//            "order by p.productId")
//    List<ProductViewDto> getAllProductAndType();
    @Query(value = "with RankedProducts as (" +
        "    select " +
        "        p.product_id, " +
        "        p.name, " +
        "        w.image, " +
        "        w.price, " +
        "        e.name_event_purchasing, " +
        "        coalesce(d.number_of_discounts, 0) AS number_of_discounts, " +
        "        row_number() OVER (partition by p.product_id order by w.ware_house_id) as rn " +
        "    from " +
        "        Product p " +
        "        join mapping_event_purchasing_product mpe on p.product_id = mpe.product_id " +
        "        join event_purchasing e on mpe.event_purchasing_id = e.event_purchasing_id " +
        "        join ware_house w on w.product_id = p.product_id " +
        "        left join discount d on d.ware_house_id = w.ware_house_id " +
        "    where " +
        "        e.name_event_purchasing = :eventName " +
        "        and w.quantity > 0 " +
        ") " +
        "select " +
        "    product_id, " +
        "    name, " +
        "    image, " +
        "    price, " +
        "    name_event_purchasing, " +
        "    number_of_discounts " +
        "from " +
        "    RankedProducts " +
        "where " +
        "    rn = 1 order by product_id",nativeQuery = true)
    List<Map<String, Object>> getProducts_By_Event(@Param("eventName") String eventName);

}
