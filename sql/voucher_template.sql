/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web-server

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-03-28 00:48:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for voucher_template
-- ----------------------------
DROP TABLE IF EXISTS `voucher_template`;
CREATE TABLE `voucher_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id_list` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品id集合，关联goods.id',
  `goods_quantity_list` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '商品数量集合，与goods_id_set一一对应',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '零售价',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '0|下架 1|上架',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `create_user` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建者账号id',
  `update_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  `update_user` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新者账号id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
