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

    <link rel="stylesheet" type="text/css" href="../css/front.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/pager.css">
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="../plugins/simple-line-icons/simple-line-icons.css"/>

    <script type="text/javascript" src="../plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="../plugins/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="../plugins/bootstrap/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="../plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../js/xinsilu.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            $("#column_2").attr("class", "selected");
            $('#datetimepicker').datetimepicker({
                language:  'zh-CN',
                weekStart: 1,
                todayBtn:  1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format:'yyyy-mm-dd'
            });
        }
    </script>
</head>
<body>

<%@ include file="../common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="addTrainJSP">新增培训</a>
        </div>
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="content list_plate_bg">
                    <div class="list_content">

                        <div class="portlet-body form">
                            <form action="addTrain" method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label style="margin-right: 10px;">培训日期</label>
                                                <s:textfield name="train.trainTime" type="date" id="datetimepicker">
                                                    <s:param name="value">
                                                        <s:date name="train.trainTime" format="yyyy-MM-dd"/>
                                                    </s:param>
                                                </s:textfield>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label style="margin-right: 10px;">培训开始时间</label>
                                                <select name="train.beginTime" value="train.beginTime">
                                                    <c:forEach var="item" items="${quantums}">
                                                        <c:choose>
                                                            <c:when test="${train.beginTime==item}">
                                                                <option value="${item}" selected="selected">${item}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${item}">${item}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group">
                                                <label style="margin-right: 10px;">培训结束时间</label>
                                                <select name="train.endTime" value="train.endTime">
                                                    <c:forEach var="item" items="${quantums}">
                                                        <c:choose>
                                                            <c:when test="${train.endTime==item}">
                                                                <option value="${item}" selected="selected">${item}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${item}">${item}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>培训标题</label>
                                            <div class="input-group" style="width: 100%;">
                                                <s:textfield class="form-control" name="train.title"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>培训内容</label>
                                            <div class="input-group" style="width: 100%;">
                                                <s:textarea class="form-control" name="train.content" required="true"
                                                            style="min-height:200px; border:2px solid; "/>
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
                <div class="list_plate_bg main_bot"></div>
            </div>
                <div class="clear"></div>
        </div>
    </div>
</div>


<%@ include file="../common/footer.jsp" %>
</body>
</html>

