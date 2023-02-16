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
-- Table structure for table `scene`
--

DROP TABLE IF EXISTS `scene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scene` (
  `scene_id` bigint NOT NULL AUTO_INCREMENT,
  `scene_file_name` varchar(255) DEFAULT NULL,
  `scene_number` int DEFAULT NULL,
  `scene_timestamp` time DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `story_id` bigint DEFAULT NULL,
  PRIMARY KEY (`scene_id`),
  KEY `FKakrq9gn1vu1wf1as8g7cscl1g` (`role_id`),
  KEY `FK5wgvktdlgqbhklchxxvyknriu` (`story_id`),
  CONSTRAINT `FK5wgvktdlgqbhklchxxvyknriu` FOREIGN KEY (`story_id`) REFERENCES `story` (`story_id`),
  CONSTRAINT `FKakrq9gn1vu1wf1as8g7cscl1g` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scene`
--

LOCK TABLES `scene` WRITE;
/*!40000 ALTER TABLE `scene` DISABLE KEYS */;
INSERT INTO `scene` VALUES (1,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/1_1.mp4',1,'00:05:00',2,1),(2,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/1_2.mp4',2,'00:27:00',1,1),(3,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/1_3.mp4',3,'00:37:00',2,1),(5,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/4_1.mp4',1,'00:00:00',5,4),(6,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/4_2.mp4',2,'00:10:00',6,4),(7,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/4_3.mp4',3,'00:35:00',5,4),(8,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_1.mp4',1,'00:00:00',2,2),(9,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_2.mp4',2,'00:14:00',1,2),(10,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_3.mp4',3,'00:17:00',2,2),(11,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_4.mp4',4,'00:25:00',1,2),(12,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_5.mp4',5,'00:29:00',2,2),(13,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_6.mp4',6,'00:41:00',1,2),(14,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_7.mp4',7,'00:48:00',1,2),(15,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_8.mp4',8,'01:05:00',2,2),(16,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/2_9.mp4',9,'01:11:00',1,2),(17,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/3_1.mp4',1,'00:00:00',7,3),(18,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/3_2.mp4',2,'00:14:00',8,3),(19,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/3_3.mp4',3,'00:33:00',8,3),(20,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/5_1.mp4',1,'00:00:00',10,5),(21,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/5_2.mp4',2,'00:02:00',9,5),(22,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/5_3.mp4',3,'00:16:00',10,5),(23,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/5_4.mp4',4,'00:18:00',9,5),(24,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/5_5.mp4',5,'00:30:00',10,5),(25,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/5_6.mp4',6,'00:44:00',9,5),(26,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/5_7.mp4',7,'00:48:00',10,5),(27,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/5_8.mp4',8,'00:49:00',9,5),(28,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/6_1.mp4',1,'00:00:00',9,6),(29,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/6_2.mp4',2,'00:18:00',10,6),(30,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/6_3.mp4',3,'00:43:00',9,6),(31,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/6_4.mp4',4,'00:53:00',10,6),(32,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/6_5.mp4',5,'00:56:00',9,6),(33,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/7_1.mp4',1,'00:00:00',13,7),(34,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/7_2.mp4',2,'00:02:00',14,7),(35,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/7_3.mp4',3,'00:07:00',13,7),(36,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/7_4.mp4',4,'00:11:00',14,7),(37,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/7_5.mp4',5,'00:14:00',13,7),(38,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/7_6.mp4',6,'00:23:00',14,7),(39,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/7_7.mp4',7,'00:28:00',13,7),(40,'https://s3ffmpegtest.s3.ap-northeast-2.amazonaws.com/scenes/7_8.mp4',8,'00:33:00',14,7);
/*!40000 ALTER TABLE `scene` ENABLE KEYS */;
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
