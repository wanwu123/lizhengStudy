# lizhengStudy
lizhengStudy
/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : xxx

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 07/10/2020 17:00:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for broad
-- ----------------------------
DROP TABLE IF EXISTS `broad`;
CREATE TABLE `broad` (
  `xid` int NOT NULL AUTO_INCREMENT,
  `fid` int DEFAULT NULL,
  `tid` int DEFAULT NULL,
  `eid` int DEFAULT NULL,
  `begintime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Endtime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `xmoney` float DEFAULT NULL,
  PRIMARY KEY (`xid`) USING BTREE,
  KEY `fid` (`fid`) USING BTREE,
  KEY `tid` (`tid`) USING BTREE,
  KEY `eid` (`eid`) USING BTREE,
  CONSTRAINT `broad_ibfk_1` FOREIGN KEY (`fid`) REFERENCES `fiml` (`fid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `broad_ibfk_2` FOREIGN KEY (`tid`) REFERENCES `hall` (`tid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `broad_ibfk_3` FOREIGN KEY (`eid`) REFERENCES `emp` (`eid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of broad
-- ----------------------------
BEGIN;
INSERT INTO `broad` VALUES (1, 4, 1, 1, '2020-09-07', '2020-09-07', 11.1);
INSERT INTO `broad` VALUES (2, 7, 1, 1, '2020-09-06', '2020-09-06', 10);
COMMIT;

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `eid` int NOT NULL AUTO_INCREMENT,
  `ename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `esex` int DEFAULT NULL,
  `egw` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of emp
-- ----------------------------
BEGIN;
INSERT INTO `emp` VALUES (1, '李老师', 1, '教师');
INSERT INTO `emp` VALUES (2, '王教授', 1, '教师');
INSERT INTO `emp` VALUES (3, 'lixxx', 1, '啊啊啊');
COMMIT;

-- ----------------------------
-- Table structure for fiml
-- ----------------------------
DROP TABLE IF EXISTS `fiml`;
CREATE TABLE `fiml` (
  `fid` int NOT NULL AUTO_INCREMENT,
  `sid` int DEFAULT NULL,
  `fname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fauto` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ontime` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fremark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fflag` int DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE,
  UNIQUE KEY `fname` (`fname`) USING BTREE,
  KEY `sid` (`sid`) USING BTREE,
  CONSTRAINT `fiml_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `fsort` (`sid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of fiml
-- ----------------------------
BEGIN;
INSERT INTO `fiml` VALUES (4, 2, '经济学讲座', '王教授', '2020-09-07', '经济学讲座', 1);
INSERT INTO `fiml` VALUES (7, 3, '数学讲座', '李老师', '2020-09-06', '数学讲座', 1);
INSERT INTO `fiml` VALUES (8, 4, '英语讲座', '黄老师', '2020-09-06', '英语讲座', 1);
COMMIT;

-- ----------------------------
-- Table structure for fsort
-- ----------------------------
DROP TABLE IF EXISTS `fsort`;
CREATE TABLE `fsort` (
  `sid` int NOT NULL AUTO_INCREMENT,
  `sname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sremark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE,
  UNIQUE KEY `sname` (`sname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of fsort
-- ----------------------------
BEGIN;
INSERT INTO `fsort` VALUES (2, '经济学', '经济学');
INSERT INTO `fsort` VALUES (3, '数学', '数学');
INSERT INTO `fsort` VALUES (4, '英语', '英语');
COMMIT;

-- ----------------------------
-- Table structure for hall
-- ----------------------------
DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall` (
  `tid` int NOT NULL AUTO_INCREMENT,
  `tname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `tcount` int DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE,
  UNIQUE KEY `tname` (`tname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of hall
-- ----------------------------
BEGIN;
INSERT INTO `hall` VALUES (1, '教学楼102', 10);
INSERT INTO `hall` VALUES (2, '教学楼202', 50);
INSERT INTO `hall` VALUES (3, '教学楼', 404);
INSERT INTO `hall` VALUES (4, '礼堂4', 111);
COMMIT;

-- ----------------------------
-- Table structure for kucun
-- ----------------------------
DROP TABLE IF EXISTS `kucun`;
CREATE TABLE `kucun` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `num` int NOT NULL COMMENT '数量',
  `stock_num` int NOT NULL COMMENT '锁数量',
  `version` int NOT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of kucun
-- ----------------------------
BEGIN;
INSERT INTO `kucun` VALUES (1, 800, 200, 1);
INSERT INTO `kucun` VALUES (2, 0, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `orderId` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `c_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `u_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `num` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (1, '3f366878-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:49:49', '2020-08-18 12:49:49', 1);
INSERT INTO `order` VALUES (2, '6c4dedea-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (3, '6c4faf7c-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (4, '6c515fb6-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (5, '6c543484-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (6, '6c5a0a6c-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (7, '6c5a2c0e-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (8, '6c5a3d3e-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (9, '6c5e5a18-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (10, '6c6066be-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (11, '6c60bb5a-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (12, '6c70051a-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (13, '6c71ff3c-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (14, '6c7eb34e-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (15, '6c89e6d8-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (16, '6c8e8a76-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (17, '6c91684a-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (18, '6c9a7412-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (19, '6ca88732-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (20, '6cb0cbd6-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (21, '6cb4f602-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (22, '6cbac622-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (23, '6cc25310-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (24, '6cc83da2-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (25, '6ccfacae-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:05', '2020-08-18 12:51:05', 1);
INSERT INTO `order` VALUES (26, '6cd94610-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (27, '6ce5bf3a-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (28, '6cec6434-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (29, '6cef9ad2-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (30, '6cf263d4-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (31, '6d07bbe4-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (32, '6d0cfbd6-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (33, '6d102284-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (34, '6d13c664-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (35, '6d171148-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (36, '6d2250b2-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (37, '6d3d61ae-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (38, '6d421e74-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (39, '6d44cc46-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (40, '6d478008-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (41, '6d4a13a4-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (42, '6d4cf2c2-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (43, '6d4e8c04-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (44, '6d56f5b0-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (45, '6d5fa34a-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (46, '6d650e16-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:06', '2020-08-18 12:51:06', 1);
INSERT INTO `order` VALUES (47, '6d736a24-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (48, '6d79dde6-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (49, '6d7cf4f4-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (50, '6d80728c-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (51, '6d857778-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (52, '6d8a6f08-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (53, '6d96c190-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (54, '6d9a6ba6-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (55, '6d9f9d60-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (56, '6da44d4c-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (57, '6da94ebe-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (58, '6dade712-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (59, '6dafbe02-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (60, '6dbb7d64-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (61, '6dbe2140-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (62, '6dcc90fe-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (63, '6dd4193c-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (64, '6dd83cd8-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (65, '6ddd59d4-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (66, '6de38520-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (67, '6de9c6f6-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (68, '6df0f5b6-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (69, '6df26338-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (70, '6df4ee1e-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (71, '6df66f28-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (72, '6df8b828-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (73, '6dfb1000-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (74, '6dfd01ee-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (75, '6e077a48-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:07', '2020-08-18 12:51:07', 1);
INSERT INTO `order` VALUES (76, '6e11ded4-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (77, '6e1566ee-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (78, '6e18aa52-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (79, '6e1bb544-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (80, '6e22b286-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (81, '6e2902b2-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (82, '6e2dbcbc-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (83, '6e308276-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (84, '6e331540-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (85, '6e36c582-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (86, '6e3fe266-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (87, '6e487f0c-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (88, '6e5798e8-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (89, '6e57f298-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (90, '6e5829ac-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (91, '6e5ce320-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (92, '6e64a90c-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (93, '6e6d1bdc-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (94, '6e717a2e-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (95, '6e72dc48-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (96, '6e757d5e-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (97, '6e781550-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (98, '6e7e4f6a-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (99, '6e828f94-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (100, '6e9062a4-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
INSERT INTO `order` VALUES (101, '6e973868-e10e-11ea-99cf-75d1b92baad5', '2020-08-18 12:51:08', '2020-08-18 12:51:08', 1);
COMMIT;

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `mid` int NOT NULL AUTO_INCREMENT,
  `xid` int DEFAULT NULL,
  `mzw` int DEFAULT NULL,
  PRIMARY KEY (`mid`) USING BTREE,
  KEY `xid` (`xid`) USING BTREE,
  CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`xid`) REFERENCES `broad` (`xid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sale
-- ----------------------------
BEGIN;
INSERT INTO `sale` VALUES (1, 1, 2);
INSERT INTO `sale` VALUES (2, 2, 1);
INSERT INTO `sale` VALUES (3, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '名字',
  `age` int DEFAULT '0' COMMENT '年龄',
  `qr_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'lz', 20, '666');
COMMIT;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `upsw` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `urealname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  UNIQUE KEY `uname` (`uname`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
BEGIN;
INSERT INTO `userinfo` VALUES (1, 'wangwu', 'wangwu', '王五');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
