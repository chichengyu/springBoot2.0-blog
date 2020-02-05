/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-02-05 20:05:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('55');
INSERT INTO `hibernate_sequence` VALUES ('55');
INSERT INTO `hibernate_sequence` VALUES ('55');
INSERT INTO `hibernate_sequence` VALUES ('55');
INSERT INTO `hibernate_sequence` VALUES ('55');

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL,
  `appreciation` bit(1) NOT NULL,
  `commentabled` bit(1) NOT NULL,
  `content` longtext,
  `create_time` datetime DEFAULT NULL,
  `first_picture` varchar(255) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `published` bit(1) NOT NULL,
  `recommend` bit(1) NOT NULL,
  `share_statement` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `views` int(11) unsigned DEFAULT '0',
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `tag_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK292449gwg5yf7ocdlmswv9w4j` (`type_id`),
  KEY `FK8ky5rrsxh01nkhctmo7d48p82` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog
-- ----------------------------
INSERT INTO `t_blog` VALUES ('23', '', '', '# 测试文章2', '2020-02-05 14:50:05', 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '', '测试文章2', '2019-02-03 18:37:54', '2', '4', '1', '测试文章', null);
INSERT INTO `t_blog` VALUES ('28', '\0', '\0', '# 测试文章3', null, 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '\0', '测试文章3', '2019-02-05 18:37:54', '4', '9', '1', '测试文章3', null);
INSERT INTO `t_blog` VALUES ('29', '\0', '', '# 测试文章9', '2020-02-12 19:09:36', 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '\0', '测试文章9', '2018-02-03 18:37:54', '13', '9', '1', '测试文章9', null);
INSERT INTO `t_blog` VALUES ('30', '\0', '\0', '# 测试文章4', null, 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '\0', '测试文章4', '2018-02-03 18:37:54', '1', '5', '1', '测试文章4', null);
INSERT INTO `t_blog` VALUES ('31', '\0', '\0', '# 测试文章8', null, 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '\0', '测试文章8', '2018-02-03 18:37:54', '0', '9', '1', '测试文章8', null);
INSERT INTO `t_blog` VALUES ('32', '', '\0', '# 测试文章1', null, 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '\0', '测试文章1', '2017-02-03 18:37:54', '1', '7', '1', '测试文章1', null);
INSERT INTO `t_blog` VALUES ('33', '\0', '\0', '# 测试文章7', null, 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '\0', '测试文章7', '2017-02-03 18:37:54', '7', '7', '1', '测试文章7', null);
INSERT INTO `t_blog` VALUES ('34', '\0', '\0', '# 测试文章6', null, 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '\0', '测试文章6', '2017-02-03 18:37:54', '5', '10', '1', '测试文章6', null);
INSERT INTO `t_blog` VALUES ('35', '\0', '\0', '# 测试文章5', null, 'https://unsplash.it/1000/400?image=1005', '', '\0', '', '\0', '测试文章5', '2017-02-03 18:37:54', '0', '6', '1', '测试文章5', null);

-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags` (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  KEY `FK5feau0gb4lq47fdb03uboswm8` (`tags_id`),
  KEY `FKh4pacwjwofrugxa9hpwaxg6mr` (`blogs_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES ('28', '22');
INSERT INTO `t_blog_tags` VALUES ('28', '20');
INSERT INTO `t_blog_tags` VALUES ('32', '20');
INSERT INTO `t_blog_tags` VALUES ('23', '20');
INSERT INTO `t_blog_tags` VALUES ('30', '20');
INSERT INTO `t_blog_tags` VALUES ('30', '22');
INSERT INTO `t_blog_tags` VALUES ('34', '20');
INSERT INTO `t_blog_tags` VALUES ('33', '22');
INSERT INTO `t_blog_tags` VALUES ('31', '20');
INSERT INTO `t_blog_tags` VALUES ('29', '22');
INSERT INTO `t_blog_tags` VALUES ('29', '20');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL,
  `admin_comment` bit(1) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKke3uogd04j4jx316m1p51e05u` (`blog_id`),
  KEY `FK4jj284r3pb7japogvo6h72q95` (`parent_comment_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('40', '\0', '/images/avatar.png', '测试评论功能', '2020-02-04 20:26:30', '875267425@qq.com', '小二', '29', null);
