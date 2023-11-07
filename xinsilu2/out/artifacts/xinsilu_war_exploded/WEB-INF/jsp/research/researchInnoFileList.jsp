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
            $("#column_5").attr("class", "selected");
            $(".li_nav span:contains('创新大赛')").attr("style", "font-weight:800");
        });
        $(function(){
            $("#preData").attr('disabled','true');
        });
    </script>
</head>
<body>

<%@ include file="../teacher/common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="researchInnoFileJSP.action">我的下载</a>
        </div>
        <div class="w_986">
            <%@ include file="siderInnovation.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="list_main f_r">
                    <div class="content list_plate_bg">
                        <div class="list_content">
                            <p>个人材料下载</p>
                            <table class="table" style="width:680px">
                                <tr>
                                    <td class="col-md-2" >
                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                            <a href="${file2}" id="preData" class="btn btn-default"
                                               style="margin-top: 10px; float: left; pointer-events: none"  target="_blank">现场汇报视频下载
                                            </a>
                                            <a href="${file1}" class="btn btn-default"
                                               style="margin-top: 10px; float: left" target="_blank">${year}创新大赛资料.zip
                                            </a>
                                        </p>
                                    </td >
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="list_plate_bg main_bot"></div>
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
