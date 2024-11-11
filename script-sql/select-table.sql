use ecommerce_shop;

select * from user where id = 24;

select * from user where email = 'thang@123.com' or phone_number ='02190739693';

select email from user where email is not null;

select phone_number from user where user.phone_number is not null;

select first_name, middle_name, last_name from user;

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

select * from ware_house where color is not null;

select size from ware_house where size is not null;

select image from ware_house where color is not null;

# clean mã màu thành hexa
# update ware_house
# set color = concat('#', lpad(round(rand() * 16777215), 6, '0'))
# where color is not null;

select * from product where product_type_id = 16 and product_id !=39;

with RankedProducts as (
    select
        p.product_id,
        p.name,
        w.image,
        w.ware_house_id,
        w.price,
        coalesce(d.number_of_discounts, 0) as number_of_discounts,
        row_number() over (partition by p.product_id order by w.ware_house_id) as rn
    from
        Product p
            join ware_house w on w.product_id = p.product_id
            left join discount d on d.ware_house_id = w.ware_house_id
    where
        w.quantity >= 0 and p.product_type_id = 16 and p.product_id !=39
)
select
    product_id,
    name,
    image,
    ware_house_id,
    price,
    number_of_discounts
from
    RankedProducts
where
    rn = 1
order by product_id;

select parent_id from product_type where product_type_id = 16;