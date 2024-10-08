use ecommerce_shop;

select * from user;

select * from product;

select * from product_type;

select * from ware_house;

select * from discount;

# delete from product;
# delete from product_type;

-- lay ds sp va co theo loai
# SELECT
#     p.id AS product_id,
#     p.name AS product_name,
#     pt.name AS product_type_name
# FROM
#     Product p
#         JOIN
#     ProductType pt ON p.product_type_id = pt.id
# WHERE
#     p.product_type_id = ?; -- Thay ? bằng giá trị n-1 bạn muốn

select
    p.product_id,
    p.name,
    p.description,
    p.status,
    pt.type_product
from product p
left join product_type pt on p.product_type_id = pt.product_type_id
order by p.product_type_id;

-- where pt.type_product ='Ball Sport'



