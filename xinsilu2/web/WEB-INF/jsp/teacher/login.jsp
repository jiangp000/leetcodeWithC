<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教学新思路</title>
    <link rel="stylesheet" type="text/css" href="../css/front2.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../plugins/simple-line-icons/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap.css">

    <script type="text/javascript" src="../plugins/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
</head>
<body>
<!--head start-->
<div align="center" style="color:#BB0000;font-family:Microsoft Yahei;font-size: 24px; font-weight: bold; margin: 20px 20px 20px 20px;">
    <p>正在转向北京大学身份认证系统......</p>
</div>
<script language=javascript>
    document.write("<form action='https://iaaa.pku.edu.cn/iaaa/oauth.jsp' method=post name='formx1' style='display:none'>");
    document.write("<input type=hidden name='appID' value='PKU-xinsilu2'>");
    document.write("<input type=hidden name='redirectUrl' value='http://jpk.pku.edu.cn/xinsilu2/teacher/index.action'>");
    document.write("</form>");
    setTimeout("document.formx1.submit();",1000);
</script>

</body>
</html>


<%--<h3>用户登录</h3>--%>
<%--<form action="login" method="post">--%>
<%--<fieldset>--%>
<%--<s:textfield name="username"/>--%>
<%--<s:textfield name="pwd" type="password"/>--%>
<%--<s:submit key="登录"></s:submit>--%>
<%--</fieldset>--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
