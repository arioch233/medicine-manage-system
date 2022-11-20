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

 Date: 20/11/2022 14:30:41
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
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (44, 'f6a6510ae10a4565b5104567d17ab435-2022-11-10-23-54-55.gif', 'gif', 384, 'http://localhost:9090/file/image/download/f6a6510ae10a4565b5104567d17ab435-2022-11-10-23-54-55.gif', '3bf0574718532db771c3296ab76de485', 1, '2022-11-10 23:54:56');
INSERT INTO `sys_file` VALUES (45, '12121b2534724a7e89dc80856470bbc2-2022-11-10-23-55-03.gif', 'gif', 979, 'http://localhost:9090/file/image/download/12121b2534724a7e89dc80856470bbc2-2022-11-10-23-55-03.gif', 'f4c2f011b32ac1e1d075b35696557db5', 1, '2022-11-10 23:55:03');
INSERT INTO `sys_file` VALUES (46, 'a902debf93b644aaa16380aca4cd7eed-2022-11-10-23-55-09.gif', 'gif', 979, 'http://localhost:9090/file/image/download/12121b2534724a7e89dc80856470bbc2-2022-11-10-23-55-03.gif', 'f4c2f011b32ac1e1d075b35696557db5', 1, '2022-11-10 23:55:10');
INSERT INTO `sys_file` VALUES (50, '1645bb1fb8424c759130b3ed75c9cc26-2022-11-11-14-47-02.gif', 'gif', 346, 'http://localhost:9090/file/image/download/1645bb1fb8424c759130b3ed75c9cc26-2022-11-11-14-47-02.gif', '028971170f292c48f87ac30c0b5c31e1', 1, '2022-11-11 14:47:03');
INSERT INTO `sys_file` VALUES (51, '8cc090a00b13434e8d244fa83ab884b8-2022-11-11-16-25-33.gif', 'gif', 830, 'http://localhost:9090/file/image/download/8cc090a00b13434e8d244fa83ab884b8-2022-11-11-16-25-33.gif', '10a0681389d2391bc5a09b83bdfb6b4f', 1, '2022-11-11 16:25:33');
INSERT INTO `sys_file` VALUES (52, '86f82ae02f2c479289b942d34e504da4-2022-11-11-17-34-11.gif', 'gif', 53, 'http://localhost:9090/file/image/download/86f82ae02f2c479289b942d34e504da4-2022-11-11-17-34-11.gif', '1b8373cfaa3d68fbfe6ad1e40e8cce7c', 1, '2022-11-11 17:34:12');
INSERT INTO `sys_file` VALUES (53, '47ce0c44aabe4ae6956d67110b5eaf52-2022-11-11-17-35-21.gif', 'gif', 53, 'http://localhost:9090/file/image/download/86f82ae02f2c479289b942d34e504da4-2022-11-11-17-34-11.gif', '1b8373cfaa3d68fbfe6ad1e40e8cce7c', 1, '2022-11-11 17:35:22');

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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (4, '权限管理', '/permission-submenu', 'Layout', 'icon-quanxian', '2022-10-21 10:41:59', '2022-10-23 23:33:06', 4, NULL, 0);
INSERT INTO `sys_menu` VALUES (5, '文件管理', '/image-submenu', 'Layout', 'icon-wenjian', '2022-10-21 10:42:33', '2022-11-08 13:58:19', 6, NULL, 0);
INSERT INTO `sys_menu` VALUES (6, '图片管理', '/file', '/file/FileImageView.vue', 'icon-tupian', '2022-10-21 10:43:55', NULL, 1, 5, 0);
INSERT INTO `sys_menu` VALUES (7, '首页', '/', '/home/HomeView.vue', 'icon-shouye-shouye', '2022-10-21 10:44:34', '2022-11-01 10:50:20', 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (8, '菜单管理', '/menu', '/permission/MenuView.vue', 'icon-caidan1', '2022-10-21 10:46:05', NULL, 1, 4, 0);
INSERT INTO `sys_menu` VALUES (9, '接口管理', '/resources', '/permission/ResourceView.vue', 'icon-API', '2022-10-21 10:47:09', NULL, 2, 4, 0);
INSERT INTO `sys_menu` VALUES (10, '角色管理', '/roles', '/permission/RoleView.vue', 'icon-role', '2022-10-21 10:48:18', NULL, 3, 4, 0);
INSERT INTO `sys_menu` VALUES (11, '系统管理', '/system-submenu', 'Layout', 'icon-xitong', '2022-10-23 00:50:41', '2022-11-08 13:58:25', 7, NULL, 0);
INSERT INTO `sys_menu` VALUES (12, '医疗药品', '/drug-submenu', 'Layout', 'icon--yiliao-yaopin', '2022-10-23 21:57:43', '2022-11-09 19:16:32', 1, NULL, 0);
INSERT INTO `sys_menu` VALUES (13, '药品列表', '/drugs/list', '/drugs/DrugsView.vue', 'icon-yiliao_yaoji', '2022-10-23 21:59:25', '2022-11-05 14:07:58', 1, 12, 0);
INSERT INTO `sys_menu` VALUES (14, '药品分类', '/drugs/category', '/drugs/DrugsCategoryView.vue', 'icon-fenlei', '2022-10-23 22:02:57', '2022-11-05 14:07:53', 2, 12, 0);
INSERT INTO `sys_menu` VALUES (15, '医疗器械', '/instrument-submenu', 'Layout', 'icon-yiliaoqixie', '2022-10-23 23:24:27', NULL, 2, NULL, 0);
INSERT INTO `sys_menu` VALUES (16, '器械列表', '/medical_devices/list', '/medical_devices/MedicalDevicesView.vue', 'icon-yiliaoqicai', '2022-10-23 23:27:02', '2022-11-05 14:05:53', 1, 15, 0);
INSERT INTO `sys_menu` VALUES (17, '器械分类', '/medical_devices/category', '/medical_devices/MedicalDevicesCategoryView.vue', 'icon-fenlei', '2022-10-23 23:28:23', '2022-11-05 14:06:00', 2, 15, 0);
INSERT INTO `sys_menu` VALUES (18, '工单管理', '/work-order-submenu', 'Layout', 'icon-gongdanguanli', '2022-10-23 23:32:49', '2022-10-23 23:33:24', 3, NULL, 0);
INSERT INTO `sys_menu` VALUES (19, '进货管理', '/purchase_list', '/work_order/PurchaseListView.vue', 'icon-jinhuo', '2022-10-23 23:34:47', '2022-10-26 21:28:17', 2, 18, 0);
INSERT INTO `sys_menu` VALUES (20, '个人中心', '/setting', '/personal/SettingView.vue', 'icon-gerenxinxi', '2022-10-24 00:34:41', '2022-11-11 16:56:35', 10, NULL, 1);
INSERT INTO `sys_menu` VALUES (21, '网站管理', '/webconfig', '/webconfig/WebConfigView.vue', 'icon-website', '2022-10-24 00:37:54', NULL, 1, 11, 0);
INSERT INTO `sys_menu` VALUES (22, '供货商管理', '/supplier', '/supplier/SupplierView.vue', 'icon-gonghuoshang', '2022-10-26 21:24:53', '2022-10-26 21:28:02', 1, 18, 0);
INSERT INTO `sys_menu` VALUES (24, '用户管理', '/user-submenu', 'Layout', 'icon-yonghuguanli', '2022-11-08 13:57:45', '2022-11-08 13:58:11', 5, NULL, 0);
INSERT INTO `sys_menu` VALUES (25, '用户列表', '/user-list', '/user/UserView.vue', 'icon-s_yonghuliebiao', '2022-11-08 13:59:26', NULL, 1, 24, 0);
INSERT INTO `sys_menu` VALUES (26, '首页', '/', '/customer/CustomerHomeView.vue', 'icon-shouye-shouye', '2022-11-11 16:51:36', '2022-11-11 17:02:10', 8, NULL, 0);
INSERT INTO `sys_menu` VALUES (27, '医疗药品', '/durgs', '/customer/CustomerDrugsView.vue', 'icon--yiliao-yaopin', '2022-11-11 16:53:47', '2022-11-11 16:55:57', 9, NULL, 0);
INSERT INTO `sys_menu` VALUES (28, '医疗器械', '/medical-devices', '/customer/CustomerMedicalDevicesView.vue', 'icon-yiliaoqixie', '2022-11-11 16:57:58', '2022-11-11 16:58:13', 9, NULL, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '菜单模块', NULL, NULL, NULL, 0, '2022-10-21 10:50:30', NULL);
INSERT INTO `sys_resource` VALUES (2, '获取所有菜单', '/menu/all', 'GET', 1, 0, '2022-10-21 10:51:09', '2022-11-09 20:21:45');
INSERT INTO `sys_resource` VALUES (3, '新增或更新菜单', '/menu/update', 'POST', 1, 0, '2022-10-21 10:52:07', '2022-11-09 20:21:45');
INSERT INTO `sys_resource` VALUES (4, '删除菜单', '/menu/delete', 'DELETE', 1, 0, '2022-10-21 10:52:40', '2022-11-09 20:21:46');
INSERT INTO `sys_resource` VALUES (5, '查看角色菜单选项', '/menu/role', 'GET', 1, 0, '2022-11-07 23:30:37', '2022-11-09 20:21:47');
INSERT INTO `sys_resource` VALUES (6, '查看当前用户菜单', '/menu/user', 'GET', 1, 0, '2022-11-07 23:31:15', '2022-11-09 20:21:49');
INSERT INTO `sys_resource` VALUES (7, '角色模块', NULL, NULL, NULL, 0, '2022-11-07 23:31:50', NULL);
INSERT INTO `sys_resource` VALUES (8, '查询角色列表', '/role/all', 'GET', 7, 0, '2022-11-07 23:32:08', '2022-11-09 20:21:55');
INSERT INTO `sys_resource` VALUES (9, '删除角色', '/role/delete', 'DELETE', 7, 0, '2022-11-07 23:32:25', '2022-11-09 20:21:56');
INSERT INTO `sys_resource` VALUES (10, '更新角色状态', ' /role/disable', 'POST', 7, 0, '2022-11-07 23:32:38', '2022-11-09 20:21:57');
INSERT INTO `sys_resource` VALUES (11, '更新角色信息', ' /role/update', 'POST', 7, 0, '2022-11-07 23:33:00', '2022-11-09 20:21:57');
INSERT INTO `sys_resource` VALUES (12, '资源模块', NULL, NULL, NULL, 0, '2022-11-07 23:33:41', NULL);
INSERT INTO `sys_resource` VALUES (13, '获取资源列表', '/resource/all', 'GET', 12, 0, '2022-11-07 23:33:55', '2022-11-09 20:22:05');
INSERT INTO `sys_resource` VALUES (14, '删除资源', '/resource/delete/{resourceId}', 'DELETE', 12, 0, '2022-11-07 23:34:08', '2022-11-09 20:22:05');
INSERT INTO `sys_resource` VALUES (15, '查看角色资源选项', '/resource/role', 'GET', 12, 0, '2022-11-07 23:34:22', '2022-11-09 20:22:06');
INSERT INTO `sys_resource` VALUES (16, '更新保存资源', '/resource/update', 'POST', 12, 0, '2022-11-07 23:34:33', '2022-11-09 20:22:07');
INSERT INTO `sys_resource` VALUES (17, '网站配置模块', NULL, NULL, NULL, 0, '2022-11-07 23:35:04', NULL);
INSERT INTO `sys_resource` VALUES (18, '更新网站配置', '/config/update', 'PUT', 17, 0, '2022-11-07 23:35:16', '2022-11-09 21:45:37');
INSERT INTO `sys_resource` VALUES (19, '获取网站配置', ' /config/website', 'GET', 17, 1, '2022-11-07 23:35:29', '2022-11-10 22:17:47');
INSERT INTO `sys_resource` VALUES (20, '用户账号模块', NULL, NULL, NULL, 0, '2022-11-07 23:35:51', NULL);
INSERT INTO `sys_resource` VALUES (21, '修改管理员密码', '/user/admin/password', 'PUT', 20, 0, '2022-11-07 23:36:06', '2022-11-09 21:51:37');
INSERT INTO `sys_resource` VALUES (22, '修改密码', '/user/password', 'PUT', 20, 0, '2022-11-07 23:36:21', '2022-11-09 21:51:36');
INSERT INTO `sys_resource` VALUES (24, '文件模块', NULL, NULL, NULL, 0, '2022-11-07 23:38:48', NULL);
INSERT INTO `sys_resource` VALUES (25, '获取当前文件列表', '/file/image/all', 'GET', 24, 0, '2022-11-07 23:38:56', '2022-11-09 21:51:33');
INSERT INTO `sys_resource` VALUES (26, '删除图片', '/file/image/delete', 'DELETE', 24, 0, '2022-11-07 23:39:14', '2022-11-09 21:51:34');
INSERT INTO `sys_resource` VALUES (27, '图片下载', '/file/image/download/{fileName}', 'GET', 24, 1, '2022-11-07 23:39:28', '2022-11-11 17:53:14');
INSERT INTO `sys_resource` VALUES (28, '上传图片', '/file/image/upload', 'POST', 24, 0, '2022-11-07 23:39:42', '2022-11-09 21:51:35');

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
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 0, '2022-10-21 10:50:00', '2022-11-11 17:54:33');
INSERT INTO `sys_role` VALUES (2, '员工', 'employee', 0, '2022-10-21 10:53:14', '2022-11-11 17:54:37');
INSERT INTO `sys_role` VALUES (3, '客户', 'customer', 0, '2022-10-21 11:02:46', '2022-11-11 17:01:21');
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
) ENGINE = InnoDB AUTO_INCREMENT = 438 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (120, 4, 7);
INSERT INTO `sys_role_menu` VALUES (382, 1, 7);
INSERT INTO `sys_role_menu` VALUES (383, 1, 12);
INSERT INTO `sys_role_menu` VALUES (384, 1, 13);
INSERT INTO `sys_role_menu` VALUES (385, 1, 14);
INSERT INTO `sys_role_menu` VALUES (386, 1, 15);
INSERT INTO `sys_role_menu` VALUES (387, 1, 16);
INSERT INTO `sys_role_menu` VALUES (388, 1, 17);
INSERT INTO `sys_role_menu` VALUES (389, 1, 18);
INSERT INTO `sys_role_menu` VALUES (390, 1, 22);
INSERT INTO `sys_role_menu` VALUES (391, 1, 19);
INSERT INTO `sys_role_menu` VALUES (392, 1, 4);
INSERT INTO `sys_role_menu` VALUES (393, 1, 8);
INSERT INTO `sys_role_menu` VALUES (394, 1, 9);
INSERT INTO `sys_role_menu` VALUES (395, 1, 10);
INSERT INTO `sys_role_menu` VALUES (396, 1, 24);
INSERT INTO `sys_role_menu` VALUES (397, 1, 25);
INSERT INTO `sys_role_menu` VALUES (398, 1, 5);
INSERT INTO `sys_role_menu` VALUES (399, 1, 6);
INSERT INTO `sys_role_menu` VALUES (400, 1, 11);
INSERT INTO `sys_role_menu` VALUES (401, 1, 21);
INSERT INTO `sys_role_menu` VALUES (402, 1, 20);
INSERT INTO `sys_role_menu` VALUES (414, 2, 7);
INSERT INTO `sys_role_menu` VALUES (415, 2, 12);
INSERT INTO `sys_role_menu` VALUES (416, 2, 13);
INSERT INTO `sys_role_menu` VALUES (417, 2, 14);
INSERT INTO `sys_role_menu` VALUES (418, 2, 15);
INSERT INTO `sys_role_menu` VALUES (419, 2, 16);
INSERT INTO `sys_role_menu` VALUES (420, 2, 17);
INSERT INTO `sys_role_menu` VALUES (421, 2, 18);
INSERT INTO `sys_role_menu` VALUES (422, 2, 22);
INSERT INTO `sys_role_menu` VALUES (423, 2, 19);
INSERT INTO `sys_role_menu` VALUES (424, 2, 20);
INSERT INTO `sys_role_menu` VALUES (435, 3, 26);
INSERT INTO `sys_role_menu` VALUES (436, 3, 27);
INSERT INTO `sys_role_menu` VALUES (437, 3, 28);
INSERT INTO `sys_role_menu` VALUES (438, 3, 20);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `resource_id` int NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 435 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES (383, 3, 6);
INSERT INTO `sys_role_resource` VALUES (384, 3, 20);
INSERT INTO `sys_role_resource` VALUES (385, 3, 21);
INSERT INTO `sys_role_resource` VALUES (386, 3, 22);
INSERT INTO `sys_role_resource` VALUES (409, 1, 1);
INSERT INTO `sys_role_resource` VALUES (410, 1, 2);
INSERT INTO `sys_role_resource` VALUES (411, 1, 3);
INSERT INTO `sys_role_resource` VALUES (412, 1, 4);
INSERT INTO `sys_role_resource` VALUES (413, 1, 5);
INSERT INTO `sys_role_resource` VALUES (414, 1, 6);
INSERT INTO `sys_role_resource` VALUES (415, 1, 7);
INSERT INTO `sys_role_resource` VALUES (416, 1, 8);
INSERT INTO `sys_role_resource` VALUES (417, 1, 9);
INSERT INTO `sys_role_resource` VALUES (418, 1, 10);
INSERT INTO `sys_role_resource` VALUES (419, 1, 11);
INSERT INTO `sys_role_resource` VALUES (420, 1, 12);
INSERT INTO `sys_role_resource` VALUES (421, 1, 13);
INSERT INTO `sys_role_resource` VALUES (422, 1, 14);
INSERT INTO `sys_role_resource` VALUES (423, 1, 15);
INSERT INTO `sys_role_resource` VALUES (424, 1, 16);
INSERT INTO `sys_role_resource` VALUES (425, 1, 17);
INSERT INTO `sys_role_resource` VALUES (426, 1, 18);
INSERT INTO `sys_role_resource` VALUES (427, 1, 24);
INSERT INTO `sys_role_resource` VALUES (428, 1, 25);
INSERT INTO `sys_role_resource` VALUES (429, 1, 26);
INSERT INTO `sys_role_resource` VALUES (430, 1, 28);
INSERT INTO `sys_role_resource` VALUES (431, 2, 6);
INSERT INTO `sys_role_resource` VALUES (432, 2, 24);
INSERT INTO `sys_role_resource` VALUES (433, 2, 25);
INSERT INTO `sys_role_resource` VALUES (434, 2, 26);
INSERT INTO `sys_role_resource` VALUES (435, 2, 28);

