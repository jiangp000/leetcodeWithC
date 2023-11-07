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
    document.write("<input type=hidden name='redirectUrl' value='http://jpk.pku.edu.cn/xinsilu2/judge/index.action'>");
    document.write("</form>");
    setTimeout("document.formx1.submit();",1000);
</script>

<%--以下部分为了测试方便，将上文中的统一认证暂时注释--%>
<%--<div class="list_box">--%>
<%--    <div class="list_bor" style="width: 500px; margin: 0 auto">--%>
<%--        <div class="list_main f_r">--%>
<%--            <div class="content list_content" style="height: 50%;">--%>
<%--                <div style="margin:40px 0 20px 0; text-align: center">--%>
<%--                    <h2>用户登录</h2>--%>
<%--                </div>--%>
<%--                <div class="portlet-body form">--%>
<%--                    <form role="form" action="login" method="post" enctype="multipart/form-data">--%>
<%--                        <div class="form-body" style="width: 50%;margin: 20px auto;">--%>
<%--                            <div class="form-group">--%>
<%--                                <div class="input-group">--%>
<%--                                    <span class="input-group-addon">--%>
<%--                                            <i class="glyphicon glyphicon-user"></i>--%>
<%--                                    </span>--%>
<%--                                    <input class="form-control" id="username" name="username"--%>
<%--                                           placeholder="用户名" type="text" required/>--%>
<%--                                </div>--%>
<%--                            </div>--%>

<%--                            <div class="form-group" style="margin-top: 20px;">--%>
<%--                                <div class="input-group">--%>
<%--                                    <span class="input-group-addon">--%>
<%--                                              <i class="glyphicon glyphicon-lock"></i>--%>
<%--                                     </span>--%>
<%--                                    <input class="form-control" id="id_password" name="pwd"--%>
<%--                                           placeholder="登录密码" type="password" required>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div style="margin-top: 30px; margin-bottom: 10px; text-align: center">--%>
<%--                                <button type="submit" class="btn btn-primary">--%>
<%--                                    <i class="icon-login"></i>--%>
<%--                                    &nbsp; 登 录--%>
<%--                                </button>--%>
<%--                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                                <button type="submit" class="btn btn-danger">--%>
<%--                                    <i class="icon-arrow-down"></i> &nbsp; 取 消--%>
<%--                                </button>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="clear"></div>--%>
<%--        </div>--%>
<%--        <div class="main_bot"></div>--%>
<%--    </div>--%>
<%--    <div class="clear"></div>--%>
<%--</div>--%>

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
