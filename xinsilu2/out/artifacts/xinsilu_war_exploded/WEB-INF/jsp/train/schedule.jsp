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
    <link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/front.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/pager.css">

    <script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
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
            <a href="schedule.action">日程安排</a>
        </div>
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="content list_plate_bg">
                    <div class="list_content">
                        <c:if test="${empty list}">
                            <p>今年尚未安排培训</p>
                        </c:if>
                        <c:if test="${!empty list}">
                        <table class="table table-striped table-bordered table-hover">
                            <tr>
                                <th>#</th>
                                <th>培训日期</th>
                                <th>培训时间</th>
                                <th>培训主题</th>
                                <th>培训内容</th>
                            </tr>
                            <c:forEach var="train" items="${list}" varStatus="s">
                                <tr>
                                    <td>${s.count} </td>
                                    <td><fmt:formatDate value="${train.trainTime}" pattern="yyyy/MM/dd"/></td>
                                    <td>${train.beginTime}-${train.endTime}</td>
                                    <td>${train.title}</td>
                                    <td>${train.content}</td>
                                </tr>
                            </c:forEach>
                            </c:if>
                        </table>
                    </div>
                    <div class="pager-list">
                        <a href="trainEnroll" class="btn">培训报名</a>
                    </div>
                </div>
                <div class="list_plate_bg main_bot"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>


<%@ include file="../common/footer.jsp" %>
</body>
</html>
