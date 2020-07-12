/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50726
Source Host           : 127.0.0.1:3306
Source Database       : admin_api

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-07-12 16:18:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) DEFAULT NULL COMMENT 'URL地址',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级编号',
  `order_by` int(11) DEFAULT NULL COMMENT '排序',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `drop_state` int(1) DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Table structure for sys_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_function`;
CREATE TABLE `sys_menu_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `name` varchar(255) DEFAULT NULL COMMENT '功能名称',
  `code` varchar(255) DEFAULT NULL COMMENT '功能编码',
  `enabled` int(1) DEFAULT '0' COMMENT '是否可用(0否，1是)',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `drop_state` int(1) DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='菜单功能表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称（中文名）',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `drop_state` int(1) DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `drop_state` int(1) DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Table structure for sys_role_menu_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_function`;
CREATE TABLE `sys_role_menu_function` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `menu_function_id` bigint(20) DEFAULT NULL COMMENT '菜单功能id',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `drop_state` int(1) DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='角色与菜单功能表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '系统用户id',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `drop_state` int(1) DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `creator_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_name` varchar(255) DEFAULT NULL COMMENT '创建人姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updater_id` bigint(20) DEFAULT NULL COMMENT '更新人',
  `update_name` varchar(255) DEFAULT NULL COMMENT '更新人姓名',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `drop_state` int(1) DEFAULT '0' COMMENT '0未删除，1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
