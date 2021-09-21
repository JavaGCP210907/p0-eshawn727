CREATE TABLE customers (
	cust_num serial PRIMARY KEY NOT NULL,
	f_name TEXT NOT NULL,
	l_name TEXT NOT NULL
	--street_fk TEXT REFERENCES cust_address (street)
);
DROP TABLE customers;

CREATE TABLE products (
	prod_id serial PRIMARY KEY,
	prod_name TEXT NOT NULL,
	prod_type TEXT NOT NULL,
	prod_production_cost decimal (10, 2) NOT NULL,
	prod_sale_price decimal (10, 2) NOT NULL 
);
DROP TABLE products;

CREATE TABLE orders (
	order_num serial PRIMARY KEY,
	prod_id_fk int REFERENCES products (prod_id) ON DELETE CASCADE,
	cust_num_fk int REFERENCES customers (cust_num) ON DELETE CASCADE  
);
DROP TABLE orders;

CREATE TABLE cust_address (
	street TEXT PRIMARY KEY,
	city TEXT,
	state char(2),
	zip int
);
DROP TABLE cust_address;

--Show Tables--
SELECT * FROM customers;
SELECT * FROM products;
SELECT * FROM orders;
--SELECT * FROM cust_address;

--Customer join with address
--SELECT * FROM customers
--INNER JOIN cust_address ON street = street_fk;

--Populate DB with 5 customers
INSERT INTO customers (f_name, l_name)
VALUES ('Dave', 'Jones'), ('Jim','Howard'), ('Kelly', 'Thomas'), ('Jamie', 'Williams'), ('Mike', 'Davis');

--Populate products with 5 items
INSERT INTO products (prod_name, prod_type, prod_production_cost, prod_sale_price)
VALUES ('Toy Car', 'Toy', .50, 1.50),
		('Toy Truck', 'Toy', .75, 3.00),
		('TV', 'Electronics', 450.00, 700.00),
		('Recliner', 'Furniture', 200.00, 375.50),
		('Milk', 'Grocery', 1.25, 3.50);
		
--Populate orders with 5 records
INSERT INTO orders (prod_id_fk, cust_num_fk)
VALUES (1, 1),
		(2, 1),
		(5, 1),
		(1, 4),
		(3, 5);

--Join orders with products
SELECT * FROM orders 
INNER JOIN products ON prod_id = prod_id_fk;

--Sum all products in orders table for total sales
SELECT sum(prod_sale_price)
FROM products, orders 
WHERE products.prod_id = orders.prod_id_fk;

