CREATE database tienda_db;
USE tienda_db;
 
CREATE TABLE roles(
    id int primary key not null auto_increment,
    name varchar(50) not null
);

select * from users;
select * from roles;

update users set rol_id = 1;

SET SQL_SAFE_UPDATES = 0;

CREATE TABLE users(
    id int primary key not null auto_increment,
    username varchar(50) not null unique,
    email varchar(255) not null unique,
    password varchar(255) not null,
    active boolean not null default true,
    rol_id int not null,
    foreign key (rol_id) references roles (id)
);

CREATE TABLE categories(
    id int primary key not null auto_increment,
    name varchar(100) not null unique
);

select*from categories;
select*from products;

CREATE TABLE products(
    id int primary key not null auto_increment,
    name varchar(150) not null,
    description varchar(500),
    price decimal(12,2) not null,
    stock int not null,
    category_id int not null,
    seller_id int not null,
    foreign key (category_id) references categories (id),
    foreign key (seller_id) references users (id)
);
 
CREATE TABLE orders(
    id int primary key not null auto_increment,
    client_id int not null,
    product_id int not null,
    quantity int not null,
    total decimal(12,2) not null,
    status varchar(20) not null default 'PENDIENTE',
    created_at datetime not null default current_timestamp,
    foreign key (client_id) references users (id),
    foreign key (product_id) references products (id)
);

DELIMITER //
CREATE PROCEDURE pa_get_ordes_seller(IN p_seller_id BIGINT)
BEGIN
    SELECT o.id, o.client_id, o.created_at, o.product_id, o.quantity, o.status, o.total
    FROM orders o
    JOIN products p ON o.product_id = p.id
    WHERE p.seller_id = p_seller_id;
END //
DELIMITER ;
select*from orders;
drop table orders;
INSERT INTO roles (name) VALUES ('ADMIN'), ('CLIENTE'), ('VENDEDOR');