CREATE SCHEMA IF NOT EXISTS jstl_tags;
USE jstl_tags;
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id_user INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL DEFAULT '',
  userpass VARCHAR(45) NOT NULL DEFAULT '',
  name VARCHAR(45) NOT NULL DEFAULT '',
  lastName VARCHAR(45) NOT NULL DEFAULT '',
  PRIMARY KEY  (id_user)
);

INSERT INTO users (username, userpass, name, lastName)
VALUES ('admin', 'admin','Cosme','Fulanito');