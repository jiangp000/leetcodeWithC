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
    <link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="css/front.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/pager.css">


    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="plugins/bootstrap/js/bootstrap.js"></script>
</head>
<body>

<%@ include file="../common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="#">教师介绍</a>
        </div>
        <div class="w_986">
            <div class="list_sider f_l">
                <h2 class="h2_title list_plate_bg f_family f_normal">
                    <span class="more"></span>培训讲座</h2>
                <div class="content list_plate_bg">
                    <ul class="list_sid_nav">
                        <li>
                            <a href="#" class="selected">
                                <i class="icon_a icon_all"></i>优秀教师介绍
                            </a>
                        </li>
                    </ul>

                    <div class="clear"></div>
                </div>
                <div class="list_sider_bot list_plate_bg"></div>
            </div>
            <div class="list_main f_r">
                <div class="content">
                    <div class="list_content">
                        <h1 class="h1_detail_title f_family f_normal">优秀教师介绍</h1>
                        <div class="detail_title_info"></div>
                        <div class="portlet-body form">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">教师姓名:</label>
                                    <s:textfield class="form-control" name="teacher.username"
                                                 disabled="true"></s:textfield>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">所属院系:</label>
                                    <s:textfield class="form-control" name="teacher.department"
                                                 disabled="true"></s:textfield>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">电子邮箱:</label>
                                    <s:textfield class="form-control" name="teacher.email"
                                                 disabled="true"></s:textfield>
                                </div>
                            </form>
                        </div>

                        <hr class="simple" color="#6f5499" />

                        <h3 class="h3_detail_title">成果展示</h3>
                        <div class="detail_text_content">
                            <p><a href="${teacher.filePath}">${teacher.title}</a></p>
                        </div>
                    </div>


                    <div class="clear"></div>
                </div>
                <div class="main_bot"></div>
            </div>
            <div class="clear"></div>
        </div>
    </div>

</div>


<%@ include file="../common/footer.jsp" %>
</body>
</html>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="s" uri="/struts-tags" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--<title>项目展示</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<a href="index">首页</a> <br/>--%>
<%--教师介绍--%>
<%--${teacher.userId}<br/>--%>
<%--${teacher.username}<br/>--%>
<%--${teacher.email}<br/>--%>
<%--${teacher.department}<br/>--%>
<%--<a href="${teacher.filePath}">${teacher.title}</a><br/>--%>
<%--</body>--%>
<%--</html>--%>
