DROP TABLE IF EXISTS products;
CREATE TABLE products (
  id    SERIAL PRIMARY KEY,
  name  VARCHAR(100) NOT NULL,
  brand VARCHAR(100) NULL,
  price NUMERIC(9,2)
);
INSERT INTO products (name, brand, price) VALUES ('Product6', 'Brand1', 16.22);
INSERT INTO products (name, brand, price) VALUES ('Product7', 'Brand1', 15.30);
INSERT INTO products (name, brand, price) VALUES ('Product8', 'Brand2', 12.17);
INSERT INTO products (name, brand, price) VALUES ('Product9', 'Brand2', 11.32);
INSERT INTO products (name, brand, price) VALUES ('Product10', 'Brand2', 17.24);
