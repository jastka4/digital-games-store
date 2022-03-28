drop table if exists authorities cascade;

drop table if exists user_authorities cascade;

drop table if exists users cascade;

drop table if exists order_details cascade;

drop table if exists products cascade;

drop table if exists orders cascade;

-- Create table
create table users
(
    id       INT          NOT NULL PRIMARY KEY,
    username varchar(50)  NOT NULL,
    password varchar(100) NOT NULL,
    enabled  boolean      not null DEFAULT true
);
---------------------------------------
-- Create table
create table authorities
(
    id   INT          NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
---------------------------------------
-- Create table
create table user_authorities
(
    authority_id INT not null,
    user_id      INT not null,
    CONSTRAINT user_authorities_users_fk foreign key (user_id) references users (id)
);

CREATE UNIQUE INDEX ix_user_authorities_authority_id_user_id on user_authorities (authority_id, user_id);
---------------------------------------
create table products
(
    id               SERIAL                      not null PRIMARY KEY,
    code             VARCHAR(20)                 not null,
    image            bytea,
    name             VARCHAR(255)                not null,
    price            double precision            not null,
    online_catalogue BOOLEAN                     not null,
    create_date      Timestamp without time zone not null
);

CREATE UNIQUE INDEX ix_products_code_online_catalogue on products (code, online_catalogue);
---------------------------------------
-- Create table
create table orders
(
    id               INT                         not null PRIMARY KEY,
    amount           double precision            not null,
    customer_address VARCHAR(255)                not null,
    customer_email   VARCHAR(128)                not null,
    customer_name    VARCHAR(255)                not null,
    customer_phone   VARCHAR(128)                not null,
    order_date       Timestamp without time zone not null,
    order_num        INT                         not null
);

alter table orders
    add constraint orders_uk unique (order_num);
---------------------------------------
-- Create table
create table order_details
(
    id                       INT              not null PRIMARY KEY,
    amount                   double precision not null,
    price                    double precision not null,
    quantity                 INT              not null,
    order_id                 INT              not null,
    product_code             VARCHAR(20)      not null,
    product_online_catalogue BOOLEAN          not null
);

alter table order_details
    add constraint order_details_ord_fk foreign key (order_id)
        references orders (id);
alter table order_details
    add constraint order_details_prod_fk foreign key (product_code, product_online_catalogue)
        references products (code, online_catalogue);
