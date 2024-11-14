select * from product;

select * from ware_house;

select * from product p
left join ware_house wh
on p.product_id = wh.product_id
where p.product_id = 40
and wh.color = '#806919'
and wh.size = 'M';