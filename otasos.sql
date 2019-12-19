/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.46 : Database - otasos
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`otasos` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `otasos`;

/*Table structure for table `contacts` */

CREATE TABLE `contacts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT '0',
  `uname` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `remake` varchar(20) DEFAULT '""',
  `available` tinyint(2) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `device` */

CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceName` varchar(50) DEFAULT NULL,
  `firmwareName` varchar(50) DEFAULT NULL,
  `version` varchar(10) DEFAULT NULL,
  `updateTime` varchar(50) DEFAULT '2017年12月1日以前',
  `info` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `deviceName` (`deviceName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `history_sos` */

CREATE TABLE `history_sos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL,
  `info` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `sendTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `device_bind_info` */

CREATE TABLE `device_bind_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) DEFAULT NULL,
  `mac` varchar(32) DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
