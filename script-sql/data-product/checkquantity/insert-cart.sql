select id from user order by id;

select * from cart;


INSERT INTO cart (id)
VALUES
   (5), (6), (8), (9), (10), (11), (12),
    (13), (14), (15), (16), (18), (20),
    (21), (22),  (24), (25), (26), (27), (28),
    (29), (30), (31), (32), (33), (34), (35), (36),
    (37), (38), (39), (40), (41);

select u.id, u.first_name, u.middle_name, u.last_name,u.phone_number, c.cart_id
from user u
left join cart c on u.id = c.id order by u.id;

update cart set cart_id = 77 where id = 42;
update cart set cart_id = 78 where id = 43;

delete from user where id = 42 or id = 43;

select * from user where id = 42 or id = 43;
