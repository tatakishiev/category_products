create table users
(
  id       bigserial primary key,
  username varchar not null,
  password varchar not null
);

create table categories
(
  id   bigserial primary key,
  name varchar not null
);

create table products
(
  id          bigserial primary key,
  name        varchar                           not null,
  category_id bigint references categories (id) not null,
  price       decimal,
  type        varchar
);

-- default user credentials: username: admin, password: admin
insert into users (username, password)
values ('admin', '$2a$10$9pnAOjLmJUl12Dfmg77iuOkD6P5KpM0L4bbmSg.XdxXEbWd7f4QAG');

insert into categories(name)
values ('Phones'),
       ('Laptops');
SELECT setval('categories_id_seq', max(id))
FROM categories;

insert into products(name, category_id, price, type)
VALUES ('IPhone 6S', 1, 600.00, 'PUBLIC'),
       ('IPhone 7', 1, 700.00, 'PUBLIC'),
       ('IPhone X', 1, 1000.00, 'PRIVATE'),
       ('Asus zenbook', 2, 1200.00, 'PUBLIC'),
       ('Mackbook pro', 2, 2600.00, 'PUBLIC');
SELECT setval('products_id_seq', max(id))
FROM products;