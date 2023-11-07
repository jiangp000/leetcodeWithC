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

    <script>
        $(function(){
            $(".li_nav span:contains('中期报告')").attr("style", "font-weight:bold");
        });
    </script>

</head>
<body>
<div id="container">
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%@ include file="common/head.jsp" %>
    <div class="list_box" id="contain">

        <div class="list_bor">
            <div class="w_986">
                <%--            <%@ include file="sider.jsp" %>--%>
                <div class="w_986">
                    <div class="list_sider f_l">
                        <h2 class="h2_title list_plate_bg f_family f_normal">
                            <span class="more"></span>评委评审</h2>
                        <div class="content list_plate_bg">
                            <ul class="list_sid_nav">
                                <!--<li>
                                    <a href="scoreResearchJSP?id=1" id="column_1">
                                        <i class="icon_a icon_all"></i>大赛评审
                                    </a>
                                </li>
                                <li>
                                    <a href="paperScoreJSP?id=1" id="column_2">
                                        <i class="icon_a icon_all"></i>论文评审
                                    </a>
                                </li>-->
                                <li>
                                    <a href="middleScoreJSP?id=1" id="column_3">
                                        <i class="icon_a icon_all"></i>中期报告
                                    </a>
                                </li>
                                <li>
                                    <a href="middleDownloadScoreJSP" id="column_4">
                                        <i class="icon_a icon_all"></i>成绩下载
                                    </a>
                                </li>
                                <li>
                                    <a href="middleDownloadFileJSP" id="column_5" class="selected">
                                        <i class="icon_a icon_all"></i>资料下载
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

                                <div class="portlet-body form">
                                    <form action="middleDownloadFile" method="post">
                                        <fieldset>
                                            <div class="form-body">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <label style="margin-right: 10px;width: 100px;">请选择年份:</label>
                                                        <select name="year" value="year"
                                                                class="selectpicker form-control"
                                                                data-live-search="true"
                                                                data-hide-disabled="true">
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
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <label style="margin-right: 10px;width: 100px;">请选择阶段:</label>
                                                        <select name="researchType"
                                                                class="selectpicker form-control"
                                                                data-live-search="true"
                                                                data-hide-disabled="true">
                                                            <option value ="中期报告">中期报告</option>

                                                        </select>
                                                    </div>
                                                </div>
                                                <div style="margin-top: 60px; margin-bottom: 10px; text-align: center">
                                                    <button type="submit" class="btn blue-madison"><i
                                                            class="glyphicon glyphicon-ok"></i> &nbsp; 提 交
                                                    </button>
                                                    <button type="reset" class="btn blue-madison"><i
                                                            class="glyphicon glyphicon-remove"></i> &nbsp; 取 消
                                                    </button>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div>
                                <!-- form end-->

                            </div>
                            <div class="list_plate_bg main_bot"></div>
                        </div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@ include file="common/footer.jsp" %>
</div>



</body>
</html>
