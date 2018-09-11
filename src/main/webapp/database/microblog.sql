/*
 Navicat Premium Data Transfer

 Source Server         : 实验室服务器
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 192.168.1.247:3306
 Source Schema         : microblog

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 01/07/2018 22:33:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `blogid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NULL DEFAULT NULL,
  `publishtime` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `content` text CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NULL,
  PRIMARY KEY (`blogid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_mysql500_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (12, 41, '2018-07-01 21:48:57', '解说员：“梅西失望的不是这场比赛，而是阿根廷足球的这个时代。” ​​​​');
INSERT INTO `blog` VALUES (13, 1, '2018-07-01 22:27:20', '最近十年，梅西和 C 罗包揽了世界足坛几乎所有的个人荣誉。谁也没想到，世界杯淘汰赛首轮首日，“梅罗” 竟同时出局，让我们表示深深的遗憾。人生就是这样，在残酷的竟争中，不是崛起，就是倒下，无论怎样我们都要做到胜不骄，败不馁，永远保持一颗奋斗的心！');
INSERT INTO `blog` VALUES (14, 1, '2018-07-01 22:27:32', '10 岁就是球王！姆巴佩童年踢球珍贵视频曝光！从小便开挂！');
INSERT INTO `blog` VALUES (15, 1, '2018-07-01 22:27:42', '今晚吃鸡，大吉大利！西班牙必胜！ ​​​​');
INSERT INTO `blog` VALUES (16, 41, '2018-07-01 22:28:02', '世界杯来到淘汰赛阶段，按照常理来讲，大家都会踢得小心翼翼，更加稳扎稳打，注重防守，多数要抓对手的失误。');
INSERT INTO `blog` VALUES (17, 41, '2018-07-01 22:28:13', '【大 Bo 知道你们因为 c 罗梅西伤感了一天，保证这是今天最后一个伤感视频】\r\n阿根廷与法国的比赛结束后，门将阿尔马尼伤心地趴在草坪上痛哭，队友卡巴列罗和古兹曼见状忙跑过来安慰他。在大家的搀扶下，阿尔马尼才勉强站了起来。不过，起身后的他还是站在那里，久久不愿离去。');
INSERT INTO `blog` VALUES (18, 42, '2018-07-01 22:28:36', '2010 年德国队携青春风暴席卷南非，首战 3:0，半决赛更是 4:0 屠杀阿根廷，水银泻地般的进攻，让我从此爱上了这只高傲的、年轻的日耳曼战车。2014 年在巴西，一路追随，见证了德国队走上世界之巅。');
INSERT INTO `blog` VALUES (19, 43, '2018-07-01 22:29:24', '从小白到老白，从世界杯新秀到西班牙传奇，伊涅斯塔简单而伟大，有他的中场永远是最安心的，因为他的魔力，他的谦逊，他的低调，他的人格魅力，他的团队奉献，他关键时刻的大心脏，都是我喜欢他的理由。');
INSERT INTO `blog` VALUES (20, 44, '2018-07-01 22:30:38', '今晚拉莫斯将追平卡西的 17 场世界杯出场记录，并列成为世界杯出场次数最多的西班牙人！！');
INSERT INTO `blog` VALUES (21, 43, '2018-07-01 22:31:12', '今晚世界杯 1/8 决赛重量级对决，今晚 22:00 西班牙 vs 俄罗斯！明天凌晨 02:00 克罗地亚 vs 丹麦！你看好哪只球队晋级？');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NOT NULL,
  `headpic` int(11) NOT NULL DEFAULT 0,
  `md5password` char(32) CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NOT NULL,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_mysql500_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', 1, '202cb962ac59075b964b07152d234b70');
INSERT INTO `user` VALUES (41, 'user1', 4, '202cb962ac59075b964b07152d234b70');
INSERT INTO `user` VALUES (42, 'user2', 2, '202cb962ac59075b964b07152d234b70');
INSERT INTO `user` VALUES (43, 'user4', 3, '202cb962ac59075b964b07152d234b70');
INSERT INTO `user` VALUES (44, 'user3', 4, '202cb962ac59075b964b07152d234b70');
INSERT INTO `user` VALUES (45, 'user6', 2, '202cb962ac59075b964b07152d234b70');

SET FOREIGN_KEY_CHECKS = 1;
