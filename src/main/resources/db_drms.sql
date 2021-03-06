/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : db_drms

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 01/04/2019 18:52:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for campus
-- ----------------------------
DROP TABLE IF EXISTS `campus`;
CREATE TABLE `campus`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '校区编号',
  `campus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校区名称',
  `status` int(1) NULL DEFAULT 1 COMMENT '是否删除',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `campus`(`campus`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of campus
-- ----------------------------
INSERT INTO `campus` VALUES (1, '双桥校区', 1, '2019-03-25 15:58:47');
INSERT INTO `campus` VALUES (2, '南泉校区', 1, '2019-03-25 16:07:03');
INSERT INTO `campus` VALUES (3, 'testadmin', 0, '2019-03-27 14:08:54');
INSERT INTO `campus` VALUES (4, '大族小区', 0, '2019-03-27 14:43:52');
INSERT INTO `campus` VALUES (5, '重庆校区', 0, '2019-03-27 14:44:22');
INSERT INTO `campus` VALUES (6, '北京校区', 0, '2019-03-27 14:47:08');
INSERT INTO `campus` VALUES (7, '上海小区', 0, '2019-03-27 14:47:42');
INSERT INTO `campus` VALUES (8, '深圳校区', 0, '2019-03-27 14:48:05');
INSERT INTO `campus` VALUES (9, '广东校区', 0, '2019-03-27 14:48:09');
INSERT INTO `campus` VALUES (10, '天津校区', 0, '2019-03-27 14:48:45');
INSERT INTO `campus` VALUES (11, '测试校区', 0, '2019-03-27 14:49:29');
INSERT INTO `campus` VALUES (12, '测试校区1', 0, '2019-03-27 14:49:35');
INSERT INTO `campus` VALUES (13, '测试校区2', 0, '2019-03-27 14:49:49');

-- ----------------------------
-- Table structure for dormitory_floor
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_floor`;
CREATE TABLE `dormitory_floor`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '宿舍楼编号',
  `floor_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍楼名称',
  `sex` int(1) NULL DEFAULT NULL COMMENT '男女宿舍',
  `campus_id` int(10) NULL DEFAULT NULL COMMENT '校区编号',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '删除',
  `open` int(1) NULL DEFAULT 1 COMMENT '开放',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `floor_name`(`floor_name`, `campus_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory_floor
-- ----------------------------
INSERT INTO `dormitory_floor` VALUES (1, '明远楼', 1, 1, '2019-03-25 16:06:41', 1, 1);
INSERT INTO `dormitory_floor` VALUES (2, '明德楼', 1, 2, '2019-03-25 16:07:39', 1, 1);
INSERT INTO `dormitory_floor` VALUES (5, '容园1号', 1, 2, '2019-03-28 13:01:03', 1, 1);
INSERT INTO `dormitory_floor` VALUES (6, '容园2号', 1, 2, '2019-03-28 13:01:32', 1, 1);
INSERT INTO `dormitory_floor` VALUES (7, '容园3号', 1, 2, '2019-03-28 13:01:57', 0, 1);