-- ----------------------------
-- Table structure for sys_website_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_website_config`;
CREATE TABLE `sys_website_config`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `config` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配置信息',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_website_config
-- ----------------------------
INSERT INTO `sys_website_config` VALUES (1, '{\"cargoCover\":\"http://localhost:9090/file/image/download/86f82ae02f2c479289b942d34e504da4-2022-11-11-17-34-11.gif\",\"isChatRoom\":1,\"isEmailNotice\":0,\"userAvatar\":\"http://localhost:9090/file/image/download/1645bb1fb8424c759130b3ed75c9cc26-2022-11-11-14-47-02.gif\",\"websocketUrl\":\"ws://127.0.0.1:9090/websocket\"}', '2022-11-07 16:20:38', '2022-11-11 17:35:24');

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
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_category
-- ----------------------------
INSERT INTO `tb_category` VALUES (5, '肠胃用药', '溃疡类 助消化类 炎症类 止泻类 痔疮便秘便血 胃脘疼痛', '2022-10-26 20:29:30', '2022-11-05 16:57:33', 0);
INSERT INTO `tb_category` VALUES (6, '五官用药', '眼科用药 耳鼻喉用药 口腔科用药 咽痛音哑 扁桃体炎 耳鸣耳聋 口舌生疮', '2022-10-26 20:29:37', '2022-11-05 16:58:46', 0);
INSERT INTO `tb_category` VALUES (7, '体温计', '电子体温计、红外耳蜗体温计、 口腔、肛门、腋下体温计、皮肤体温计、液晶体温计', '2022-10-26 20:33:00', '2022-11-06 11:17:55', 1);
INSERT INTO `tb_category` VALUES (10, '血压计', '无创性电子血压计、台式、立式血压计、血压表、小儿血压表', '2022-10-26 20:54:20', '2022-11-06 11:18:11', 1);
INSERT INTO `tb_category` VALUES (11, '感冒发热', '解热镇痛 病毒性感冒 驱风祛暑 清热解毒 头痛头晕', '2022-10-26 20:58:23', '2022-11-05 16:56:46', 0);
INSERT INTO `tb_category` VALUES (12, '皮肤用药', '癣症（银屑病） 皮炎湿疹 疖肿疥疮 手足皲裂 座疮粉刺 湿毒疮 白癜风 灰指甲 疤痕 色斑 狐臭 酒渣鼻 荨麻疹', '2022-10-26 21:02:28', '2022-11-05 16:57:14', 0);
INSERT INTO `tb_category` VALUES (13, '肺量计', '肺活量计、单、双简肺功能测定器', '2022-10-26 21:04:32', '2022-11-06 11:18:30', 1);
INSERT INTO `tb_category` VALUES (14, '家庭常备', '扭伤挫伤冻伤 烧伤烫伤 牙痛 晕车晕船 肌肉关节痛', '2022-11-05 16:59:28', NULL, 0);
INSERT INTO `tb_category` VALUES (15, '儿科用药', '小儿感冒发烧 小儿胃肠用药 小儿止咳化痰 小儿泄泻用药', '2022-11-05 17:00:26', NULL, 0);
INSERT INTO `tb_category` VALUES (16, '听诊器（无电能）', '单用、二用、三用听诊器、额戴听诊器、胎音听诊器', '2022-11-06 11:18:49', NULL, 1);
INSERT INTO `tb_category` VALUES (17, '叩诊锤（无电能）', '打诊锤、脑打诊锤、多用途叩诊锤', '2022-11-06 11:19:06', NULL, 1);
INSERT INTO `tb_category` VALUES (18, '反光器具', '额戴反光镜、电额灯、反光喉镜、聚光灯、反光灯、检眼灯、头灯', '2022-11-06 11:19:22', NULL, 1);
INSERT INTO `tb_category` VALUES (19, '视力诊察器具', '卡片投影仪、视力表灯（箱）、视力检查仪、遮眼器、标准视力字标	', '2022-11-06 11:19:38', NULL, 1);
INSERT INTO `tb_category` VALUES (20, '可吸收性止血、防粘连材料', '明胶海绵、胶原海绵、生物蛋白胶、透明质酸钠凝胶、聚乳酸防粘连膜	', '2022-11-06 11:20:25', NULL, 1);
INSERT INTO `tb_category` VALUES (21, '敷料、护创材料', '止血海绵、医用脱脂棉、医用脱脂纱布', '2022-11-06 11:20:43', NULL, 1);
INSERT INTO `tb_category` VALUES (22, '粘贴材料', '橡皮膏、透气胶带', '2022-11-06 11:21:14', NULL, 1);

-- ----------------------------
-- Table structure for tb_chat_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_chat_record`;
CREATE TABLE `tb_chat_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '聊天内容',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ip地址',
  `ip_source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ip来源',
  `type` tinyint NOT NULL COMMENT '类型',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_chat_record
