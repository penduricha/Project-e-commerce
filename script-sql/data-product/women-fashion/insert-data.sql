use ecommerce_shop;

select * from product_type where parent_id is null;

select * from product_type where parent_id = 66;

show columns from ware_house;

# ALTER TABLE ware_house
# DROP CONSTRAINT UKh0vog5cavq25gxo0tb32f9kgb;

select * from ware_house;

show columns from product_type;

select * from product;

select * from ware_house;

select * from product where product_type_id is null;

update product set product_type_id = 76 where product_id = 109;

select * from product_type where product_type_id = 71;

select * from product where product_id= 108;

select *