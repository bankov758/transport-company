CREATE DATABASE  IF NOT EXISTS `mydb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mydb`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `family_name` varchar(45) DEFAULT NULL,
  `company_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Employee_Company1_idx` (`company_id`),
  CONSTRAINT `fk_Employee_Company1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee_has_skill`
--

DROP TABLE IF EXISTS `employee_has_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_has_skill` (
  `Employee_ID` int NOT NULL,
  `skilll_id` int NOT NULL,
  PRIMARY KEY (`Employee_ID`,`skilll_id`),
  KEY `fk_Employee_has_skill_skill1_idx` (`skilll_id`),
  KEY `fk_Employee_has_skill_Employee1_idx` (`Employee_ID`),
  CONSTRAINT `fk_Employee_has_skill_Employee1` FOREIGN KEY (`Employee_ID`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_Employee_has_skill_skill1` FOREIGN KEY (`skilll_id`) REFERENCES `skill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `id` int NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `snd_time` datetime DEFAULT NULL,
  `arrival_point` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `departure_point` varchar(45) DEFAULT NULL,
  `payload_id` int NOT NULL,
  `Employee_id` int NOT NULL,
  `Company_id` int NOT NULL,
  `Vehicles_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_payload1_idx` (`payload_id`),
  KEY `fk_order_Employee1_idx` (`Employee_id`),
  KEY `fk_order_Company1_idx` (`Company_id`),
  KEY `fk_order_Vehicles1_idx` (`Vehicles_id`),
  CONSTRAINT `fk_order_Company1` FOREIGN KEY (`Company_id`) REFERENCES `company` (`id`),
  CONSTRAINT `fk_order_Employee1` FOREIGN KEY (`Employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_order_payload1` FOREIGN KEY (`payload_id`) REFERENCES `payload` (`id`),
  CONSTRAINT `fk_order_Vehicles1` FOREIGN KEY (`Vehicles_id`) REFERENCES `vehicles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payload`
--

DROP TABLE IF EXISTS `payload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payload` (
  `id` int NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `vehicles`
--

DROP TABLE IF EXISTS `vehicles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicles` (
  `id` int NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `capacity` float DEFAULT NULL,
  `company_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Vehicles_Company1_idx` (`company_id`),
  CONSTRAINT `fk_Vehicles_Company1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-11 21:08:21
