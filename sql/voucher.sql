/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web-server

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-03-28 00:48:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for voucher
-- ----------------------------
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE `voucher` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `voucher_template_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '关联voucherTemplateId.id',
  `purchase_uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '购买者id',
  `purchase_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '购买时间',
  `exchange_uid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '兑换者id',
  `exchange_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '兑换时间',
  `status` tinyint(4) unsigned NOT NULL DEFAULT '0' COMMENT '0|冻结 1|生效 2|失效',
  `create_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建时间戳',
  `create_user` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '创建者账号id',
  `update_time` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新时间戳',
  `update_user` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '更新者账号id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
