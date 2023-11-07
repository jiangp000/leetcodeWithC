<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":"+request.getServerPort() + request.getContextPath() + "/";
%>
<div class="header">
    <div class="head_top line_bg" style="background: #700005">
        <div class="head_top_content">
            <div class="top_l f_l">
            <span class="txt f_l" style="color:#e5e3e3">
                您好，欢迎访问教学新思路！
            </span>
            </div>
            <div class="f_r top_r">
                <ul class="top_r_ul">
                    <li class="f_l">
                        <i class="icon-logout" style="color: white"></i>
                        <a class="txt f_l" href="judge1.action" style="color:#e5e3e3">
                            校内评委登录&nbsp;
                        </a>
                    </li>
                </ul>
            </div>
            <div class="f_r top_r">
                <ul class="top_r_ul">
                    <li class="f_l">
                        <i class="icon-logout" style="color: white"></i>
                        <a class="txt f_l" href="teacher.action" style="color:#e5e3e3">
                            教师登录&nbsp;
                        </a>
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="head_logo_nav1">
        <div style="width:980px; margin:0 auto">
            <img src="images/banner2.jpg" width="979px" height="100px">
            <!--< img src="<%=basePath%>images/banner2.jpg" width="100%" height="100px">-->
            <!--<img src="../images/banner2.jpg"/>" width="979px" height="100px">-->
        </div>
    </div>
    <div class="head_logo_nav">
        <div class="head_nav f_l">
            <ul class="head_nav_ul" id="head_nav_ul">
                <li class="li_nav">
                    <a href="index.action"><span>首页</span></a>
                </li>
                <li class="li_nav">
                    <a href="projectIntro.action"><span>项目介绍</span></a>
                    <div class="li_nav_div">
                        <ul class="li_nav_ul">
                            <li>
                                <a href="projectIntro.action">申报指南</a>
                            </li>
                            <li>
                                <a href="downloadFile.action">文件下载</a>
                            </li>
                            <li>
                                <a href="downloadExe.action">软件下载</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="li_nav">
                    <a href="schedule.action"><span>培训讲座</span></a>
                    <div class="li_nav_div">
                        <ul class="li_nav_ul">
                            <li>
                                <a href="schedule.action">日程安排</a>
                            </li>
                            <li>
                                <a href="oldTraining.action">往期回顾</a>
                            </li>
                            <li>
                                <a href="trainEnroll.action">培训申请</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="li_nav">
                    <a href="http://jpk.pku.edu.cn/coursecontest/" target="_blank">
                        <span>创新大赛</span>
                    </a>
                </li>
<%--                <li class="li_nav ">--%>
<%--                    <a href="index.action"><span>课题研究</span></a>--%>
<%--                </li>--%>
                <li class="li_nav ">
                    <a href="discussion.action"><span>教师专访</span></a>
                </li>
<%--                <li class="li_nav">--%>
<%--                    <a href="InnovationEnroll.action"><span>资料提交</span></a>--%>
<%--                    <div class="li_nav_div">--%>
<%--                        <ul class="li_nav_ul">--%>
<%--                            <li>--%>
<%--                                <a href="InnovationEnroll.action">创新大赛</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="midResearchEnroll.action">中期报告</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="paperResearchEnroll.action">教研论文</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="finalResearchEnroll.action">结题报告</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </li>--%>
<%--                <li class="li_nav ">--%>
<%--                    <a href="judge.action"><span>用户登录</span></a>--%>
<%--                    <div class="li_nav_div">--%>
<%--                        <ul class="li_nav_ul">--%>
<%--                            <li>--%>
<%--                                <a href="teacher.action">参赛教师</a>--%>
<%--                            </li>--%>
<%--                            <li>--%>
<%--                                <a href="judge.action">大赛评委</a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
<%--                    </div>--%>
<%--                </li>--%>
            </ul>
        </div>
        <div class="clear"></div>
    </div>

</div>
