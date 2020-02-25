DROP TABLE IF EXISTS products;
CREATE TABLE products (
  id        INT NOT NULL AUTO_INCREMENT,
  name      VARCHAR(45) NULL,
  category  VARCHAR(45) NULL,
  price     DECIMAL(9,2) NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO products (name, category, price) VALUES ('Product1', 'Category1', 10.78);
INSERT INTO products (name, category, price) VALUES ('Product2', 'Category2', 17.24);
INSERT INTO products (name, category, price) VALUES ('Product3', 'Category2', 13.17);
INSERT INTO products (name, category, price) VALUES ('Product4', 'Category2', 18.20);
INSERT INTO products (name, category, price) VALUES ('Product5', 'Category1', 22.43);
