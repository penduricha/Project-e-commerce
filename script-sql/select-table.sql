use ecommerce_shop;

select * from user;

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


select * from product where product_type_id = 17;

select * from ware_house where product_id is null;

select * from product_type pt left join product p
on pt.product_type_id=p.product_type_id;

update product set product_type_id = 90 where product_id =118;