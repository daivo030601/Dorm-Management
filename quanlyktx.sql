-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlyktx
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `IDAccount` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Permission` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`IDAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('AC1','admin','admin','admin'),('AC2','admin2','admin','admin');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartment` (
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NoRoom` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Gender` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDEmployee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`IDApartment`),
  KEY `fk_employee` (`IDEmployee`),
  CONSTRAINT `fk_employee` FOREIGN KEY (`IDEmployee`) REFERENCES `employee` (`IDEmployee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES ('A','20','Nam','TN1'),('B','20','Nữ','TN2');
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electricityandwaterbill`
--

DROP TABLE IF EXISTS `electricityandwaterbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electricityandwaterbill` (
  `IDEWBill` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDEmployee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Createday` date DEFAULT NULL,
  `ChiSoDauDien` double DEFAULT NULL,
  `ChiSoCuoiDien` double DEFAULT NULL,
  `ChiSoDauNuoc` double DEFAULT NULL,
  `ChiSoCuoiNuoc` double DEFAULT NULL,
  `Total` double DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDEWBill`),
  KEY `fk_employee_electricityandwaterbill` (`IDEmployee`),
  KEY `fk_room_electricityandwaterbill` (`IDRoom`),
  KEY `fk_apartment_electricityandwaterbill` (`IDApartment`),
  CONSTRAINT `fk_apartment_electricityandwaterbill` FOREIGN KEY (`IDApartment`) REFERENCES `apartment` (`IDApartment`),
  CONSTRAINT `fk_employee_electricityandwaterbill` FOREIGN KEY (`IDEmployee`) REFERENCES `employee` (`IDEmployee`),
  CONSTRAINT `fk_room_electricityandwaterbill` FOREIGN KEY (`IDRoom`) REFERENCES `room` (`IDRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electricityandwaterbill`
--

LOCK TABLES `electricityandwaterbill` WRITE;
/*!40000 ALTER TABLE `electricityandwaterbill` DISABLE KEYS */;
INSERT INTO `electricityandwaterbill` VALUES ('EW1','TN1','A101','A','2022-05-30',0,100,0,10,1000000,'Đã thu'),('EW2','TN2','B101','B','2022-05-30',0,200,0,10,1000000,'Chưa thu'),('EW3','TN1','A102','A','2022-05-30',0,200,0,10,1000000,'Chưa thu');
/*!40000 ALTER TABLE `electricityandwaterbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `IDEmployee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Fullname` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Birthday` date DEFAULT NULL,
  `Address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Position` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PhoneNumber` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDAccount` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`IDEmployee`),
  KEY `fk_account_employee` (`IDAccount`),
  CONSTRAINT `fk_account_employee` FOREIGN KEY (`IDAccount`) REFERENCES `account` (`IDAccount`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('TN1','Minh','2001-12-09','Vietnam','Trưởng nhà','0888734218','AC1'),('TN2','Đại','2001-01-01','Vietnam','Trưởng nhà','0123456789','AC2');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilities`
--

DROP TABLE IF EXISTS `facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facilities` (
  `IDFacilities` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Status` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Amount` int DEFAULT NULL,
  `Discripition` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`IDFacilities`),
  KEY `fk_room_facilities` (`IDRoom`),
  KEY `fk_aparment_facilities` (`IDApartment`),
  CONSTRAINT `fk_aparment_facilities` FOREIGN KEY (`IDApartment`) REFERENCES `apartment` (`IDApartment`),
  CONSTRAINT `fk_room_facilities` FOREIGN KEY (`IDRoom`) REFERENCES `room` (`IDRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilities`
--

LOCK TABLES `facilities` WRITE;
/*!40000 ALTER TABLE `facilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentbill`
--

DROP TABLE IF EXISTS `rentbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentbill` (
  `IDRBill` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDEmployee` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDStudent` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Createday` date DEFAULT NULL,
  `Total` double NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDRBill`),
  KEY `fk_room_rentbill` (`IDRoom`),
  KEY `fk_employee_rentbill` (`IDEmployee`),
  KEY `fk_apartment_rentbill` (`IDApartment`),
  KEY `fk_student_rentbill` (`IDStudent`),
  CONSTRAINT `fk_apartment_rentbill` FOREIGN KEY (`IDApartment`) REFERENCES `apartment` (`IDApartment`),
  CONSTRAINT `fk_employee_rentbill` FOREIGN KEY (`IDEmployee`) REFERENCES `employee` (`IDEmployee`),
  CONSTRAINT `fk_room_rentbill` FOREIGN KEY (`IDRoom`) REFERENCES `room` (`IDRoom`),
  CONSTRAINT `fk_student_rentbill` FOREIGN KEY (`IDStudent`) REFERENCES `student` (`IDStudent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentbill`
--

LOCK TABLES `rentbill` WRITE;
/*!40000 ALTER TABLE `rentbill` DISABLE KEYS */;
INSERT INTO `rentbill` VALUES ('RB1','TN2','B101','B','ST2','2021-05-03',6000000,'Đã thu'),('RB2','TN1','A101','A','ST1','2021-05-01',6000000,'Đã thu'),('RB3','TN1','A101','A','ST3','2021-05-03',6000000,'Chưa thu');
/*!40000 ALTER TABLE `rentbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDApartment` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NoStudent` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Status` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Type` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `RentingPrice` int DEFAULT NULL,
  PRIMARY KEY (`IDRoom`),
  KEY `fk_apartment_room` (`IDApartment`),
  CONSTRAINT `fk_apartment_room` FOREIGN KEY (`IDApartment`) REFERENCES `apartment` (`IDApartment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES ('A101','A','4','Hết chỗ','4',500000),('A102','A','2','Còn chỗ','4',500000),('A103','A','1','Còn chỗ','6',500000),('B101','B','4','Hết chỗ','4',500000),('B102','B','2','Còn chỗ','6',500000),('B103','B','0','Còn Chỗ','6',500000);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `IDStudent` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Fullname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Birthday` date DEFAULT NULL,
  `Gender` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDCard` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `PhoneNumber` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `University` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Syear` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Eyear` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IDRoom` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`IDStudent`),
  KEY `fk_student_room` (`IDRoom`),
  CONSTRAINT `fk_student_room` FOREIGN KEY (`IDRoom`) REFERENCES `room` (`IDRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('ST1','Nguyễn Văn B','2003-09-10','Nam','123783874','0168666687','ĐH CNTT','2','ĐX','2021','2024','A102'),('ST2','Nguyễn Thị A','2003-09-10','Nữ','123783874','0168666687','ĐH CNTT','2','ĐX','2021','2025','B101'),('ST3','Nguyễn Văn C','2003-02-10','Nam','123783874','0168666687','ĐH CNTT','2','ĐX','2021','2025','A101'),('ST4','Võ Thời Đại','2001-03-06','Nam','123783877','0949241357','ĐH CNTT','3','ĐX','2020','2024','A101'),('ST5','Sơn Ngọc Minh','2022-05-03','Nam','123786821','0949231347','ĐH CNTT','1','ĐX','2022','2025','A102'),('ST6','Vũ Thị Khánh Linh','2022-05-01','Nữ','1678123442','0949241333','USSH','2','ĐX','2021','2025','B102');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-16 23:04:24
