insert into authorities (id, name)
values (1, 'ROLE_ADMIN');

insert into authorities (id, name)
values (2, 'ROLE_USER');

---------------------------------------
insert into users (id, username, enabled, password)
values (1, 'admin1', true, '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');

insert into users (id, username, enabled, password)
values (2, 'user1', true, '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');

---------------------------------------
insert into user_authorities (authority_id, user_id)
values (1, 1);

insert into user_authorities (authority_id, user_id)
values (2, 2);

---------------------------------------
insert into products (id, code, name, price, online_catalogue, create_date)
values (1, 'S001', 'Game 1', 100, true, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (2, 'S001', 'Game 1', 100, false, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (3, 'S002', 'Game 2', 50, true, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (4, 'S002', 'Game 2', 50, false, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (5, 'S003', 'Game 3', 120, true, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (6, 'S003', 'Game 3', 120, false, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (7, 'S004', 'Game 4', 120, true, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (8, 'S004', 'Game 4', 120, false, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (9, 'S005', 'Game 5', 110, true, current_timestamp);

insert into products (id, code, name, price, online_catalogue, create_date)
values (10, 'S005', 'Game 5', 110, false, current_timestamp);
