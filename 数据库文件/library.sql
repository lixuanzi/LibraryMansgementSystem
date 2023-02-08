/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 08/02/2023 09:55:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bookinfo
-- ----------------------------
DROP TABLE IF EXISTS `bookinfo`;
CREATE TABLE `bookinfo`  (
  `bookId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `bookName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `synopsis` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bookId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bookinfo
-- ----------------------------
INSERT INTO `bookinfo` VALUES ('20230113', '三国演义', '罗贯中', '45.5', '四大名著之三国演义');
INSERT INTO `bookinfo` VALUES ('20230114', '西游记', '吴承恩', '45.5', '四大名著之西游记');
INSERT INTO `bookinfo` VALUES ('20230115', '水浒传', '施耐庵', '45.5', '四大名著之水浒传');
INSERT INTO `bookinfo` VALUES ('20230116', '红楼梦', '施耐庵', '45.5', '四大名著之红楼梦');
INSERT INTO `bookinfo` VALUES ('20230117', '茶花女', '小仲马', '32.6', '让我来成全你的幸福');
INSERT INTO `bookinfo` VALUES ('20230118', '红与黑', '司汤达', '15.2', '灵魂的哲学与博爱');
INSERT INTO `bookinfo` VALUES ('20230119', '傲慢与偏见', '简.奥斯丁', '16.5', '越过爱情，看见春暖花开');
INSERT INTO `bookinfo` VALUES ('20230120', '一个陌生女人的来信', '茨威格', '23.21', '我爱你，与你无关');
INSERT INTO `bookinfo` VALUES ('20230121', '安娜.卡列尼娜', '列夫.托尔斯泰', '45.2', '难得糊涂的爱情与婚姻');
INSERT INTO `bookinfo` VALUES ('20230122', '红字', '霍桑', '52.3', '二十四小时，路过爱，走过禁区');
INSERT INTO `bookinfo` VALUES ('20230123', '珍妮姑娘', '西奥多.德莱塞', '31.5', '.只有渺小的人物，没有渺小的爱情');
INSERT INTO `bookinfo` VALUES ('20230124', '平凡的世界', '路遥', '32.89', '黄叶铺满地，我们已不再年轻');
INSERT INTO `bookinfo` VALUES ('20230125', '这里的黎明静悄悄', '瓦西里耶夫', '35.4', '战争，让女人走开');
INSERT INTO `bookinfo` VALUES ('20230126', '情人', '[法]玛格丽特·杜拉斯', '42.31', '后来，她哭了，因为她想到堤岸的那个男人，因为她一时之间无法确定她是不是曾经爱过他，');
INSERT INTO `bookinfo` VALUES ('20230127', '霍乱时期的爱情', '[哥伦比亚]加西亚·马尔克斯', '31.5', '我去旅行，是因为我决定了要去，并不是因为对风景的兴趣。');

-- ----------------------------
-- Table structure for borrowinfo
-- ----------------------------
DROP TABLE IF EXISTS `borrowinfo`;
CREATE TABLE `borrowinfo`  (
  `borrowId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `borrowers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `bookName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lendingLate` date NOT NULL,
  `returnDate` date NOT NULL,
  PRIMARY KEY (`borrowId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of borrowinfo
-- ----------------------------
INSERT INTO `borrowinfo` VALUES ('1218', '水浒传', '李四', '2023-01-15', '2023-01-16');
INSERT INTO `borrowinfo` VALUES ('1219', '三国演义', '张三', '2023-01-15', '2023-01-16');

-- ----------------------------
-- Table structure for employeeinfo
-- ----------------------------
DROP TABLE IF EXISTS `employeeinfo`;
CREATE TABLE `employeeinfo`  (
  `employeeId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mold` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '未分配' COMMENT '未分配',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`employeeId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employeeinfo
-- ----------------------------
INSERT INTO `employeeinfo` VALUES ('20230127', 'tom', '123456', '男', '会员', '13266554455');
INSERT INTO `employeeinfo` VALUES ('20230128', 'mathilda', '123456', '男', '管理员', '18766665555');

SET FOREIGN_KEY_CHECKS = 1;
