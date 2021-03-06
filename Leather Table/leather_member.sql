CREATE DATABASE  IF NOT EXISTS `leather` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `leather`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: leather
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `member` (
  `member_id` varchar(16) NOT NULL,
  `member_pw` varchar(255) NOT NULL,
  `member_name` varchar(45) NOT NULL,
  `member_phone` varchar(20) NOT NULL,
  `member_post` int(11) DEFAULT NULL,
  `member_address1` varchar(45) DEFAULT NULL COMMENT '기본주소',
  `member_address2` varchar(45) DEFAULT NULL COMMENT '상세주소',
  `member_email` varchar(45) NOT NULL,
  `member_grade` varchar(5) NOT NULL DEFAULT '일반회원',
  `member_state` varchar(5) DEFAULT 'I',
  `member_authority` varchar(45) DEFAULT 'user',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('a12345678','$2a$10$xvHrPbCIU4kRlGproPEQqOLHeLFEthafQRVpYatF67.cdzTYz1fX.',' 이순신','01098765432',666666,'청주시 서원구','산남동','jihyo828@naver.com','일반회원','I','user'),('aaa123123','$2a$10$5iTfG37oAUSafPVVygJaCupq820oZYkMeTAu3JVIjoqPWkL5DNmYu','홍길동','01000000000',123456,'청주시서원구','사창동','jihyo828@naver.com','관리자','I','ADMIN'),('bbb123123','$2a$10$HKGgbBfjRFCT3PHEqoh8qexap76l8Id35K7/xBdRRakKyTTXLVTMi','유관순','01022222222',123456,'청주시서원구','사창동','jihyo828@naver.com','일반회원','I','user'),('ccc123123','$2a$10$t7F/J4oOnsb7YXT8WOgKmuhgoXNqO7FpY10QzTJ09/DPVFPvRZdxm','김구','01000000000',123456,'청주시서원구','사창동','jihyo828@naver.com','일반회원','I','user'),('ddd123123','$2a$10$6KZbB9z1mziDYZa4FG/dOetpUuzIQOKHxCeTLwWWmUPLvuxut2TOq','고길동','01000000000',123456,'청주시서원구','사창동','jihyo828@naver.com','일반회원','I','user'),('eee123123','$2a$10$kz3ePUtlqwhMXP90Qt5VVeCiGp0gPn4QUSk2HwYnjy6o6402RehZm','고길동','01000000000',123456,'청주시서원구','사창동','jihyo828@naver.com','일반회원','I','user'),('fff123123','$2a$10$8W1ybEVZnN.ik2sk6XvC.OQzOSZZdI5uJgbrrCm1Ceofk92Zn9iXW','고길동','01000000000',123456,'청주시서원구','사창동','jihyo828@naver.com','일반회원','I','user'),('ggg123123','$2a$10$xae1CK/yoyrOhqNE6/ZPAOkE47SjAkcOBTELSjvvp6r3cDl.vLPGm','고길동','01000000000',123456,'청주시서원구','사창동','jihyo828@naver.com','일반회원','I','user'),('hhh123123','$2a$10$wZZPEhH9dkVtETlCf/qKkeR.TWioWY36cXBtQxC8cBJ1Qpj.lswAi','유관순','01000000000',NULL,NULL,NULL,'jihyo828@naver.com','일반회원','I','user'),('iii123123','$2a$10$p3EzJ2Ua6P3mD7lHSisXLuT936Ag10OP6OUIIkNYUVz.jp7N8P43e','홍길동','01000000000',NULL,NULL,NULL,'jihyo828@naver.com','일반회원','I','user'),('jjj123123','$2a$10$VLGnMU2BvBomIT1MkcNz0OnIzVDAAOPRZux3YF8Qq3Mlm3ITgOwDe','신사임당','01000000000',NULL,NULL,NULL,'jihyo828@naver.com','일반회원','I','user'),('kkk123123','$2a$10$dmUlSxR8T3p/evrVhmaoUuI33vaAzladP1dLd/6Iy4g8LpDJI1B3a','홍길동','01000000000',NULL,NULL,NULL,'jihyo828@naver.com','일반회원','I','user'),('lll123123','$2a$10$YbxpPysRoMp3bdKWRR1U1O/ECkUjfecN4hmlAKg9XfYWw6l.BDUjS','고길동','01000000000',NULL,NULL,NULL,'jihyo828@naver.com','일반회원','I','user'),('mmm123123','$2a$10$M3RiS66hOW/mrddYBHepGODeILdmlID6tK9pDcOmjQjidqN5P3L0K','고길동','01012345678',NULL,'','','jihyo828@naver.com','일반회원','I','user');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-25 16:48:27
