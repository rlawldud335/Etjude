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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` varchar(255) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `role_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','d@naver.com','unnamed','https://img.hankyung.com/photo/202107/03.18270549.1.jpg','GUEST'),('1xYL3ksuk4aBnSrbWq8wQZSAmvk2','taekyun.contact@gmail.com','TAEKYUN KIM','https://lh3.googleusercontent.com/a/AEdFTp4Jh0aGJtPzVfVrajEZhe1m6m3xKfecU0N0Otip=s96-c','USER'),('2','b@naver.com','정대만','https://img1.daumcdn.net/thumb/R1280x0/?fname=http://t1.daumcdn.net/brunch/service/user/6kqa/image/qfYWoNmaXGToCkWEowuqt86KGLM.jpg','GUEST'),('3','c@naver.com','user-c','https://img.hankyung.com/photo/202207/01.30770386.1.jpg','GUEST'),('7TctHiPKMxYNSmxt01ozQmrPgS82','dhlim715@gmail.com','임두현','https://lh3.googleusercontent.com/a/AEdFTp7D7yesFb57OItMFyeVHPjJJFjUYDCsxprFX5a2=s96-c','USER'),('DpHnkjbSzFQUav9vSdcVqm81vu53','etjudetest@gmail.com','황수정','https://lh3.googleusercontent.com/a/AEdFTp5HeRk_bPZccYCdoVmf4_ooL6t58tsrqD-xF5RM=s96-c','USER'),('g3bpMydVtKWyIphZVSMkGWfSbrA2','bottler3123@naver.com','박병우[서울_6반_A605]팀원','https://lh3.googleusercontent.com/a/AEdFTp6CEi0zh-FijmKKBWWW4GrG0pWyYb_Kog5sibg8=s96-c','USER'),('njpuIpTXdEb5GZqNIK2EaZjTM3m2','variety82p@gmail.com','김창현','https://lh3.googleusercontent.com/a/AEdFTp785zTWS85bSrX60KGNMykvVohzMsuRBkPO1WlJ=s96-c','USER'),('yIuJebfNgfawWBO0H3h4LQUWKvy1','rlawldud335@gmail.com','김지영[서울_15반_A605]팀원','https://lh3.googleusercontent.com/a/AEdFTp4fqlhfM2ORpBCJohMAYbq6BUwT9rD8hpM6Ive4=s96-c','USER');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17  0:18:00
