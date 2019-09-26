CREATE DATABASE IF NOT EXISTS `grpc_mysql`;
USE `grpc_mysql`;
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ;
INSERT INTO `userinfo`(`name`,`age`) values('wangyan',32);
