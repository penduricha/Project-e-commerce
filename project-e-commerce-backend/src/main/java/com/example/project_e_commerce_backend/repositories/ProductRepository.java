package com.example.project_e_commerce_backend.repositories;

import com.example.project_e_commerce_backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product,Long> {
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

    @Query(value = "with prioritized_products as ( select p.product_id, p.name, w.image, w.price, coalesce(d.number_of_discounts, 0) as number_of_discounts,\n" +
            "e.name_event_purchasing, row_number() over (partition by p.product_id order by\n" +
            "    case\n" +
            "        when e.name_event_purchasing = 'New' then 1\n" +
            "        when e.name_event_purchasing = 'Explore Our Products' then 2 else 3 end) as rn\n" +
            "    from Product p\n" +
            "        join mapping_event_purchasing_product mpe on p.product_id = mpe.product_id\n" +
            "        join event_purchasing e on mpe.event_purchasing_id = e.event_purchasing_id\n" +
            "        left join (select product_id, min(ware_house_id) AS warehouse_id\n" +
            "                    from ware_house group by product_id\n" +
            "        ) w_min on p.product_id = w_min.product_id\n" +
            "    left join ware_house w on w.ware_house_id = w_min.warehouse_id\n" +
            "    left join discount d on d.ware_house_id = w.ware_house_id\n" +
            "    where (e.name_event_purchasing = 'New' or e.name_event_purchasing = 'Explore Our Products') and w.quantity > 0)\n" +
            "select product_id, name, image, name_event_purchasing, price, number_of_discounts\n" +
            "from prioritized_products\n" +
            "where\n" +
            "    rn = 1\n" +
            "order by\n" +
            "    product_id;", nativeQuery = true)
    List<Map<String, Object>> getProducts_By_event_Explore_Product();


    @Query(value="with RankedProducts as (\n" +
            "    select\n" +
            "        p.product_id,\n" +
            "        p.name,\n" +
            "        w.image,\n" +
            "        w.ware_house_id,\n" +
            "        w.price,\n" +
            "        e.name_event_purchasing,\n" +
            "        coalesce(d.number_of_discounts, 0) as number_of_discounts,\n" +
            "        row_number() over (partition by p.product_id order by w.ware_house_id) as rn\n" +
            "    from\n" +
            "        Product p\n" +
            "            join mapping_event_purchasing_product mpe on p.product_id = mpe.product_id\n" +
            "            join event_purchasing e on mpe.event_purchasing_id = e.event_purchasing_id\n" +
            "            join ware_house w on w.product_id = p.product_id\n" +
            "            left join discount d on d.ware_house_id = w.ware_house_id\n" +
            "    where\n" +
            "        w.quantity > 0\n" +
            "        and\n" +
            "        (e.event_purchasing_id = :eventPurchasingId)\n" +
            ")\n" +
            "select\n" +
            "    product_id,\n" +
            "    name,\n" +
            "    image,\n" +
            "    ware_house_id,\n" +
            "    price,\n" +
            "    name_event_purchasing,\n" +
            "    number_of_discounts\n" +
            "from\n" +
            "    RankedProducts\n" +
            "where\n" +
            "    rn = 1\n" +
            "order by product_id;", nativeQuery = true)
    List<Map<String, Object>> getProductsBy_EventPurchasingId(@Param("eventPurchasingId") Long eventPurchasingId);

    Product findProductByProductId(Long productId);
}
