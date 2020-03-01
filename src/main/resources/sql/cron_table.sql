/*
Navicat MySQL Data Transfer

Source Server         : lookhere
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2020-03-01 19:21:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cron_table`
-- ----------------------------
DROP TABLE IF EXISTS `cron_table`;
CREATE TABLE `cron_table` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(11) DEFAULT NULL,
  `cron` varchar(255) DEFAULT NULL,
  `quarz_name` varchar(255) DEFAULT NULL,
  `scheduler_class` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cron_table
-- ----------------------------
INSERT INTO `cron_table` VALUES ('1', 'AAA', '0/5 * * * * ?', '用户AAA', 'github.com.bobgit.study.pinyin.quartz.task.SmsTask', '2018-09-06 20:26:55');
INSERT INTO `cron_table` VALUES ('2', 'BBB', '0/2 * * * * ?', '用户BBB', 'github.com.bobgit.study.pinyin.quartz.task.EmailTask', '2018-09-09 21:02:08');
