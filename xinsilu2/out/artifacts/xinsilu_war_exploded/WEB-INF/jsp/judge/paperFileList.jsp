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
            $(".li_nav span:contains('论文评审')").attr("style", "font-weight:bold");
        });
        $(function(){
            $("#activityData").attr('disabled','true');
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
                                </li>-->
                                <li>
                                    <a href="paperScoreJSP?id=1" id="column_2">
                                        <i class="icon_a icon_all"></i>论文评审
                                    </a>
                                </li>
                                <li>
                                    <a href="paperDownloadScoreJSP" id="column_4">
                                        <i class="icon_a icon_all"></i>成绩下载
                                    </a>
                                </li>
                                <li>
                                    <a href="paperDownloadFileJSP" id="column_5" class="selected">
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
                                        <fieldset>
                                            <div class="form-body">
                                                <div class="form-group">
                                                        <label>材料下载</label>
                                                        <table class="table" style="width:680px">
                                                            <tr>
                                                                <td class="col-md-2" >
                                                                    <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                                        <a href="${file1}" class="btn btn-default"
                                                                           style="margin-top: 10px; float: left" target="_blank">${year}论文资料.zip
                                                                        </a>
                                                                        <a href="${file3}" class="btn btn-default" disabled="true"
                                                                           style="margin-top: 10px; float: left;pointer-events: none" target="_blank">${year}论文分数表下载
                                                                        </a>
                                                                        <a href="${file2}" id="activityData" class="btn btn-default"
                                                                           style="margin-top: 10px; float: left; pointer-events: none" target="_blank">项目活动资料
                                                                        </a>
                                                                    </p>
                                                                </td >
                                                            </tr>
                                                        </table>
                                                </div>
                                            </div>
                                        </fieldset>
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
