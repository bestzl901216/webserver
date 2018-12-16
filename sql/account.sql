/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web-server

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-16 22:58:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(16) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '账号名称',
  `nick_name` varchar(16) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '昵称',
  `pwd` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '加密后的密码',
  `salt` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '加密盐',
  `create_time` int(10) NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `create_user` int(10) NOT NULL DEFAULT '0' COMMENT '创建者账号id',
  `update_time` int(10) NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  `update_user` int(10) NOT NULL DEFAULT '0' COMMENT '更新者账号id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'sysadmin', '系统管理员', 'J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=', 'wxKYXuTPST5SG0jMQzVPsg==', '0', '0', '0', '0');
