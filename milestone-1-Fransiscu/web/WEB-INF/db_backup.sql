-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: fpw19_DbSoruFrancesco
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `id_paper` bigint(20) unsigned NOT NULL,
  `id_user` bigint(20) unsigned NOT NULL,
  `isGraded` tinyint(4) DEFAULT NULL,
  `grade` tinyint(4) DEFAULT NULL,
  `gradingDate` date DEFAULT NULL,
  `authorComments` varchar(100) DEFAULT NULL,
  `organizerComments` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_paper`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`id_paper`) REFERENCES `paper` (`id_paper`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,6,1,3,'2019-04-01','author comment 1','organizer comment 1'),(2,6,1,2,'2018-12-03','author comment 2','organizer comment 2'),(3,6,0,NULL,NULL,NULL,NULL),(4,6,1,0,'2019-06-08','author comment 4','organizer comment 4'),(5,6,1,0,'2017-03-04','author comment 5','organizer comment 5'),(6,6,1,4,'2019-11-16','author comment 6','organizer comment 6'),(7,7,0,NULL,NULL,NULL,NULL),(8,7,1,1,'2017-11-05','author comment 8','organizer comment 8'),(9,7,1,2,'2019-03-28','author comment 9','organizer comment 9');
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paper`
--

DROP TABLE IF EXISTS `paper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paper` (
  `id_paper` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) unsigned DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `submissionDate` date DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `picture` varchar(50) DEFAULT NULL,
  `category` varchar(15) DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_paper`),
  UNIQUE KEY `id_paper` (`id_paper`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `paper_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paper`
--

LOCK TABLES `paper` WRITE;
/*!40000 ALTER TABLE `paper` DISABLE KEYS */;
INSERT INTO `paper` VALUES (1,6,'Inbox chiude','2019-03-29','inbox content',NULL,'AJAX','APERTO'),(2,6,'Google Stadia','2019-03-25','google stadia content',NULL,'SERVLET','RIFIUTATO'),(3,6,'Titolo random','2019-06-12','random content',NULL,'AJAX','VALUTAZIONE'),(4,6,'Articolo Prova di prova','2019-06-03','prova di prova content',NULL,'AJAX','APERTO'),(5,6,'Javascript test','2019-02-04','javascript content a caso here',NULL,'JAVASCRIPT','RIFIUTATO'),(6,6,'JSP HELP','2019-11-13','JSP content blabla',NULL,'JSP','APERTO'),(7,7,'Cosa ha di nuovo Java 10','2018-12-25','Content java',NULL,'JSP','VALUTAZIONE'),(8,7,'SS fatto semplice','2017-10-31','Content css',NULL,'CSS','ACCETTATO'),(9,7,'Altro articolo di test','2019-03-22','altro articolo content',NULL,'AJAX','APERTO');
/*!40000 ALTER TABLE `paper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer`
--

DROP TABLE IF EXISTS `reviewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewer` (
  `id_review` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_paper` bigint(20) unsigned DEFAULT NULL,
  `id_user` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_review`),
  UNIQUE KEY `id_review` (`id_review`),
  KEY `id_paper` (`id_paper`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `reviewer_ibfk_1` FOREIGN KEY (`id_paper`) REFERENCES `paper` (`id_paper`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reviewer_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer`
--

LOCK TABLES `reviewer` WRITE;
/*!40000 ALTER TABLE `reviewer` DISABLE KEYS */;
INSERT INTO `reviewer` VALUES (1,9,6),(2,7,6),(3,6,7),(4,4,7),(5,3,7),(6,1,7);
/*!40000 ALTER TABLE `reviewer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(24) NOT NULL,
  `surname` varchar(24) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(24) NOT NULL,
  `birthDate` date DEFAULT NULL,
  `entity` varchar(24) DEFAULT NULL,
  `organizer` tinyint(4) DEFAULT NULL,
  `author` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'Maurizio','Loi','organizer1@mail.com','password1','1993-02-24',NULL,1,0),(5,'Francesco','Soru','email@email.com','password','1993-04-07',NULL,1,0),(6,'Giancarlo','Autore','author1@mail.com','password1','1995-02-24',NULL,0,1),(7,'Chiara','Autrice','author2@mail.com','password2','1992-08-10',NULL,0,1);
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

-- Dump completed on 2019-06-14 11:03:00
