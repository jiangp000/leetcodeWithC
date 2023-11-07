<%--
  Created by IntelliJ IDEA.
  User: HongweiHe
  Date: 21/6/14
  Time: 下午16:52
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
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
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
            $("#column_2").attr("class", "selected");
            $(".li_nav span:contains('创新大赛')").attr("style", "font-weight:800");
        });
    </script>
</head>
<body>

<%@ include file="../teacher/common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="innovationResearchFirstTrial.action">我的初审</a>
        </div>
        <div class="w_986">
            <%@ include file="siderInnovation.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form id="researchEnrollForm"
                                  enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>大赛初审</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2" style="width: 40px">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                初审结果
                                                            </span>
                                                                <s:textfield class="form-control" name="researchEnroll.isPass" disabled="true" id="isPass" required="true"/>

                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 40px;">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 大赛编号
                                                            </span>
                                                                <s:textfield class="form-control" disabled="true" name="researchEnroll.applyId" required="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" colspan="2">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                初审意见
                                                            </span>
                                                                <s:textfield class="form-control" name="researchEnroll.applyPreComment" disabled="true" required="true" style="width:563px"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
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

<%@ include file="../teacher/common/footer.jsp" %>

</body>
</html>