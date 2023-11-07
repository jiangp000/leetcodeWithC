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
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教学新思路</title>
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap-fileinput/fileinput.css">
    <link rel="stylesheet" type="text/css" href="../css/front.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/pager.css">

    <script type="text/javascript" src="../plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../plugins/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="../plugins/bootstrap-fileinput/fileinput.js"></script>
    <script type="text/javascript" src="../plugins/bootstrap/js/bootstrap-paginator.js"></script>
    <script type="text/javascript" src="../js/xinsilu.js"></script>

    <style type="text/css">
        div {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            position: relative;
        }
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }
        #container {
            width: 100%;
            min-height: 100%;
            display: flex;
            flex-direction: column;
        }
        #footer {
            width: 100%;
            height: 100px;
            margin-top: auto;
        }
    </style>
</head>
<body>
<div id="container">
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ include file="common/head.jsp" %>

    <div class="list_box">
        <div class="list_bor">
            <div class="w_986">
                <%--            <%@ include file="sider.jsp" %>--%>

                <div class="list_sider f_l">
                    <h2 class="h2_title list_plate_bg f_family f_normal">
                        <span class="more"></span>评委评审</h2>
                    <div class="content list_plate_bg">
                        <ul class="list_sid_nav">
                            <li>
                                <a href="scoreResearchJSP?id=1" id="column_1">
                                    <i class="icon_a icon_all"></i>评委评审
                                </a>
                            </li>
                            <li>
                                <a href="downloadResearchScoreJSP" id="column_2" class="selected">
                                    <i class="icon_a icon_all"></i>成绩下载
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
                            <p>成绩表下载</p>
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
</div>
</body>
</html>
