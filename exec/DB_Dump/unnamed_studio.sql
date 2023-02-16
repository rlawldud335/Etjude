CREATE DATABASE  IF NOT EXISTS `unnamed` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `unnamed`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: i8a605.p.ssafy.io    Database: unnamed
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.20.04.2

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
-- Table structure for table `studio`
--

DROP TABLE IF EXISTS `studio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studio` (
  `studio_id` bigint NOT NULL AUTO_INCREMENT,
  `captain_id` varchar(255) DEFAULT NULL,
  `studio_end_date` date DEFAULT NULL,
  `studio_repository` varchar(255) DEFAULT NULL,
  `studio_title` varchar(20) DEFAULT NULL,
  `story_id` bigint DEFAULT NULL,
  PRIMARY KEY (`studio_id`),
  KEY `FKb3lnu2ng3vjb9luvo7hqg3cb2` (`story_id`),
  CONSTRAINT `FKb3lnu2ng3vjb9luvo7hqg3cb2` FOREIGN KEY (`story_id`) REFERENCES `story` (`story_id`)
) ENGINE=InnoDB AUTO_INCREMENT=790 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studio`
--

LOCK TABLES `studio` WRITE;
/*!40000 ALTER TABLE `studio` DISABLE KEYS */;
INSERT INTO `studio` VALUES (755,'g3bpMydVtKWyIphZVSMkGWfSbrA2','2023-02-23','get Repository Method','개인팀',2),(766,'yIuJebfNgfawWBO0H3h4LQUWKvy1','2023-02-23','get Repository Method','지영이의 스튜디오',1),(772,'yIuJebfNgfawWBO0H3h4LQUWKvy1','2023-02-23','get Repository Method','짱스튜디오',1),(783,'DpHnkjbSzFQUav9vSdcVqm81vu53','2023-02-23','get Repository Method','2521',5),(785,'DpHnkjbSzFQUav9vSdcVqm81vu53','2023-02-23','get Repository Method','이태원',2),(787,'DpHnkjbSzFQUav9vSdcVqm81vu53','2023-02-23','get Repository Method','그해우리는',7),(789,'DpHnkjbSzFQUav9vSdcVqm81vu53','2023-02-23','get Repository Method','클라쓰',1);
/*!40000 ALTER TABLE `studio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17  0:18:02
