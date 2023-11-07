/*
 Navicat Premium Data Transfer

 Source Server         : xsl
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : 162.105.14.84:3310
 Source Schema         : xinsilu

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 21/12/2020 12:16:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `news_id` bigint(20) NULL DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of files
-- ----------------------------
INSERT INTO `files` VALUES (1, 'Adobe_Acrobat_DC_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (2, 'Adobe_Pl_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (3, 'Adobe_Pr_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (4, 'Adobe_PS_CC_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (5, 'Adobe_Ai_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (6, 'Adobe_Lr_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (7, 'Adobe_Ae_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (8, 'Adobe_Au_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (9, 'Adobe_Dw_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (10, 'Adobe_ID_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (11, 'Adobe_An_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (12, 'Adobe_Me_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (13, 'Adobe_Br_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (14, 'Adobe_IC_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (15, 'Adobe_SC_Win_X64', 'http://software.pku.edu.cn/', NULL, 1);
INSERT INTO `files` VALUES (45, '2020年北京大学第九届创新教与学应用大赛现场决赛日程表.pdf', '', -1, 0);
INSERT INTO `files` VALUES (46, '北京大学第九届创新教与学应用大赛获奖名单.pdf', '', -1, 0);
INSERT INTO `files` VALUES (47, '2020年北京大学第三届创新教与学论文格式要求.pdf', '', -1, 0);
INSERT INTO `files` VALUES (48, '2021年北京大学“教学新思路2.0”项目指南.pdf', '', -1, 0);
INSERT INTO `files` VALUES (49, '2021年北京大学“教学新思路2.0”项目申请书.doc', '', -1, 0);

-- ----------------------------
-- Table structure for judge
-- ----------------------------
DROP TABLE IF EXISTS `judge`;
CREATE TABLE `judge`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of judge
-- ----------------------------
INSERT INTO `judge` VALUES (1, 'judge', 'judge', 'judge', NULL, NULL);
INSERT INTO `judge` VALUES (2, '0006170278', '13261049123', '王肖群', NULL, NULL);
INSERT INTO `judge` VALUES (27, '0016176070', '13011116041', '许雅君', NULL, NULL);
INSERT INTO `judge` VALUES (26, '0006182077', '18610812645', '董礼', NULL, NULL);
INSERT INTO `judge` VALUES (25, '0006174035', '13261049121', '何山', NULL, NULL);
INSERT INTO `judge` VALUES (24, '0016170088', '13641238170', '张英涛', NULL, NULL);
INSERT INTO `judge` VALUES (23, '0016165139', '13801209736', '蔡景一', NULL, NULL);
INSERT INTO `judge` VALUES (22, '0006152012', '13391571346', '刘树华', NULL, NULL);
INSERT INTO `judge` VALUES (21, '0006172062', '13501239398', '孙华', NULL, NULL);
INSERT INTO `judge` VALUES (20, '0006166389', '13681364651', '刘建波', NULL, NULL);
INSERT INTO `judge` VALUES (19, '0006161023', '13601242020', '陈向群', NULL, NULL);
INSERT INTO `judge` VALUES (18, '0006166149', '13911765432', '李淑静', NULL, NULL);

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `publish_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `link` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '2019-03-18 00:00:00', '2019年“教学新思路2.0”培训1：项目宣讲及申报指导', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=395&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (2, '2019-04-01 00:00:00', '2019年“教学新思路2.0”培训2：图像素材制作与美化', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=405&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (3, '2019-04-08 00:00:00', '2019年“教学新思路2.0”培训3：视频素材采集与制作', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=407&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (4, '2019-04-15 00:00:00', '2019年“教学新思路2.0”培训4：音频剪辑与格式转换', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=412&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (5, '2019-04-22 00:00:00', '2019年“教学新思路2.0”培训5：交互课件设计与制作', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=420&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (6, '2019-05-06 00:00:00', '2019年“教学新思路2.0”培训6：跨平台移动交互课件', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=425&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (7, '2019-05-13 00:00:00', '2019年“教学新思路2.0”培训7：快速课件设计与制作', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=447&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (8, '2019-05-20 00:00:00', '2019年“教学新思路2.0”培训8：人工智能与教育', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=464&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (9, '2019-06-03 00:00:00', '2019年“教学新思路2.0”培训9：UMU互动教学', '2019年“教学新思路2.0”培训9：UMU互动教学', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=468&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (20, '2019-10-14 00:00:00', '    关于征集北京大学创新教与学论文的通知', '   关于征集北京大学创新教与学论文的通知', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=523&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (10, '2019-04-19 00:00:00', '2019年北京大学 “教学新思路2.0”项目立项公示', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=418&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 1);
INSERT INTO `news` VALUES (11, '2019-05-06 00:00:00', '2019年北京大学 “教学新思路2.0”项目立项通知', NULL, 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=424&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 1);
INSERT INTO `news` VALUES (13, '2019-06-07 00:00:00', '2019年“教学新思路2.0”培训10： 教学网教学深度融合与learn SAAS云平台', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=469&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=469&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (14, '2019-06-14 00:00:00', '2019年“教学新思路2.0”培训11：实时互动教学平台体验', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=473&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=473&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (15, '2019-06-21 00:00:00', '2019年“教学新思路2.0”培训12：平台简介与建课实践', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=474&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=474&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (16, '2019-07-01 00:00:00', '2019年“教学新思路2.0”培训13：创新大赛赛前准备与决赛指导', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=476&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=476&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (21, '2019-10-21 00:00:00', '2019年“教学新思路2.0”培训14： 创新教与学教研论文交流与指导', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=524&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=524&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (24, '2019-11-17 00:00:00', '北京大学2019年教学新思路项目结题情况公示', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=539&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=539&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 1);
INSERT INTO `news` VALUES (25, '2019-11-18 00:00:00', '北京大学2019年创新教与学论文评选情况公示', 'http://jpk.pku.edu.cn/xinsilu2/admin/addNews.action', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=540&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 1);
INSERT INTO `news` VALUES (26, '2019-11-29 00:00:00', '北京大学教学新思路2019年研讨会暨2020年启动会成功召开', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=546&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=906&longa=9&FATHER_LONGB=999', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=546&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=906&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (27, '2019-12-03 18:44:46', '关于启动2020年北京大学“教学新思路”项目的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=548&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (28, '2019-12-10 11:53:17', '2020年“教学新思路2.0”培训1：项目宣讲及申报指导', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=550&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (29, '2019-12-17 11:37:32', '2020年“教学新思路2.0”培训2：“说课”视频制作与自制微课', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=554&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (32, '2020-01-02 12:30:15', '2020年“教学新思路2.0”培训4：快速交互课件设计与制作', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=561&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (31, '2019-12-24 11:21:59', '2020年“教学新思路2.0”培训3：快速3D微视频设计与制作', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=559&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (33, '2020-01-06 11:05:04', '2020年“教学新思路2.0”培训5：Canvas 建课基础与交互教学', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=566&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (34, '2020-01-10 00:00:00', '2020年北京大学“教学新思路2.0”项目立项公示', 'test', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=569&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 1);
INSERT INTO `news` VALUES (35, '2020-02-01 08:59:56', '关于为全校师生提供在线教学及资源服务的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=575&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (36, '2020-02-05 13:33:10', '关于组织全校教师开展“在线教学”培训的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=577&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (37, '2020-03-05 13:04:41', '关于组织全校教师开展“在线教学”应用培训的通知', '', 'https://courseweb.pku.edu.cn/course/EditorAction.do?dispatch=viewObject&editor_view=jiaoxuewang&id=3184&editor_longb=2', 0);
INSERT INTO `news` VALUES (38, '2020-03-30 11:56:21', '关于组织全校教师开展“在线教学”创新培训的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=593&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (39, '2020-03-31 15:19:16', '北京大学 “在线教学”培训统计与实施方式调查的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=595&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (40, '2020-04-01 13:26:05', '2020年“在线教学”创新培训1：北大在线考试系统组合方案', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=596&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (41, '2020-04-09 13:09:13', '2020年“在线教学”创新培训2：北大网络教学平台作业查重和写作助手', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=598&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (42, '2020-04-14 13:53:02', '2020年“在线教学”创新培训3：基于学习科学的高效教学设计   ', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=599&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (43, '2020-05-04 00:00:00', '北京大学完成三批次在线教学培训工作', '无', 'https://news.pku.edu.cn/xwzh/572fee14795346208cf1cd605ad9cbc8.htm', 0);
INSERT INTO `news` VALUES (44, '2020-05-31 21:44:27', '2020年“在线教学”创新培训4：人工智能与教育教学', '', 'https://courseweb.pku.edu.cn/course/EditorAction.do?dispatch=viewObject&id=3247', 0);
INSERT INTO `news` VALUES (45, '2020-05-31 21:44:54', '2020年“在线教学”创新培训5：智能技术与教学应用', '', 'https://courseweb.pku.edu.cn/course/EditorAction.do?dispatch=viewObject&id=3248', 0);
INSERT INTO `news` VALUES (46, '2020-05-31 21:45:22', '2020年“在线教学”创新培训6：OBE教学理念与建课实践', '', 'https://courseweb.pku.edu.cn/course/EditorAction.do?dispatch=viewObject&id=3249', 0);
INSERT INTO `news` VALUES (47, '2020-05-31 21:45:53', '2020年“在线教学”创新培训7：北大在线考试组合方案答疑专场', '', 'https://courseweb.pku.edu.cn/course/EditorAction.do?dispatch=viewObject&id=3257', 0);
INSERT INTO `news` VALUES (56, '2020-09-10 22:54:54', '2020年北大“混合式教学”培训7：北大Canvas混合式教学经验分享', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=719&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (57, '2020-09-10 22:55:15', '2020年北大“混合式教学”培训8：教学网混合式教学经验分享与答疑', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=720&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (50, '2020-09-10 22:51:49', '2020年北大“混合式教学”培训1：教学网在线课堂Classin', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=715&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (51, '2020-09-10 22:52:10', '2020年北大“混合式教学”培训2：教学网建课、交互教学及作业考试', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=716&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (52, '2020-09-10 22:52:31', '2020年北大“混合式教学”培训3：北大Canvas建课与Teams协作会议', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=713&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (53, '2020-09-10 22:52:56', '2020年北大“混合式教学”培训4：北大教学网在线考核组合方案', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=714&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (54, '2020-09-10 22:53:32', '2020年北大“混合式教学”培训5：华文慕课建课及课程展示', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=717&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (55, '2020-09-10 22:53:54', '2020年北大“混合式教学”培训6：教室直播录播及视频管理', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=718&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (58, '2020-09-11 08:41:57', '2020年北大“混合式教学”培训9：北大Canvas和Teams答疑专场', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=726&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (59, '2020-09-11 08:42:14', '2020年北大“混合式教学”培训10：一体化服务平台答疑专场', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=727&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (60, '2020-10-13 23:44:39', '关于举办2020年北京大学第九届创新教与学应用大赛的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=742&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (61, '2020-09-05 00:00:00', '关于组织全校教师开展“混合式教学”培训的通知', 'test', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=712&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (62, '2020-11-02 21:57:08', '2020年“教学新思路2.0”培训18：创新大赛解读与决赛指导', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=747&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (63, '2020-11-03 22:30:56', '关于网络观摩2020年北京大学第九届创新教与学应用大赛的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=755&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (64, '2020-11-03 22:31:14', '关于公示“北京大学第九届创新教与学应用大赛”结果的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=756&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (65, '2020-11-04 22:35:31', '关于征集2020年北京大学第三届创新教与学论文的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=743&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (70, '2020-12-19 19:12:29', '2020年北京大学第三届创新教与学论文评选结果公示', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=774&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (71, '2020-12-19 19:13:07', '2020年北京大学教学新思路2.0项目结题结果公示', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=775&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (72, '2020-12-19 19:13:42', '关于启动2021年北京大学“教学新思路2.0”项目的通知', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=784&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);
INSERT INTO `news` VALUES (73, '2020-12-19 19:14:17', '2021年“教学新思路2.0”培训1：项目宣讲及申报指导', '', 'http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=788&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999', 0);

-- ----------------------------
-- Table structure for project_enroll
-- ----------------------------
DROP TABLE IF EXISTS `project_enroll`;
CREATE TABLE `project_enroll`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `submit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_cv` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `projectType` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `p_found` float NULL DEFAULT NULL,
  `p_month` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1485 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_enroll
-- ----------------------------
INSERT INTO `project_enroll` VALUES (182, '2019-12-31 19:50:36', '0016177039', '杨笑菡', '以教科书背后的经典科研故事进行生物化学启发式教学探索', '/upload/xinsilu/projectEnroll/2019/0016177039-杨笑菡-项目申请书.doc', NULL, 53, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (3, '2019-12-19 16:55:40', '04101', '李玉慧', '虚拟现实技术在风湿科住院医师关节腔穿刺实践教学中的应用', '/upload/xinsilu/projectEnroll/2020/04101-李玉慧-项目申请书.docx', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (66, '2019-12-30 11:47:56', '0006165613', '郇庆治', '北京大学习近平生态文明思想教学案例微课视频', '/upload/xinsilu/projectEnroll/2019/0006165613-郇庆治-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (124, '2019-12-31 11:30:54', '1006184106', '张冰', '北京大学射箭课程“三级制”教学模式创新研究', '/upload/xinsilu/projectEnroll/2019/1006184106-张冰-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (158, '2019-12-31 15:45:09', '0016172108', '康继宏', '基于互联网的虚拟仿真实验教学平台在生理学教学中的应用与探索', '/upload/xinsilu/projectEnroll/2019/0016172108-康继宏-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (207, '2020-01-02 17:19:25', '0006183049', '张林', '北京大学研究生教学管理的探索与实践', '/upload/xinsilu/projectEnroll/2020/0006183049-张林-项目申请书.doc', NULL, 0, '研究生院', '重点', 5, 12);
INSERT INTO `project_enroll` VALUES (192, '2020-01-01 22:40:20', '0006157242', '黄恒学', '公共经济学教学法改革研究', '/upload/xinsilu/projectEnroll/2020/0006157242-黄恒学-项目申请书.doc', NULL, 0, '政府管院', '重点', 5, 24);
INSERT INTO `project_enroll` VALUES (14, '2019-12-25 13:15:09', '1801111441', '董倩', '基于学习科学的高校混合教学设计研究 ——以《教学设计与教学开发》课程为例', '/upload/xinsilu/projectEnroll/2020/1801111441-董倩-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (15, '2019-12-25 14:07:41', '0006178044', '马乃强', '大学英语教学的“散文道路”探索与实践 ——基于Canvas平台《英语散文选读》课程建设与互动教学', '/upload/xinsilu/projectEnroll/2020/0006178044-马乃强-项目申请书.pdf', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (198, '2020-01-01 22:52:47', '1206177299', '黄文彬', '基于知识图谱与学习分析的学习路径评估体系研究', '/upload/xinsilu/projectEnroll/2020/1206177299-黄文彬-项目申请书.doc', NULL, 0, '信息管理系', '一般', 2, 12);
INSERT INTO `project_enroll` VALUES (122, '2019-12-31 11:21:32', '0016176064', '侯睿', '信息化创新教学模式在医学本科教育中的应用与评价', '/upload/xinsilu/projectEnroll/2019/0016176064-侯睿-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (155, '2019-12-31 15:23:54', '0016169097', '孙玉梅', '传染病医院安全防护虚拟仿真项目', '/upload/xinsilu/projectEnroll/2019/0016169097-孙玉梅-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (44, '2019-12-29 21:22:55', '1906185157', '刘旖璇', '实验课程多层次教学的创新实践', '/upload/xinsilu/projectEnroll/2019/1906185157-刘旖璇-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (195, '2020-01-01 22:46:55', '0016184021', '王胜锋', '流行病学通识教育类课程教学新思路的理论探索与实证研究', '/upload/xinsilu/projectEnroll/2020/0016184021-王胜锋-项目申请书.doc', NULL, 0, '北医公卫学院', '重点', 5, 24);
INSERT INTO `project_enroll` VALUES (194, '2020-01-01 22:44:55', '0016172090', '王月丹', '基于人工智能的《医学免疫学》情景化试题出题系统的建立与应用研究', '/upload/xinsilu/projectEnroll/2020/0016172090-王月丹-项目申请书.doc', NULL, 0, '基础医学院', '一般', 2, 12);
INSERT INTO `project_enroll` VALUES (60, '2019-12-30 09:42:51', '0016184016', '初明', '基于虚拟仿真的混合式综合实验教学研究', '/upload/xinsilu/projectEnroll/2019/0016184016-初明-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (201, '2020-01-02 10:09:47', '1811110188', '臧瑜', '于微课的全程参与式教学在妇产科护理学实践教学中的应用与评价', '/upload/xinsilu/projectEnroll/2020/1811110188-臧瑜-项目申请书.doc', NULL, 0, '护理学院', '一般', 2, 12);
INSERT INTO `project_enroll` VALUES (61, '2019-12-30 09:43:41', '0016181054', '刘菊芬', '社会流行病学案例库教学在Canvas建课平台的实现和应用', '/upload/xinsilu/projectEnroll/2019/0016181054-刘菊芬-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (187, '2020-01-01 16:30:27', '0016180031', '杨园园', '基于“Small teaching”理念的儿科护理学教学模式探讨及效果评价', '/upload/xinsilu/projectEnroll/2020/0016180031-杨园园-项目申请书.doc', NULL, 0, '护理学院', '一般', 2, 12);
INSERT INTO `project_enroll` VALUES (203, '2020-01-02 10:13:59', '0016178043', '陈华', '智能婴儿体验式教学 在儿科护理学实践教学中的应用', '/upload/xinsilu/projectEnroll/2020/0016178043-陈华-项目申请书.doc', NULL, 0, '北医护理学院', '一般', 2, 12);
INSERT INTO `project_enroll` VALUES (123, '2019-12-31 11:28:33', '0006171422', ' 吴昊', '智能可穿戴设备辅助网球动作技术教学实践', '/upload/xinsilu/projectEnroll/2019/0006171422- 吴昊-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (68, '2019-12-30 12:19:18', '0016184030', '万梨', '基于终身体育理念的高校体育混合教学模式实践研究  以瑜伽教学为例', '/upload/xinsilu/projectEnroll/2019/0016184030-万梨-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (191, '2020-01-01 22:38:27', '0006156371', '杨伯溆', '《新媒介社会学》课程游戏化教学探索', '/upload/xinsilu/projectEnroll/2020/0006156371-杨伯溆-项目申请书.doc', NULL, 0, '新媒体研究院', '一般', 2, 12);
INSERT INTO `project_enroll` VALUES (171, '2019-12-31 16:32:11', '0016162178', '彭宜红', 'BSL-2实验室中流感病毒分离鉴定虚拟仿真实验教学案例', '/upload/xinsilu/projectEnroll/2019/0016162178-彭宜红-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (193, '2020-01-01 22:42:26', '0006173231', '闫宏飞', '基于北大教学网系统、课程和学生在线学习数据的分析研究', '/upload/xinsilu/projectEnroll/2020/0006173231-闫宏飞-项目申请书.doc', NULL, 0, '信科学院', '重点', 5, 12);
INSERT INTO `project_enroll` VALUES (181, '2019-12-31 18:41:28', '0006160125', '张锐', '安全教育与自卫防身课程混合式教学模式的探索与实践', '/upload/xinsilu/projectEnroll/2019/0006160125-张锐-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (199, '2020-01-02 08:20:58', '0016177046', '金三丽', '基于SimMan综合模拟人和标准化病人的《外科护理学》高仿真混合模拟教学案例的开发与应用', '/upload/xinsilu/projectEnroll/2020/0016177046-金三丽-项目申请书.doc', NULL, 0, '护理学院', '优先', 3, 11);
INSERT INTO `project_enroll` VALUES (50, '2019-12-30 01:19:12', '1906184197', '杨梦', '《传统与创新————走近犹太人》 ', '/upload/xinsilu/projectEnroll/2019/1906184197-杨梦-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (48, '2019-12-29 22:39:29', '0006180098', '钱俊伟', '户外探索课融合创新的教学模式研究', '/upload/xinsilu/projectEnroll/2019/0006180098-钱俊伟-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (186, '2020-01-01 10:52:43', '0016180063', '孙凤', '基于Canvas平台的混合式教学模式的设计与实践研究--以医学研究生《网状Meta分析》课程为例', '/upload/xinsilu/projectEnroll/2020/0016180063-孙凤-项目申请书.doc', NULL, 0, '北京大学公共卫生学院', '重点', 5, 12);
INSERT INTO `project_enroll` VALUES (166, '2019-12-31 16:11:07', '0016178050', '张娟', '新时代医学网络教育学生思政教育新思路探索', '/upload/xinsilu/projectEnroll/2019/0016178050-张娟-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (170, '2019-12-31 16:21:27', '0016277804', '孙宝芝', '医学生公选课在线混合课程设计与实施探索', '/upload/xinsilu/projectEnroll/2019/0016277804-孙宝芝-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (110, '2019-12-31 10:24:08', '0016172089', '杨晓梅', '基于内分泌系统的医学整合教学平台建设', '/upload/xinsilu/projectEnroll/2019/0016172089-杨晓梅-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (144, '2019-12-31 12:52:21', '1506183169', '王灿娟', '以学生小组为制作主体的微课与日语实体教学融合模式研究', '/upload/xinsilu/projectEnroll/2019/1506183169-王灿娟-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (75, '2019-12-30 16:13:41', '0006180096', '王东敏', '课内外一体化的击剑创新教学模式研究', '/upload/xinsilu/projectEnroll/2019/0006180096-王东敏-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (58, '2019-12-30 09:40:03', '78481', '刘慧琳', 'PBL融合微课教学在老年医学住院医师培养中的应用', '/upload/xinsilu/projectEnroll/2019/78481-刘慧琳-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (59, '2019-12-30 09:41:54', '03840', '张晓威', '微信教学平台的建立及效果评估', '/upload/xinsilu/projectEnroll/2019/03840-张晓威-项目申请书.docx', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (63, '2019-12-30 10:14:57', '0006174090', '赫忠慧', '身体运动智慧评分系统在“在线开放课程”中的应用', '/upload/xinsilu/projectEnroll/2019/0006174090-赫忠慧-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (168, '2019-12-31 16:14:32', '0016174063', '侯淑肖', '社区护理学教学案例编写与案例教学实践：COPD患者的社区管理', '/upload/xinsilu/projectEnroll/2019/0016174063-侯淑肖-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (69, '2019-12-30 13:57:10', '02572', '余兵', '传统讲课方式与智能信息系统嵌合的教学模式探索', '/upload/xinsilu/projectEnroll/2019/02572-余兵-项目申请书.docx', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (70, '2019-12-30 13:59:31', '04087', '闫琦', '过敏性休克-临床危机事件教学体系重建：从基础到临床，从理论到模拟', '/upload/xinsilu/projectEnroll/2019/04087-闫琦-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (71, '2019-12-30 14:56:59', '03514', '鹿群', '利用智能宫腔操作模型培训临床技能刮宫术', '/upload/xinsilu/projectEnroll/2019/03514-鹿群-项目申请书.docx', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (136, '2019-12-31 12:21:31', '0016167152', '孙宏玉', '课程思政与护理人文课程融合的案例库构建', '/upload/xinsilu/projectEnroll/2019/0016167152-孙宏玉-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (74, '2019-12-30 16:03:38', '0016180048', '陈春花', '“增强现实技术（AR）+混合式教学”在神经解剖学教学中的探索和实践', '/upload/xinsilu/projectEnroll/2019/0016180048-陈春花-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (77, '2019-12-30 17:32:22', '1406182255', '张航', '“抽象思维动作化”在数理类课程教学中的应用', '/upload/xinsilu/projectEnroll/2019/1406182255-张航-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (79, '2019-12-30 21:04:46', '0016172093', '张艳', '虚拟仿真技术在局部解剖学“金课”建设中的应用', '/upload/xinsilu/projectEnroll/2019/0016172093-张艳-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (188, '2020-01-01 17:35:41', '0061004350', '李晓晶', '高级模拟人训练及多媒体等方法在大学急救知识技能教学中的应用探索', '/upload/xinsilu/projectEnroll/2020/0061004350-李晓晶-项目申请书.doc', NULL, 0, '北大医院', '重点', 5, 12);
INSERT INTO `project_enroll` VALUES (204, '2020-01-02 12:04:08', '0006168208', '陈军', '虚拟仿真技术在促进工程制图教学中的应用', '/upload/xinsilu/projectEnroll/2020/0006168208-陈军-项目申请书.doc', NULL, 0, '工学院', '优先', 3, 24);
INSERT INTO `project_enroll` VALUES (90, '2019-12-31 00:05:26', '1083618562', '高福强', '基于互联网+AI三维手术辅助系统探讨不同临床医学院骨科研究生同质化PBL教学改革的研究', '/upload/xinsilu/projectEnroll/2019/1083618562-高福强-项目申请书.zip', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (95, '2019-12-31 08:50:08', '0006177174', '亓昕', '高校体育课智能化教学与评价系统的应用研究', '/upload/xinsilu/projectEnroll/2019/0006177174-亓昕-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (89, '2019-12-30 23:09:19', '50362', '邓小铁', '离散数学课程专家学生制度化改革方案', '/upload/xinsilu/projectEnroll/2019/50362-邓小铁-项目申请书.docx', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (116, '2019-12-31 11:06:57', '0016175042', '庞冬', '《外科护理学》混合教学课程的开发与探索', '/upload/xinsilu/projectEnroll/2019/0016175042-庞冬-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (92, '2019-12-31 04:58:33', '0016183026', '王云', '混合式教学模式在《职业卫生学》课程中的应用', '/upload/xinsilu/projectEnroll/2019/0016183026-王云-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (94, '2019-12-31 08:09:37', '11976052', '王志稳', '本科生《护理研究》多模态混合教学的实施及评价', '/upload/xinsilu/projectEnroll/2019/11976052-王志稳-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (96, '2019-12-31 09:22:40', '0006173216', '周劲', '科学与艺术', '/upload/xinsilu/projectEnroll/2019/0006173216-周劲-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (137, '2019-12-31 12:26:06', '1811110190', '侯罗娅', '增强现实（AR）技术指导不同类型伤口止血包扎的教学案例开发', '/upload/xinsilu/projectEnroll/2019/1811110190-侯罗娅-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (200, '2020-01-02 08:23:25', '78478', '刘余庆', '5G+智慧课堂在外科学临床教学中的应用', '/upload/xinsilu/projectEnroll/2020/78478-刘余庆-项目申请书.doc', NULL, 0, '北京大学第三临床医学院', '重点', 5, 24);
INSERT INTO `project_enroll` VALUES (104, '2019-12-31 09:44:51', '0006174301', '唐彦', '中华毽课程线上线下混合式教学改革', '/upload/xinsilu/projectEnroll/2019/0006174301-唐彦-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (118, '2019-12-31 11:08:42', '1716191006', '仇冠楠', '新体制教师的教学实践状况研究', '/upload/xinsilu/projectEnroll/2019/1716191006-仇冠楠-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (106, '2019-12-31 09:54:20', '02652', '王泠', '基于SPOC的混合护士长分层培训模式的构建及实践研究', '/upload/xinsilu/projectEnroll/2019/02652-王泠-项目申请书.docx', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (174, '2019-12-31 16:45:39', '1006184100', '边磊', '以测促学、以学促教——在线测试与学习平台在有机化学实验教学中的应用与探索', '/upload/xinsilu/projectEnroll/2019/1006184100-边磊-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (196, '2020-01-01 22:48:36', '0065643019', '胡亚洲', '《肿瘤相关并发症的处理与预防》在线混合教学的实践与探索', '/upload/xinsilu/projectEnroll/2020/0065643019-胡亚洲-项目申请书.doc', NULL, 0, '北医肿瘤医院', '重点', 5, 12);
INSERT INTO `project_enroll` VALUES (111, '2019-12-31 10:24:23', '0016170098', '张卫光', '基于线上课程的闯关式教学考评设计和实践', '/upload/xinsilu/projectEnroll/2019/0016170098-张卫光-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (112, '2019-12-31 10:30:10', '0006174283', '刘岩', '“学生主导，教师引导”的电化学教改研究与实践', '/upload/xinsilu/projectEnroll/2019/0006174283-刘岩-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (113, '2019-12-31 10:32:02', '04084', '周健', '基于真实病例及融合现实的标准化胸腔镜手术网络视频互动教学平台', '/upload/xinsilu/projectEnroll/2019/04084-周健-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (117, '2019-12-31 11:07:26', '0016178044', '江华', '线上线下混合式教学在神经系统疾病护理中的应用', '/upload/xinsilu/projectEnroll/2019/0016178044-江华-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (161, '2019-12-31 15:56:57', '1506177322', '化柏林', '教学数据统计分析建模与挖掘研究', '/upload/xinsilu/projectEnroll/2019/1506177322-化柏林-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (121, '2019-12-31 11:15:29', '0061004867', '张欣', '建立及评价多种教学模式联合应用的新生儿复苏课程体系', '/upload/xinsilu/projectEnroll/2019/0061004867-张欣-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (125, '2019-12-31 11:33:04', '0061004340', '齐建光', '全科医师儿科专业培训混合式课程体系建设', '/upload/xinsilu/projectEnroll/2019/0061004340-齐建光-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (172, '2019-12-31 16:41:39', '0006178076', '曾腾', '教学媒体资源服务平台的创新与实践', '/upload/xinsilu/projectEnroll/2019/0006178076-曾腾-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (127, '2019-12-31 11:34:55', '0016184037', '李珂', '基于项目化学习的呼吸支持治疗相关专业性实践活动整合', '/upload/xinsilu/projectEnroll/2019/0016184037-李珂-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (178, '2019-12-31 17:56:18', '11969138', '李明子', '以岗位胜任力为导向的《内科护理学》混合式教学模式建设', '/upload/xinsilu/projectEnroll/2019/11969138-李明子-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (130, '2019-12-31 11:47:23', '1306187073', '李田', '有机化学实验中计算化学模块的微课建设', '/upload/xinsilu/projectEnroll/2019/1306187073-李田-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (150, '2019-12-31 14:34:12', '1106185067', '吴桃李', '本科生科研训练对拔尖人才培养的促进影响探究-以北京大学物理学院为例', '/upload/xinsilu/projectEnroll/2019/1106185067-吴桃李-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (133, '2019-12-31 12:02:46', '1306572107', '张宏岩', '跨文化沟通与管理立体案例建设', '/upload/xinsilu/projectEnroll/2019/1306572107-张宏岩-项目申请书.zip', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (134, '2019-12-31 12:12:17', '1764012506', '唐琳', '新型数字化评估体系训练系统在口腔修复临床实践教学中的应用探索', '/upload/xinsilu/projectEnroll/2019/1764012506-唐琳-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (169, '2019-12-31 16:20:32', '0016173048', '吴涛', '基于遗传关联研究慕课的翻转课堂实践及评估', '/upload/xinsilu/projectEnroll/2019/0016173048-吴涛-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (202, '2020-01-02 10:13:07', '0016178026', '张岩', '基于人机互动的模拟教学案例在护理技能训练课程中的应用', '/upload/xinsilu/projectEnroll/2020/0016178026-张岩-项目申请书.docx', NULL, 0, '护理学院', '一般', 2, 12);
INSERT INTO `project_enroll` VALUES (145, '2019-12-31 13:13:23', '0363177471', '索宝军', '基于VR技术的消化科胃镜操作课程研究与效果评价', '/upload/xinsilu/projectEnroll/2019/0363177471-索宝军-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (180, '2019-12-31 18:40:17', '0016176054', '易霞', '构建创新型生物化学与分子生物学混合式教学定制课程', '/upload/xinsilu/projectEnroll/2019/0016176054-易霞-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (151, '2019-12-31 14:42:33', '0016184010', '管静', '基于学生视角的护理模拟教学中引导性反馈实施质量相关因素的质性研究', '/upload/xinsilu/projectEnroll/2019/0016184010-管静-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (153, '2019-12-31 15:08:05', '0006166463', '苗庆红', '“财政预算”课程教学新思路', '/upload/xinsilu/projectEnroll/2019/0006166463-苗庆红-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (154, '2019-12-31 15:15:56', '0164011872', '沈勇', '基于增强现实技术的口腔生理学移动端课程的建立', '/upload/xinsilu/projectEnroll/2019/0164011872-沈勇-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (156, '2019-12-31 15:30:12', '310601', '严钰洁', '基于PBL的情景教学模式在眼科本科生教学中的效果探讨', '/upload/xinsilu/projectEnroll/2019/310601-严钰洁-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (163, '2019-12-31 16:05:46', '0006164508', '周守晋', '基于认知情境的《博雅汉语》课程/教材改革新思路', '/upload/xinsilu/projectEnroll/2019/0006164508-周守晋-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (179, '2019-12-31 18:16:11', '0006161232', '王健平', '空军飞行学员航空模型实践课程', '/upload/xinsilu/projectEnroll/2019/0006161232-王健平-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (173, '2019-12-31 16:44:05', '1706186119', '王荣婧', '“以学为中心”的教学模式探索研究——以环境学科为例', '/upload/xinsilu/projectEnroll/2019/1706186119-王荣婧-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (197, '2020-01-01 22:50:21', '0065643247', '李香蕊', '医学题库构建及线上、线下业务融合的模式探索', '/upload/xinsilu/projectEnroll/2020/0065643247-李香蕊-项目申请书.doc', NULL, 0, '北医肿瘤医院', '一般', 2, 12);
INSERT INTO `project_enroll` VALUES (176, '2019-12-31 17:19:10', '1262004088', '田雪', '基于视频观察的操作性技能评估在超声辅助区域阻滞技术考核中的应用', '/upload/xinsilu/projectEnroll/2019/1262004088-田雪-项目申请书.doc', NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `project_enroll` VALUES (88, '2020-01-02 12:38:53', '0006157268', '朱晓阳', '影视艺术与社会学人类学教学', '/upload/xinsilu/projectEnroll/2020/0006157268-朱晓阳-项目申请书.docx', NULL, 0, '社会学系', '重点', 5, 24);
INSERT INTO `project_enroll` VALUES (206, '2020-01-02 12:43:05', '0006167385', '杨柳新', '思想政治教育的艺术载体研究', '/upload/xinsilu/projectEnroll/2020/0006167385-杨柳新-项目申请书.doc', NULL, 0, '马克思学院', '重点', 5, 12);

-- ----------------------------
-- Table structure for project_score
-- ----------------------------
DROP TABLE IF EXISTS `project_score`;
CREATE TABLE `project_score`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_enroll_id` bigint(20) NOT NULL,
  `judge_id` bigint(20) NOT NULL,
  `score` int(11) NOT NULL,
  `comment` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `submit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_score
-- ----------------------------

-- ----------------------------
-- Table structure for research_enroll
-- ----------------------------
DROP TABLE IF EXISTS `research_enroll`;
CREATE TABLE `research_enroll`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `submit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_cv` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `research_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1317 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of research_enroll
-- ----------------------------
INSERT INTO `research_enroll` VALUES (155, '2019-11-04 23:11:40', '0016183026', '王云', '0016183026_中期报告_2019YB005_王云_公共卫生学院', '/upload/xinsilu/researchEnroll/2019/0016183026-王云-中期报告.pdf', NULL, 99, '中期报告');
INSERT INTO `research_enroll` VALUES (147, '2019-11-04 22:59:48', '0006172225', '尚俊杰', '0006172225_中期报告_2019YB003_尚俊杰_教育学院', '/upload/xinsilu/researchEnroll/2019/0006172225-尚俊杰-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (188, '2019-11-05 09:02:22', '78481', '刘慧琳', '78481_教研论文_2019YB014_刘慧琳_第三医院', '/upload/xinsilu/researchEnroll/2019/78481_教研论文_2019YB014_刘慧琳_第三医院.pdf', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (163, '2019-11-04 23:23:47', '1811110188', '臧瑜', '1811110188_中期报告_2019YB006_臧瑜_护理学院', '/upload/xinsilu/researchEnroll/2019/1811110188-臧瑜-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (138, '2019-11-04 21:55:14', '70098', '张卫光', '70098_教研论文_2019ZD003_张卫光_基础医学院', '/upload/xinsilu/researchEnroll/2019/70098_教研论文_2019ZD003_张卫光_基础医学院.pdf', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (151, '2019-11-04 23:02:36', '0006177174', '亓昕', '0006177174_中期报告_2019YX003_亓昕_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006177174-亓昕-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (152, '2019-11-04 23:03:24', '0006178048', '王添淼', '0006178048_中期报告_2019YX005_王添淼_对外汉语学院', '/upload/xinsilu/researchEnroll/2019/0006178048-王添淼-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (154, '2019-11-04 23:10:46', '0016180063', '孙凤', '0016180063_中期报告_2019YX007_孙凤_公共卫生学院', '/upload/xinsilu/researchEnroll/2019/0016180063-孙凤-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (145, '2019-11-04 22:54:42', '0006169222', '贾积有', '0006169222_中期报告_2019YX004_贾积有_教育学院', '/upload/xinsilu/researchEnroll/2019/0006169222-贾积有-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (142, '2019-11-04 22:51:46', '78481', '刘慧琳', '78481_中期报告_2019YB014_刘慧琳_第三医院', '/upload/xinsilu/researchEnroll/2019/78481-刘慧琳-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (148, '2019-11-04 23:00:32', '0006173307', '田凯', '0006173307_中期报告_2019YB011_田凯_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006173307-田凯-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (144, '2019-11-04 22:53:37', '0006166372', '白智立', '0006166372_中期报告_2019ZD002_白智立_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006166372-白智立-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (158, '2019-11-04 23:13:00', '1106180276', '李鸿', '1106180276_中期报告_2019YB001_李鸿_歌剧研究院', '/upload/xinsilu/researchEnroll/2019/1106180276-李鸿-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (149, '2019-11-04 23:01:15', '0006173313', '吴定锋', '0006173313_中期报告_2019YB012_吴定锋_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006173313-吴定锋-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (150, '2019-11-04 23:01:53', '0006174090', '赫忠慧', '0006174090_中期报告_2019YB007_赫忠慧_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006174090-赫忠慧-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (143, '2019-11-04 22:52:36', '0006165488', '白彦', '0006165488_中期报告_2019YB010_白彦_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006165488-白彦-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (141, '2019-11-04 22:47:39', '03496', '张媛媛', '03496_中期报告_2019YX006_张媛媛_人民医院', '/upload/xinsilu/researchEnroll/2019/03496-张媛媛-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (146, '2019-11-04 22:58:45', '0006171199', '田剪秋', '0006171199_中期报告_2019YB009_田剪秋_外国语学院', '/upload/xinsilu/researchEnroll/2019/0006171199-田剪秋-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (153, '2019-11-04 23:06:13', '0006178233', '李建新', '0006178233_中期报告_2019YB008_李建新_对外汉语学院', '/upload/xinsilu/researchEnroll/2019/0006178233-李建新-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (157, '2019-11-04 23:12:23', '0016183033', '尹慧', '0016183033_中期报告_2019YB015_尹慧_公共卫生学院', '/upload/xinsilu/researchEnroll/2019/0016183033-尹慧-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (159, '2019-11-04 23:20:35', '1106181244', '张鹏翼', '1106181244_中期报告_2019YB004_张鹏翼_信息管理系', '/upload/xinsilu/researchEnroll/2019/1106181244-张鹏翼-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (160, '2019-11-04 23:21:22', '1306186086', '纪晓璐', '1306186086_中期报告_2019ZD001_纪晓璐_信息科学技术学院', '/upload/xinsilu/researchEnroll/2019/1306186086-纪晓璐-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (161, '2019-11-04 23:22:05', '1306572107', '张宏岩', '1306572107_中期报告_2019YB002_张宏岩_软件与微电子学院', '/upload/xinsilu/researchEnroll/2019/1306572107-张宏岩-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (162, '2019-11-04 23:22:57', '1506182262', '张剑葳', '1506182262_中期报告_2019YX001_张剑葳_考古文博学院', '/upload/xinsilu/researchEnroll/2019/1506182262-张剑葳-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (167, '2019-11-04 23:41:22', '03496', '张媛媛', '03496_结题报告_2019YX006_张媛媛_人民医院', '/upload/xinsilu/researchEnroll/2019/03496-张媛媛-结题报告.pdf', NULL, 5, '结题报告');
INSERT INTO `research_enroll` VALUES (165, '2019-11-04 23:39:38', '70098', '张卫光', '70098_结题报告_2019ZD003_张卫光_基础医学院', '/upload/xinsilu/researchEnroll/2019/70098-张卫光-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (166, '2019-11-04 23:40:16', '78481', '刘慧琳', '78481_结题报告_2019YB014_刘慧琳_第三医院', '/upload/xinsilu/researchEnroll/2019/78481-刘慧琳-结题报告.pdf', NULL, 0, '结题报告');
INSERT INTO `research_enroll` VALUES (168, '2019-11-04 23:42:19', '0006165488', '白彦', '0006165488_结题报告_2019YB010_白彦_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006165488-白彦-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (169, '2019-11-04 23:43:02', '0006166372', '白智立', '0006166372_结题报告_2019ZD002_白智立_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006166372-白智立-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (170, '2019-11-04 23:43:39', '0006169222', '贾积有', '0006169222_结题报告_2019YX004_贾积有_教育学院', '/upload/xinsilu/researchEnroll/2019/0006169222-贾积有-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (171, '2019-11-04 23:44:26', '0006171199', '田剪秋', '0006171199_结题报告_2019YB009_田剪秋_外国语学院', '/upload/xinsilu/researchEnroll/2019/0006171199-田剪秋-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (172, '2019-11-04 23:45:06', '0006173307', '田凯', '0006173307_结题报告_2019YB011_田凯_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006173307-田凯-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (173, '2019-11-04 23:45:46', '0006173313', '吴定锋', '0006173313_结题报告_2019YB012_吴定锋_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006173313-吴定锋-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (174, '2019-11-04 23:46:24', '0006173390', '毕明辉', '0006173390_结题报告_2019YX002_毕明辉_艺术学院', '/upload/xinsilu/researchEnroll/2019/0006173390-毕明辉-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (175, '2019-11-04 23:47:09', '0006177174', '亓昕', '0006177174_结题报告_2019YX003_亓昕_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006177174-亓昕-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (176, '2019-11-04 23:47:46', '0006178048', '王添淼', '0006178048_结题报告_2019YX005_王添淼_对外汉语学院', '/upload/xinsilu/researchEnroll/2019/0006178048-王添淼-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (177, '2019-11-04 23:48:24', '0006178233', '李建新', '0006178233_结题报告_2019YB008_李建新_对外汉语学院', '/upload/xinsilu/researchEnroll/2019/0006178233-李建新-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (178, '2019-11-04 23:49:07', '0016180063', '孙凤', '0016180063_结题报告_2019YX007_孙凤_公共卫生学院', '/upload/xinsilu/researchEnroll/2019/0016180063-孙凤-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (179, '2019-11-04 23:49:43', '0016183026', '王云', '0016183026_结题报告_2019YB005_王云_公共卫生学院', '/upload/xinsilu/researchEnroll/2019/0016183026-王云-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (180, '2019-11-04 23:50:22', '0016183033', '尹慧', '0016183033_结题报告_2019YB015_尹慧_公共卫生学院', '/upload/xinsilu/researchEnroll/2019/0016183033-尹慧-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (181, '2019-11-04 23:53:56', '1106180276', '李鸿', '1106180276_结题报告_2019YB001_李鸿_歌剧研究院', '/upload/xinsilu/researchEnroll/2019/1106180276-李鸿-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (182, '2019-11-04 23:54:31', '1106181244', '张鹏翼', '1106181244_结题报告_2019YB004_张鹏翼_信息管理系', '/upload/xinsilu/researchEnroll/2019/1106181244-张鹏翼-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (183, '2019-11-04 23:55:09', '1306186086', '纪晓璐', '1306186086_结题报告_2019ZD001_纪晓璐_信息科学技术学院', '/upload/xinsilu/researchEnroll/2019/1306186086-纪晓璐-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (184, '2019-11-04 23:55:47', '1306572107', '张宏岩', '1306572107_结题报告_2019YB002_张宏岩_软件与微电子学院', '/upload/xinsilu/researchEnroll/2019/1306572107-张宏岩-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (185, '2019-11-04 23:56:23', '1506182262', '张剑葳', '1506182262_结题报告_2019YX001_张剑葳_考古文博学院', '/upload/xinsilu/researchEnroll/2019/1506182262-张剑葳-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (186, '2019-11-04 23:56:58', '1811110188', '臧瑜', '1811110188_结题报告_2019YB006_臧瑜_护理学院', '/upload/xinsilu/researchEnroll/2019/1811110188-臧瑜-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (187, '2019-11-05 08:59:27', '03496', '张媛媛', '03496_教研论文_2019YX006_张媛媛_人民医院', '/upload/xinsilu/researchEnroll/2019/03496_教研论文_2019YX006_张媛媛_人民医院.pdf', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (189, '2019-11-05 09:06:16', '0006165488', '白彦', '0006165488_教研论文_2019YB010_白彦_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006165488_教研论文_2019YB010_白彦_政府管理学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (190, '2019-11-05 09:06:27', '0006166372', '白智立', '0006166372_教研论文_2019ZD002_白智立_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006166372_教研论文_2019ZD002_白智立_政府管理学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (191, '2019-11-05 09:06:38', '0006169222', '贾积有', '0006169222_教研论文_2019YX004_贾积有_教育学院', '/upload/xinsilu/researchEnroll/2019/0006169222_教研论文_2019YX004_贾积有_教育学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (192, '2019-11-05 09:06:53', '0006173307', '田凯', '0006173307_教研论文_2019YB011_田凯_政府管理学院', '/upload/xinsilu/researchEnroll/2019/0006173307_教研论文_2019YB011_田凯_政府管理学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (193, '2019-11-05 09:07:12', '0006173313', '吴定锋', '0006173313_教研论文_2019YB012_吴定锋_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006173313_教研论文_2019YB012_吴定锋_体育教研部', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (194, '2019-11-05 09:07:16', '0006174090', '赫忠慧', '0006174090_教研论文_2019YB007_赫忠慧_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006174090_教研论文_2019YB007_赫忠慧_体育教研部', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (195, '2019-11-05 09:07:27', '0006177174', '亓昕', '0006177174_教研论文_2019YX003_亓昕_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006177174_教研论文_2019YX003_亓昕_体育教研部', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (197, '2019-11-05 09:08:20', '0006178233', '李建新', '0006178233_教研论文_2019YB008_李建新_对外汉语学院', '/upload/xinsilu/researchEnroll/2019/0006178233_教研论文_2019YB008_李建新_对外汉语学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (198, '2019-11-05 09:08:32', '0016180063', '孙凤', '0016180063_教研论文_2019YX007_孙凤_公共卫生学院', '/upload/xinsilu/researchEnroll/2019/0016180063_教研论文_2019YX007_孙凤_公共卫生学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (200, '2019-11-05 09:09:19', '0016183033', '尹慧', '0016183033_教研论文_2019YB015_尹慧_公共卫生学院', '/upload/xinsilu/researchEnroll/2019/0016183033_教研论文_2019YB015_尹慧_公共卫生学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (201, '2019-11-05 09:09:24', '1106181244', '张鹏翼', '1106181244_教研论文_2019YB004_张鹏翼_信息管理系', '/upload/xinsilu/researchEnroll/2019/1106181244_教研论文_2019YB004_张鹏翼_信息管理系', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (202, '2019-11-05 09:09:32', '1306186086', '纪晓璐', '1306186086_教研论文_2019ZD001_纪晓璐_信息科学技术学院', '/upload/xinsilu/researchEnroll/2019/1306186086_教研论文_2019ZD001_纪晓璐_信息科学技术学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (203, '2019-11-05 09:09:43', '1306572107', '张宏岩', '1306572107_教研论文_2019YB002_张宏岩_软件与微电子学院', '/upload/xinsilu/researchEnroll/2019/1306572107_教研论文_2019YB002_张宏岩_软件与微电子学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (204, '2019-11-05 09:10:00', '1506182262', '张剑葳', '1506182262_教研论文_2019YX001_张剑葳_考古文博学院', '/upload/xinsilu/researchEnroll/2019/1506182262_教研论文_2019YX001_张剑葳_考古文博学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (205, '2019-11-05 09:10:03', '1811110188', '臧瑜', '1811110188_教研论文_2019YB006_臧瑜_护理学院', '/upload/xinsilu/researchEnroll/2019/1811110188_教研论文_2019YB006_臧瑜_护理学院', NULL, NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (223, '2019-11-06 14:06:55', '2140', '赵思铭', NULL, '/upload/xinsilu/researchEnroll/2019/2140-赵思铭-教研论文.pdf', '2140-赵思铭-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (215, '2019-11-06 13:46:44', '0016183026', '王云', NULL, '/upload/xinsilu/researchEnroll/2019/0016183026-王云-教研论文.pdf', '0016183026_教研论文_2019YB005_王云_公共卫生学院', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (229, '2019-11-06 14:09:57', '1764012506', '唐琳', NULL, '/upload/xinsilu/researchEnroll/2019/1764012506-唐琳-教研论文.pdf', '1764012506-唐琳-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (230, '2019-11-06 14:41:03', '0006162086', '张华', NULL, '/upload/xinsilu/researchEnroll/2019/0006162086-张华-教研论文.pdf', '0006162086-张华-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (207, '2019-11-05 11:31:20', '0006173390', '毕明辉', NULL, '/upload/xinsilu/researchEnroll/2019/0006173390-毕明辉-教研论文.docx', '毕明辉，北京大学艺术学院音乐学系副教授，复旦大学中文系文艺学博士后，中央音乐学院西方音乐史博士，北京大学“中国器乐研究与表演”硕士学位项目负责人。教研范围涉及中西音乐史学与美学、音乐表演、音乐教育等，注重理论与实践结合，坚持讲台与舞台同步，获得国家精品在线课程的《20世纪西方音乐》与近年开设的新课《西方音乐欣赏》等课程，长期保持北大500人次选课量之最，英文课程《中国音乐》系北大国际课程品牌项目。教学追求精进，潜心教改和研究，2013年作为首批北大慕课成员，以一流的教学设计、授课风格与制作品质，成为全球音乐通识课之佼佼者。因其教学之综合性、前沿性、交叉性与国际化，深受学生和社会好评。一系列中英文教材与专著的研究与写作，使其跻身优秀青年学者行列。曾获北京市青年教学名师、首都劳动奖章、北京市高校青教赛文科组第一名、全国青教赛文科组二等奖、全国微课比赛一等奖、全国在线教育先锋教师奖、国家精品在线开放课程等众多奖项。其多次参与海外国际会议与高校演讲,并指挥中国乐团参加包括爱丁堡音乐节在内的全球音乐节。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (208, '2019-11-06 13:19:57', '0006173390', '毕明辉', '0006173390_中期报告_2019YX002_毕明辉_艺术学院', '/upload/xinsilu/researchEnroll/2019/0006173390-毕明辉-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (209, '2019-11-06 13:29:29', '0006172225', '尚俊杰', '0006172225_结题报告_2019YB003_尚俊杰_教育学院', '/upload/xinsilu/researchEnroll/2019/0006172225-尚俊杰-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (210, '2019-11-06 13:31:00', '70098', '张卫光', '70098_中期报告_2019ZD003_张卫光_基础医学院', '/upload/xinsilu/researchEnroll/2019/70098-张卫光-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (211, '2019-11-06 13:33:55', '0006174090', '赫忠慧', '0006174090_结题报告_2019YB007_赫忠慧_体育教研部', '/upload/xinsilu/researchEnroll/2019/0006174090-赫忠慧-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (212, '2019-11-06 13:40:08', '1106180276', '李鸿', NULL, '/upload/xinsilu/researchEnroll/2019/1106180276-李鸿-教研论文.pdf', '1106180276_教研论文_2019YB001_李鸿_歌剧研究院', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (213, '2019-11-06 13:42:31', '0006172225', '尚俊杰', NULL, '/upload/xinsilu/researchEnroll/2019/0006172225-尚俊杰-教研论文.pdf', '0006172225_教研论文_2019YB003_尚俊杰_教育学院', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (214, '2019-11-06 13:44:44', '0006171199', '田剪秋', NULL, '/upload/xinsilu/researchEnroll/2019/0006171199-田剪秋-教研论文.pdf', '0006171199_教研论文_2019YB009_田剪秋_外国语学院', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (216, '2019-11-06 13:55:11', '0006166501', '夏庆杰', NULL, '/upload/xinsilu/researchEnroll/2019/0006166501-夏庆杰-教研论文.pdf', '0006166501-夏庆杰-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (217, '2019-11-06 13:57:56', '0016169097', '孙玉梅', NULL, '/upload/xinsilu/researchEnroll/2019/0016169097-孙玉梅-教研论文.pdf', '0016169097-孙玉梅-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (218, '2019-11-06 14:03:45', '0016169102', '路潜', NULL, '/upload/xinsilu/researchEnroll/2019/0016169102-路潜-教研论文.pdf', '0016169102-路潜-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (219, '2019-11-06 14:04:25', '0016178044', '江华', NULL, '/upload/xinsilu/researchEnroll/2019/0016178044-江华-教研论文.pdf', '0016178044-江华-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (220, '2019-11-06 14:04:49', '0016184037', '李珂', NULL, '/upload/xinsilu/researchEnroll/2019/0016184037-李珂-教研论文.pdf', '0016184037-李珂-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (221, '2019-11-06 14:06:06', '13488693598', '王胜锋', NULL, '/upload/xinsilu/researchEnroll/2019/13488693598-王胜锋-教研论文.pdf', '13488693598-王胜锋-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (222, '2019-11-06 14:06:33', '2079', '吴晓冉', NULL, '/upload/xinsilu/researchEnroll/2019/2079-吴晓冉-教研论文.pdf', '2079-吴晓冉-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (224, '2019-11-06 14:07:17', '82195597', '沈勇', NULL, '/upload/xinsilu/researchEnroll/2019/82195597-沈勇-教研论文.pdf', '82195597-沈勇-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (225, '2019-11-06 14:07:40', '13911137538', '施捷', NULL, '/upload/xinsilu/researchEnroll/2019/13911137538-施捷-教研论文.pdf', '13911137538-施捷-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (226, '2019-11-06 14:08:33', '1464012322', '袁重阳', NULL, '/upload/xinsilu/researchEnroll/2019/1464012322-袁重阳-教研论文.pdf', '1464012322-袁重阳-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (227, '2019-11-06 14:08:58', '2318', '章文博', NULL, '/upload/xinsilu/researchEnroll/2019/2318-章文博-教研论文.pdf', '2318-章文博-教研论文', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (232, '2019-11-08 14:14:53', '0006178048', '王添淼', NULL, '/upload/xinsilu/researchEnroll/2019/0006178048-王添淼-教研论文.7z', '基于文本挖掘技术的汉语慕课课程评估指标体系研究\r\n工号/学号: 1601213390\r\n姓名: 吴婉秋\r\n性别: 女\r\n出生年月: 19930818\r\n学历学位: 硕士研究生\r\n工作单位:北京大学对外汉语教育学院\r\n职务/职称:硕士研究生\r\n主要成果: 《基于citespace的二语慕课教学研究》、《互联网+下的教师发展研究》优秀论文奖\r\n研究方向: 对外汉语教学\r\n通讯地址: 四川省成都市高新区逸都国际3栋二单元801\r\n联系电话: 18514608703\r\nE-mail: wwqbeida@163.com\r\n\r\n国内第二语言慕课教学研究热点问题和发展趋势分析\r\n工号/学号:0006178048\r\n姓名:王添淼\r\n性别:女\r\n出生年月:1978年10月\r\n学历学位:博士\r\n工作单位:北京大学对外汉语教育学院\r\n职务/职称:研究员/长聘副教授\r\n主要成果:论文\r\n研究方向:教师发展和学生发展\r\n通讯地址:北京市海淀区颐和园路5号北京大学对外汉语教育学院\r\n联系电话:13520679395\r\nE-mail:tmwang@pku.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1305, '2020-11-12 17:45:58', '1306187073', '李田', '2020YB008_有机化学实验中计算化学模块的微课建设', '/upload/xinsilu/researchEnroll/2020/1306187073-李田-结题报告.zip', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1300, '2020-11-11 11:12:26', '1206177299', '黄文彬', '2020YB002_基于知识图谱与学习分析的学习路径评估体系研究', '/upload/xinsilu/researchEnroll/2020/1206177299-黄文彬-结题报告.zip', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1301, '2020-11-11 16:24:32', '0016172108', '康继宏', '2020YB009_基于互联网的虚拟仿真实验教学平台在生理学教学中的应用与探索', '/upload/xinsilu/researchEnroll/2020/0016172108-康继宏-结题报告.zip', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1315, '2020-11-14 13:07:19', '0006161232', '王健平', '6201000790_空军飞行学员航空模型实践课程', '/upload/xinsilu/researchEnroll/2020/0006161232-王健平-结题报告.zip', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1316, '2020-11-14 13:25:11', '0006161232', '王健平', '空军飞行学员航空模型实践课程总结', '/upload/xinsilu/researchEnroll/2020/0006161232-王健平-教研论文.zip', '王健平，工号0006161232，男，汉族，1961年12月出生，名古屋大学工学院航空航天系毕业，工学博士，现任北京大学工学院教授、博士生导师。主要研究方向是超音速燃烧技术。联系电话：13651270065，邮箱：wangjp@pku.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1311, '2020-11-12 22:31:11', 'test', 'test', 'test', '/upload/xinsilu/researchEnroll/2020/test-test-结题报告.pdf', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1310, '2020-11-12 19:01:22', '0006178076', '曾腾', '2020YB003_教学媒体资源服务平台的创新与实践', '/upload/xinsilu/researchEnroll/2020/0006178076-曾腾-结题报告.docx', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1312, '2020-11-13 11:34:57', '0016180063', '孙凤', '2020ZD001基于Canvas平台的混合式教学设计 与实践--以医学研究生《网状Meta分析》课程为例', '/upload/xinsilu/researchEnroll/2020/0016180063-孙凤-结题报告.zip', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1308, '2020-11-12 18:18:32', '1006184100', '边磊', '以测促学、以学促教——在线学习与测试平台在有机化学实验教学中的应用与探索', '/upload/xinsilu/researchEnroll/2020/1006184100-边磊-结题报告.zip', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1307, '2020-11-12 18:02:35', '1506183169', '王灿娟', '2020YB007', '/upload/xinsilu/researchEnroll/2020/1506183169-王灿娟-结题报告.rar', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1313, '2020-11-13 15:24:16', '77471', '索宝军', '2020YB010-索宝军医学生对基于VR技术的消化科胃镜操作课程接受意愿研究', '/upload/xinsilu/researchEnroll/2020/77471-索宝军-结题报告.jpeg', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1306, '2020-11-12 17:51:31', '0006173231', '闫宏飞', '2020ZD002', '/upload/xinsilu/researchEnroll/2020/0006173231-闫宏飞-结题报告.zip', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1303, '2020-11-12 11:14:30', '0016176054', '易霞', '2020YX002-构建创新型生物化学与分子生物学混合式教学定制课程', '/upload/xinsilu/researchEnroll/2020/0016176054-易霞-结题报告.zip', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1302, '2020-11-11 17:12:07', '0016172090', '王月丹', '71009Y0023_基于人工智能的《医学免疫学》 情景化试题出题系统的建立与应用研究', '/upload/xinsilu/researchEnroll/2020/0016172090-王月丹-结题报告.rar', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1297, '2020-11-09 16:41:51', '0016170098', '张卫光', '2020YX001', '/upload/xinsilu/researchEnroll/2020/0016170098-张卫光-结题报告.rar', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1296, '2020-11-09 15:33:34', '0016172089', '杨晓梅', '71009Y0024-基于内分泌系统的医学整合教学平台建设', '/upload/xinsilu/researchEnroll/2020/0016172089-杨晓梅-结题报告.docx', NULL, NULL, '结题报告');
INSERT INTO `research_enroll` VALUES (1290, '2020-11-05 19:48:27', '1306187073', '李田', '有机化学实验中计算化学模块的网络教学设计', '/upload/xinsilu/researchEnroll/2020/1306187073-李田-教研论文.docx', '李田(工号：1306187073)，女，1987年8月出生，理学博士，北京大学化学与分子工程学院化学国家级实验教学示范中心高级工程师，研究方向为有机化学实验室管理及实验教学。Email：litian@pku.edu.cn。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1291, '2020-11-05 19:50:03', '0006181118', '杨玲', '化学实验室安全技术课程在线教学实践与思考', '/upload/xinsilu/researchEnroll/2020/0006181118-杨玲-教研论文.zip', '作者简介：杨玲，女，工作证号：0006181118，1981年出生，北京大学化学与分子工程学院本科生必修课程化学实验室安全技术主讲老师，高级工程师，研究方向为化学实验室安全技术。\r\nTel：010-62754078，E-mail：yangling07@pku.edu.cn。\r\n', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1295, '2020-11-08 17:11:55', '0006178076', '曾腾', '教学媒体资源服务平台的创新与实践——基于实现北京大学线下线上同步教学的案例研究', '/upload/xinsilu/researchEnroll/2020/0006178076-曾腾-教研论文.docx', '第一作者简介：曾腾，北京大学教师教学发展中心工程师，媒体室主任，研究方向为教学资源建设、教学媒体设计与应用，近年来重点关注高校数字教育资源平台共建共治机制建设以及在线教学支撑环境标准化建设，核心期刊发表过论文《高校信息化教学平台应用推广研究》、《融入教学设计理论实施教师培训推动教师队伍建设》等。2018年获北京市教学成果一等奖及北京大学教学成果一等奖，2020年获北京大学第九届创新教与学应用大赛二等奖。联系方式：北京大学电教楼327室，邮箱：zengteng@pku.edu.cn 手机：13261049133', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1284, '2020-11-05 05:25:42', '1801111441', '董倩', '基于学习科学的高校教学设计研究', '/upload/xinsilu/researchEnroll/2020/1801111441-董倩-教研论文.docx', '作者简介：董倩，女，1992年8月，北京大学教育学院博士在读，研究方向为学习科学、教学设计、可穿戴设备，主要成果有主持中国科学技术协会高端智库博士生项目、中国棋院杭州分院棋文化研究课题等，通讯地址为北京市海淀区颐和园路5号北京大学畅春新园3号楼（18632263161,1801111441@pku.edu.cn）。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1281, '2020-11-03 16:51:16', '0016172108', '康继宏', '虚拟仿真实验在生理实验教学中的应用', '/upload/xinsilu/researchEnroll/2020/0016172108-康继宏-教研论文.rar', '康继宏（工号：0016172108），女，1972年9月出生，博士，北京大学医学部生理学与病理生理学系副教授，系副主任。从事多囊卵巢综合征的病理机制研究。主持国家自然科学基金项目5项。地址：北京海淀区学院路38号。联系电话：82805613，E-mail：kangjihong@bjmu.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1283, '2020-11-04 22:23:35', '0016183026', '王云', '情景模拟教学与微课在职业卫生学综合性实验中的应用', '/upload/xinsilu/researchEnroll/2020/0016183026-王云-教研论文.docx', '0016183026，王云，女，1983年7月出生，理学博士。北京大学公共卫生学院劳动卫生与环境卫生学系副教授，主要研究方向为纳米颗粒物的健康影响，Tel：13810503224，E-mail: wangyun@bjmu.edu.cn。欧夏娴，北京大学公共卫生学院预防医学2018级本科生。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1274, '2020-10-30 14:02:46', '0016170098', '张卫光', '以临床手术入路为导向的人体局部解剖学教学', '/upload/xinsilu/researchEnroll/2020/0016170098-张卫光-教研论文.docx', '张卫光（工号：0016170098，北京大学医学部解剖楼326，82805778/ 13683145110，zhangwg@bjmu.edu.cn），男，1970年10月出生，医学博士，北京市高校教学名师。现任基础医学院人体解剖学与组织胚胎学系教授，博士生导师。长期从事人体解剖学的教学和科研工作，主持国家级精品课程等5门，主持教育部等教学改革项目15项，主编教材或专著20部，发表教研论文60余篇。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1282, '2020-11-03 18:10:40', '2006190095', '尹旭', '基础俄语教学过程中历时阐释的活用——基于网络教学平台的微课体系构建设想', '/upload/xinsilu/researchEnroll/2020/2006190095-尹旭-教研论文.zip', '尹旭，男，1990.3，博士，北京大学外国语学院俄语系助理教授；主要成果：在中、俄期刊发表论文8篇，参与中、俄科研项目3个；研究方向：俄罗斯语法思想史、俄语标准语史、对外俄语教学中的俄语历时知识应用；通讯地址：北京市海淀区颐和园路5号北京大学外国语学院新楼526，电话：13426303102，邮箱：yinxuxy@pku.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1280, '2020-11-03 11:19:54', '2011110143；73048（通讯作者）', '王斯悦；吴涛（通讯作者）', '翻转课堂混合教学模式提高流行病学学习效果的研究', '/upload/xinsilu/researchEnroll/2020/2011110143；73048（通讯作者）-王斯悦；吴涛（通讯作者）-教研论文.zip', '吴涛，女，1973年9月生，博士，北京大学公共卫生学院教授。研究方向为遗传流行病学；以责任作者或第一作者身份发表中英文学术论文九十多篇。作为负责人建设了两门国家精品在线课程《流行病学基础》（一）、（二），发表多篇国内外教学论文。通讯地址：北京大学医学部公共卫生学院，联系电话13910033458，email：twu@bjmu.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1279, '2020-11-02 15:22:22', '0016172062', '贾默稚', '《医学寄生虫学》在线翻转课堂的建立与建设', '/upload/xinsilu/researchEnroll/2020/0016172062-贾默稚-教研论文.zip', '0016172062，贾默稚，女，1972年10月出生，理学硕士，北京大学基础医学院病原生物学系，讲师，长期从事医学寄生虫学教学，主要研究方向为医学原虫的致病机制，学院路38号 北京大学医学部病原生物学系寄生虫学教研室，010-82801423/13911015044，mzhjia@bjmu.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1278, '2020-11-02 13:11:04', '0016172089', '杨晓梅', '疫情期间临床八年制局部解剖学的教学经验总结', '/upload/xinsilu/researchEnroll/2020/0016172089-杨晓梅-教研论文.docx', '杨晓梅，女，1972年2月出生，北京大学基础医学院解剖学系讲师，工号：0016172089,北京大学医学博士， 哈佛大学医学院访问学者。长期从事人体解剖学的教学科研工作，主要科研方向为脑血管病变的脑保护机制研究。主持并参与国家自然科学基金四项，发表过SCI文章数篇，并获得过北京大学基础医学院青工教学比赛三等奖，北京大学医学部优秀教师，北京大学优秀教师等表彰。\r\n通讯地址：北京市海淀区学院路38号解剖楼327房间\r\n联系电话：13651016348\r\nEmail：xiaomeiyang@bjmu.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1277, '2020-11-02 09:54:49', '0006178044', '马乃强', '大学英语教学“散文道路”探索与实践——基于《英语散文选读》课程建设与互动教学', '/upload/xinsilu/researchEnroll/2020/0006178044-马乃强-教研论文.docx', '马乃强（0006178044），男，本科、硕士和博士均毕业于北京大学，曾赴美国、澳大利亚和新西兰等多所高校访学，现任教于北京大学外国语学院英语系，教学副教授，担任英语系副主任，主管全校大学英语和研究生英语教学；讲授大学英语综合、大学英语听说、英语阅读等基础课程，常年开设大学英语B模块课程美国短篇小说与电影和C模块课程英语散文选读，并为北京大学国际暑期学校开设现当代中国小说与电影课程；主要研究领域为英语教学、英语文学以及南太平洋国家高等教育，在《语言学研究》、《汉语教学学刊》、《北京大学教育评论》等学术期刊发表论文10余篇，已完成或目前正承担科研项目5项，在北京大学出版社出版教材《美国短篇小说与电影》，参与北大版《大学英语视听说教程》、全新版《大学英语阅读教程》、北大版《博雅英语》等多部教材的编写。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1285, '2020-11-05 09:04:54', '0006174090', '赫忠慧', '人工智能辅助功能性动作筛查（AI-FMS） 评价系统的研发与应用', '/upload/xinsilu/researchEnroll/2020/0006174090-赫忠慧-教研论文.docx', '0006174090，赫忠慧，女，1974年7月，体育教育训练学博士，北京大学体育教研部 教授，研究方向：体质与健康。主要研究领域与专长：青少年身心健康发展与运动干预，体能发展与健康提升；身体动作评价与知识图谱构建。北京大学体育与健康研究中心主任。通讯地址：北京海淀北京大学五四体育中心3030室，联系电话：13911353512，E-mail:hezhh@pku.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1276, '2020-11-02 09:38:13', '0016169097', '孙玉梅', '疫情期间护理学专业本科生在线学习效果及其影响因素的调查研究', '/upload/xinsilu/researchEnroll/2020/0016169097-孙玉梅-教研论文.docx', '孙玉梅（0016169097），北京大学护理学院副教授，女，1969.6，所负责的《健康评估》获得国家级一流本科课程、北京市优质本科课程，获国家级精品在线开放课程，所主编的教材获北京大学优秀教材；所负责的《高级健康评估》课程获医药学研究生精品课程。主要研究领域：护理教育、慢性病病人的护理，邮箱sym8022@163.com，手机13641042422', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1272, '2020-10-30 11:29:23', '88504', '欧阳汉强', '两种模式的中国骨科住院医师规范化培训比较 ——来自北京大学第三医院的临床全量数据证据', '/upload/xinsilu/researchEnroll/2020/88504-欧阳汉强-教研论文.zip', '88504，欧阳汉强，男，1988年4月出生，博士研究生，临床医学博士，北京大学第三医院骨科主治医师，研究方向医学人工智能，目前为AOSpine协会会员兼青年讲师、中国康复医学会颈椎病专业委员会成员，发表中英文论文8篇，主持省部级课题2项，曾获北京市三好学生、北京大学创新奖、北京大学优秀党员。地址：北京市海淀区花园北路49号，13810267205，ouyanghanqiang@bjmu.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1271, '2020-10-29 23:34:14', '1800013079', '刘宇晗', '游戏AI算法设计——一个适合以问题求解为导向教学的例子', '/upload/xinsilu/researchEnroll/2020/1800013079-刘宇晗-教研论文.zip', '刘宇晗（2000——），男，本科生，主要研究方向：机器学习，北京大学，电话+86 13811070290, 邮箱1800013079@pku.edu.cn；李睢（1992——），男，博士生，主要研究方向：机器学习；闫宏飞（1973——），男，北京大学信息科学技术学院副教授，主要研究方向：知识图谱、计算金融。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1270, '2020-10-28 16:30:47', '1206177299', '黄文彬', '基于知识图谱的学习路径自动生成研究', '/upload/xinsilu/researchEnroll/2020/1206177299-黄文彬-教研论文.zip', '工号/学号：1206177299\r\n姓名：黄文彬\r\n性别：男\r\n出生年月：1977.11\r\n学历学位：博士\r\n工作单位：信息管理系\r\n职务职称：副教授\r\n主要成果：\r\n基于在线教学平台构建学习者学习效率指标研究，录用期刊：现代教育技术，未见刊\r\n研究方向：数据科学、知识发现\r\n通讯地址：北京市海淀区颐和园路5号信息管理系\r\n联系电话：13810454537\r\nE-mail：huangwb@pku.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1268, '2020-10-27 09:17:10', '1106579109', '朱郑州', '基于学习数据的《IT项目管理》课程思政的探索与实践', '/upload/xinsilu/researchEnroll/2020/1106579109-朱郑州-教研论文.rar', '工号：1106579109\r\n姓名：朱郑州\r\n性别：男\r\n出生年月：1979年8月生\r\n学习学位：博士研究生\r\n工作单位：软件与微电子学院\r\n职务职称：副教授\r\n研究成果：新工科课程思政教学模式、软件工程自适应学习系统\r\n研究方向：教育大数据，智慧教育\r\n通讯地址：北京市海淀区颐和园路5号理科一号楼1505，100871\r\n联系电话：15010769789\r\nE-mail： zhuzz@pku.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1289, '2020-11-05 18:24:00', '0006180096', '王东敏', '太极拳“课内外一体化”【最新版本20201104】0006180096_教研论文__太极拳“课内外一体化”在线教学实践研究：问题、措施与效果：问题、措施与效果', '/upload/xinsilu/researchEnroll/2020/0006180096-王东敏-教研论文.pdf', '工作证号：0006180096\r\n王东敏，1980.12，北京大学体育教研部副教授，人口学博士，美国华盛顿大学访问学者。现任北京大学体育教研部副书记，北京大学武术研究中心主任，北京大学体医融合创新实验室常务副主任，北京大学未名太极社社长。研究方向为运动、人口与健康，体育教育。研究重点为太极拳运动处方与慢性病管理、传统体育养生与慢性病管理等研究。承担国家级课题1项、省部级课题7项，横向课题6项，发表学术论文20余篇，出版著作3部。\r\n联系方式：北京大学体育教研部；13810560530；dongmin_wang@pku.edu.cn\r\n', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1286, '2020-11-05 10:02:31', '1006184100', '边磊', '基于建构主义理论的有机化学实验混合式教学设计与实践', '/upload/xinsilu/researchEnroll/2020/1006184100-边磊-教研论文.zip', '1006184100，边磊，女，生于1984年10月，理学硕士学位，就职于北京大学化学与分子工程学院化学国家级实验教学示范中心，工程师，研究方向为有机化学实验教学研究、实验室技术与管理。通讯地址：北京市海淀区成府路292号；联系电话：010-62754077；E-mail：bianlei911@pku.edu.cn。本文通讯作者：张奇涵，联系电话：010-62765780；E-mail：zqh@pku.edu.cn。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1269, '2020-10-27 11:09:54', '0006171401', '王思广', '穆斯堡尔实验模拟软件mossbSim介绍', '/upload/xinsilu/researchEnroll/2020/0006171401-王思广-教研论文.rar', '王思广,男,1971.7出生，博士学位，北京大学物理学院任职副教授、长期从事数据模拟、分析技术研究与教学工作，高能物理与核物理专业，通讯地址：北京市海淀区成府路209号北京大学物理学院物理楼北214，手机：13439583217，办公室座机010-62753888，E-mail：siguang@pku.edu.cn', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1259, '2020-10-20 21:44:21', '9564011733', '崔念晖', '口腔颌面外科临床实习教学中操作并发症及应对策略', '/upload/xinsilu/researchEnroll/2020/9564011733-崔念晖-教研论文.zip', '作者：陈硕，1464012319，男，出生于1988.2，博士研究生，北京大学口腔医学院，主治医师。\r\n责任作者：9564011733，崔念晖，男，出生于1971.3，博士研究生，北京大学口腔医学院，主任医师，口腔颌面外科（教学）副主任，多次获得院级/北医级教学改革项目支持，研究方向： 牙槽外科，15611521695，cuinianhui@sina.com。', NULL, '教研论文');
INSERT INTO `research_enroll` VALUES (1257, '2020-08-30 21:10:23', '0006178076', '曾腾', '2020YB003_教学媒体资源服务平台的创新与实践', '/upload/xinsilu/researchEnroll/2020/0006178076-曾腾-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1253, '2020-08-30 14:00:57', '11', '11', '11', '/upload/xinsilu/researchEnroll/2020/11-11-中期报告.txt', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1256, '2020-08-30 17:04:26', '1206177299', '黄文彬', '2020YB002_基于知识图谱与学习分析的学习路径评估体 系研究', '/upload/xinsilu/researchEnroll/2020/1206177299-黄文彬-中期报告.zip', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1252, '2020-08-30 11:10:51', '0006165613', '郇庆治', '2020ZD003—北京大学习近平生态文明思想教学案例微课视频', '/upload/xinsilu/researchEnroll/2020/0006165613-郇庆治-中期报告.zip', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1255, '2020-08-30 15:50:04', '0016180063', '孙凤', '基于Canvas平台的混合式教学设计与实践--以医学研究生《网状Meta分析》课程为例', '/upload/xinsilu/researchEnroll/2020/0016180063-孙凤-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1254, '2020-08-30 14:23:06', '0016169097', '孙玉梅', '2020YX004_传染病医院安全防护虚拟仿真项目', '/upload/xinsilu/researchEnroll/2020/0016169097-孙玉梅-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1251, '2020-08-29 23:19:09', '1801111441@pku.edu.cn', '董倩', '2020YB005_基于学习科学的高校混合教学设计研究 ——以《教学设计与教学开发》课程为例', '/upload/xinsilu/researchEnroll/2020/1801111441@pku.edu.cn-董倩-中期报告.zip', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1250, '2020-08-29 22:55:53', '1406182255', '张航', '2020YB012', '/upload/xinsilu/researchEnroll/2020/1406182255-张航-中期报告.pdf', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1248, '2020-08-29 19:57:17', '1506183169', '王灿娟', '2020YB007_以学生小组为制作主体的微课与日语实体教学融合模式研究', '/upload/xinsilu/researchEnroll/2020/1506183169-王灿娟-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1249, '2020-08-29 20:46:49', '1306187073', '李田', '6201000796_有机化学实验中计算化学模块的微课建设', '/upload/xinsilu/researchEnroll/2020/1306187073-李田-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1246, '2020-08-28 22:42:06', '0006156371', '杨伯溆', '《新媒介社会学》游戏化教学探索', '/upload/xinsilu/researchEnroll/2020/0006156371-杨伯溆-中期报告.zip', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1245, '2020-08-28 12:48:32', '0016172090', '王月丹', '71009Y0023_基于人工智能的《医学免疫学》情景化试题出题系统的建立与应用研究', '/upload/xinsilu/researchEnroll/2020/0016172090-王月丹-中期报告.zip', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1244, '2020-08-26 21:53:56', '0006174090', '赫忠慧', '2020YX005-身体运动智慧评分系统在“在线开放课程”中的应用', '/upload/xinsilu/researchEnroll/2020/0006174090-赫忠慧-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1243, '2020-08-26 17:22:18', '0006173231', '闫宏飞', '2020ZD002', '/upload/xinsilu/researchEnroll/2020/0006173231-闫宏飞-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1242, '2020-08-24 12:50:46', '0016172089', '杨晓梅', '2020YB006-基于内分泌系统的医学整合的平台建设', '/upload/xinsilu/researchEnroll/2020/0016172089-杨晓梅-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1241, '2020-08-21 17:20:33', '0016176064', '侯睿', '2020YX003_信息化创新教学模式在医学本科教育中的应用与评价', '/upload/xinsilu/researchEnroll/2020/0016176064-侯睿-中期报告.rar', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1240, '2020-08-14 18:44:41', '1006184100', '边磊', '2020YB011_以测促学、以学促教——在线测试与学习平台在有机化学实验教学中的应用与探索', '/upload/xinsilu/researchEnroll/2020/1006184100-边磊-中期报告.zip', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1239, '2020-08-14 18:27:47', '04101', '李玉慧', '6201000793-虚拟现实技术在风湿科住院医师关节腔穿刺实践教学中的应用', '/upload/xinsilu/researchEnroll/2020/04101-李玉慧-中期报告.docx', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1238, '2020-08-11 10:39:06', '0006178044', '马乃强', '2020YX008_大学英语教学的“散文道路”探索与实践——基于CANVAS平台《英语散文选读》课程建设与互动教学', '/upload/xinsilu/researchEnroll/2020/0006178044-马乃强-中期报告.rar', NULL, NULL, '中期报告');
INSERT INTO `research_enroll` VALUES (1237, '2020-08-07 15:44:00', '0016172108', '康继宏', '2020YB009', '/upload/xinsilu/researchEnroll/2020/0016172108-康继宏-中期报告.docx', NULL, NULL, '中期报告');

-- ----------------------------
-- Table structure for research_score
-- ----------------------------
DROP TABLE IF EXISTS `research_score`;
CREATE TABLE `research_score`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `research_enroll_id` bigint(20) NOT NULL,
  `judge_id` bigint(20) NOT NULL,
  `score` int(11) NOT NULL,
  `comment` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `submit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of research_score
-- ----------------------------

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `train_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `begin_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '14:00',
  `end_time` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '16:30',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train
-- ----------------------------
INSERT INTO `train` VALUES (9, '2020-03-05 23:03:03', '14:00', '16:30', '教学网标准化建课实践', '基于OBE标准化建课实践、教学深度融合教学案例分享；');
INSERT INTO `train` VALUES (10, '2020-03-12 23:03:32', '14:00', '16:30', '实时互动教学平台体验', '在线教室ClassIn设计原理与基础功能、教学案例与深度使用、北大教学网整合应用；');
INSERT INTO `train` VALUES (8, '2020-02-27 23:02:37', '14:00', '16:30', '知识可视化与教学PPT设计', '以知识可视化原理，从三个维度改进PPT案例，提升教学PPT设计与制作水平；');
INSERT INTO `train` VALUES (7, '2020-01-08 23:01:58', '14:00', '16:30', 'Canvas标准化建课基础与教学实践', 'Canvas个性化教学实践与指导；');
INSERT INTO `train` VALUES (6, '2020-01-07 23:01:19', '14:00', '16:30', 'Canvas标准化建课基础与教学实践', 'Canvas标准化建课基础2（教学内容设计、布置作业、分组讨论、成绩管理等）；');
INSERT INTO `train` VALUES (5, '2020-01-06 23:00:56', '14:00', '16:30', 'Canvas标准化建课基础与教学实践', 'Canvas标准化建课基础1（云平台简介、课程参数设置、学生管理、框架设计等）；');
INSERT INTO `train` VALUES (4, '2020-01-02 00:00:00', '14:00', '16:30', '在线和移动学习课件制作', '利用Articulate 360快速开发跨平台自适应eLearning课程；');
INSERT INTO `train` VALUES (1, '2019-12-12 00:00:00', '14:00', '16:30', '项目宣讲及申报指导', '教学新思路项目解读、结题项目畅谈心得体会、了解教师需求项目指导；\r\n\r\n\r\n');
INSERT INTO `train` VALUES (2, '2019-12-19 22:59:08', '14:00', '16:30', '微课设计与制作技巧', '微课设计理念与应用模式、微课典型模式与案例赏析、录屏式微课制作技巧；');
INSERT INTO `train` VALUES (3, '2019-12-26 22:59:48', '14:00', '16:30', '快速3D微课及交互视频设计与制作', '利用iStudio便携虚拟演播系统，高效创作3D微课及慕课交互视频课件；');

-- ----------------------------
-- Table structure for train_enroll
-- ----------------------------
DROP TABLE IF EXISTS `train_enroll`;
CREATE TABLE `train_enroll`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `train_id` bigint(20) NOT NULL,
  `user_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `submit_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3890 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of train_enroll
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

select e.user_id as userId, e.user_name as userName, e.title as title, avg(s.video_score)*0.3 as videoScore, avg(s.document_score)*0.3 as documentScore, avg(s.video_score)*0.3 + avg(s.document_score)*0.3 as score
from research_score s, research_enroll e
where e.id = s.research_enroll_id and year(s.submit_time) = '2021'
group by s.research_enroll_id
order by avg(s.video_score)*0.3 + avg(s.document_score)*0.3 desc;

select distinct DATE_FORMAT(submit_time,'%Y') from research_score order by DATE_FORMAT(submit_time,'%Y');

select e.group, e.user_id, e.user_name, e.title, IFNULL(s.video_score, 0), IFNULL(s.document_score, 0), IFNULL(s.video_score*0.3 + s.document_score*0.3, 0), IFNULL(s.comment, '[0,0,0,0,0,0,0,0,0]尚未评分')
from research_enroll e
         left join research_score s
                   on e.id = s.research_enroll_id and s.judge_id = '2'
where year(e.submit_time) = '2021'
order by substr(e.group, 5, 2), s.video_score*0.3 + s.document_score*0.3 desc, e.group;

select distinct DATE_FORMAT(submit_time,'%Y') from research_score where judge_id = '101';

select IFNULL(mix.user_id, '0006170278'), (select username from judge where judge.user_id = '0006170278'), e.group, e.user_id, e.user_name, e.title, IFNULL(mix.video_score, 0), IFNULL(mix.document_score, 0), IFNULL(mix.video_score*0.3 + mix.document_score*0.3, 0), IFNULL(mix.comment, '[0,0,0,0,0,0,0,0,0]尚未评分')
from research_enroll e
left join (
    select s.judge_id as judge_id, j.user_id as user_id, j.username as username, s.research_enroll_id as research_enroll_id, s.video_score as video_score, s.document_score as document_score, s.comment as comment, s.submit_time as submit_time
    from research_score s, judge j
    where s.judge_id = j.id
) mix
on e.id = mix.research_enroll_id and mix.user_id = '0006170278'
where year(e.submit_time) = '2021'
order by e.group;


select s.judge_id as judge_id, j.user_id as user_id, s.research_enroll_id as research_enroll_id, s.video_score as video_score, s.document_score as document_score, s.comment as comment, s.submit_time as submit_time
from research_score s, judge j
where s.judge_id = j.id;

select distinct DATE_FORMAT(submit_time,'%Y')
from research_score s,(
    select s.judge_id as judge_id, j.user_id as user_id, s.research_enroll_id as research_enroll_id, s.video_score as video_score, s.document_score as document_score, s.comment as comment, s.submit_time as submit_time
    from research_score s, judge j
    where s.judge_id = j.id
) mix
where judge_id = '101';

select distinct DATE_FORMAT(s.submit_time,'%Y')
from research_score s, judge j
where s.judge_id = j.id and j.user_id = '0006170278'

select e.user_id, e.user_name, s.research_enroll_id, e.title, avg(s.video_score)*0.3, avg(s.document_score)*0.3, avg(s.video_score)*0.3 + avg(s.document_score)*0.3, count(e.user_id)
from research_enroll e
         left join research_score s
                   on e.id = s.research_enroll_id and year(s.submit_time) = '2021'
group by s.research_enroll_id
order by avg(s.video_score)*0.3 + avg(s.document_score)*0.3 desc;


select e.user_id as user_id, e.user_name as user_name, e.id as id, e.title as title, s.video_score as video_score, s.document_score as document_score
from research_enroll e
         left join research_score s
                   on e.id = s.research_enroll_id and year(s.submit_time) = '2021'
where year(e.submit_time) = '2021';



select mix.id, mix.user_id, mix.user_name, mix.title, ifnull(avg(mix.video_score)*0.3, 0), count(mix.video_score)
from (
         select e.id as id, e.user_id as user_id, e.user_name as user_name, e.title as title, s.video_score as video_score, s.document_score as document_score
         from research_enroll e
                  left join research_score s
                            on e.id = s.research_enroll_id and year(s.submit_time) = '2021'
where year(e.submit_time) = '2021'
    ) mix

group by mix.id
order by avg(mix.video_score)*0.3 + avg(mix.document_score)*0.3 desc;


where mix.video_score not in(
    select min(r.video_score) from research_score r where r.research_enroll_id = mix.id
) and mix.video_score not in(
    select max(r.video_score) from research_score r where r.research_enroll_id = mix.id
)


select distinct DATE_FORMAT(submit_time,'%Y') from research_score;

select j.user_id from judge j


select IFNULL(mix.user_id, 1901210672), (select username from judge where judge.user_id = '1901210672'), e.user_id, e.user_name, e.title, IFNULL(mix.video_score, 0), IFNULL(mix.document_score, 0), IFNULL(mix.video_score*0.3 + mix.document_score*0.3, 0), IFNULL(mix.comment, '[0,0,0,0,0,0,0,0,0]尚未评分') from research_enroll e left join (     select s.judge_id as judge_id, j.user_id as user_id, j.username as username, s.research_enroll_id as research_enroll_id, s.video_score as video_score, s.document_score as document_score, s.comment as comment, s.submit_time as submit_time     from research_score s, judge j     where s.judge_id = j.id     ) mix on e.id = mix.research_enroll_id and mix.user_id = '1901210672' where year(e.submit_time) = '2021' order by mix.video_score*0.3 + mix.document_score*0.3 desc;

select e.zb, e.user_id, e.user_name, e.title, IFNULL(s.video_score, 0), IFNULL(s.document_score, 0), IFNULL(s.video_score*0.3 + s.document_score*0.3, 0), IFNULL(s.comment, '[0,0,0,0,0,0,0,0,0]尚未评分')
from (
    select id, submit_time, user_id, user_name, title, file_path, user_cv, score, research_type, research_enroll.group as groupId, substr(research_enroll.group,5,2) as zb, substr(research_enroll.group,7,2) as bh
    from research_enroll
         ) e
         left join research_score s
                   on e.id = s.research_enroll_id and s.judge_id = '2'
where year(e.submit_time) = '2021'
order by e.zb, s.video_score*0.3 + s.document_score*0.3 desc;

select e.group, e.user_id, e.user_name, e.title, IFNULL(mix.video_score, 0), IFNULL(mix.document_score, 0), IFNULL(mix.video_score*0.3 + mix.document_score*0.3, 0), IFNULL(mix.comment, '[0,0,0,0,0,0,0,0,0]尚未评分')
from research_enroll e
left join (
    select s.judge_id as judge_id, j.user_id as user_id, s.research_enroll_id as research_enroll_id, s.video_score as video_score, s.document_score as document_score, s.comment as comment, s.submit_time as submit_time
    from research_score s, judge j
    where s.judge_id = j.id
    ) mix
on e.id = mix.research_enroll_id and mix.user_id = '1901210672'
where year(e.submit_time) = '2021'
order by substr(e.group, 5, 2), mix.video_score*0.3 + mix.document_score*0.3 desc, e.group;


select *
from research_enroll e
         left join research_score s
                   on e.id = s.research_enroll_id and year(s.submit_time) = '2021'
where year(e.submit_time) = '2021'

# 求平均分
select mix.id, mix.user_id, mix.user_name, mix.title, if(count(mix.video_score) > 2, (sum(mix.video_score)-max(mix.video_score)-min(mix.video_score))/(count(mix.video_score)-2)*0.3, 0), if(count(mix.document_score) > 2, (sum(mix.document_score)-max(mix.document_score)-min(mix.document_score))/(count(mix.document_score)-2)*0.3, 0), if(count(mix.document_score) > 2, (sum(mix.video_score)-max(mix.video_score)-min(mix.video_score))/(count(mix.video_score)-2)*0.3 + (sum(mix.document_score)-max(mix.document_score)-min(mix.document_score))/(count(mix.document_score)-2)*0.3, 0), count(mix.video_score), mix.groupId
from (
         select s.judge_id as judge_id, e.user_id as user_id, e.user_name as user_name, e.id as id, e.title as title, s.video_score as video_score, s.document_score as document_score, e.group as groupId
         from research_enroll e
                  left join research_score s
                            on e.id = s.research_enroll_id and year(s.submit_time) = '2021'
         where year(e.submit_time) = '2021'
     ) mix
where mix.judge_id in(
    select judge.id
    from judge
    where judge.is2021Jugde = 1
)
group by mix.id
order by mix.groupId;

# 下载所有成绩
select IFNULL(mix.user_id, ?), (select username from judge where judge.user_id = ?), e.user_id, e.user_name, e.title, IFNULL(mix.video_score, 0), IFNULL(mix.document_score, 0), IFNULL(mix.video_score*0.3 + mix.document_score*0.3, 0), IFNULL(mix.comment, '[0,0,0,0,0,0,0,0,0]尚未评分'), e.group
from research_enroll e
left join (
    select j.is2021Jugde as is2021Jugde, s.judge_id as judge_id, j.user_id as user_id, j.username as username, s.research_enroll_id as research_enroll_id, s.video_score as video_score, s.document_score as document_score, s.comment as comment, s.submit_time as submit_time
    from research_score s, judge j
    where s.judge_id = j.id
    ) mix
on e.id = mix.research_enroll_id and mix.user_id = ?
where year(e.submit_time) = ?
order by e.group;