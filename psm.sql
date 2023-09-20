/*
 Navicat Premium Data Transfer

 Source Server         : moye
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : psm

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 21/09/2023 02:03:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accountinfo
-- ----------------------------
DROP TABLE IF EXISTS `accountinfo`;
CREATE TABLE `accountinfo`  (
  `id` int(32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `phoneNumber` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户注册手机号码',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户输入密码',
  `userName` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名(初始值为随机值，用户自行更改)',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盐值',
  `token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '令牌',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账号创建时间',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '账号状态，1为可用，2为封禁中，3为已注销',
  `honor` json NULL COMMENT '曾获荣誉',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_token`(`token` ASC) USING BTREE COMMENT 'token唯一',
  UNIQUE INDEX `unique_phone`(`phoneNumber` ASC) USING BTREE COMMENT 'phone唯一',
  UNIQUE INDEX `unique_userName`(`userName` ASC) USING BTREE COMMENT 'userName唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accountinfo
-- ----------------------------
INSERT INTO `accountinfo` VALUES (00000000000000000000000000000105, '19820482808', '181540785', '新用户588634267', '/images/defaultProfile/defaultProfile.jpg', '842220194', '1128925627', '2023-09-19 14:26:55', '2023-09-21 00:18:03', 1, '[\"日语N1\", \"初级教师资格证\"]');
INSERT INTO `accountinfo` VALUES (00000000000000000000000000000106, '19820482807', '443553273', '新用户496707545', '/images/defaultProfile/defaultProfile.jpg', '498039096', '1223258725', '2023-09-21 00:40:11', '2023-09-21 00:40:11', 1, NULL);

-- ----------------------------
-- Table structure for showcasebox
-- ----------------------------
DROP TABLE IF EXISTS `showcasebox`;
CREATE TABLE `showcasebox`  (
  `id` int(32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '橱窗唯一id',
  `author_brief` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者简介',
  `author_id` int(32) UNSIGNED ZEROFILL NOT NULL COMMENT '外键：作者账户唯一id',
  `works` json NOT NULL COMMENT '作品信息',
  `modify_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  `commentNum` int NOT NULL DEFAULT 0 COMMENT '获得评论数',
  `primarySort` int UNSIGNED NOT NULL COMMENT '大分类标签，0为剪辑类，1为美工类，2为翻译类，3为字幕类',
  `lastSort` int UNSIGNED NOT NULL COMMENT '小分类标签，具体内容不确定',
  `isIdle` bit(1) NOT NULL COMMENT '是否档期空闲，0为否，1为是',
  `canQuicky` bit(1) NOT NULL COMMENT '是否可加急，0为否，1为是',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `linkAccountId`(`author_id` ASC) USING BTREE,
  CONSTRAINT `linkAccountId` FOREIGN KEY (`author_id`) REFERENCES `accountinfo` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of showcasebox
-- ----------------------------
INSERT INTO `showcasebox` VALUES (00000000000000000000000000000006, '加急需要提前联系 价格需要*2 特急*2.5 要发票*3', 00000000000000000000000000000105, '[{\"price\": \"200-300\", \"imgPath\": \"/images/Carousel/bg-1.jpg\", \"abstract\": \"直播歌切 带普轴\"}, {\"price\": \"300-500\", \"imgPath\": \"/images/Carousel/bg-2.jpg\", \"abstract\": \"直播歌切 带普轴\"}, {\"price\": \"300-500\", \"imgPath\": \"/images/Carousel/bg-2.jpg\", \"abstract\": \"直播歌切 带普轴\"}, {\"price\": \"300-500\", \"imgPath\": \"/images/Carousel/bg-2.jpg\", \"abstract\": \"直播歌切 带普轴\"}]', '2023-09-21 00:52:47', 0, 0, 0, b'0', b'0');

-- ----------------------------
-- Table structure for showcasecomment
-- ----------------------------
DROP TABLE IF EXISTS `showcasecomment`;
CREATE TABLE `showcasecomment`  (
  `source_id` int(32) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '评论来源的账户唯一识别ID',
  `target_id` int(32) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '评论目标的账户唯一识别ID',
  `id` int(32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '本条评论的唯一识别ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `reply_id` int(32) UNSIGNED ZEROFILL NOT NULL COMMENT '回复的人的id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建评论时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `linkSourceAccountId`(`source_id` ASC) USING BTREE,
  INDEX `linkTargetAccountId`(`target_id` ASC) USING BTREE,
  CONSTRAINT `linkSourceAccountId` FOREIGN KEY (`source_id`) REFERENCES `accountinfo` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `linkTargetAccountId` FOREIGN KEY (`target_id`) REFERENCES `accountinfo` (`id`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of showcasecomment
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
