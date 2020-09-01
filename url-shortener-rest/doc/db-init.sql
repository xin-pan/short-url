/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : short_url

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : utf-8
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_short_url`
-- ----------------------------
DROP TABLE IF EXISTS `tb_short_url`;
CREATE TABLE `tb_short_url` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `hash_value` varchar(32) NOT NULL,
  `url` varchar(2048) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP,
  `yn` tinyint(2) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique` (`hash_value`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=325781 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
