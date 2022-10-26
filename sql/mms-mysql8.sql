/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : mms

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 26/10/2022 21:06:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint NULL DEFAULT NULL COMMENT '文件大小(KB)',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件链接',
  `md5` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'md5',
  `enable` tinyint(1) NULL DEFAULT 0 COMMENT '是否禁用链接(1:是  0:否)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (6, '0a74bdece4374bd4bbdca4424725a8a8-2022-10-22-16-24-53.gif', 'gif', 630, 'http://localhost:9090/file/image/download/0a74bdece4374bd4bbdca4424725a8a8-2022-10-22-16-24-53.gif', 'b66baf6fa554531cde259bc4b1ccb855', 1, '2022-10-22 16:24:53');
INSERT INTO `sys_file` VALUES (7, 'ad08d55f010140a79eb41df1babe4649-2022-10-22-16-35-55.gif', 'gif', 979, 'http://localhost:9090/file/image/download/ad08d55f010140a79eb41df1babe4649-2022-10-22-16-35-55.gif', 'f4c2f011b32ac1e1d075b35696557db5', 1, '2022-10-22 16:35:55');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单路径',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单icon',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `order_num` tinyint(1) NOT NULL COMMENT '排序',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id',
  `is_hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏  0否1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (4, '权限管理', '/permission-submenu', 'Layout', 'icon-quanxian', '2022-10-21 10:41:59', '2022-10-23 23:33:06', 4, NULL, 0);
INSERT INTO `sys_menu` VALUES (5, '文件管理', '/image-submenu', 'Layout', 'icon-wenjian', '2022-10-21 10:42:33', '2022-10-23 23:33:11', 5, NULL, 0);
INSERT INTO `sys_menu` VALUES (6, '图片管理', '/file', '/file/FileImageView.vue', 'icon-tupian', '2022-10-21 10:43:55', NULL, 1, 5, 0);
INSERT INTO `sys_menu` VALUES (7, '首页', '/index', '/home/HomeView.vue', 'icon-shouye-shouye', '2022-10-21 10:44:34', NULL, 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, '菜单管理', '/menu', '/permission/MenuView.vue', 'icon-caidan1', '2022-10-21 10:46:05', NULL, 1, 4, 0);
INSERT INTO `sys_menu` VALUES (9, '接口管理', '/resources', '/permission/ResourceView.vue', 'icon-API', '2022-10-21 10:47:09', NULL, 2, 4, 0);
INSERT INTO `sys_menu` VALUES (10, '角色管理', '/roles', '/permission/RoleView.vue', 'icon-role', '2022-10-21 10:48:18', NULL, 3, 4, 0);
INSERT INTO `sys_menu` VALUES (11, '系统管理', '/system-submenu', 'Layout', 'icon-xitong', '2022-10-23 00:50:41', '2022-10-23 23:24:46', 6, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, '医疗药品', '/drug-submenu', 'Layout', 'icon--yiliao-yaopin', '2022-10-23 21:57:43', '2022-10-23 21:57:57', 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, '药品列表', '/drug/list', '/drug/DrugView.vue', 'icon-yiliao_yaoji', '2022-10-23 21:59:25', '2022-10-23 22:06:07', 1, 12, 0);
INSERT INTO `sys_menu` VALUES (14, '药品分类', '/drug/category', '/drug/DrugCategoryView.vue', 'icon-fenlei', '2022-10-23 22:02:57', '2022-10-23 23:28:29', 2, 12, 0);
INSERT INTO `sys_menu` VALUES (15, '医疗器械', '/instrument-submenu', 'Layout', 'icon-yiliaoqixie', '2022-10-23 23:24:27', NULL, 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, '器械列表', '/instrument/list', '/instrument/InstrumentView.vue', 'icon-yiliaoqicai', '2022-10-23 23:27:02', NULL, 1, 15, 0);
INSERT INTO `sys_menu` VALUES (17, '器械分类', '/instrument/category', '/instrument/InstrumentCategoryView.vue', 'icon-fenlei', '2022-10-23 23:28:23', NULL, 2, 15, 0);
INSERT INTO `sys_menu` VALUES (18, '工单管理', '/work-order-submenu', 'Layout', 'icon-gongdanguanli', '2022-10-23 23:32:49', '2022-10-23 23:33:24', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, '进货管理', '/purchase_list', '/work_order/PurchaseListView.vue', 'icon-jinhuo', '2022-10-23 23:34:47', '2022-10-23 23:35:33', 1, 18, 0);
INSERT INTO `sys_menu` VALUES (20, '个人中心', '/setting', '/personal/SettingView.vue', 'icon-gerenxinxi', '2022-10-24 00:34:41', NULL, 10, NULL, 1);
INSERT INTO `sys_menu` VALUES (21, '网站管理', '/webconfig', '/webconfig/WebConfigView.vue', 'icon-website', '2022-10-24 00:37:54', NULL, 1, 11, 0);

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限路径',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方式',
  `parent_id` int NULL DEFAULT NULL COMMENT '父权限id',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否匿名访问 0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '菜单模块', NULL, NULL, NULL, 0, '2022-10-21 10:50:30', NULL);
INSERT INTO `sys_resource` VALUES (2, '获取所有菜单', '/menu/all', 'GET', 1, 0, '2022-10-21 10:51:09', '2022-10-22 15:47:22');
INSERT INTO `sys_resource` VALUES (3, '新增或更新菜单', '/menu/update', 'POST', 1, 0, '2022-10-21 10:52:07', NULL);
INSERT INTO `sys_resource` VALUES (4, '删除菜单', '/menu/delete', 'DELETE', 1, 0, '2022-10-21 10:52:40', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `role_label` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色描述',
  `is_disable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用  0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 0, '2022-10-21 10:50:00', '2022-10-24 00:40:29');
