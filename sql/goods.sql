/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web-server

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-16 22:58:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `specification` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品规格',
  `manufacturer` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '生产商信息',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：下架|1：上架',
  `create_time` int(10) NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `create_user` int(10) NOT NULL DEFAULT '0' COMMENT '创建者账号id',
  `update_time` int(10) NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  `update_user` int(10) NOT NULL DEFAULT '0' COMMENT '更新者账号id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '哇哈哈AD钙奶', '125ml', '哇哈哈食品责任有限公司', '0', '1544966825', '1', '0', '0');
INSERT INTO `goods` VALUES ('2', '农夫山泉', '500ml', '农夫山泉饮料责任有限公司', '0', '1544966975', '1', '0', '0');
INSERT INTO `goods` VALUES ('3', '大闸蟹礼包', '4公4母', '强子水产公司', '1', '1544967091', '1', '0', '0');
