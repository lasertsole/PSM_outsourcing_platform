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

 Date: 01/11/2023 03:17:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accountinfo
-- ----------------------------
DROP TABLE IF EXISTS `accountinfo`;
CREATE TABLE `accountinfo`  (
  `ID` int(32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '用户唯一id',
  `phoneNumber` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户注册手机号码',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户输入密码',
  `userName` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名(初始值为随机值，用户自行更改)',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盐值',
  `token` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '令牌',
  `createdTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '账号创建时间',
  `modifyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  `status` int UNSIGNED NOT NULL DEFAULT 1 COMMENT '账号状态，1为可用，2为封禁中，3为已注销',
  `honor` json NULL COMMENT '曾获荣誉',
  `commentNum` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '被评论数，每被别人评论一次就+1',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `unique_token`(`token` ASC) USING BTREE COMMENT 'token唯一',
  UNIQUE INDEX `unique_phone`(`phoneNumber` ASC) USING BTREE COMMENT 'phone唯一',
  UNIQUE INDEX `unique_userName`(`userName` ASC) USING BTREE COMMENT 'userName唯一',
  INDEX `ID`(`ID` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 161 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accountinfo
-- ----------------------------
INSERT INTO `accountinfo` VALUES (00000000000000000000000000000157, '19820482808', '$2a$10$kg9W96e/uyUlnfJMmkEB4u96RXuwSZ2m6c6BmRN0twGFWXAlXANUy', '新用户2051305854', '\\images\\userProfile\\6ba28915f46c646780e3abecb80a78f2.jpg', '2037819313', '$2a$10$LttjjkNzZIcEtnKe89bF7eDa3VwNtD7mZHbyV/JSqW.yGOgrvRSja', '2023-10-22 19:06:41', '2023-11-01 03:01:23', 1, '[\"日语N1\"]', 0);
INSERT INTO `accountinfo` VALUES (00000000000000000000000000000158, '19820482807', '$2a$10$2rnG2X50FqXAK9oK/eLJMeNgX31MOQGjx399hcUI.53tvPIsvlrgC', '新用户707430702', '/images/defaultProfile/defaultProfile.jpg', '1089401621', '$2a$10$L8p7ikAkCPFWXqFutZH3g.nnNJS6ZGfGW0jB04Sl5gCm1Uv20.xr2', '2023-10-23 20:15:04', '2023-11-01 02:31:45', 1, NULL, 10);

-- ----------------------------
-- Table structure for itembox
-- ----------------------------
DROP TABLE IF EXISTS `itembox`;
CREATE TABLE `itembox`  (
  `ID` int(32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '盒子唯一id',
  `authorID` int(32) UNSIGNED ZEROFILL NOT NULL COMMENT '外键:所属用户的账号唯一id',
  `price` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '价格区间',
  `type` int UNSIGNED NOT NULL COMMENT '盒子类型：0为橱窗页作品盒子，1为企划页的需求盒子，2为作品页的作品盒子',
  `imgPath` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片地址',
  `videoPath` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '视频地址',
  `abstractInfo` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '概要描述',
  `modifyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  `mainInfo` json NULL COMMENT '主要信息',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `itemBox&authorID Link Account&ID`(`authorID` ASC) USING BTREE,
  CONSTRAINT `itemBox&authorID Link Account&ID` FOREIGN KEY (`authorID`) REFERENCES `accountinfo` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of itembox
-- ----------------------------
INSERT INTO `itembox` VALUES (00000000000000000000000000000001, 00000000000000000000000000000157, '100-200', 0, '/images/Carousel/bg-1.jpg', '/Video/Showcase/test1.mp4', 'cos瑶瑶', '2023-11-01 01:59:05', '{\"detail\": {\"text\": \"擅長白化人物、少女體型、冷淡 虛無性格、注重細節\"}, \"phrase\": {\"itemArr\": [{\"text\": \"起步\", \"percentage\": \"0%\"}, {\"text\": \"最终成稿\", \"percentage\": \"100%\"}]}}');
INSERT INTO `itembox` VALUES (00000000000000000000000000000002, 00000000000000000000000000000157, '100-200', 0, '/images/Carousel/bg-2.jpg', '/Video/Showcase/test2.mp4', 'cos璐璐', '2023-11-01 02:26:43', '{\"detail\": {\"text\": \"擅長白化人物、少女體型、冷淡 虛無性格、注重細節\"}, \"phrase\": {\"itemArr\": [{\"text\": \"起步\", \"percentage\": \"0%\"}, {\"text\": \"最终成稿\", \"percentage\": \"100%\"}]}}');
INSERT INTO `itembox` VALUES (00000000000000000000000000000003, 00000000000000000000000000000158, '100-200', 0, '/images/Carousel/bg-2.jpg', '/Video/Showcase/test1.mp4', 'cos瑶瑶', '2023-11-01 02:29:02', '{\"detail\": {\"text\": \"擅長白化人物、少女體型、冷淡 虛無性格、注重細節\"}, \"phrase\": {\"itemArr\": [{\"text\": \"起步\", \"percentage\": \"0%\"}, {\"text\": \"最终成稿\", \"percentage\": \"100%\"}]}}');
INSERT INTO `itembox` VALUES (00000000000000000000000000000004, 00000000000000000000000000000158, '100-200', 0, '/images/Carousel/bg-1.jpg', '/Video/Showcase/test1.mp4', 'cos璐璐', '2023-11-01 02:26:46', '{\"detail\": {\"text\": \"擅長白化人物、少女體型、冷淡 虛無性格、注重細節\"}, \"phrase\": {\"itemArr\": [{\"text\": \"起步\", \"percentage\": \"0%\"}, {\"text\": \"最终成稿\", \"percentage\": \"100%\"}]}}');

-- ----------------------------
-- Table structure for showcasebox
-- ----------------------------
DROP TABLE IF EXISTS `showcasebox`;
CREATE TABLE `showcasebox`  (
  `ID` int(32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '橱窗唯一id',
  `authorBrief` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者简介',
  `authorID` int(32) UNSIGNED ZEROFILL NOT NULL COMMENT '外键：作者账户唯一id',
  `modifyTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  `primarySort` int UNSIGNED NOT NULL COMMENT '大分类标签，0为剪辑类，1为美工类，2为翻译类，3为字幕类',
  `lastSort` int UNSIGNED NOT NULL COMMENT '小分类标签，具体内容不确定',
  `isIdle` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否档期空闲，0为否，1为是',
  `canQuicky` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可加急，0为否，1为是',
  `introduce` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `showcaseBox&authorID link Account&ID`(`authorID` ASC) USING BTREE,
  CONSTRAINT `showcaseBox&authorID link Account&ID` FOREIGN KEY (`authorID`) REFERENCES `accountinfo` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of showcasebox
-- ----------------------------
INSERT INTO `showcasebox` VALUES (00000000000000000000000000000001, '来一个会翻译日语的', 00000000000000000000000000000157, '2023-11-01 02:33:56', 0, 0, b'0', b'1', '玩了某个游戏后一时兴起想出的企划（x\r\n一共4张图 1原图+中破+大破+胖次only的以及表情的差分 最终成图效果参考图1 2的（确定后还有图3 图4）\r\n图3是设子 细节不用完全还原（特别是手套还要简化一下）反正缩小了也看不清 像素也就400*400左右的程度\r\n有武器设定 一把片手剑和一把轻弩');
INSERT INTO `showcasebox` VALUES (00000000000000000000000000000002, '来一个会翻译英语的', 00000000000000000000000000000158, '2023-11-01 02:31:16', 0, 0, b'1', b'0', '不超时不超时');

-- ----------------------------
-- Table structure for showcasecomment
-- ----------------------------
DROP TABLE IF EXISTS `showcasecomment`;
CREATE TABLE `showcasecomment`  (
  `sourceID` int(32) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '评论来源的账户唯一识别ID',
  `targetID` int(32) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '评论目标的账户唯一识别ID',
  `ID` int(32) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '本条评论的唯一识别ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `replyID` int(32) UNSIGNED ZEROFILL NOT NULL COMMENT '回复的人的id',
  `createdTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建评论时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `showcaseComment&sourceID link Account&ID`(`sourceID` ASC) USING BTREE,
  INDEX `showcaseComment&targetID link Account&ID`(`targetID` ASC) USING BTREE,
  CONSTRAINT `showcaseComment&sourceID link Account&ID` FOREIGN KEY (`sourceID`) REFERENCES `accountinfo` (`ID`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `showcaseComment&targetID link Account&ID` FOREIGN KEY (`targetID`) REFERENCES `accountinfo` (`ID`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of showcasecomment
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
