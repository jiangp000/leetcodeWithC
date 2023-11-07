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
    <link rel="stylesheet" type="text/css" href="plugins/bootstrap-fileinput/fileinput.css">
    <link rel="stylesheet" type="text/css" href="css/front.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/pager.css">

    <script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="plugins/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript" src="plugins/bootstrap-fileinput/fileinput.js"></script>
    <script type="text/javascript" src="js/xinsilu.js"></script>

    <script type="application/javascript">
        $(function () {
            $("#column_1").attr("class", "selected");
            initFileInput("uploadFile");
        });
    </script>
</head>
<body>

<%@ include file="../common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="projectEnroll.action">项目申请</a>
        </div>
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitProjectEnroll" id="projectEnrollForm" enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>工号/学号</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="projectEnroll.userId"
                                                             required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>负责人姓名</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="projectEnroll.username"
                                                             required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>所属院系</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-flag"></i>
                                            </span>
                                                <s:textfield class="form-control" name="projectEnroll.department"
                                                             required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>课题题目</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-edit"></i>
                                            </span>
                                                <s:textfield class="form-control" name="projectEnroll.title"
                                                             required="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>项目类别</label>
                                            <%--<div class="input-group">--%>
                                            <%--<span class="input-group-addon">--%>
                                                <%--<i class="glyphicon glyphicon-book"></i>--%>
                                            <%--</span>--%>
                                            <div class="input-group">
                                                <select   style="width: 100px;" class="form-control" name="projectEnroll.projectType"
                                                        value="projectEnroll.projectType">
                                                    <option value="重点">重点</option>
                                                    <option value="优先">优先</option>
                                                    <option value="一般" selected="selected">一般</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>申请经费(单位:万元)</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-gbp"></i>
                                            </span>
                                                <s:textfield class="form-control" name="projectEnroll.found"
                                                             required="true" type="number"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>项目周期(单位:月)</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-list"></i>
                                            </span>
                                                <s:textfield class="form-control" name="projectEnroll.months"
                                                             required="true" type="number"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>上传项目申请书</label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;工号/学号_项目申请书_姓名_单位.doc（若提交的申请材料有附件，则后缀名为.zip）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile"
                                                        required="true" data-show-caption="true"/>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div>
                                        <p style="color: red;margin-bottom: 10px"><span id="error_msg"
                                                                                        style="display: none"></span>
                                        </p>
                                    </div>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison"
                                                onclick="return validate_enroll_exist()">
                                            <i class="glyphicon glyphicon-ok"></i> &nbsp; 提 交
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
