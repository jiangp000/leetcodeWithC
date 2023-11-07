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
    <link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/front.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/pager.css">

    <script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="application/javascript">
        window.onload=function() {
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
            <a href="trainEnroll.action">培训申请</a>
        </div>
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitTrainEnroll" method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>工号/学号</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="trainEnroll.userId"
                                                             required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>姓名</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="trainEnroll.username"
                                                             required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>电子邮箱</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-envelope"></i>
                                            </span>
                                                <s:textfield class="form-control" name="trainEnroll.email"
                                                             required="true"
                                                             type="email"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>手机号码</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-phone"></i>
                                            </span>
                                                <s:textfield class="form-control" name="trainEnroll.phone"
                                                             maxlength="12"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>选择培训期数</label>
                                            <div class="input-group" style="width: 90%;">
                                                <c:if test="${!empty list}">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <tr >
                                                            <th>#</th>
                                                            <th>培训日期</th>
                                                            <th>培训时间</th>
                                                            <th>培训主题</th>
                                                        </tr>
                                                        <c:forEach var="train" items="${list}" varStatus="s">
                                                            <tr>
                                                                <td><input type="checkbox" name="trainIds"
                                                                           value="${train.id}"
                                                                <c:forEach items="${trainIds}" varStatus="va">
                                                                           <c:if test="${trainIds[va.index]==train.id}">checked</c:if>
                                                                </c:forEach>>&nbsp;&nbsp;${s.count}
                                                                </td>
                                                                <td><fmt:formatDate value="${train.trainTime}"
                                                                                    pattern="yyyy/MM/dd"/></td>
                                                                <td>${train.beginTime}-${train.endTime}</td>
                                                                <td>${train.title}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>

                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison"><i
                                                class="glyphicon glyphicon-ok"></i> &nbsp; 提 交
                                        </button>
                                        <button type="reset" class="btn blue-madison"><i
                                                class="glyphicon glyphicon-remove"></i> &nbsp; 取 消
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


