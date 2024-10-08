create or replace table ecommerce_shop.discount
(
    number_of_discounts double null,
    ware_house_id       bigint not null
);

alter table ecommerce_shop.discount
    add constraint `PRIMARY`
        primary key (ware_house_id);

create or replace table ecommerce_shop.product
(
    product_id      bigint                       not null,
    created_at      datetime                     not null,
    description     varchar(300) charset utf8mb3 null,
    name            varchar(100) charset utf8mb3 not null,
    removed_at      datetime                     null,
    status          varchar(50) charset utf8mb3  null,
    updated_at      datetime                     null,
    product_type_id bigint                       null
);

alter table ecommerce_shop.product
    add constraint `PRIMARY`
        primary key (product_id);

create or replace table ecommerce_shop.product_seq
(
    next_not_cached_value bigint(21)          not null,
    minimum_value         bigint(21)          not null,
    maximum_value         bigint(21)          not null,
    start_value           bigint(21)          not null comment 'start value when sequences is created or value if RESTART is used',
    increment             bigint(21)          not null comment 'increment value',
    cache_size            bigint(21) unsigned not null,
    cycle_option          tinyint(1) unsigned not null comment '0 if no cycles are allowed, 1 if the sequence should begin a new cycle when maximum_value is passed',
    cycle_count           bigint(21)          not null comment 'How many cycles have been done'
);

create or replace table ecommerce_shop.product_type
(
    product_type_id bigint auto_increment
        primary key,
    parent_id       bigint                      null,
    type_product    varchar(50) charset utf8mb3 not null
)
    auto_increment = 7;

alter table ecommerce_shop.product
    add constraint FKlabq3c2e90ybbxk58rc48byqo
        foreign key (product_type_id) references ecommerce_shop.product_type (product_type_id);

create or replace table ecommerce_shop.user
(
    id           bigint auto_increment
        primary key,
    address      varchar(255) charset utf8mb3 null,
    created_at   datetime                     not null,
    email        varchar(50) charset utf8mb3  null,
    first_name   varchar(30) charset utf8mb3  not null,
    last_name    varchar(30) charset utf8mb3  not null,
    middle_name  varchar(30) charset utf8mb3  not null,
    password     varchar(130) charset utf8mb3 not null,
    phone_number varchar(11) charset utf8mb3  null,
    updated_at   datetime                     null
)
    auto_increment = 19;

alter table ecommerce_shop.user
    add constraint UK4bgmpi98dylab6qdvf9xyaxu4
        unique (phone_number);

alter table ecommerce_shop.user
    add constraint UKob8kqyqqgmefl0aco34akdtpe
        unique (email);

create or replace table ecommerce_shop.ware_house
(
    ware_house_id bigint auto_increment
        primary key,
    color         varchar(20) charset utf8mb3  null,
    image         varchar(200) charset utf8mb3 not null,
    price         double                       not null,
    quantity      bigint                       not null,
    size          varchar(20) charset utf8mb3  null,
    product_id    bigint                       null
)
    auto_increment = 9;

alter table ecommerce_shop.discount
    add constraint FK1jmp9yyl5jea2b9t8bwomd0e1
        foreign key (ware_house_id) references ecommerce_shop.ware_house (ware_house_id);

alter table ecommerce_shop.ware_house
    add constraint FKc2aemr31otvnbp3ir44cv7rjl
        foreign key (product_id) references ecommerce_shop.product (product_id);


