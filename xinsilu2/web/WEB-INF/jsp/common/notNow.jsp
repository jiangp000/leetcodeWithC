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
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/front.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <script type="text/javascript" src="../js/jquery.pack.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
</head>
<body>

<%@ include file="../teacher/common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a>
        </div>
        <div class="w_986">
            <div class="list_main f_r" style="width: 100%;">
                <div class="content">
                    <div class="list_content">
                        <div class="detail_title_info"></div>
                        <div class="detail_text_content">
                            <p><span style="color: #8C0000">活动暂未开启, 敬请期待!</span></p>
                            <p><a href="index.action">返回首页</a></p>
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
