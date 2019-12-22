-- MySQL dump 10.15  Distrib 10.0.38-MariaDB, for Linux (i686)
--
-- Host: localhost    Database: Danila
-- ------------------------------------------------------
-- Server version	10.0.38-MariaDB

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
-- Table structure for table `Auto`
--

DROP TABLE IF EXISTS `Auto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Auto` (
  `AutoID` int(11) NOT NULL,
  `Model` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `year_V` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `volume_D` int(11) NOT NULL,
  `pNumber` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`AutoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Auto`
--

LOCK TABLES `Auto` WRITE;
/*!40000 ALTER TABLE `Auto` DISABLE KEYS */;
INSERT INTO `Auto` VALUES (1,'BMW','2000',2,'165'),(2,'Toyota Corolla','2006',2,'658'),(3,'Toyota Camry','2019',4,'189'),(4,'Audi TT','2019',3,'534'),(5,'Mercedes-Benz GL','2019',5,'896');
/*!40000 ALTER TABLE `Auto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Masters`
--

DROP TABLE IF EXISTS `Masters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Masters` (
  `MastersID` int(11) NOT NULL,
  `FIO` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Specif` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `money` int(11) NOT NULL,
  `Adress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Year_Job` int(11) NOT NULL,
  `Number_Phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`MastersID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Masters`
--

LOCK TABLES `Masters` WRITE;
/*!40000 ALTER TABLE `Masters` DISABLE KEYS */;
INSERT INTO `Masters` VALUES (1,'Azizov Ahmet','Universal',50000,'Samara',10,'87771304354'),(2,'Igilich Nurlan','Universal',70000,'Samara',20,'89934234244');
/*!40000 ALTER TABLE `Masters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Repair`
--

DROP TABLE IF EXISTS `Repair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Repair` (
  `IDREP` int(11) NOT NULL,
  `Name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `DATA_P` date NOT NULL,
  `quant` int(11) NOT NULL,
  `price_x1` int(11) NOT NULL,
  PRIMARY KEY (`IDREP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Repair`
--

LOCK TABLES `Repair` WRITE;
/*!40000 ALTER TABLE `Repair` DISABLE KEYS */;
INSERT INTO `Repair` VALUES (1,'Bearing','2019-10-10',100,1500),(2,'Brake fluid','2019-11-23',20,45000),(3,'Carburettor','2019-10-19',10,70000),(4,'Chassis','2019-12-02',4,200000),(5,'Coolant','2019-12-03',200,1000);
/*!40000 ALTER TABLE `Repair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Repairs`
--

DROP TABLE IF EXISTS `Repairs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Repairs` (
  `IDUSER` int(11) NOT NULL,
  `IDREP` int(11) NOT NULL,
  `quant` int(11) NOT NULL,
  `Price` int(11) NOT NULL,
  `MastersID` int(11) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`Date`),
  KEY `Repairs_fk0` (`IDUSER`),
  KEY `Repairs_fk1` (`IDREP`),
  KEY `Repairs_fk2` (`MastersID`),
  CONSTRAINT `Repairs_fk0` FOREIGN KEY (`IDUSER`) REFERENCES `User` (`IDUSER`),
  CONSTRAINT `Repairs_fk1` FOREIGN KEY (`IDREP`) REFERENCES `Repair` (`IDREP`),
  CONSTRAINT `Repairs_fk2` FOREIGN KEY (`MastersID`) REFERENCES `Masters` (`MastersID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Repairs`
--

LOCK TABLES `Repairs` WRITE;
/*!40000 ALTER TABLE `Repairs` DISABLE KEYS */;
INSERT INTO `Repairs` VALUES (4,1,5,8000,1,'2019-11-27'),(1,5,10,12000,1,'2019-12-01'),(2,2,1,55000,2,'2019-12-02'),(3,5,20,20000,1,'2019-12-03'),(5,2,1,70000,2,'2019-12-04');
/*!40000 ALTER TABLE `Repairs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `IDUSER` int(11) NOT NULL,
  `fiop` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Number_Phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `AutoID` int(11) NOT NULL,
  `price_repair` int(11) NOT NULL,
  PRIMARY KEY (`IDUSER`),
  KEY `User_fk0` (`AutoID`),
  CONSTRAINT `User_fk0` FOREIGN KEY (`AutoID`) REFERENCES `Auto` (`AutoID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Chuhnev Danila','89991709734',3,10000),(2,'Khadiullin Dmitry','89994536734',1,55000),(3,'Piatova Natasha','89171587373',4,20000),(4,'Bahtiiarov Ilnar','89291749634',2,8000),(5,'Feshin Gleb','89412709734',5,70000);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-19 19:16:59
