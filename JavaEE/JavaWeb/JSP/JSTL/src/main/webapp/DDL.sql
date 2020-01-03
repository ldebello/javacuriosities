CREATE SCHEMA IF NOT EXISTS jstl_tags;
USE jstl_tags;
DROP TABLE IF EXISTS usuarios;
CREATE TABLE usuarios (
  id_usuario INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL DEFAULT '',
  userpass VARCHAR(45) NOT NULL DEFAULT '',
  nombre VARCHAR(45) NOT NULL DEFAULT '',
  apellido VARCHAR(45) NOT NULL DEFAULT '',
  PRIMARY KEY  (id_usuario)
);

INSERT INTO usuarios (username, userpass, nombre, apellido)
VALUES ('admin', 'admin','Cosme','Fulanito');