select w.ware_house_id, w.color from product p left join ware_house w
on p.product_id=w.product_id where p.product_id = 50;

update ware_house set color = '#0070E8' where ware_house_id =37;
update ware_house set color = '#FF0000' where ware_house_id =38;
update ware_house set color = '#EEDC39' where ware_house_id =39;
