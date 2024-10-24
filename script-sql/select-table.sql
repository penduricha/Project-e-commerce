use ecommerce_shop;

select * from user where id = 24;

# delete from product_type;
# select * from product;
#


# delete from product_type where product_type_id = 37;

select * from product_type p where p.parent_id is null;

select * from product_type p where p.parent_id = 39;

show columns from product;

show columns from ware_house;

select * from ware_house;

select * from product;

# delete from product;

select * from ware_house;

select * from product_type;

select * from product_type pt left join product p
on pt.product_type_id=p.product_type_id;

select * from product p left join ware_house w
on p.product_id=w.product_id;

select * from event_purchasing;

select * from mapping_event_purchasing_product;

select mpe.product_id, mpe.event_purchasing_id, wh.ware_house_id
from mapping_event_purchasing_product mpe
left join product p on mpe.product_id = p.product_id
left join ware_house wh on p.product_id = wh.product_id;

select product_id from product order by product_id;

select * from ware_house w
left join discount d
on w.ware_house_id = d.ware_house_id
right join product p on p.product_id = w.product_id
where w.product_id = 38;

select * from ware_house order by ware_house_id;

select p.name, pt.type_product, pt.product_type_id from product_type pt
left join product p on pt.product_type_id = p.product_type_id
where product_id is not null order by product_id;