-- ----------------------------
-- Table structure for dormitory_room
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_room`;
CREATE TABLE `dormitory_room`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '宿舍ID',
  `room_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍名',
  `campus_id` int(10) NULL DEFAULT NULL COMMENT '校区编号',
  `floor_id` int(10) NULL DEFAULT NULL COMMENT '宿舍楼编号',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '删除',
  `open` int(1) NULL DEFAULT 1 COMMENT '开放',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `room_name`(`room_name`, `campus_id`, `floor_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory_room
-- ----------------------------
INSERT INTO `dormitory_room` VALUES (1, '457', 1, 1, '2019-03-27 18:10:12', 1, 1);
INSERT INTO `dormitory_room` VALUES (2, '458', 2, 2, '2019-03-27 18:10:23', 1, 1);
INSERT INTO `dormitory_room` VALUES (3, '456', 1, 1, '2019-03-28 12:51:06', 1, 1);
INSERT INTO `dormitory_room` VALUES (4, '455', 1, 1, '2019-03-28 12:51:24', 1, 1);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `href` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '后台首页', 'icon-computer', 'main.html', 'SYS,ADMIN');
INSERT INTO `menu` VALUES (2, '校区管理', '&#xe68e;', 'campus/index.html', 'SYS');
INSERT INTO `menu` VALUES (3, '宿舍楼管理', '&#xe68e;', 'floor/index.html', 'SYS');
INSERT INTO `menu` VALUES (4, '宿舍管理', '&#xe68e;', 'room/index.html', 'SYS');
INSERT INTO `menu` VALUES (5, '管理员', '&#xe66f;', 'user/index.html', 'SYS');
INSERT INTO `menu` VALUES (6, '我的管辖区', '&#xe656;', 'my_manager/index.html', 'SYS,ADMIN');
INSERT INTO `menu` VALUES (7, '违纪统计', '&#xe629;', 'tongji/wjshow.html', 'SYS,ADMIN');
INSERT INTO `menu` VALUES (8, '报修统计', '&#xe629;', 'tongji/wxshow.html', 'SYS,ADMIN');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '报修编号',
  `room_id` int(10) NULL DEFAULT NULL COMMENT '报修宿舍',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(1) NULL DEFAULT 1 COMMENT '删除',
  `report` int(1) NULL DEFAULT 0 COMMENT '上报',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `type` int(1) NULL DEFAULT 1 COMMENT '类型（1，自然报修，2人为报修）',
  `principal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '统一排查' COMMENT '负责人及描述',
  `complete` int(1) NULL DEFAULT 0 COMMENT '结案',
  `floor_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍楼名称',
  `room_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍名称',
  `floor_id` int(10) NULL DEFAULT NULL COMMENT '宿舍楼编号',
  `reg_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记人名称',
  `reg_id` int(10) NULL DEFAULT NULL COMMENT '登记人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (1, 1, '空调线路短路1111', 1, 1, '2019-03-25 16:53:22', 0, '统一排查', 1, '明远楼', '457', 1, '李老师', 1);
INSERT INTO `repair` VALUES (2, 1, '电灯不亮', 1, 1, '2019-03-28 18:11:00', 1, '统一报修', 1, '明远楼', '457', 1, '李老师', 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '学生编号',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生名称',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `clasz` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '班级',
  `birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `campus_id` int(10) NULL DEFAULT NULL COMMENT '校区编号',
  `floor_id` int(10) NULL DEFAULT NULL COMMENT '宿舍楼编号',
  `room_id` int(10) NULL DEFAULT NULL COMMENT '宿舍编号',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '学生状态',
  `floor_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍楼名称',
  `room_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍名称',
  `stu_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学籍号',
  `campus_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '校区名',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stu_no`(`stu_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'test', 1, '1590011', '1997-07-07', 2, 1, 1, '2019-03-25 16:24:48', 1, '明远楼', '457', '159001101', '双桥校区');
