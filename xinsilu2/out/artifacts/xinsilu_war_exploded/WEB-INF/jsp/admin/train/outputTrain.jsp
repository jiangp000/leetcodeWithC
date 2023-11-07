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
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap-select.min.css">
    <link rel="stylesheet" type="text/css" href="../plugins/simple-line-icons/simple-line-icons.css"/>

    <script type="text/javascript" src="../plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="../plugins/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="../plugins/bootstrap/js/bootstrap-select.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../js/xinsilu.js"></script>
    <script type="text/javascript">
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
            <a href="outputTrainJSP">培训申请表导出</a>
        </div>
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="content list_plate_bg">
                    <div class="list_content">

                        <div class="portlet-body form">
                            <form>
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <label style="margin-right: 10px;">请选择年份:</label>
                                                <select name="year" value="year"
                                                        id="yearSelect"
                                                        onchange="return send_ajax('outputTrainJSP')">
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
                                </fieldset>
                            </form>
                        </div>
                        <!-- form end-->
                        <div class="portlet-body form">
                            <form action="outputTrain" method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <c:if test="${!empty list}">
                                            <table class="table table-striped table-bordered table-hover">
                                                <c:forEach var="train" items="${list}" varStatus="s">
                                                    <tr>
                                                        <td>
                                                            <input type="checkbox" name="trainIds" value="${train.id}"
                                                            <c:forEach items="${trainIds}" varStatus="va">
                                                                   <c:if test="${trainIds[va.index]==train.id}">checked</c:if>
                                                            </c:forEach>>
                                                                ${s.count}
                                                        </td>
                                                        <td>
                                                            <fmt:formatDate value="${train.trainTime}"
                                                                            pattern="yyyy/MM/dd"/>
                                                        </td>
                                                        <td>${train.beginTime}-${train.endTime}</td>
                                                        <td>${train.title}</td>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                        </c:if>
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

