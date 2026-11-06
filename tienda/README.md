Tienda API

API REST de una tienda en línea hecha con Spring Boot, Spring Data JPA (Hibernate), MySQL y autenticación con JWT. Maneja tres roles: ADMIN, CLIENTE y VENDEDOR.

Base URL: http://localhost:9090/api/v1/tienda
Puerto: 9090
Requisitos
Java 21
MySQL 8
Maven (no hace falta instalarlo, el proyecto incluye mvnw / mvnw.cmd)
1. Base de datos

Crea la base de datos y las tablas ejecutando este script en MySQL (en este orden):

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

 
La conexión se configura en src/main/resources/application.yaml. Por defecto usa:

yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tienda_db
    username: root
    password: 1234

Cambia username y password si tu MySQL usa otros.

2. Variables de entorno

El proyecto necesita dos variables para firmar los tokens JWT:

Variable	Descripción	Ejemplo
env.jwt.secret-key	Clave secreta en Base64 (mínimo 32 bytes)	uJQrpA6z/CUpO66ZacWP/Y307WRJ9ToYgPFIpdzPois=
env.jwt.token-expiration	Duración del token en milisegundos	600000 (10 minutos)

Para generar una clave nueva:

bash
openssl rand -base64 32

Elige una de estas formas de configurarlas:

a) VS Code: ya están en .vscode/launch.json, dentro de env. Edita ahí los valores y ejecuta con Run / Debug.

json
"env": {
    "env.jwt.secret-key": "TU_CLAVE_BASE64",
    "env.jwt.token-expiration": 600000
}

b) Variables del sistema (el nombre cambia los puntos y guiones por _ y va en mayúsculas):

Windows (PowerShell):

powershell
$env:ENV_JWT_SECRET_KEY = "TU_CLAVE_BASE64"
$env:ENV_JWT_TOKEN_EXPIRATION = "600000"

Linux / macOS:

bash
export ENV_JWT_SECRET_KEY="TU_CLAVE_BASE64"
export ENV_JWT_TOKEN_EXPIRATION="600000"

c) Argumentos al ejecutar con Maven:

bash
./mvnw spring-boot:run "-Dspring-boot.run.arguments=--env.jwt.secret-key=TU_CLAVE_BASE64 --env.jwt.token-expiration=600000"

No subas tu clave real a un repositorio público. Si ya la subiste, genera una nueva.

3. Cómo correr el proyecto
Asegúrate de que MySQL esté corriendo y de haber creado la base de datos (paso 1).
Configura las variables de entorno (paso 2).
Desde la carpeta del proyecto (donde está el pom.xml): Windows:
powershell
   .\mvnw.cmd spring-boot:run

Linux / macOS:

bash
   ./mvnw spring-boot:run

También puedes ejecutar la clase TiendaApplication desde VS Code con el launch.json.

Cuando veas Tomcat started on port 9090 (http) with context path '/api/v1/tienda', la API está lista en http://localhost:9090/api/v1/tienda.
4. Credenciales del ADMIN inicial
Campo	Valor
Usuario	pedro
Contraseña	12345678
Rol	ADMIN (id 1)

La app no crea este usuario sola. Créalo la primera vez así (después de insertar los roles):

1. Registra el usuario por la API. Se crea como CLIENTE:

http
POST http://localhost:9090/api/v1/tienda/auth/register
Content-Type: application/json

{
  "userName": "pedro",
  "email": "pedro@tienda.com",
  "password": "12345678",
  "active": true
}

Cambia el email por el que quieras usar.

2. Súbele el rol a ADMIN en MySQL:

sql
USE tienda_db;
UPDATE users SET rol_id = 1 WHERE username = 'pedro';

3. Inicia sesión:

http
POST http://localhost:9090/api/v1/tienda/auth/login
Content-Type: application/json

{
  "user": "pedro",
  "password": "12345678"
}

La respuesta trae el jwt. Envíalo en las siguientes peticiones con el header Authorization: Bearer <jwt>.

Con este usuario puedes crear otros administradores (POST /users/) y cambiar roles o estados de usuarios.