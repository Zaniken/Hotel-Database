-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotelmanagement
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerID` int(11) NOT NULL,
  `FirstName` varchar(20) DEFAULT NULL,
  `LastName` varchar(20) DEFAULT NULL,
  `Street` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `Postal_Code` int(11) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Phone_Number` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Christopher','Newton','123 Example Street','Enoch',84721,'1999-01-24','6025162778'),(2,'Parker','Rosenberg','124 Example Street','Paragonah',84721,'1980-10-15','4355901234'),(3,'Zaniken','Gurule','125 example Street','Cedar City',84721,'1997-01-19','4355929876');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `HotelID` int(11) NOT NULL,
  `HotelName` varchar(25) NOT NULL,
  `Street` varchar(50) DEFAULT NULL,
  `City` varchar(15) DEFAULT NULL,
  `State` varchar(2) DEFAULT NULL,
  `Zip` int(5) DEFAULT NULL,
  `Manager` varchar(25) DEFAULT NULL,
  `numofRooms` int(3) DEFAULT NULL,
  `Parking` enum('True','False') DEFAULT NULL,
  `Swimpool` enum('True','False') DEFAULT NULL,
  `wifi` enum('True','False') DEFAULT NULL,
  `PhoneNumber` varchar(10) DEFAULT NULL,
  `StaffID` int(11) DEFAULT NULL,
  PRIMARY KEY (`HotelID`),
  KEY `Hotel_Staff_FK` (`StaffID`),
  CONSTRAINT `Hotel_Staff_FK` FOREIGN KEY (`StaffID`) REFERENCES `staff` (`StaffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'The Hamilton','110 Example Avenue','Cedar City','UT',84720,'Chad Smith',100,'True','False','True','1234567890',1),(2,'Value Inn','420 N Street','Dothan','AL',84720,'Mack Holmes',100,'False','False','False','2495067489',2),(3,'Motel 69','69 South Avenue','San Francisco','CA',84720,'Eddie Keller',100,'True','True','True','9384756378',3);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `ResID` int(11) NOT NULL,
  `Res_Date` date DEFAULT NULL,
  `Total_Price` double DEFAULT NULL,
  `Payment_Method` varchar(50) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `HotelID` int(11) DEFAULT NULL,
  `RoomID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ResID`),
  KEY `Res_Cust_FK` (`CustomerID`),
  KEY `Res_Hotel_FK` (`HotelID`),
  KEY `Res_Room_FK` (`RoomID`),
  CONSTRAINT `Res_Cust_FK` FOREIGN KEY (`CustomerID`) REFERENCES `customer` (`CustomerID`),
  CONSTRAINT `Res_Hotel_FK` FOREIGN KEY (`HotelID`) REFERENCES `hotel` (`HotelID`),
  CONSTRAINT `Res_Room_FK` FOREIGN KEY (`RoomID`) REFERENCES `room` (`RoomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (100,'2019-11-25',99.99,'Visa',1,1,2),(101,'2019-11-23',299.95,'Mastercard',2,2,4),(102,'2019-11-24',10.01,'Cash',3,3,6);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `RoomID` int(11) NOT NULL,
  `Available` enum('True','False') DEFAULT NULL,
  `RoomNum` int(3) DEFAULT NULL,
  `BedType` varchar(10) DEFAULT NULL,
  `Smoking` enum('True','False') DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `HotelID` int(11) DEFAULT NULL,
  PRIMARY KEY (`RoomID`),
  KEY `Room_Hotel_FK` (`HotelID`),
  CONSTRAINT `Room_Hotel_FK` FOREIGN KEY (`HotelID`) REFERENCES `hotel` (`HotelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'True',101,'King','True',89.99,1),(2,'False',102,'Dbl Queen','False',99.99,1),(3,'True',101,'Queen','True',89.99,2),(4,'False',102,'Dbl King','True',299.95,2),(5,'False',101,'Dbl Queen','False',89.99,3),(6,'False',102,'King','False',10.01,3);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `StaffID` int(11) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `HotelID` int(11) DEFAULT NULL,
  `Shift` enum('Morning','Swing','Night') DEFAULT NULL,
  `Position` enum('Manager','FrontDesk','HouseKeeping','RoomService') DEFAULT NULL,
  `PhoneNum` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`StaffID`),
  KEY `Staff_Hotel_FK` (`HotelID`),
  CONSTRAINT `Staff_Hotel_FK` FOREIGN KEY (`HotelID`) REFERENCES `hotel` (`HotelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Chad','Smith',3,'Morning','Manager','2345678901'),(2,'Mack','Holmes',2,'Morning','Manager','3456789012'),(3,'Eddie','Keller',1,'Morning','Manager','4567890123'),(4,'Marmie','Newman',3,'Morning','HouseKeeping','1936574830'),(5,'Preston','Soto',2,'Morning','FrontDesk','3173649533'),(6,'Ben','Joseph',1,'Morning','RoomService','1039567389'),(7,'Thomas','Lawson',3,'Swing','HouseKeeping','9281570983'),(8,'May','Garcia',2,'Swing','FrontDesk','4735968473'),(9,'Susan','Phillips',1,'Swing','RoomService','8394756381'),(10,'Della','Gray',3,'Night','FrontDesk','3710472904'),(11,'Jill','Watson',2,'Night','HouseKeeping','0374926184'),(12,'Lee','Parsons',1,'Night','RoomService','4729103657');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `test` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-30 10:01:46
