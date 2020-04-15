/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : spring_class_style

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 12/04/2020 19:47:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class_activity
-- ----------------------------
DROP TABLE IF EXISTS `class_activity`;
CREATE TABLE `class_activity`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `program` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动方案',
  `audit` tinyint(1) NOT NULL DEFAULT 1 COMMENT '审核结果 1 未审核 2 审核中 3 审核通过 4 审核不通过',
  `activity_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动照片',
  `is_show` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否显示 1 显示 2 不显示',
  `add_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '添加活动的作者',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人',
  `is_valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效 1 有效 2 无效',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_valid`(`is_valid`) USING BTREE,
  INDEX `index_titile`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动方案表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_article
-- ----------------------------
DROP TABLE IF EXISTS `class_article`;
CREATE TABLE `class_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  `new_type` tinyint(1) NOT NULL COMMENT '文章类型',
  `author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人',
  `is_valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效 1 有效 2 无效',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_valid`(`is_valid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级学习园地文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_honor
-- ----------------------------
DROP TABLE IF EXISTS `class_honor`;
CREATE TABLE `class_honor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '获奖标题',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片路径',
  `is_show` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否显示 1 显示 2 不显示',
  `is_valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否 有效 1 有效 2 无效',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作者',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_title`(`title`) USING BTREE,
  INDEX `index_valid`(`is_valid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '获奖信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_message
-- ----------------------------
DROP TABLE IF EXISTS `class_message`;
CREATE TABLE `class_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `office` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位',
  `context` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  `is_valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1 有效 2 无效',
  `operator` char(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作者',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_username`(`username`) USING BTREE,
  INDEX `index_valid`(`is_valid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户留言表	' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_news
-- ----------------------------
DROP TABLE IF EXISTS `class_news`;
CREATE TABLE `class_news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '新闻标题',
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '新闻内容',
  `new_type` tinyint(1) NOT NULL COMMENT '新闻类型',
  `author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人',
  `is_valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效 1 有效 2 无效',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_valid`(`is_valid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级新闻表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_notice
-- ----------------------------
DROP TABLE IF EXISTS `class_notice`;
CREATE TABLE `class_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `context` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容',
  `author` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `is_valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1 有效 2 无效 被删除',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作者',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_valid`(`is_valid`) USING BTREE,
  INDEX `index_title`(`title`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_style_config
-- ----------------------------
DROP TABLE IF EXISTS `class_style_config`;
CREATE TABLE `class_style_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置的名称',
  `config_value` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置的值',
  `config_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置的描述',
  `is_valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效 1 有效 2 无效',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作人',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_config_name`(`config_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for class_user
-- ----------------------------
DROP TABLE IF EXISTS `class_user`;
CREATE TABLE `class_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `identity` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户身份 1 学生 2 班干',
  `office` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '职位名称',
  `header_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `user_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1 学生 2 老师',
  `is_show` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否显示 1 显示 2 不显示',
  `summary` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `number` int(11) NOT NULL COMMENT '学号',
  `account_status` tinyint(1) NOT NULL DEFAULT 2 COMMENT '账号状态 1 启用 2 未启用',
  `operator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作者',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '班级成员表' ROW_FORMAT = Dynamic;

INSERT INTO `spring_class_style`.`class_user`(`username`, `password`, `identity`, `office`, `header_img`, `user_type`, `is_show`, `summary`, `number`, `account_status`, `operator`, `created_at`, `updated_at`) VALUES ('teacher', '724349B04985CEED48A3D2490A4BEB95', 2, '老师', '', 2, 1, '老师', 20200001, 1, 'teacher', now(), now());

SET FOREIGN_KEY_CHECKS = 1;
