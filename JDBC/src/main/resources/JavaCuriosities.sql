CREATE DATABASE  IF NOT EXISTS `javacuriosities` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `javacuriosities`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: javacuriosities
-- ------------------------------------------------------
-- Server version	5.7.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnos` (
  `id_alumno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `materias_cursadas` tinyint(3) unsigned DEFAULT '0',
  `promedio` double DEFAULT NULL,
  `auditoria` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `activo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id_alumno`),
  UNIQUE KEY `dni_UNIQUE` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES (1,'Alberto','Zend','30241233','1985-10-02',5,7.5,'2013-03-17 23:02:59',''),(2,'Macarena','Grinch','30126534','1986-01-07',15,8.9,'2013-03-17 23:02:59',''),(3,'Nicolas','Franco','29458712','1984-03-04',8,6.4,'2013-03-17 23:02:59',''),(4,'Luis%','Blinz','25904515','1983-05-06',20,7.3,'2013-03-17 23:02:59',''),(5,'Luis\\Ejemplo','Figueroa','28908333','1984-06-12',3,5.3,'2013-03-17 23:02:59',''),(6,'Natalia','Blanco','23786711','1978-02-11',25,8.6,'2013-03-17 23:02:59',''),(7,'Soledad','Quintana','29340043','1984-08-08',11,6,'2013-03-17 23:02:59',''),(8,'Cristina','Amarilla','33123454','1985-11-01',12,6.8,'2013-03-17 23:02:59',''),(9,'Patricia','Zoler','22802431','1976-07-04',9,9,'2013-03-17 23:02:59',''),(10,'Luis_','Bluer','32314598','1980-01-30',18,7.33,'2013-03-17 23:02:59',''),(11,'Emiliano','Vera','30747132','1982-01-01',17,8.5,'2013-03-17 23:02:59',''),(12,'lorena','Bluer','29245134','1987-08-03',15,8,'2013-03-17 23:02:59',''),(13,'Leonel','Iur','31032422','1985-05-07',13,7,'2013-03-17 23:02:59',''),(14,'Lorena','Armonin','31420223','1983-07-20',10,6.5,'2013-03-17 23:02:59','');
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bulk_table`
--

DROP TABLE IF EXISTS `bulk_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bulk_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bulk_table`
--

LOCK TABLES `bulk_table` WRITE;
/*!40000 ALTER TABLE `bulk_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `bulk_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos` (
  `id_curso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `codigo_curso` int(6) unsigned zerofill NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id_curso`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (1,'Java Basico',000001,''),(2,'Java Avanzado',000002,''),(3,'Introduccion a bases de datos y SQL',000003,''),(4,'UML',000004,''),(5,'Java Web',000005,''),(6,'Java Web Services',000006,''),(7,'Java Hibernate',000007,''),(8,'Admininistrador bases de datos',000008,''),(9,'SQL Performance',000009,''),(10,'Seguridad Web',000010,'');
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos_ofrecidos`
--

DROP TABLE IF EXISTS `cursos_ofrecidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos_ofrecidos` (
  `id_curso` int(11) NOT NULL,
  `id_turno` tinyint(4) NOT NULL,
  `precio` double unsigned DEFAULT NULL,
  PRIMARY KEY (`id_curso`,`id_turno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos_ofrecidos`
--

LOCK TABLES `cursos_ofrecidos` WRITE;
/*!40000 ALTER TABLE `cursos_ofrecidos` DISABLE KEYS */;
INSERT INTO `cursos_ofrecidos` VALUES (1,1,135.5),(1,2,150),(1,3,175),(2,3,200),(3,3,180),(4,2,165),(5,2,140.5),(5,3,170),(6,1,140),(6,2,145),(6,3,170.25),(7,3,190),(8,2,125);
/*!40000 ALTER TABLE `cursos_ofrecidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutos`
--

DROP TABLE IF EXISTS `institutos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institutos` (
  `id_instituto` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `cantidad_personal` int(10) unsigned NOT NULL,
  `barrio` varchar(50) DEFAULT NULL,
  `fecha_apertura` date DEFAULT NULL,
  PRIMARY KEY (`id_instituto`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutos`
--

LOCK TABLES `institutos` WRITE;
/*!40000 ALTER TABLE `institutos` DISABLE KEYS */;
INSERT INTO `institutos` VALUES (1,'Instituto 01',122,'Puerto Madero','1990-10-01'),(2,'Instituto 02',99,'Retiro','1991-03-05'),(3,'Instituto 03',143,'San Nicolas','1992-01-02'),(4,'Instituto 04',123,'San Telmo','1995-05-05'),(5,'Instituto 05',54,'Constitucion','1995-07-28'),(6,'Instituto 06',87,'Balvanera','1998-02-11'),(7,'Instituto 07',131,'Retiro','1996-08-09'),(8,'Instituto 08',24,'Recoleta','1996-09-26'),(9,'Instituto 09',78,NULL,'1999-11-04'),(10,'Instituto 10',43,'Balvanera','1994-05-06'),(11,'Instituto 11',59,'Palermo','1990-12-30'),(12,'Instituto 12',66,'San Telmo','1992-04-22'),(13,'Instituto 13',23,'Retiro','1994-12-15'),(14,'Instituto 14',58,'San Nicolas','1997-08-14'),(15,'Instituto 15',29,'Almagro','1999-04-03');
/*!40000 ALTER TABLE `institutos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turnos`
--

DROP TABLE IF EXISTS `turnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turnos` (
  `id_turno` tinyint(3) unsigned NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `activo` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id_turno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turnos`
--

LOCK TABLES `turnos` WRITE;
/*!40000 ALTER TABLE `turnos` DISABLE KEYS */;
INSERT INTO `turnos` VALUES (1,'Maniana',''),(2,'Noche',''),(3,'Tarde','');
/*!40000 ALTER TABLE `turnos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-17 14:04:57
