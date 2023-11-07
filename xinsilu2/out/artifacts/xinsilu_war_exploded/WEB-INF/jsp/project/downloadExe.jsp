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
    <link rel="stylesheet" type="text/css" href="css/pager.css">
    <link rel="stylesheet" type="text/css" href="plugins/simple-line-icons/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap.css">
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jq.js"></script>
    <script type="text/javascript" src="js/jquery.easing-1.3.js"></script>
    <script type="text/javascript" src="js/jquery.iosslider.js"></script>
    <script type="text/javascript" src="js/visualMatch.js"></script>
    <script type="text/javascript" src="js/slider.js"></script>
    <script type="text/javascript" src="js/banner.js"></script>
    <script type="application/javascript">
        window.onload = function () {
            $("#column_3").attr("class", "selected");
        }
    </script>
</head>
<body>

<%@ include file="../common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="downloadExe.action">软件下载</a>
        </div>
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="list_plate_bg main_top"></div>
                <div class="content list_plate_bg">
                    <div class="list_content">
                        <c:if test="${!empty list}">
                            <table class="table table-striped table-bordered table-hover">
                                <c:forEach var="item" items="${list}">
                                    <tr>
                                        <td width="20px">
                                            <i class="icon-flag" style="color:#8C0000;"></i>
                                        </td>
                                        <td>
                                                ${item.fileName}
                                        </td>
                                        <td width="100px">
                                            <a href="${item.filePath}" target="_blank">下载</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                    </div>
                    <div class="pager-list">
                        <a href="#" class="page-item current">上一页</a>
                        <a href="#" class="page-item">1</a>
                        <a href="#" class="page-item current">下一页</a>
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
