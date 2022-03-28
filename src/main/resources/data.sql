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
insert into products (code, name, price, online_catalogue, create_date)
values ('S001', 'Game 1', 100, true, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S001', 'Game 1', 100, false, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S002', 'Game 2', 50, true, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S002', 'Game 2', 50, false, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S003', 'Game 3', 120, true, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S003', 'Game 3', 120, false, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S004', 'Game 4', 120, true, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S004', 'Game 4', 120, false, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S005', 'Game 5', 110, true, current_timestamp);

insert into products (code, name, price, online_catalogue, create_date)
values ('S005', 'Game 5', 110, false, current_timestamp);
