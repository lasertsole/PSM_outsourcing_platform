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

 Date: 28/09/2023 21:48:23
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
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户输入密码',
  `userName` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名(初始值为随机值，用户自行更改)',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户头像',
  `salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '盐值',
  `token` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '令牌',
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
) ENGINE = InnoDB AUTO_INCREMENT = 107 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accountinfo
-- ----------------------------
INSERT INTO `accountinfo` VALUES (00000000000000000000000000000105, '19820482808', '181540785', '帕斯猫', '/images/Profile/defaultProfile.jpg', '842220194', '1128925627', '2023-09-19 14:26:55', '2023-09-22 22:41:38', 1, '[\"日语N1\", \"初级教师资格证\", \"中级教师资格证\", \"高级教师资格证\"]', 1);
INSERT INTO `accountinfo` VALUES (00000000000000000000000000000106, '19820482807', '443553273', '汐123', '/images/Profile/xiProfile.jpg', '498039096', '1223258725', '2023-09-21 00:40:11', '2023-09-22 22:41:35', 1, '[\"高级教师资格证\"]', 3);

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
INSERT INTO `itembox` VALUES (00000000000000000000000000000001, 00000000000000000000000000000105, '200-300', 0, '/images/Carousel/bg-1.jpg', '/Video/Showcase/test1.mp4', '直播歌切 带普轴', '2023-09-28 21:37:07', '{\"detail\": {\"text\": \"javascript实现json字符串与对象转换的方法：1、json转换为对象，代码为【var obj = eval(\'(\' + str + \')\')】；2、对象转成json，代码为【var str=obj.toJSONString()】。\"}, \"phrase\": {\"itemArr\": [{\"text\": \"起步\", \"percentage\": \"0%\"}, {\"text\": \"最终成稿\", \"percentage\": \"100%\"}]}}');
INSERT INTO `itembox` VALUES (00000000000000000000000000000002, 00000000000000000000000000000105, '300-500', 0, '/images/Carousel/bg-2.jpg', '/Video/Showcase/test2.mp4', '直播歌切 带普轴', '2023-09-28 21:37:14', '{\"detail\": {\"text\": \"javascript实现json字符串与对象转换的方法：1、json转换为对象，代码为【var obj = eval(\'(\' + str + \')\')】；2、对象转成json，代码为【var str=obj.toJSONString()】。\"}, \"phrase\": {\"itemArr\": [{\"text\": \"起步\", \"percentage\": \"0%\"}, {\"text\": \"最终成稿\", \"percentage\": \"100%\"}]}}');
INSERT INTO `itembox` VALUES (00000000000000000000000000000003, 00000000000000000000000000000106, '200-300', 0, '/images/Carousel/bg-1.jpg', '/Video/Showcase/test1.mp4', '直播歌切 带普轴', '2023-09-28 21:37:16', '{\"detail\": {\"text\": \"javascript实现json字符串与对象转换的方法：1、json转换为对象，代码为【var obj = eval(\'(\' + str + \')\')】；2、对象转成json，代码为【var str=obj.toJSONString()】。\"}, \"phrase\": {\"itemArr\": [{\"text\": \"起步\", \"percentage\": \"0%\"}, {\"text\": \"最终成稿\", \"percentage\": \"100%\"}]}}');
INSERT INTO `itembox` VALUES (00000000000000000000000000000004, 00000000000000000000000000000106, '300-500', 0, '/images/Carousel/bg-2.jpg', '/Video/Showcase/test2.mp4', '直播歌切 带普轴', '2023-09-28 21:37:19', '{\"detail\": {\"text\": \"javascript实现json字符串与对象转换的方法：1、json转换为对象，代码为【var obj = eval(\'(\' + str + \')\')】；2、对象转成json，代码为【var str=obj.toJSONString()】。\"}, \"phrase\": {\"itemArr\": [{\"text\": \"起步\", \"percentage\": \"0%\"}, {\"text\": \"最终成稿\", \"percentage\": \"100%\"}]}}');

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
INSERT INTO `showcasebox` VALUES (00000000000000000000000000000007, '\r\n加急需要提前联系 价格需要*2 特急*2.5 要发票*3', 00000000000000000000000000000105, '2023-09-27 22:14:34', 0, 0, b'0', b'1', '安好:擅长古民值男/各种图组少女灶。\r\n    [请说明一下外或年龄偏好 同人大欢连!\r\n不接:真人J纯文宇设/大叔/正太 车队(等不起反馈是一款我的问题)\r\n张吉成增的间只有营单背看1带点水彩的厚流《西古风设定会更水影一点)笔教多且乱，请确本可以楼受例围啦!!!《走价了会比双朝编节一表表:封面上面两张对比下面两张这样)\r\n9连胜:色脑照理不大改! 不大改!不大改!有要说! !谢市谢谢谢谢谢\r\n喜欢的设定会多画一点我是升梅使)跌认复杂的设定可以赁化，或餐+30\r\n请给获清明确的无据条的立脸! !! 者能看的参!  谢谢谢谢谢谢谢谢谢谢');
INSERT INTO `showcasebox` VALUES (00000000000000000000000000000008, '加急需要提前联系 价格需要*2 特急*2.5 要发票*3', 00000000000000000000000000000106, '2023-09-27 22:14:37', 0, 0, b'1', b'0', '安好:擅长古民值男/各种图组少女灶。\r\n    [请说明一下外或年龄偏好 同人大欢连!\r\n不接:真人J纯文宇设/大叔/正太 车队(等不起反馈是一款我的问题)\r\n张吉成增的间只有营单背看1带点水彩的厚流《西古风设定会更水影一点)笔教多且乱，请确本可以楼受例围啦!!!《走价了会比双朝编节一表表:封面上面两张对比下面两张这样)\r\n9连胜:色脑照理不大改! 不大改!不大改!有要说! !谢市谢谢谢谢谢\r\n喜欢的设定会多画一点我是升梅使)跌认复杂的设定可以赁化，或餐+30\r\n请给获清明确的无据条的立脸! !! 者能看的参!  谢谢谢谢谢谢谢谢谢谢');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of showcasecomment
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
