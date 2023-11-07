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
    <script type="text/javascript" src="../js/xinsilu.js"></script>
    <script>
        $(function () {
            $("#column_3").attr("class", "selected");
            $(".li_nav span:contains('教研论文')").attr("style", "font-weight:800");
        });
    </script>
</head>
<body>

<%@ include file="../teacher/common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="researchPaperFileJSP.action">我的下载</a>
        </div>
        <div class="w_986">
            <%@ include file="siderPaper.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitPaperFileDownload" id="researchEnrollForm" enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>校园卡号</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="userId" value="%{#session.teacher}"
                                                             required="true" readonly="true"/>
                                            </div>
                                        </div>

                                        <%--<div class="form-group">
                                            <label>负责人</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="username"
                                                             required="true"/>
                                            </div>
                                        </div>--%>

                                        <div class="form-group">
                                            <label>请选择阶段：</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <select name="researchType"
                                                        class="selectpicker form-control"
                                                        data-live-search="true"
                                                        data-hide-disabled="true"
                                                        required="true">
                                                    <option value="教研论文" selected="selected">教研论文</option>
                                                </select>
                                                <%--<s:textfield class="form-control" name="username" value="教研论文" disabled="true" required="true"/>--%>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label style="margin-right: 10px;width: 100px;">请选择年份:</label>
                                            <div class="input-group">
                                             <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-calendar"></i>
                                            </span>
                                                <select name="year"
                                                        class="selectpicker form-control"
                                                        data-live-search="true"
                                                        data-hide-disabled="true"
                                                        required="true">
                                                    <%--<option value="2021" selected="selected">2021</option>--%>
                                                    <c:forEach var="item" items="${years}">
                                                        <c:choose>
                                                            <c:when test="${year==item}">
                                                                <option value="${item}"
                                                                        selected="selected">${item}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${item}">${item}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div>
                                        <p style="color: red;margin-bottom: 10px"><span id="error_msg" style="display: none"></span></p>
                                    </div>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison" onclick="return validate_enroll_exist()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 提 交
                                        </button>
                                        <button type="reset" class="btn blue-madison">
                                            <i class="glyphicon glyphicon-remove"></i>
                                            &nbsp; 取 消
                                        </button>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
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
