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
    <link rel="stylesheet" type="text/css" href="../plugins/simple-line-icons/simple-line-icons.css"/>

    <script type="text/javascript" src="../plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../js/xinsilu.js"></script>
    <script type="text/javascript">
        window.onload = function () {
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
            <a href="train?year=${current_year}">管理培训</a>
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
                                                        onchange="return send_ajax('train')">
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
                        <c:if test="${!empty list}">
                            <table class="table table-striped table-bordered table-hover">
                                <c:forEach var="train" items="${list}" varStatus="s">
                                    <tr>
                                        <td width="20px">${s.count}</td>
                                        <td width="100px"><fmt:formatDate value="${train.trainTime}"
                                                                          pattern="yyyy/MM/dd"/></td>
                                        <td width="110px">${train.beginTime}-${train.endTime}</td>
                                        <td>${train.title}</td>
                                            <%--<td>${train.content}</td>--%>
                                        <td width="50px">
                                            <a href="editTrainJSP?train.id=${train.id}">编辑</a>
                                        </td>
                                        <td width="50px">
                                            <a href="deleteTrain?train.id=${train.id}" onclick="return isDelete()">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
                        </table>
                    </div>
                    <div class="pager-list">
                        <a href="train?year=${year}&startPage=${startPage-1}" class="page-item current">上一页</a>
                        <a href="train?year=${year}&startPage=${startPage}" class="page-item ">${startPage}</a>
                        <a href="train?year=${year}&startPage=${startPage+1}" class="page-item current">下一页</a>
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

