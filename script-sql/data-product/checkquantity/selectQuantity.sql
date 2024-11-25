select * from product;

select * from ware_house;

select * from cart;

select * from user where id = 24;

select * from product_type where product_type_id = 73;

select * from product_type where product_type_id = 7;

select * from product where name = 'Cooker Mitsubishi';

update product set product_type_id = 73 where product_id = 125;

select * from product p
left join ware_house wh
on p.product_id = wh.product_id
where p.product_id = 40
and wh.color = '#806919'
and wh.size = 'M';


select p.product_id, p.name
from Product p
         join mapping_event_purchasing_product mpe
              on p.product_id = mpe.product_id
         join event_purchasing e
              on mpe.event_purchasing_id = e.event_purchasing_id
where e.name_event_purchasing = 'Flash Sales' limit 8;

select * from event_purchasing;

select p.product_id, p.name, w.image, w.price, coalesce(d.number_of_discounts, 0) as numberOfDiscount
from Product p
         join mapping_event_purchasing_product mpe on p.product_id = mpe.product_id
         join event_purchasing e on mpe.event_purchasing_id = e.event_purchasing_id
         left join (
    select product_id, min(ware_house_id) as warehouse_id
    from ware_house
    group by product_id
) w_min on p.product_id = w_min.product_id
         left join ware_house w on w.ware_house_id = w_min.warehouse_id
         left join discount d on d.ware_house_id = w.ware_house_id
where (e.name_event_purchasing = 'New' or e.name_event_purchasing = 'Explore Our Products')
  and w.quantity > 0 order by product_id;

select * from user order by id;