INSERT INTO `t_comment` VALUES ('41', '\0', '/images/avatar.png', '测试回复评论', '2020-02-04 20:26:53', '875267425@qq.com', '小二', '29', '40');
INSERT INTO `t_comment` VALUES ('43', '\0', '/images/avatar.png', '456', '2020-02-04 21:09:11', '875267425@qq.com', '小三', '29', '41');
INSERT INTO `t_comment` VALUES ('44', '\0', '/images/avatar.png', '123', '2020-02-04 22:31:41', '2222@qq.com', '小五', '29', '43');
INSERT INTO `t_comment` VALUES ('45', '\0', '/images/avatar.png', '测试', '2020-02-04 22:38:39', '22222222@163.com', '123', '29', null);
INSERT INTO `t_comment` VALUES ('46', '\0', '/images/avatar.png', '测试回复', '2020-02-04 22:39:00', '22222222@163.com', '456', '29', '45');
INSERT INTO `t_comment` VALUES ('47', '\0', '/images/avatar.png', '水水水水水水', '2020-02-04 22:39:19', '22222222@163.com', '789', '29', '46');
INSERT INTO `t_comment` VALUES ('48', '\0', '/images/avatar.png', '柔柔弱弱日日日', '2020-02-04 22:39:37', '22222222@163.com', '489', '29', '47');
INSERT INTO `t_comment` VALUES ('49', '\0', '/images/avatar.png', '反反复复烦烦烦', '2020-02-04 22:44:45', '333@163.com', '1111222', '29', '48');
INSERT INTO `t_comment` VALUES ('50', '\0', '/images/avatar.png', '44455566', '2020-02-04 22:44:58', '333@163.com', '1111222', '29', '43');
INSERT INTO `t_comment` VALUES ('51', '\0', '/images/avatar.png', '89898989', '2020-02-04 22:45:25', '333@163.com', '2222333333', '29', '49');
INSERT INTO `t_comment` VALUES ('52', '\0', '/images/avatar.png', '测试', '2020-02-04 22:45:41', '333@163.com', '测试', '29', '48');
INSERT INTO `t_comment` VALUES ('53', '\0', '/images/avatar.png', '测试456', '2020-02-04 22:45:55', '333@163.com', '测试123', '29', '43');
INSERT INTO `t_comment` VALUES ('54', '', 'https://unsplash.it/1000/400?image=1005', '测试', '2020-02-05 11:57:18', '875267425@qq.com', '小池', '29', null);

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('20', '测试标签1');
INSERT INTO `t_tag` VALUES ('22', '测试标签2');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES ('4', '测试分类10');
INSERT INTO `t_type` VALUES ('5', '测试分类9');
INSERT INTO `t_type` VALUES ('6', '测试分类8');
INSERT INTO `t_type` VALUES ('7', '测试分类7');
INSERT INTO `t_type` VALUES ('9', '测试分类6');
INSERT INTO `t_type` VALUES ('10', '测试分类5');
INSERT INTO `t_type` VALUES ('11', '测试分类4');
INSERT INTO `t_type` VALUES ('12', '测试分类3');
INSERT INTO `t_type` VALUES ('13', '测试分类2');
INSERT INTO `t_type` VALUES ('15', '测试分类1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'https://unsplash.it/1000/400?image=1005', '2020-01-31 16:02:56', '875267425@qq.com', '小池', '$2a$10$IQdn3FMjJQa8UlUug66ciuXhJi5QfC8w1ejENrKXyIVBNR6C5wOay', '1', null, 'admin');
