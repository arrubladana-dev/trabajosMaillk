create table users(
id int primary key auto_increment,
name varchar (100),
email varchar(100),
phone int
);

create table products(
id int primary key auto_increment,
name_Product varchar(100),
price double,
stock int,

id_mark int,
foreign key (id_mark) references mark (id)
);

create table mark(
id int primary key auto_increment,
name varchar(50)
);

create table catalog(
id int primary key auto_increment,
name varchar(50),

id_product int,
foreign key (id_product) references products (id)
);

select * from catalog;

create table wish_list (
id int primary key auto_increment,
entry_date timestamp default current_timestamp,

id_product int,
id_user int,
foreign key (id_product) references products (id),
foreign key (id_user) references users (id)
);

### INSERCIONES 
insert into users (name, email, phone) values
('Juan Pérez', 'juan.perez@carvajal.com', 310555123),
('María Gómez', 'maria.gomez@gmail.com', 320444567),
('Carlos Rodríguez', 'carlos.rod@outlook.com', 315777890),
('Ana Martínez', 'ana.martinez@carvajal.com', 300111222);

insert into mark (name) values
('Norma'),
('Reprograf'),
('Edding'),
('Carvajal Mobiliario'),
('Kilométrico');


update products set stock = 0 where id = 4;

insert into products (name_product, price, stock, id_mark) values
('Resma de Papel A4 75g', 85000.00, 150, 2),
('Resma de Papel Carta 80g', 89000.00, 100, 2),
('Cuaderno Argollado 100H', 15000.00, 400, 1),
('Caja de Bolígrafos Negros x12', 12000.00, 250, 5),
('Silla Ergonómica Premium', 450000.00, 20, 4),
('Marcadores Borrables x4', 18500.00, 95, 3),
('Escritorio Modular Ejecutivo', 620000.00, 8, 4);

insert into catalog (name, id_product) values
('Útiles Escolares', 3),
('Papelería y Oficina', 1),
('Papelería y Oficina', 2),
('Papelería y Oficina', 4),
('Mobiliario de Oficina', 5),
('Mobiliario de Oficina', 7),
('Línea de Marcación', 6);


insert into wish_list (id_product, id_user) values
(4, 1),
(1, 1),
(4, 2),
(6, 2),
(5, 3),
(2, 4);
