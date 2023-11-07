<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教学新思路</title>
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" type="text/css" href="../plugins/simple-line-icons/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="../css/front.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/pager.css">

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
                language: 'zh-CN',
                weekStart: 1,
                todayBtn: 1,
                autoclose: 1,
                todayHighlight: 1,
                startView: 2,
                minView: 2,
                forceParse: 0,
                format: 'yyyy-mm-dd'
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
            <a href="#">编辑新闻</a>
        </div>
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="editNews" method="post" enctype="multipart/form-data">
                                <fieldset>
                                    <div style="display:none">
                                        <s:textfield name="news.id"/>
                                    </div>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>新闻标题</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textfield class="form-control" name="news.title"
                                                             required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>发布时间</label>
                                            <div class="input-group">
                                                <s:textfield type="date" name="news.publishTime" id="datetimepicker">
                                                    <s:param name="value">
                                                        <s:date name="news.publishTime" format="yyyy-MM-dd"/>
                                                    </s:param>
                                                </s:textfield>
                                                <%--<s:textfield name="news.publishTime" type="date">--%>
                                                <%--<s:param name="value"><s:date name="meeting.meetingTime"--%>
                                                <%--format="yyyy-MM-dd"/></s:param>--%>
                                                <%--</s:textfield>--%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>新闻类型</label>
                                            <div class="input-group">
                                                <select name="news.type" value="news.type">
                                                    <c:choose>
                                                        <c:when test="${news.type==0}">
                                                            <option value="0" selected>培训讲座</option>
                                                            <option value="1">交流研讨</option>
                                                        </c:when>
                                                        <c:when test="${news.type==1}">
                                                            <option value="0">培训讲座</option>
                                                            <option value="1" selected>交流研讨</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="0">培训讲座</option>
                                                            <option value="1">交流研讨</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>新闻链接</label>
                                            <div class="input-group" style="width: 100%;">
                                                <s:textfield class="form-control" name="news.link" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>新闻内容</label>
                                            <div class="input-group" style="width: 100%;">
                                                <s:textarea class="form-control" name="news.content" required="true"
                                                            style="min-height:200px; border:2px solid; "/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>附件列表</label>
                                            <div class="input-group" style="width: 100%;">
                                                <c:if test="${empty fileList}">
                                                    <p>无</p>
                                                     </c:if>
                                                <c:if test="${!empty fileList}">
                                                    <table class="table table-striped table-bordered table-hover">
                                                        <c:forEach var="item" items="${fileList}" varStatus="s">
                                                            <tr>
                                                                <td>${s.count}</td>
                                                                <td>${item.fileName}</td>
                                                                <td width="50px"><a href="${item.filePath}">下载</a></td>
                                                                <td width="50px"><a href="deleteFile?file.id=${item.id}"
                                                                       onclick="return isDelete()">删除</a></td>
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
                            <s:fielderror/>
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

