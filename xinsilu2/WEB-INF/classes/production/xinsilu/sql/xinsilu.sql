SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_database = utf8;
SET character_set_results = utf8;
SET character_set_server = utf8; 
drop database if exists xinsilu;
create database xinsilu;
use xinsilu;

/*新闻通知*/

/*:type 【0意味着新闻属于培训类，1意味着新闻属于交流研讨类】*/
drop table if exists news;
create table news(
	id bigint(20) not null primary key auto_increment,
	publish_time timestamp not null default current_timestamp on update current_timestamp,
	title varchar(255) not null,
	content text,
	link varchar(1024),
	type int not null default 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


/*附件列表*/
/*:type 【0指文件，1指exe】*/
drop table if exists files;
create table files(
	id bigint(20) not null primary key auto_increment,
	file_name varchar(255) not null,
	file_path varchar(255) not null,
	news_id bigint(20),
	type int not null default 0
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*培训安排*/
drop table if exists train;
create table train(
	id bigint(20) not null primary key auto_increment,
	train_time timestamp not null default current_timestamp,
	begin_time varchar(64) not null default "14:00",
	end_time varchar(64) not null default "16:30",
	title varchar(255) not null,
	content text not null
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*培训申请表*/
drop table if exists train_enroll;
create table train_enroll(
	id bigint(20) not null primary key auto_increment,
	train_id bigint(20) not null,
	user_id varchar(30) not null,
	user_name varchar(255) not null,
	email varchar(255) not null,
	phone varchar(32)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*项目申请表*/
drop table if exists project_enroll;
create table project_enroll(
	id bigint(20) not null primary key auto_increment,
	submit_time timestamp not null default current_timestamp,
	user_id varchar(30) not null,
	user_name varchar(255) not null,
	title varchar(255) not null,
	file_path varchar(255) not null,
	user_cv varchar(1024),
	score int,
	department varchar(255) not null,
	projectType varchar(64) not null,
	found float,
	month int
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*课题研究申请表*/
drop table if exists research_enroll;
create table research_enroll(
	id bigint(20) not null primary key auto_increment,
	submit_time timestamp not null default current_timestamp,
	user_id varchar(30) not null,
	user_name varchar(255) not null,
	title varchar(255) not null,
	file_path varchar(255) not null,
	user_cv varchar(1024),
	score int,
	research_type VARCHAR(30) not null
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*评委*/
drop table if exists judge;
create table judge(
	id bigint(20) not null primary key auto_increment,
	user_id varchar(30) not null,
	password varchar(30) not null,
	username varchar(255),
	phone varchar(20),
	email varchar(50)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*项目评分*/
drop table if exists project_score;
create table project_score(
  id bigint(20) not null primary key auto_increment,
  project_enroll_id bigint(20) not null,
  judge_id bigint(20) not null ,
  score int not null,
  comment varchar(1024),
  submit_time timestamp not null default current_timestamp
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*课题评分*/
drop table if exists research_score;
create table research_score(
  id bigint(20) not null primary key auto_increment,
  research_enroll_id bigint(20) not null,
  judge_id bigint(20) not null ,
  score int not null,
  comment varchar(1024),
  submit_time timestamp not null default current_timestamp
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


/* Test Data */

/* 1. News*/
/*type0:培训 type1:交流研讨*/
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训1：项目宣讲及申报指导','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=395&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190318');
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训2：图像素材制作与美化','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=405&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190401');
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训3：视频素材采集与制作','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=407&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190408');
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训4：音频剪辑与格式转换','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=412&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190415');
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训5：交互课件设计与制作','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=420&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190422');
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训6：跨平台移动交互课件','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=425&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190506');
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训7：快速课件设计与制作','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=447&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190513');
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训8：人工智能与教育','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=464&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190520');
insert into news (title,link,publish_time) values ('2019年“教学新思路2.0”培训9：UMU互动教学','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=468&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190528');
insert into news (title,link,publish_time,type) values ('2019年北京大学 “教学新思路2.0”项目立项公示','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=418&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190419',1);
insert into news (title,link,publish_time,type) values ('2019年北京大学 “教学新思路2.0”项目立项通知','http://cetl.pku.edu.cn/peking/jsp/jspeditor9/to/facade.jsp?dispatch=viewObject&id=424&af=/cetl/view1/viewObjectOk.jsp&editor_banner_longb=null&editor_longb=905&longa=9&FATHER_LONGB=999','20190506',1);


/* 2. Files */
insert into files (file_name,file_path,type) values('Adobe_Acrobat_DC_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Pl_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Pr_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_PS_CC_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Ai_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Lr_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Ae_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Au_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Dw_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_ID_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_An_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Me_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_Br_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_IC_Win_X64','http://software.pku.edu.cn/',1);
insert into files (file_name,file_path,type) values('Adobe_SC_Win_X64','http://software.pku.edu.cn/',1);

/* 3. Train */
insert into train (train_time,title,content) values ('20190321','项目宣讲动员','教学新思路项目解读、了解师生教学需求、指导教师项目申报；');
insert into train (train_time,title,content) values ('20190404','图像素材制作与美化','利用美图秀秀和Adobe Photoshop，帮助老师制作出丰富多彩的图像素材；');
insert into train (train_time,title,content) values ('20190411','视频素材采集与制作','用专业视频软件Adobe Premiere，帮助老师制作视频素材，包括视采集、处理及格式转换等；');
insert into train (train_time,title,content) values ('20190418','音频剪辑与格式处理','利用专业音频处理软件Adobe Audition，快速高效制作教学中使用的音频素材；');
insert into train (train_time,title,content) values ('20190425','跨平台移动交互课件','利用Adobe InDesign专业排版软件，可以制作出精美的跨平台移动交互课件；');
insert into train (train_time,title,content) values ('20190509','交互式课件设计与制作','利用Raptivity模板化的交互软件，快速制作在教学中使用的网络互动学习课件；');
insert into train (train_time,title,content) values ('20190516','快速课件设计与制作','利用iStudio便携虚拟演播系统 简单高效创作出高端互动、多终端应用的微课及慕课交互视频课件；
');
insert into train (train_time,title,content) values ('20190523','人工智能与教育','前沿学术讲座，介绍融合创新与智能引领、学习科学与深度学习等教育案例；');
insert into train (train_time,title,content) values ('20190530','互动教学与协作教研','融合学习科学、利用学习技术的UMU学习平台，支持互动教学、协同教研、家校共育、教师发展等多种场景；');
insert into train (train_time,title,content) values ('20190606','教学网教学深度融合','基于北大教学网，大赛获奖教师的教学深度融合教学实践；');
insert into train (train_time,title,content) values ('20190613','实时互动教学平台','ClassIn是全球首个还原线下体验的在线教室，支持多人音视频实时互动、实时同步黑板、多方协作文档等功能；');
insert into train (train_time,title,content) values ('20190620','新型平台建课实践','Canvas平台集课程资源建设与管理、交流互动、评估评测、统计分析、移动学习于一体，指导教师在Canvas建课实践；');
insert into train (train_time,title,content) values ('20190627','现场决赛参赛指导','讲解大赛流程及规则、指导教师赛前准备及演练；');


/* 4. train_enroll*/
insert into train_enroll (train_id,user_id,user_name,email,phone) values (1,'00000623456','教师1','jiaoshi@pku.edu.cn','13112345678');
insert into train_enroll (train_id,user_id,user_name,email,phone) values (2,'00000623457','教师2','jiaoshi@pku.edu.cn','13112345679');
insert into train_enroll (train_id,user_id,user_name,email,phone) values (3,'00000623456','教师1','jiaoshi@pku.edu.cn','13112345678');
insert into train_enroll (train_id,user_id,user_name,email,phone) values (3,'00000623457','教师2','jiaoshi@pku.edu.cn','13112345679');
insert into train_enroll (train_id,user_id,user_name,email,phone) values (4,'00000623458','教师3','jiaoshi@pku.edu.cn','13112345681');


/* 5.project_enroll */
insert into project_enroll (submit_time,user_id,user_name,department,email,phone,activity,funds) values ('20160701','00000623458','教师3','光华管理学院','jiaoshi3@pku.edu.cn','13123451234','1,2,3',2.5);
insert into project_enroll (submit_time,user_id,user_name,department,email,phone,activity,funds) values ('20190701','00000623457','教师2','软件与微电子学院','jiaoshi2@pku.edu.cn','13123451234','1',5.0);
insert into project_enroll (submit_time,user_id,user_name,department,email,phone,activity,funds) values ('20191201','00000623459','教师4','经济学院','jiaoshi4@pku.edu.cn','13123451234','2,3',2.5);
insert into project_enroll (submit_time,user_id,user_name,department,email,phone,activity,funds) values ('20170701','00000623456','教师1','光华管理学院','jiaoshi1@pku.edu.cn','13123451234','3',2.5);
insert into project_enroll (submit_time,user_id,user_name,department,email,phone,activity,funds) values ('20180701','00000623460','教师5','对外汉语学院','jiaoshi5@pku.edu.cn','13123451234','1,2,3,4',1.0);

/* 6. research_enroll */
insert into research_enroll (submit_time,user_id,user_name,title,file_path) values ('20160801','00000623458','教师3','课题研究1','/upload/xinsilu/researchEnroll/2016/enroll1.zip');
insert into research_enroll (user_id,user_name,title,file_path) values ('20160805','00000623457','教师2','课题研究2','/upload/xinsilu/researchEnroll/2016/enroll2.zip');
insert into research_enroll (user_id,user_name,title,file_path) values ('20170801','00000623459','教师4','课题研究3','/upload/xinsilu/researchEnroll/2017/enroll3.zip');
insert into research_enroll (user_id,user_name,title,file_path) values ('20170801','00000623456','教师1','课题研究4','/upload/xinsilu/researchEnroll/2017/enroll4.zip');
insert into research_enroll (user_id,user_name,title,file_path) values ('20180801','00000623460','教师5','课题研究5','/upload/xinsilu/researchEnroll/2018/enroll5.zip');
insert into research_enroll (user_id,user_name,title,file_path) values ('20190801','00000623461','教师6','课题研究6','/upload/xinsilu/researchEnroll/2019/enroll6.zip');

/* 7. judge */
insert into judge (user_id,username,password) VALUE ("judge","judge","judge");
insert into judge (user_id,username,password) VALUE ("judge2","judge2","judge");

/* 8. project_score */
insert into project_score (project_enroll_id,judge_id,score,comment) values (1,1,100,"完美");

/* 9. research_score */
insert into research_score (research_enroll_id,judge_id,score,comment) values (1,1,100,"完美");

/*
alter table project_enroll add column p_month int;
alter table project_enroll drop column found;
*/