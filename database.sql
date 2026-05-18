CREATE DATABASE testdb;
USE testdb;

CREATE TABLE items(
   id INT PRIMARY KEY,
   name VARCHAR(100),
   price DOUBLE,
   type VARCHAR(50)
);