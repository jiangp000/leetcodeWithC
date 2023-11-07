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
    <link rel="stylesheet" type="text/css" href="plugins/simple-line-icons/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/front.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/pager.css">

    <script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
</head>
<body>

<%@ include file="../common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="moreNews.action">新闻列表</a>
        </div>
        <div class="w_986">
            <div class="list_sider f_l">
                <h2 class="h2_title list_plate_bg f_family f_normal">
                    <span class="more"></span>新闻通知</h2>
                <div class="content list_plate_bg">
                    <ul class="list_sid_nav">
                        <li>
                            <a href="moreNews.action" class="selected">
                                <i class="icon_a icon_all"></i>新闻列表
                            </a>
                        </li>
                    </ul>
                    <div class="clear"></div>
                </div>
                <div class="list_sider_bot list_plate_bg"></div>
            </div>
            <div class="list_main f_r">
                <div class="content list_plate_bg">
                    <div class="list_content">
                        <c:if test="${!empty newsList}">
                        <table class="table table-striped table-bordered table-hover">
                            <c:forEach var="item" items="${newsList}">
                                <c:if test="${!empty item.link}">
                                    <tr>
                                        <td width="20px">
                                            <i class="icon-flag" style="color:#8C0000;"></i>
                                        </td>
                                        <td>
                                            <a href="${item.link}" target="_blank">${item.title}</a>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${empty item.link}">
                                    <tr>
                                        <td width="20px">
                                            <i class="icon-flag" style="color:#8C0000;"></i>
                                        </td>
                                        <td>
                                            <a href="newsInfo?news.id=${item.id}">${item.title}</a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </c:if>
                        </table>
                    </div>
                    <div class="pager-list">
                        <a href="moreNews.action?startPage=${startPage-1}" class="page-item current">上一页</a>
                        <a href="moreNews.action?startPage=${startPage}" class="page-item ">${startPage}</a>
                        <a href="moreNews.action?startPage=${startPage+1}" class="page-item current">下一页</a>
                    </div>
                </div>
                <div class="list_plate_bg main_bot"></div>
            </div>
            <div class="clear">
        </div>
    </div>
</div>


<%@ include file="../common/footer.jsp" %>
</body>
</html>

