/*
 Navicat Premium Data Transfer

 Target Server Type    : MySQL
 Target Server Version : 50629
 File Encoding         : 65001

 Date: 20/11/2019 13:34:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for yb_users
-- ----------------------------
DROP TABLE IF EXISTS `yb_users`;
CREATE TABLE `yb_users`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default.jpg',
  `created_at` datetime(0) NOT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  `is_admin` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `is_confirmed` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `employee_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yb_users
-- ----------------------------
INSERT INTO `yb_users` VALUES (29, 'admin', '123456789@qq.com', '', 'default.jpg', '2018-10-12 09:33:08', '2019-06-12 14:29:58', 0, 0, 0, NULL);
INSERT INTO `yb_users` VALUES (30, 'test', '22@qq.com', '', 'default.jpg', '2018-11-14 15:56:26', '2019-06-12 14:23:20', 0, 0, 0, NULL);
INSERT INTO `yb_users` VALUES (31, 'qwer', '3@qq.com', '', 'default.jpg', '2018-11-14 16:03:38', '2019-06-12 14:23:26', 0, 0, 0, NULL);
INSERT INTO `yb_users` VALUES (32, 'trator', '123@qq.com', '', 'default.jpg', '2017-05-25 20:13:06', '2019-11-01 10:47:34', 0, 0, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