INSERT INTO `student` VALUES (2, 'qwe', 1, '1590011', NULL, 2, 1, 1, '2019-03-28 14:33:53', 1, '明远楼', '457', '159001102', '双桥校区');
INSERT INTO `student` VALUES (3, 'asd', 1, '1590011', NULL, 2, 1, 1, '2019-03-28 14:33:57', 1, '明远楼', '457', '159001103', '双桥校区');
INSERT INTO `student` VALUES (4, 'zxc', 1, '1590011', NULL, 2, 1, 1, '2019-03-28 14:34:01', 1, '明远楼', '457', '159001104', '双桥校区');
INSERT INTO `student` VALUES (5, 'wee', 1, '1590011', NULL, 2, 1, 1, '2019-03-28 14:34:04', 1, '明远楼', '457', '159001105', '双桥校区');
INSERT INTO `student` VALUES (6, 'ddfff', 1, '1590011', NULL, 2, 1, 3, '2019-03-28 14:34:10', 1, '明远楼', '457', '159001106', '双桥校区');
INSERT INTO `student` VALUES (7, 'ffff', 1, '1590011', NULL, 2, 1, 3, '2019-03-28 14:34:13', 1, '明远楼', '456', '159001107', '双桥校区');
INSERT INTO `student` VALUES (8, 'ttttt', 1, '1590011', NULL, 2, 1, 3, '2019-03-28 14:34:17', 1, '明远楼', '456', '159001108', '双桥校区');
INSERT INTO `student` VALUES (9, '无名', 1, '1590010', '1994-10-02', 1, 1, 4, '2019-04-01 16:19:01', 1, '明远楼', '455', '159001001', '双桥校区');
INSERT INTO `student` VALUES (10, 'rtx', 1, '1590010', '2000-10-02', 1, 1, 2, '2019-04-01 16:19:01', 1, '明远楼', '458', '159001002', '双桥校区');
INSERT INTO `student` VALUES (19, '无名', 1, '1590010', '2000-10-02', 1, 1, 2, '2019-04-01 16:49:07', 1, '明远楼', '458', '159001003', '双桥校区');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职工名称',
  `status` int(1) NULL DEFAULT 1 COMMENT '状态',
  `floor_id` int(10) NULL DEFAULT NULL COMMENT '管辖区域',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'admin' COMMENT '角色',
  `campus_id` int(10) NULL DEFAULT NULL COMMENT '校区ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$/WGU8HPYz7A81A3TfhNV0.Wi4mWeu8pqQs80f9JLi3Ao0Jj9JtOwy', '李老师', 1, 1, 'SYS', 1);
INSERT INTO `user` VALUES (6, 'admin11', '$2a$10$7kDcHmyNb0Hvg9L25bR6DOxIqLy40VSf3QrLj5x3QqrbuSQBpxf2W', 'admin', 0, NULL, 'ADMIN', NULL);
INSERT INTO `user` VALUES (7, 'test111', '$2a$10$sSz5GeFxR690Ch5XNS/.FebGoFX/Ey3nr9pPmrXe2kjW0WFD7vtTm', '测试', 0, NULL, 'ADMIN', NULL);
INSERT INTO `user` VALUES (9, 'test', '$2a$10$Vxf7jpjtmPzKTc3u379Gaej6ldC2cDa6FIGQFCqZF95D8XZDyGIx.', '朱老师', 1, 2, 'ADMIN', 2);

-- ----------------------------
-- Table structure for violation
-- ----------------------------
DROP TABLE IF EXISTS `violation`;
CREATE TABLE `violation`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '违纪编号',
  `stu_id` int(10) NULL DEFAULT NULL COMMENT '学生编号',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(1) NULL DEFAULT 1 COMMENT '删除',
  `report` int(1) NULL DEFAULT 0 COMMENT '是否上报',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生名',
  `floor_id` int(11) NULL DEFAULT NULL COMMENT '宿舍楼编号',
  `room_id` int(11) NULL DEFAULT NULL COMMENT '宿舍编号',
  `floor_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍楼名称',
  `room_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宿舍名称',
  `reg_id` int(10) NULL DEFAULT NULL COMMENT '登记人ID',
  `reg_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登记人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of violation
-- ----------------------------
INSERT INTO `violation` VALUES (1, 1, '使用大功率电器', 1, 1, '2019-03-25 16:34:44', 'test', 1, 1, '明远楼', '457', 1, '李老师');
INSERT INTO `violation` VALUES (2, 2, '外卖122222', 1, 1, '2019-03-28 17:39:07', 'qwe', 1, 1, '明远楼', '457', 1, '李老师');

SET FOREIGN_KEY_CHECKS = 1;
