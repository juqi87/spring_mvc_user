/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.0.51b-community-nt : Database - myproject
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myproject` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myproject`;

/*Table structure for table `adminuser` */

DROP TABLE IF EXISTS `adminuser`;
show tables;
CREATE TABLE `adminuser` (
  `AdminUserId` varchar(32) NOT NULL COMMENT '用户编号',
  `UserName` varchar(50) default NULL COMMENT '用户名',
  `UserPwd` varchar(100) default NULL COMMENT '用户密码',
  `RoleId` varchar(32) default NULL COMMENT '角色编号',
  `EndDate` datetime default NULL COMMENT '最后登陆',
  `IsPass` tinyint(1) default '0' COMMENT '是否冻结',
  `Note` text COMMENT '备注',
  `AdderTime` datetime default NULL COMMENT '增加时间',
  `realityName` varchar(50) default NULL COMMENT '真实姓名',
  `BindMobilePhone` varchar(50) default NULL COMMENT '绑定手机',
  `LastLoginIp` varchar(50) default NULL COMMENT '上次登录IP',
  `Sex` tinyint(1) default '0' COMMENT '性别',
  `Birthday` datetime default NULL COMMENT '生日',
  `Email` varchar(50) default NULL COMMENT '邮箱',
  `Identification` varchar(50) default NULL COMMENT '身份证号',
  `smsvalidateState` tinyint(1) default '0' COMMENT '短信验证码状态 0 关闭 1开启',
  `smsvalidateNumber` int(11) default '0' COMMENT '短信验证码',
  `areaAddress` varchar(100) default NULL COMMENT '地址（县）',
  PRIMARY KEY  (`AdminUserId`),
  UNIQUE KEY `UserName` (`UserName`),
  KEY `FK_Reference_4` (`RoleId`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`RoleId`) REFERENCES `roledefinition` (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `adminuser` */

insert  into `adminuser`(`AdminUserId`,`UserName`,`UserPwd`,`RoleId`,`EndDate`,`IsPass`,`Note`,`AdderTime`,`realityName`,`BindMobilePhone`,`LastLoginIp`,`Sex`,`Birthday`,`Email`,`Identification`,`smsvalidateState`,`smsvalidateNumber`,`areaAddress`) 
values 
('4028818b457cc9a601457ccaa66b0000','query','202cb962ac59075b964b07152d234b70','f47f1515399a5cae0139a367f8b80001',NULL,1,NULL,'2014-04-20 09:40:26',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),
('4028d87e18e47d7a0118e47e360c0001','admin','202cb962ac59075b964b07152d234b70','4028d87e18c0e04c0118c0e0525c0001','2012-02-20 09:05:30',1,NULL,'2012-02-14 11:27:20','admin','','123.14.255.69',0,NULL,NULL,NULL,0,0,NULL),
('f47f1515399a5cae0139a36a18250004','jjy','202cb962ac59075b964b07152d234b70','f47f1515399a5cae0139a367f8b80001',NULL,1,NULL,'2014-04-17 16:08:20',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),
('f47f15153a5835da013a583d24700001','sll','202cb962ac59075b964b07152d234b70','f47f1515399a5cae0139a367f8b80001',NULL,1,NULL,'2012-10-19 09:31:09',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),
('f47f15153a584b37013a76a4c05d0001','ljj','202cb962ac59075b964b07152d234b70','f47f1515399a5cae0139a367f8b80001',NULL,1,NULL,'2012-10-19 09:30:53',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL),
('f47f15153b1d2930013b68d341390018','ykx','efdf28041456aafb0629df55ff58b158','f47f1515399a5cae0139a367f8b80001',NULL,1,NULL,'2012-12-05 10:09:33',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL);

/*Table structure for table `itemdefine` */

DROP TABLE IF EXISTS `itemdefine`;

CREATE TABLE `itemdefine` (
  `ItemId` varchar(32) NOT NULL COMMENT '编号',
  `ItemName` varchar(100) default NULL COMMENT '目录名字',
  `FatherId` varchar(32) default '0' COMMENT '父亲编号',
  `Target` varchar(50) default NULL COMMENT '对齐',
  `Url` varchar(300) default NULL COMMENT '超级链接',
  `AdderTime` datetime default NULL COMMENT '排序',
  `ImageName` varchar(100) default NULL COMMENT '图片(用户树形的图片)',
  `Note` text COMMENT 'Note',
  PRIMARY KEY  (`ItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='目录';

/*Data for the table `itemdefine` */

insert  into `itemdefine`(`ItemId`,`ItemName`,`FatherId`,`Target`,`Url`,`AdderTime`,`ImageName`,`Note`) values ('4028c2e435acd30d0135acdb16370000','修改密码','4028d87e18dedaf00118dedb4af60001','right','../admin/gotoUpdatePass','2012-02-24 08:55:33',NULL,'../admin/gotoUpdatePass'),('4028d87e18dedaf00118dedb4af60001','系统管理','0','right','','2008-03-24 03:35:56',NULL,''),('4028d87e18dedaf00118dee1d7a80002','用户管理','4028d87e18dedaf00118dedb4af60001','right','../admin/gotoManageAdminUser','2008-03-24 03:43:05',NULL,'../admin/gotoManageAdminUser?itemId=4028d87e18dedaf00118dedb4af60001'),('4028d87e18dedaf00118dee313350003','角色管理','4028d87e18dedaf00118dedb4af60001','right','../admin/gotoManageRole','2008-03-24 03:44:26',NULL,'../admin/gotoManageRole?itemId=4028d87e18dedaf00118dedb4af60001'),('4028d87e18dedaf00118dee352900004','权限管理','4028d87e18dedaf00118dedb4af60001','right','../admin/gotoManageRoleItem','2008-03-24 03:44:42',NULL,'../admin/gotoManageRoleItem?itemId=4028d87e18dedaf00118dedb4af60001'),('4028d87e18dedaf00118dee42aa10005','模块管理','4028d87e18dedaf00118dedb4af60001','right','../admin/gotoManageItem','2008-03-24 03:45:37',NULL,'../admin/gotoManageItem?itemId=4028d87e18dedaf00118dedb4af60001');

/*Table structure for table `roledefinition` */

DROP TABLE IF EXISTS `roledefinition`;

CREATE TABLE `roledefinition` (
  `RoleId` varchar(32) NOT NULL COMMENT '角色编号',
  `RoleName` varchar(100) default NULL COMMENT '角色名',
  `AdderTime` datetime default NULL COMMENT '增加时间',
  PRIMARY KEY  (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色定义表';

/*Data for the table `roledefinition` */

insert  into `roledefinition`(`RoleId`,`RoleName`,`AdderTime`) values ('4028818b457cc9a601457ccb0f760001','测试','2014-04-20 09:40:53'),('4028c2e435a430cc0135a43528c60000','市场部','2012-02-22 16:37:21'),('4028d87e18c0e04c0118c0e0525c0001','系统管理员','2008-03-18 07:52:45'),('f47f1515399a5cae0139a367f8b80001','客服','2012-09-08 09:04:23');

/*Table structure for table `rolelist` */

DROP TABLE IF EXISTS `rolelist`;

CREATE TABLE `rolelist` (
  `RoleListId` varchar(32) NOT NULL COMMENT '编号',
  `ItemId` varchar(32) NOT NULL COMMENT '目录编号',
  `RoleId` varchar(32) NOT NULL COMMENT '角色编号',
  `AdderTime` datetime default NULL COMMENT '增加时间',
  PRIMARY KEY  (`RoleListId`),
  KEY `FK_Reference_2` (`ItemId`),
  KEY `FK_Reference_3` (`RoleId`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`ItemId`) REFERENCES `itemdefine` (`ItemId`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`RoleId`) REFERENCES `roledefinition` (`RoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

/*Data for the table `rolelist` */

insert  into `rolelist`(`RoleListId`,`ItemId`,`RoleId`,`AdderTime`) values ('203ee222c5f811e3be8200e04c35e694','4028d87e18dedaf00118dedb4af60001','4028c2e435a430cc0135a43528c60000','2014-04-17 14:19:00'),('203f9127c5f811e3be8200e04c35e694','4028c2e435acd30d0135acdb16370000','4028c2e435a430cc0135a43528c60000','2014-04-17 14:19:00'),('20411d26c5f811e3be8200e04c35e694','4028d87e18dedaf00118dee1d7a80002','4028c2e435a430cc0135a43528c60000','2014-04-17 14:19:00'),('25b9ef14c5f811e3be8200e04c35e694','4028d87e18dedaf00118dedb4af60001','f47f1515399a5cae0139a367f8b80001','2014-04-17 14:19:10'),('25baf724c5f811e3be8200e04c35e694','4028c2e435acd30d0135acdb16370000','f47f1515399a5cae0139a367f8b80001','2014-04-17 14:19:10'),('4028c2bc4293bb7f014293bc6f400001','4028d87e18dedaf00118dedb4af60001','4028d87e18c0e04c0118c0e0525c0001','2013-11-26 17:27:49'),('4028c2bc4293bb7f014293bc70d60008','4028c2e435acd30d0135acdb16370000','4028d87e18c0e04c0118c0e0525c0001','2013-11-26 17:27:49'),('4028c2bc4293bb7f014293bc71050009','4028d87e18dedaf00118dee1d7a80002','4028d87e18c0e04c0118c0e0525c0001','2013-11-26 17:27:49'),('4028c2bc4293bb7f014293bc7144000a','4028d87e18dedaf00118dee313350003','4028d87e18c0e04c0118c0e0525c0001','2013-11-26 17:27:49'),('4028c2bc4293bb7f014293bc7163000b','4028d87e18dedaf00118dee352900004','4028d87e18c0e04c0118c0e0525c0001','2013-11-26 17:27:49'),('4028c2bc4293bb7f014293bc71c1000c','4028d87e18dedaf00118dee42aa10005','4028d87e18c0e04c0118c0e0525c0001','2013-11-26 17:27:49');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
