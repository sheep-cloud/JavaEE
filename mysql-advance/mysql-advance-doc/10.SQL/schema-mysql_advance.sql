/*
SQLyog Ultimate v12.4.1 (64 bit)
MySQL - 5.7.19 : Database - mysql_advance
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mysql_advance` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mysql_advance`;

/*Table structure for table `tbl_dept` */

DROP TABLE IF EXISTS `tbl_dept`;

CREATE TABLE `tbl_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(32) DEFAULT NULL,
  `loc_add` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_dept` */

insert  into `tbl_dept`(`id`,`dept_name`,`loc_add`) values 
(1,'RD','11'),
(2,'HR','12'),
(3,'MK','13'),
(4,'MIS','14'),
(5,'FD','15');

/*Table structure for table `tbl_emp` */

DROP TABLE IF EXISTS `tbl_emp`;

CREATE TABLE `tbl_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `dept_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `tbl_emp` */

insert  into `tbl_emp`(`id`,`name`,`dept_id`) values 
(1,'z3',1),
(2,'z4',1),
(3,'z5',1),
(4,'w5',2),
(5,'w6',2),
(6,'s7',3),
(7,'s8',4),
(8,'s9',51);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
