USE `javacuriosities`;
DROP procedure IF EXISTS `GET_ALUMNO`;

DELIMITER $$
USE `javacuriosities`$$
CREATE PROCEDURE `GET_ALUMNO` (IN id INT(10))
BEGIN
	SELECT * FROM alumnos WHERE id_alumno = id;
END$$

DELIMITER ;

