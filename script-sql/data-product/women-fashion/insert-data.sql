use ecommerce_shop;

insert into event_purchasing(event_purchasing_id,name_event_purchasing) values
(4,'Explore Our Products');

select * from event_purchasing;

select pt.type_product,p.product_id, p.name from product_type pt left join product p
on pt.product_type_id=p.product_type_id
where name is not null;

select * from mapping_event_purchasing_product;


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
where (e.name_event_purchasing = 'New' or e.name_event_purchasing ='Explore Our Products')
and w.quantity > 0 order by product_id;

select * from discount;

select p.product_id, p.name, d.number_of_discounts, w.ware_house_id from product p
left join ware_house w
on p.product_id = w.product_id
left join discount d
on d.ware_house_id = w.ware_house_id;

insert into discount(ware_house_id, number_of_discounts)values
(158,50),
(159,50),
(162,50),
(163,20),
(142,20),
(143,20),
(174,20),
(175,20);

with RankedProducts as (
    select
        p.product_id,
        p.name,
        w.image,
        w.ware_house_id,
        w.price,
        e.name_event_purchasing,
        coalesce(d.number_of_discounts, 0) as number_of_discounts,
        row_number() over (partition by p.product_id order by w.ware_house_id) as rn
    from
        Product p
            join mapping_event_purchasing_product mpe on p.product_id = mpe.product_id
            join event_purchasing e on mpe.event_purchasing_id = e.event_purchasing_id
            join ware_house w on w.product_id = p.product_id
            left join discount d on d.ware_house_id = w.ware_house_id
    where
        w.quantity > 0 and e.name_event_purchasing='Best Selling Product'
)
select
    product_id,
    name,
    image,
    ware_house_id,
    price,
    name_event_purchasing,
    number_of_discounts
from
    RankedProducts
where
    rn = 1
order by product_id;

select * from event_purchasing;

select * from product p
left join ware_house w on p.product_id = w.product_id
where p.product_id = 56;

select * from mapping_event_purchasing_product where event_purchasing_id = 1 order by product_id;

select product_id from product order by product_id;

insert into mapping_event_purchasing_product (event_purchasing_id, product_id) values
(1, 111),
(1, 112),
(1, 113),
(1, 114),
(1, 115),
(1, 116),
(1, 117),
(1, 118);

select * from mapping_event_purchasing_product where event_purchasing_id = 3 order by product_id;

select * from event_purchasing;

select product_id from product order by product_id ;

select distinct p.product_id from product p
left join ware_house w
on p.product_id = w.product_id
left join discount d
on d.ware_house_id = w.ware_house_id
where d.number_of_discounts is null order by product_id;

insert into mapping_event_purchasing_product (event_purchasing_id, product_id) values
(3, 40),
(3, 41),
(3, 42),
(3, 43),
(3, 44),
(3, 45);



with RankedProducts as (
    select
        p.product_id,
        p.name,
        w.image,
        w.ware_house_id,
        w.price,
        e.name_event_purchasing,
        coalesce(d.number_of_discounts, 0) as number_of_discounts,
        row_number() over (partition by p.product_id order by w.ware_house_id) as rn
    from
        Product p
            join mapping_event_purchasing_product mpe on p.product_id = mpe.product_id
            join event_purchasing e on mpe.event_purchasing_id = e.event_purchasing_id
            join ware_house w on w.product_id = p.product_id
            left join discount d on d.ware_house_id = w.ware_house_id
    where
        w.quantity > 0
        and
        (e.name_event_purchasing='Explore Our Products' or e.name_event_purchasing='New')
)
select
    product_id,
    name,
    image,
    ware_house_id,
    price,
    name_event_purchasing,
    number_of_discounts
from
    RankedProducts
where
    rn = 1
order by product_id;