INSERT INTO `sys_role` VALUES (2, '员工', 'employee', 0, '2022-10-21 10:53:14', '2022-10-21 18:55:50');
INSERT INTO `sys_role` VALUES (3, '客户', 'customer', 0, '2022-10-21 11:02:46', '2022-10-22 15:47:16');
INSERT INTO `sys_role` VALUES (4, '测试角色', 'test', 1, '2022-10-21 12:20:35', '2022-10-21 18:55:52');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 337 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (119, 3, 7);
INSERT INTO `sys_role_menu` VALUES (120, 4, 7);
INSERT INTO `sys_role_menu` VALUES (128, 2, 7);
INSERT INTO `sys_role_menu` VALUES (319, 1, 7);
INSERT INTO `sys_role_menu` VALUES (320, 1, 12);
INSERT INTO `sys_role_menu` VALUES (321, 1, 13);
INSERT INTO `sys_role_menu` VALUES (322, 1, 14);
INSERT INTO `sys_role_menu` VALUES (323, 1, 15);
INSERT INTO `sys_role_menu` VALUES (324, 1, 16);
INSERT INTO `sys_role_menu` VALUES (325, 1, 17);
INSERT INTO `sys_role_menu` VALUES (326, 1, 18);
INSERT INTO `sys_role_menu` VALUES (327, 1, 19);
INSERT INTO `sys_role_menu` VALUES (328, 1, 4);
INSERT INTO `sys_role_menu` VALUES (329, 1, 8);
INSERT INTO `sys_role_menu` VALUES (330, 1, 9);
INSERT INTO `sys_role_menu` VALUES (331, 1, 10);
INSERT INTO `sys_role_menu` VALUES (332, 1, 5);
INSERT INTO `sys_role_menu` VALUES (333, 1, 6);
INSERT INTO `sys_role_menu` VALUES (334, 1, 11);
INSERT INTO `sys_role_menu` VALUES (335, 1, 21);
INSERT INTO `sys_role_menu` VALUES (336, 1, 20);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `resource_id` int NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 135 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES (132, 1, 1);
INSERT INTO `sys_role_resource` VALUES (133, 1, 2);
INSERT INTO `sys_role_resource` VALUES (134, 1, 3);
INSERT INTO `sys_role_resource` VALUES (135, 1, 4);

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `category_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类名称',
  `category_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分类描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '分类类型：1-器械；0-药品',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (5, '腹泻药', '123', '2022-10-26 20:29:30', '2022-10-26 21:05:50', 0);
INSERT INTO `tb_category` VALUES (6, '胃药', '123', '2022-10-26 20:29:37', '2022-10-26 21:05:20', 0);
INSERT INTO `tb_category` VALUES (7, '药液输送保存器械', '2', '2022-10-26 20:33:00', '2022-10-26 21:04:04', 1);
INSERT INTO `tb_category` VALUES (10, '医用敷料', '123', '2022-10-26 20:54:20', '2022-10-26 21:04:19', 1);
INSERT INTO `tb_category` VALUES (11, '感冒药', '包含药品、冲剂等中成药', '2022-10-26 20:58:23', '2022-10-26 21:01:59', 0);
INSERT INTO `tb_category` VALUES (12, '退烧药', '用于退烧，', '2022-10-26 21:02:28', NULL, 0);
INSERT INTO `tb_category` VALUES (13, '医疗消毒设备', '123', '2022-10-26 21:04:32', '2022-10-26 21:04:37', 1);

-- ----------------------------
-- Table structure for tb_drug
-- ----------------------------
DROP TABLE IF EXISTS `tb_drug`;
CREATE TABLE `tb_drug`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '药品id',
  `drug_num` int NULL DEFAULT NULL COMMENT '药品编号',
  `drug_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品名称',
  `drug_take` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品服用方式',
  `drug_effect` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '药品功效',
  `drug_manufacturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品厂商',
  `drug_quantity` int NULL DEFAULT NULL COMMENT '药品数量',
  `drug_price` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '药品单价',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_drug
-- ----------------------------

-- ----------------------------
-- Table structure for tb_instrument
-- ----------------------------
DROP TABLE IF EXISTS `tb_instrument`;
CREATE TABLE `tb_instrument`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '医疗器材id',
  `instrument_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '医疗器材名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_instrument
-- ----------------------------

-- ----------------------------
-- Table structure for tb_purchase_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_list`;
CREATE TABLE `tb_purchase_list`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `employee_id` int NULL DEFAULT NULL COMMENT '负责员工id',
  `supplier_id` int NULL DEFAULT NULL COMMENT '供货商id',
  `cargo_id` int NULL DEFAULT NULL COMMENT '货物id',
  `cargo_quantity` int NULL DEFAULT NULL COMMENT '货物数量',
  `cargo_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '货物单价',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purchase_list
-- ----------------------------

-- ----------------------------
-- Table structure for tb_supplier
-- ----------------------------
DROP TABLE IF EXISTS `tb_supplier`;
CREATE TABLE `tb_supplier`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '供货商id',
  `supplier_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '供货商名称',
  `supplier_principal` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '供货商负责人',
  `supplier_contract` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '供货商联系方式',
  `supplier_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '供货商邮箱',
  `supplier_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '供货商地址',
  `supplier_bank` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '供货商开户行',
  `supplier_bank_card` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '供货商开户行账号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_auth`;
CREATE TABLE `tb_user_auth`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_info_id` int NOT NULL COMMENT '用户信息id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户登录ip',
  `ip_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ip来源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_auth
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱号',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户头像',
  `is_disable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
