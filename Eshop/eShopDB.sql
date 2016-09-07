DROP DATABASE IF EXISTS eshop;
CREATE DATABASE eshop CHARSET = utf8 COLLATE = utf8_general_ci;
USE eshop;

CREATE TABLE cities (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   CITY_NAME NVARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE categories (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   CATEGORY_NAME NVARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE fotos (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   PICTURE_PATH NVARCHAR(150) NOT NULL
);

CREATE TABLE adresses (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   CITY_ID INT NOT NULL,
   STREET NVARCHAR(500) NOT NULL,
   POSTAL_CODE INT NOT NULL,
   FOREIGN KEY (CITY_ID) REFERENCES cities (ID) on delete cascade on update cascade
);

CREATE TABLE owner (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   COMPANY_NAME NVARCHAR(100) NOT NULL,
   MOL NVARCHAR(50) NOT NULL,
   EIK NVARCHAR(20) NOT NULL,
   EMAIL NVARCHAR(50) NOT NULL,
   IBAN NVARCHAR(50) NOT NULL,
   BIC NVARCHAR(20) NOT NULL,
   PHONE_NUMBER NVARCHAR(20) NOT NULL,
   ADRESS_ID INT NOT NULL,
   FOREIGN KEY (ADRESS_ID) REFERENCES adresses (ID) on delete cascade on update cascade
);

CREATE TABLE products (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	PRODUCT_NAME NVARCHAR(100) NOT NULL,
	QUANTITY INT NOT NULL,
   CATEGORY_ID INT NOT NULL,
   PRICE DOUBLE NOT NULL,
   DESCRIPTION NVARCHAR(4000) NOT NULL,
   FOREIGN KEY (CATEGORY_ID) REFERENCES  categories (ID) on delete cascade on update cascade
);

alter table products auto_increment=1000001;

CREATE TABLE users (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   LOGIN_NAME NVARCHAR(50) NOT NULL UNIQUE,
   LOGIN_PASSWORD NVARCHAR(200) NOT NULL,
   SAULT NVARCHAR(500) NOT NULL,
   IS_ADMIN BOOLEAN NOT NULL
);

CREATE TABLE customers (
   USER_ID INT NOT NULL PRIMARY KEY,
   FIRST_NAME NVARCHAR(100) NOT NULL,
   LAST_NAME NVARCHAR(100) NOT NULL,
   EMAIL NVARCHAR(50) NOT NULL UNIQUE,
   ADRESS_ID INT NOT NULL,
   FOREIGN KEY (USER_ID) REFERENCES users (ID) on delete cascade on update cascade,
   FOREIGN KEY (ADRESS_ID) REFERENCES adresses (ID) on delete cascade on update cascade
);

CREATE TABLE history (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   USER_ID INT NOT NULL,
   KEY_V DATETIME DEFAULT CURRENT_TIMESTAMP,
   STATUS NVARCHAR(50) NOT NULL,
   FOREIGN KEY (USER_ID) REFERENCES users (ID) on delete cascade on update cascade
);

CREATE TABLE carts (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CUSTOMER_ID INT NOT NULL,
	HISTORY_ID INT NOT NULL,
	FOREIGN KEY (CUSTOMER_ID) REFERENCES customers (USER_ID) on delete cascade on update cascade,
	FOREIGN KEY (HISTORY_ID) REFERENCES history (ID) on delete cascade on update cascade
);

CREATE TABLE orders (
	ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
   PRODUCT_ID INT NOT NULL,
   CART_ID INT NOT NULL,
   QUANTITY INT NOT NULL,
   FOREIGN KEY (PRODUCT_ID) REFERENCES products (ID) on delete cascade on update cascade,
   FOREIGN KEY (CART_ID) REFERENCES carts (ID) on delete cascade on update cascade
);

CREATE TABLE fotos_products (
    foto_id INT NOT NULL,  
    product_id INT NOT NULL,  
    PRIMARY KEY (foto_id, product_id),  
    FOREIGN KEY (foto_id) REFERENCES fotos(id) ON UPDATE CASCADE,  
    FOREIGN KEY (product_id) REFERENCES products(id) ON UPDATE CASCADE
);

INSERT INTO cities (CITY_NAME)
VALUES 
   ('Sofia'), 
   ('Plovdiv'),
   ('Varna'), 
   ('Burgas'),
   ('Ruse'),
   ('Pleven'), 
   ('Stara Zagora'),
   ('Kazanlak'),
   ('Sandanski'),
   ('Lovech'), 
   ('Gabrovo'),
   ('Montana'),
   ('Lom'), 
   ('Blagoevgrad'),
   ('Pernik'),
   ('Haskovo'), 
   ('Yambol'),
   ('Sliven');

INSERT INTO categories (CATEGORY_NAME)
VALUES 
   ('Phones'), 
   ('Tablets'),
   ('Computers'),
   ('TV'), 
   ('Games'),
   ('Foto & Video'),
   ('Devices'), 
   ('Books, Music & Movies'),
   ('Toys');
   
INSERT INTO adresses (CITY_ID, STREET, POSTAL_CODE)
VALUES 
   (1, 'Vitosha blvd. 1', 1000),
   (1, 'Kiril Drangov str. 17', 1220);
   
INSERT INTO owner (COMPANY_NAME, MOL, EIK, EMAIL, IBAN, BIC, PHONE_NUMBER, ADRESS_ID)
VALUES 
   ('E Shop LTD.', 'Bai Ivan', 'BG6506489712', 'shefa@eshop.bg', 'BG76UNCR700015207898', 'BGUNCRSF', '+359897123456', 1);

INSERT INTO products (PRODUCT_NAME, QUANTITY, CATEGORY_ID, PRICE, DESCRIPTION)
VALUES 
	('Samsung Galaxy J1 (2016)', 10, 1, 245.00, 'Смартфон Samsung Galaxy J1 (2016), 8GB, 4G, Gold'),
	('LEGO® NINJAGO™', 15, 9, 42.99, 'Преследване с нинджа байк 70600'),
	('LEGO® Classic', 8, 9, 52.99, 'Classic Комплект за творческо строителство 10702'),
	('Assassins Creed Chronicles', 6, 5, 55.99, 'Игра Assassins Creed Chronicles за PS4'),
	('Prince of Persia Revelations', 4, 5, 21.99, 'Игра Prince of Persia Revelations, PlayStation Portable'),
	('LED Smart Samsung 58"', 2, 4, 1099.99, 'Телевизор LED Smart Samsung 58J5200, 58" (147 cм), Full HD'),
	('Видеокамера JVC Quad-Proof', 3, 6, 619.99, 'Видеокамера JVC Quad-Proof R GZ-R410BEU, Full HD, Black'),
	('Lenovo Tab 2 7"', 13, 2, 149.00, 'Таблет Lenovo Tab 2 A7-10 ADAM c процесор Quad-Core MTK8127 1.30GHz, 7", IPS, 1GB DDR3, 8GB, Wi-Fi, Bluetooth, Android 4.4 Kit Kat, Черен'),
	('JavaScript & jQuery', 3, 8, 19.90, 'JavaScript & jQuery - практическо програмиране'),
	('Kid Rock - Born Free', 6, 8, 14.50, 'Music: CD Kid Rock - Born Free');

INSERT INTO users (LOGIN_NAME, LOGIN_PASSWORD, SAULT, IS_ADMIN)
VALUES 
	('koki', '6b40890c2581eb4af5980cd0c98fb42858b852353452a744e832decd6e38cd43', 'afa0a0aed5c276f46b58af12a889779d86cfeebd2de82a1181b105df5d94323e', true);
	
INSERT INTO customers (USER_ID, FIRST_NAME, LAST_NAME, EMAIL, ADRESS_ID)
VALUES 
	(1, 'Kaloyan', 'Tsvetkov', 'koki123@eshop.bg', 2);

INSERT INTO fotos (PICTURE_PATH)
VALUES 
	('pics/assasin_1.jpg'),
	('pics/assasin_2.jpg'),
	('pics/book_javascript.jpg'),
	('pics/jvc_cam_1.jpg'),
	('pics/jvc_cam_2.jpg'),
	('pics/kidrock_bornfree.jpg'),
	('pics/lego_1.jpg'),
	('pics/lego_clasic_1.jpg'),
	('pics/lego_clasic_2.jpg'),
	('pics/lego_clasic_3.jpg'),
	('pics/lego_ninjago_1.jpg'),
	('pics/lego_ninjago_2.jpg'),
	('pics/lenovo_tab2_1.jpg'),
	('pics/lenovo_tab2_2.jpg'),
	('pics/princeOfPersia.jpg'),
	('pics/Samsung_Logo.jpg'),
	('pics/samsungj1_1.jpg'),
	('pics/samsungj1_2.jpg'),
	('pics/samsungj1_3.jpg'),
	('pics/tv_samsung_58.jpg');

INSERT INTO fotos_products (foto_id, product_id)
VALUES 
	(16, 1000001),
	(17, 1000001),
	(18, 1000001),
	(19, 1000001),
	(7, 1000002),
	(11, 1000002),
	(12, 1000002),
	(7, 1000003),
	(8, 1000003),
	(9, 1000003),
	(10, 1000003),
	(1, 1000004),
	(2, 1000004),
	(15, 1000005),
	(16, 1000006),
	(20, 1000006),
	(4, 1000007),
	(5, 1000007),
	(13, 1000008),
	(14, 1000008),
	(3, 1000009),
	(6, 1000010);
	
/* Заявки за базата */
SELECT * FROM fotos 
INNER JOIN fotos_products on fotos.id = fotos_products.foto_id 
where fotos_products.product_id=1000001;

SELECT products.ID, PRODUCT_NAME, QUANTITY, CATEGORY_NAME, PRICE, DESCRIPTION 
FROM products INNER JOIN categories ON products.CATEGORY_ID = categories.ID 
WHERE categories.ID = 5;

DELETE FROM fotos_products WHERE fotos_products.product_id = 1000001;

DELETE FROM products WHERE ID = 1000001;

UPDATE products SET PRODUCT_NAME = 'fghdfgdfg', QUANTITY = 555, CATEGORY_ID = 1, PRICE = 9999, DESCRIPTION = 'fgdgdfgasdff fsadfasdfsad' WHERE products.ID = 1000007;