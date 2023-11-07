<%--
  Created by IntelliJ IDEA.
  User: jinggu
  Date: 19/4/25
  Time: 下午8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教学新思路</title>
    <link rel="stylesheet" type="text/css" href="css/front.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap.css">

    <script type="text/javascript" src="js/jquery.pack.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="plugins/bootstrap/js/bootstrap.js"></script>

    <script type="application/javascript">
        window.onload=function() {
            $("#column_1").attr("class", "selected");
        }
    </script>
</head>
<body>

<%@ include file="../common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="projectIntro.action">申报指南</a>
        </div>
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="content">
                    <div class="list_content">
                        <h1 class="h1_detail_title f_family f_normal">北京大学“教学新思路”项目指南</h1>
                        <div class="detail_title_info"></div>
                        <h3 class="h3_detail_title">一、项目研究课题</h3>
                        <div class="detail_text_content">
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;全校师生（包括医学部）根据以下研究领域申报，也可在符合课题立项原则的前提下，结合学校或本单位实际工作需求，自行拟定研究方向和题目：</p>
                            <ul>
                                <li>1、高等教育教改研究与实践<br/>高等教育的理念、模式和方法的研究与创新教学实践；</li>
                                <li>2、新一代网络平台系统与建课<br/>基于OBE理念的网课标准化建课实践（BB、Canvas、Troclass）；</li>
                                <li>3、数据分析与教学指导改进<br/>北大教学网系统数据分析、课程数据分析，学生在线学习统计与改进；</li>
                                <li>4、信息技术与智慧教学案例<br/>人工智能、游戏化、AR/VR、虚拟仿真等信息技术与智慧教学案例；</li>
                                <li>5、在线混合课程创新实践<br/>微课、MOOC/SPOC、Classin、翻转课堂等在线混合课程创新实践；</li>
                                <li>6、学习科学与教学反思改进<br/>基于学习科学（脑科学）的高效教与学方法研究与多模式教学改进；</li>
                                <li>7、也可在符合课题立项原则的前提下，结合学校或本单位实际工作需求，自行拟定研究课题。</li>
                            </ul>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击&nbsp;&nbsp;<a href="projectEnroll.action"  class="btn btn-info">项目报名</a>&nbsp;&nbsp;在线报名 (面向全校师生)</p>

                        </div>

                        <h3 class="h3_detail_title">二、项目讲座培训</h3>
                        <div class="detail_text_content">
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了配合支持全校师生顺利开展项目建设，我们将开展“前沿讲座与相关主题培训”，全校师生（包括医学部）均可参加。</p>
                            <p><span>培训地点</span>：电教楼403</p>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击&nbsp;&nbsp;<a href="schedule.action" class="btn btn-warning">培训报名</a>&nbsp;&nbsp;在线报名 (面向全校师生)</p>

                        </div>

                        <h3 class="h3_detail_title">三、创新教与学大赛</h3>
                        <div class="detail_text_content">
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;北京大学创新教与学大赛是北京大学多媒体课件和网络课程大赛的延续和转型，是北京大学“教学竞赛”的重要组成部分，
                                希望各院系推荐优秀教学案例参赛，同时也欢迎广大教师自我推荐报名参赛。</p>
                            <h4 class="h4_detail_title">（一）大赛组织机构</h4>
                            <p>1．<span>组委会成员</span>：文史类、理工类、医学类各3—4人，由学校、院系主管教学领导和知名教授担任。</p>
                            <p>2．<span>组委会成员单位</span>：校工会、教务长办公室、教务部、研究生院、人事部、信息化建设与管理办公室、教师教学发展中心</p>
                            <p>3．<span>承办单位</span>：教师教学发展中心</p>

                            <h4 class="h4_detail_title">（二）大赛分组报名</h4>
                            <p><span>大赛分组</span></p>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本届大赛根据提交作品特点的不同，将参赛作品分为课件组、微课组、网课组、案例组进行比赛。</p>
                            <p><span> 报名方式</span> </p>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点击&nbsp;&nbsp;<a href="http://jpk.pku.edu.cn/coursecontest/"  class="btn btn-success">大赛报名</a>&nbsp;&nbsp;在线报名 (面向全校师生)</p>

                            <h4 class="h4_detail_title">（三）评审时间及安排</h4>
                            <p>1．<span>作品评审：10月29-30日</span></p>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;评审组将对参赛作品进行技术测试，对教学设计、教学内容等进行学科审核。选出各组参加决赛的作品。</p>
                            <p>2．<span>现场决赛：10月31日（电教331）</span></p>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要求参赛者在8分钟进行作品功能特点、创作创意以及教学应用等方面的演示和讲解，2分钟回答专家评委的现场提问。现场决赛缺席者将被视为自动放弃获奖资格。</p>

                            <h4 class="h4_detail_title">（四）奖励办法</h4>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;校级比赛根据评委会综合评判，分类别评出一等奖、二等奖、三等奖和优秀奖若干名，各奖级具体获奖人数由评委会根据比赛实际情况确定。根据院系报名参赛及获奖情况，评出优秀组织奖；</p>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;获奖证书均加盖“北京大学”公章，获奖集体将获得奖牌（杯），由组委会给予一定奖励。对于参赛的优秀作品，将推荐参加全国性或国际性大赛、研讨等交流活动；</p>


                            <h4 class="h4_detail_title">（五）技术支持</h4>
                            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了配合和支持大赛活动，教师教学发展中心将从1月至9月，面向全校教师免费提供培训课程和技术指导、设备借用及
                                咨询服务等一系列活动。</p>

                            <p><span>咨询方式：62758385/13261049123&nbsp;&nbsp;&nbsp;&nbsp;xqwang@pku.edu.cn&nbsp;&nbsp;王肖群老师</span></p>
                            <p><span>大赛网站</span>：<a href="http://jpk.pku.edu.cn/coursecontest/">http://jpk.pku.edu.cn/coursecontest/</a>
                            <p><span>大赛邮箱：coursecontest@pku.edu.cn</span></p>
                            <p><span>大赛公众号：CourseContestPKU</span></p>
                            <p><span id="gzh"></span></p>
                        </div>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="main_bot"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>

</div>


<%@ include file="../common/footer.jsp" %>
</body>
</html>
