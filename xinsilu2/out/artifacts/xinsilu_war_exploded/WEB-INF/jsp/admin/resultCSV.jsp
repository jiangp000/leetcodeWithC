<%--
  Created by IntelliJ IDEA.
  User: widesui
  Date: 21/7/12
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

    <link rel="stylesheet" type="text/css" href="../css/front.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/pager.css">
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../plugins/simple-line-icons/simple-line-icons.css"/>

    <script type="text/javascript" src="../plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../js/xinsilu.js"></script>
</head>
<body>

<%@ include file="common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="#">网审成绩导出</a>
        </div>

        <div class="w_986">
            <div class="list_sider f_l">
                <h2 class="h2_title list_plate_bg f_family f_normal">
                    <span class="more"></span>网评成绩导出</h2>
                <div class="content list_plate_bg">
                    <ul class="list_sid_nav">
                        <li>
                            <a href="#" class="selected">
                                <i class="icon_a icon_all"></i>网评成绩表下载
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
                        <p>网审成绩表</p>
                        <c:if test="${!empty fileList}">
                            <table class="table table-striped table-bordered table-hover">
                                <c:forEach var="item" items="${fileList}" varStatus="s">
                                    <tr>
                                        <td width="20px">${s.count}</td>
                                        <td>${item.fileName}</td>
                                        <td><a href="${item.filePath}">下载</a></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                </div>
                <div class="list_plate_bg main_bot"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>


<%@ include file="common/footer.jsp" %>
</body>
</html>