-- ----------------------------
INSERT INTO `tb_chat_record` VALUES (27, 3, '员工2', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', '<img src= \'https://static.talkxj.com/emoji/dq.jpg?\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:04:39', NULL);
INSERT INTO `tb_chat_record` VALUES (28, 1, '冷血毒舌', 'http://localhost:9090/file/image/download/f6a6510ae10a4565b5104567d17ab435-2022-11-10-23-54-55.gif', '<img src= \'https://static.talkxj.com/emoji/yinxian.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:04:49', NULL);
INSERT INTO `tb_chat_record` VALUES (29, 3, '员工2', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', '<img src= \'https://static.talkxj.com/emoji/dacall.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:08:17', NULL);
INSERT INTO `tb_chat_record` VALUES (30, 1, '冷血毒舌', 'http://localhost:9090/file/image/download/f6a6510ae10a4565b5104567d17ab435-2022-11-10-23-54-55.gif', 'hello<img src= \'https://static.talkxj.com/emoji/cy.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:08:30', NULL);
INSERT INTO `tb_chat_record` VALUES (31, 3, '员工2', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', '<img src= \'https://static.talkxj.com/emoji/huaji.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:08:47', NULL);
INSERT INTO `tb_chat_record` VALUES (32, 1, '冷血毒舌', 'http://localhost:9090/file/image/download/f6a6510ae10a4565b5104567d17ab435-2022-11-10-23-54-55.gif', '<img src= \'https://static.talkxj.com/emoji/leng.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:08:57', NULL);
INSERT INTO `tb_chat_record` VALUES (33, 1, '冷血毒舌', 'http://localhost:9090/file/image/download/f6a6510ae10a4565b5104567d17ab435-2022-11-10-23-54-55.gif', '<img src= \'https://static.talkxj.com/emoji/leng.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:09:01', NULL);
INSERT INTO `tb_chat_record` VALUES (34, 3, '员工2', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', '<img src= \'https://static.talkxj.com/emoji/chigua.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:10:10', NULL);
INSERT INTO `tb_chat_record` VALUES (35, 1, '冷血毒舌', 'http://localhost:9090/file/image/download/f6a6510ae10a4565b5104567d17ab435-2022-11-10-23-54-55.gif', '<img src= \'https://static.talkxj.com/emoji/miaoa.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:24:15', NULL);
INSERT INTO `tb_chat_record` VALUES (36, 2, '客户1', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', '<img src= \'https://static.talkxj.com/emoji/chigua.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 16:48:02', NULL);
INSERT INTO `tb_chat_record` VALUES (37, 2, '客户1', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', '创*贴<img src= \'https://static.talkxj.com/emoji/dacall.jpg\' width=\'24\'height=\'24\' style=\'margin: 0 1px;vertical-align: text-bottom\'/>', '未知ip', '', 3, '2022-11-11 17:16:29', NULL);
INSERT INTO `tb_chat_record` VALUES (38, 2, '客户1', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', '啦啦啦啦啦', '未知ip', '', 3, '2022-11-11 17:16:37', NULL);
INSERT INTO `tb_chat_record` VALUES (39, 2, '客户1', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', '*', '未知ip', '', 3, '2022-11-11 17:16:47', NULL);

-- ----------------------------
-- Table structure for tb_drug
-- ----------------------------
DROP TABLE IF EXISTS `tb_drug`;
CREATE TABLE `tb_drug`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '药品id',
  `drug_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品封图',
  `drug_num` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品编号',
  `drug_common_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品通用名',
  `drug_trade_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品商品名',
  `drug_form` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品剂型',
  `drug_specification` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品规格',
  `drug_packaging_material` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品包装材质',
  `drug_effect` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '药品功效',
  `drug_manufacturer` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品厂商',
  `drug_quantity` int NULL DEFAULT NULL COMMENT '药品数量',
  `drug_price` decimal(10, 2) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '药品单价',
  `drug_category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品分类',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_drug
-- ----------------------------
INSERT INTO `tb_drug` VALUES (1, 'http://img.39.net/yy/2016/1/8/8507ad2f9f1.gif', '86900574000225', '复方感冒灵片', '复方感冒灵片(糖衣片)', '片剂(糖衣)', '每1片含原药材6.25g;含对乙酰氨基酚42mg', '片装', '口干 鼻塞 疼痛 咳嗽 风热感冒 感冒', '肇庆星湖制药有限公司', 100, 00000014.00, '感冒发热', '2022-11-05 16:30:20', '2022-11-08 21:53:46');
INSERT INTO `tb_drug` VALUES (4, 'http://pimg.39.net/yaopin/20190902/11/2019090235_1162610_507577.jpg', '86900041000017', '氨溴特罗口服溶液(易坦静)', '氨溴特罗口服溶液(易坦静)', '口服溶液剂', '100ml:盐酸氨溴索150mg与盐酸克仑特罗0.1mg', '瓶装', '儿科用药 小儿咳嗽 小儿祛痰止咳 小儿止咳化痰 平喘用药 支气管炎', '北京韩美药品有限公司', 150, 00000029.00, '儿科用药', '2022-11-05 16:47:04', '2022-11-08 21:53:50');

-- ----------------------------
-- Table structure for tb_medical_devices
-- ----------------------------
DROP TABLE IF EXISTS `tb_medical_devices`;
CREATE TABLE `tb_medical_devices`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '医疗器材id',
  `medical_devices_num` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '医疗器械编号',
  `medical_devices_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '医疗器材名称',
  `medical_devices_cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '医疗器械图片',
  `medical_devices_effect` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '医疗器械功能',
  `medical_devices_specification` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '医疗器械型号规格',
  `medical_devices_manufacturer` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '医疗器械厂商',
  `medical_devices_category` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '医疗器械分类',
  `medical_devices_quantity` int NULL DEFAULT NULL COMMENT '医疗器械数量',
  `medical_devices_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '医疗器械单价',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_medical_devices
-- ----------------------------
INSERT INTO `tb_medical_devices` VALUES (1, '皖械注准20222140029', '创口贴', 'http://img.39.net/yy/2014/9/16/2020814.jpg', '该产品用于轻微创伤、擦伤等创口的止血，并保护创面。', '弹力型 、PE 型、卡通 PE 型、防水型 ', '安徽尚亿医疗用品有限公司', '敷料、护创材料', 100, 1.00, '2022-11-06 11:27:40', '2022-11-08 21:54:01');
INSERT INTO `tb_medical_devices` VALUES (2, '桂械注准20192070100', '电子体温计', 'http://img.39.net/yy/2014/9/16/2028493.jpg', '供医疗部门和家庭作测量人体体温使用', 'N561T、N569T', '广西河池欣绿医疗科技有限公司', '体温计', 150, 28.00, '2022-11-06 11:33:25', '2022-11-08 21:54:05');

-- ----------------------------
-- Table structure for tb_purchase_list
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_list`;
CREATE TABLE `tb_purchase_list`  (
  `id` bigint NOT NULL COMMENT '主键',
  `employee_id` int NULL DEFAULT NULL COMMENT '负责员工id',
  `supplier_id` int NULL DEFAULT NULL COMMENT '供货商id',
  `cargo_list` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '货物列表',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_purchase_list
-- ----------------------------
INSERT INTO `tb_purchase_list` VALUES (2022110800013, 1, 1, '[{\"cargoId\":\"86900574000225\",\"cargoPrice\":10,\"cargoQuantity\":100},{\"cargoId\":\"86900041000017\",\"cargoPrice\":20,\"cargoQuantity\":100},{\"cargoId\":\"皖械注准20222140029\",\"cargoPrice\":0.5,\"cargoQuantity\":100},{\"cargoId\":\"桂械注准20192070100\",\"cargoPrice\":20,\"cargoQuantity\":100}]', '2022-11-08 21:55:14');
INSERT INTO `tb_purchase_list` VALUES (2022110800014, 1, 3, '[{\"cargoId\":\"86900041000017\",\"cargoPrice\":20,\"cargoQuantity\":50},{\"cargoId\":\"桂械注准20192070100\",\"cargoPrice\":20,\"cargoQuantity\":50}]', '2022-11-08 21:55:44');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_supplier
-- ----------------------------
INSERT INTO `tb_supplier` VALUES (1, '供货商1', '姓名1', 'xxx xxxx xxxx', 'supplier1@xx.com', '地址1', '中国银行', 'xxxx xxxx xxxx xxxx', '2022-10-28 14:51:38', '2022-11-06 11:44:01');
INSERT INTO `tb_supplier` VALUES (3, '供货商2', '负责人2', 'xxx xxxx xxxx', 'supplier2@xx.com', '地址2', '中国工商银行', 'xxxx xxxx xxxx xxxx', '2022-11-08 14:47:15', NULL);

-- ----------------------------
-- Table structure for tb_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_auth`;
CREATE TABLE `tb_user_auth`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_info_id` int NOT NULL COMMENT '用户信息id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `login_type` tinyint(1) NULL DEFAULT NULL COMMENT '登录类型',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户登录ip',
  `ip_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ip来源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_auth
-- ----------------------------
INSERT INTO `tb_user_auth` VALUES (1, 1, 'admin@qq.com', '$2a$10$imJ2rcJ8ZmLq9A4zU6Qj4umTmkPeszaFOSU6hiFkX4hh3Ek7OkiIq', 1, '127.0.0.1', '', '2022-11-07 18:17:32', '2022-11-11 17:55:54', '2022-11-11 17:55:54');
INSERT INTO `tb_user_auth` VALUES (2, 2, '1738252674@qq.com', '$2a$10$zMAIl0a3QFxxqVDWN9E7I..BBAY.3Lip01RdIQFNdLPkkE6oAHYUG', 1, '127.0.0.1', '', '2022-11-10 00:58:48', '2022-11-11 17:57:32', '2022-11-11 17:57:31');
INSERT INTO `tb_user_auth` VALUES (3, 3, 'test1@mmzd.com', '$2a$10$vRlZddlvN098D1KU1YAU7OUlHtc9.UnePDFXB3U.OFgK084y2W4l2', 1, '127.0.0.1', '', '2022-11-10 10:42:45', '2022-11-11 16:04:13', '2022-11-11 16:04:13');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES (1, 'admin@qq.com', '冷血毒舌', 'http://localhost:9090/file/image/download/8cc090a00b13434e8d244fa83ab884b8-2022-11-11-16-25-33.gif', 0, '2022-11-07 18:17:32', '2022-11-11 17:15:09');
INSERT INTO `tb_user_info` VALUES (2, '1738252674@qq.com', '客户1', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', 0, '2022-11-10 00:58:48', '2022-11-11 17:18:09');
INSERT INTO `tb_user_info` VALUES (3, 'test1@mmzd.com', '员工2', 'https://picx1.zhimg.com/80/v2-624c20a4ba7eadb228240791ec638f8b_720w.webp?source=1940ef5c', 0, '2022-11-10 10:42:44', NULL);

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES (12, 3, 2);
INSERT INTO `tb_user_role` VALUES (13, 2, 3);
INSERT INTO `tb_user_role` VALUES (19, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
