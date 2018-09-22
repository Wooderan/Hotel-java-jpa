CREATE DATABASE  IF NOT EXISTS `hotel` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hotel`;
-- MySQL dump 10.13  Distrib 5.7.23, for Linux (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.23-0ubuntu0.18.04.1

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
-- Table structure for table `checkin_client`
--

DROP TABLE IF EXISTS `checkin_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkin_client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) DEFAULT NULL,
  `id_checkin` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkin_client`
--

LOCK TABLES `checkin_client` WRITE;
/*!40000 ALTER TABLE `checkin_client` DISABLE KEYS */;
INSERT INTO `checkin_client` VALUES (7,10,23),(8,10,24),(9,10,25),(10,10,26),(11,10,27),(12,10,28),(13,10,29),(14,10,30),(15,10,31),(16,10,32),(17,10,33),(18,12,33),(19,12,34),(20,10,35),(21,17,36),(22,18,37),(23,17,38),(24,11,39);
/*!40000 ALTER TABLE `checkin_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkins`
--

DROP TABLE IF EXISTS `checkins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_settlement` blob,
  `date_release` blob,
  `note` varchar(45) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `room` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkins`
--

LOCK TABLES `checkins` WRITE;
/*!40000 ALTER TABLE `checkins` DISABLE KEYS */;
INSERT INTO `checkins` VALUES (23,_binary '2018-09-22',_binary '2018-09-24','empty',1,18),(24,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(25,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(26,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(27,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(28,_binary '2018-09-22',_binary '2018-09-24','empty',1,18),(29,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(30,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(31,_binary '2018-09-22',_binary '2018-09-23','empty',2,18),(32,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(33,_binary '2018-09-22',_binary '2018-09-24','empty',1,19),(34,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(35,_binary '2018-09-25',_binary '2018-09-26','empty',1,21),(36,_binary '2018-09-22',_binary '2018-09-23','empty',1,18),(37,_binary '2018-09-25',_binary '2018-09-26','empty',1,21),(38,_binary '2018-09-22',_binary '2018-09-26','empty',1,19),(39,_binary '2018-09-22',_binary '2018-09-24','empty',0,19);
/*!40000 ALTER TABLE `checkins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `passport` varchar(45) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `passport_UNIQUE` (`passport`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (11,'Ivan','Ivanov','Ivanovich','UK456','no comment'),(12,'Alla','Ivanova','Ivanovna','UK789','no comment'),(13,'Petro','Petrov','Petrovich','UK1234','no comment'),(15,'Maks','Petrov','Maksimovich','UK5678','no comment'),(16,'Oleg','Boichuk','Antonovich','Uk426','no comment'),(17,'Yaric','Krivulia','Kostyantinovich','UK873','no comment'),(18,'Eugeno','Krivulia','Kostiantynovich','UK123','no comment');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `number_peoples` int(11) DEFAULT NULL,
  `comfortable` blob,
  `state` blob,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (18,'Pharaon',1,1,_binary '1',_binary '0',110),(19,'Paris',2,2,_binary '0',_binary '1',230),(20,'Venice',3,2,_binary '0',_binary '0',240),(21,'Regular',4,1,_binary '2',_binary '0',90),(22,'Regular',5,1,_binary '2',_binary '0',90);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-22 23:36:14
