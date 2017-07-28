-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cas
-- ------------------------------------------------------
-- Server version	5.6.36-log

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointmentId` int(11) NOT NULL AUTO_INCREMENT,
  `doctorId` int(11) NOT NULL,
  `patientId` int(11) NOT NULL,
  `serviceId` int(11) NOT NULL,
  `clockInId` int(11) DEFAULT NULL,
  `clockOutId` int(11) DEFAULT NULL,
  `startTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `endTime` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `clockInTime` datetime DEFAULT NULL,
  `clockOutTime` datetime DEFAULT NULL,
  PRIMARY KEY (`appointmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,9,11,1,NULL,NULL,'2017-06-15 10:00:00','2017-06-15 11:00:00',1,'2017-06-13 22:47:29',NULL),(2,10,12,2,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',2,NULL,'2017-06-15 02:34:44'),(3,12,14,1,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',0,NULL,NULL),(4,9,15,2,NULL,NULL,'2017-06-16 10:30:00','2017-06-16 10:00:00',1,'2017-06-15 02:34:37',NULL),(5,10,11,2,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',3,NULL,'2017-06-13 22:48:14'),(6,9,16,2,NULL,NULL,'2017-06-18 10:00:00','2017-06-18 10:30:00',0,NULL,NULL),(7,10,17,1,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',3,NULL,'2017-06-13 22:48:23'),(8,10,18,1,NULL,NULL,'2017-06-19 10:00:00','2017-06-19 11:00:00',1,'2017-06-15 15:01:18',NULL),(9,11,19,1,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',0,NULL,NULL),(10,12,20,2,NULL,NULL,'2017-06-01 10:00:00','2017-06-01 12:00:00',3,'2017-06-15 02:34:06','2017-06-15 02:34:14'),(11,10,16,2,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',3,'2017-06-15 02:31:06','2017-06-15 02:31:11'),(12,10,17,2,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',0,NULL,NULL),(13,9,19,1,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',-1,NULL,NULL),(14,12,11,2,NULL,NULL,'2017-06-19 10:00:00','2017-06-19 11:00:00',0,NULL,NULL),(15,9,20,1,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',3,'2017-06-15 00:42:03','2017-06-15 00:42:13'),(16,10,11,2,NULL,NULL,'2017-06-11 10:00:00','2017-06-11 11:00:00',0,NULL,NULL),(17,9,20,2,NULL,NULL,'2017-06-15 10:00:00','2017-06-15 10:00:00',0,NULL,NULL),(18,9,11,2,NULL,NULL,'2017-06-16 10:00:00','2017-06-16 10:30:00',0,NULL,NULL);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `doctorId` int(11) NOT NULL AUTO_INCREMENT,
  `personId` int(11) DEFAULT NULL,
  `specialization` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`doctorId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (9,61,'MD Maharishy University, Iowa'),(10,62,'BDS'),(11,70,'MMP'),(12,80,'NUM');
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `patientId` int(11) NOT NULL AUTO_INCREMENT,
  `personId` int(11) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patientId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (5,63,' Accident '),(6,64,'tetst'),(7,64,'tetst'),(8,68,'tetst'),(9,70,'tetst'),(10,72,'tetst'),(11,73,'tetst'),(12,74,'tetst'),(13,75,'tetst'),(14,75,'tetst'),(15,75,'tetst'),(16,76,'tetst'),(17,77,'tetst'),(18,77,'tetst'),(19,77,'tetst'),(20,80,'tetst');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `paymentId` int(11) NOT NULL AUTO_INCREMENT,
  `appointmentId` int(11) DEFAULT NULL,
  `totalAmount` double DEFAULT '0',
  `discountAmount` double DEFAULT '0',
  `paymentAmount` double DEFAULT '0',
  `paymentTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `remarks` text,
  PRIMARY KEY (`paymentId`),
  UNIQUE KEY `paymentId_UNIQUE` (`paymentId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (2,5,200,0,200,'2017-06-15 02:25:50','payed by Roy Bom'),(3,5,200,0,200,'2017-06-15 02:26:54','payed by Roy Bom'),(4,7,200,0,200,'2017-06-15 02:29:13','payed by Aisha Kulindwa'),(5,15,200,0,200,'2017-06-15 02:30:27','payed by Vu Tuan'),(6,11,200,0,200,'2017-06-15 02:31:15','payed by Shivali Jain'),(7,10,200,0,200,'2017-06-15 02:34:18','payed by Vu Tuan');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `personId` int(11) NOT NULL AUTO_INCREMENT,
  `fName` varchar(45) DEFAULT NULL,
  `mName` varchar(45) DEFAULT NULL,
  `lName` varchar(45) DEFAULT NULL,
  `dob` datetime DEFAULT CURRENT_TIMESTAMP,
  `ssn` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `passportNo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`personId`),
  UNIQUE KEY `ssn_UNIQUE` (`ssn`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (61,'John','','Smith','1950-05-02 00:00:00',NULL,'US','12345967'),(62,'Joe','','Yogi','1973-08-02 00:00:00',NULL,'US','12394567'),(63,'Nick','','Jonhs','1930-08-12 00:00:00',NULL,'US','aa'),(64,'Ram','','Tapei','2001-01-01 00:00:00','123','India','123'),(68,'Parwej','','Tuan','2001-01-01 00:00:00','12345','Pakisthan','123'),(70,'Venkatesh','','Swami','2001-01-01 00:00:00','1234','Bangladesh','123'),(72,'Chen','','Hu','2001-01-01 00:00:00','sds','Bhutan','123'),(73,'Roy','','Bom','2001-01-01 00:00:00','asdf','USA','123'),(74,'Mohammad','','Ali','2001-01-01 00:00:00','asdasd','Canada','123'),(75,'Mustafa','','Hedar','2001-01-01 00:00:00','asdasdasd','Afganisthan','123'),(76,'Shivali','','Jain','2001-01-01 00:00:00','000','India','123'),(77,'Aisha','','Kulindwa','2001-01-01 00:00:00','aaa','Tanzania','123'),(80,'Vu','','Tuan','2001-01-01 00:00:00','1233','Nepal','123'),(81,'Maria','','QQQ','1990-06-14 00:00:00','','','');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `serviceId` int(11) NOT NULL AUTO_INCREMENT,
  `serviceName` varchar(45) DEFAULT NULL,
  `cost` double DEFAULT '0',
  `duration` int(11) DEFAULT '30',
  PRIMARY KEY (`serviceId`),
  UNIQUE KEY `serviceId_UNIQUE` (`serviceId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='service duration in minutes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Consultation',30,30),(2,'Treatment',200,60);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-27 11:12:57
