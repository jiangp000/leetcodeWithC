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
</head>
<body>

<%@ include file="common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="">评委管理</a>
        </div>
        <div class="w_986">
            <div class="w_986">
                <div class="list_sider f_l">
                    <h2 class="h2_title list_plate_bg f_family f_normal">
                        <span class="more"></span>评委管理</h2>
                    <div class="content list_plate_bg">
                        <ul class="list_sid_nav">
                            <li>
                                <a href="addJudgerJSP" class="selected">
                                    <i class="icon_a icon_all"></i>新增校内评委
                                </a>
                            </li>
                            <li>
                                <a href="addExternalJudgerJSP">
                                    <i class="icon_a icon_all"></i>新增校外评委
                                </a>
                            </li>
                            <li>
                                <a href="judgeManagerJSP">
                                    <i class="icon_a icon_all"></i>管理评委
                                </a>
                            </li>
                            <li>
                                <a href="removeJudgerJSP">
                                    <i class="icon_a icon_all"></i>移除评委
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
                                <form action="addJudge" method="post">
                                    <fieldset>
                                        <div class="form-body">

                                            <div class="form-group">
                                                <label>工号</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span>
                                                    <s:textfield class="form-control" id="judgerId" name="judgerId" required="true"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label>姓名</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span>
                                                    <s:textfield class="form-control" id="judgerName" name="judgerName" required="true"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label>部门</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span>
                                                    <s:textfield class="form-control" id="judgerDepartment" name="judgerDepartment" required="true"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label>手机号</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span>
                                                    <s:textfield class="form-control" id="judgerPhone" name="judgerPhone" required="true"/>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label>邮箱</label>
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="glyphicon glyphicon-user"></i>
                                                    </span>
                                                    <s:textfield class="form-control" id="judgerMail" name="judgerMail" required="true"/>
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
</body>
</html>
