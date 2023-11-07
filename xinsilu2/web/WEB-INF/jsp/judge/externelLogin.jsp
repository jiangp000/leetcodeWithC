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

<div class="header">
    <div class="head_top line_bg" style="background: #700005">
        <div class="head_top_content">
            <div class="top_l f_l">
            <span class="txt f_l" style="color:#e5e3e3">
                您好，欢迎访问教学新思路！
            </span>
            </div>
            <div class="f_r top_r">
                <ul class="top_r_ul">

                    <li class="f_l">
                        <a class="txt f_l" href="http://www.pku.edu.cn/" target="_blank" title="北京大学"
                           style="color:#e5e3e3">
                            北京大学
                        </a>
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="head_logo_nav1">
        <div style="width:980px; margin:0 auto">
            <img src="../images/banner2.jpg" width="979px" height="100px">
        </div>
    </div>

</div>

<!--head end-->

<div class="list_box">
    <div class="list_bor" style="width: 500px; margin: 0 auto">
        <div class="list_main f_r">
            <div class="content list_content" style="height: 50%;">
                <div style="margin:40px 0 20px 0; text-align: center">
                    <h2>校外评审登录</h2>
                </div>
                <div class="portlet-body form">
                    <form role="form" action="externelLogin" method="post" enctype="multipart/form-data">
                        <div class="form-body" style="width: 50%;margin: 20px auto;">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                            <i class="glyphicon glyphicon-user"></i>
                                    </span>
                                    <input class="form-control" id="userId" name="username"
                                           placeholder="用户名" type="text" required/>
                                </div>
                            </div>

                            <div class="form-group" style="margin-top: 20px;">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                              <i class="glyphicon glyphicon-lock"></i>
                                     </span>
                                    <input class="form-control" id="pwd" name="pwd"
                                           placeholder="登录密码" type="password" required>
                                </div>
                            </div>
                            <div style="margin-top: 30px; margin-bottom: 10px; text-align: center">
                                <button type="submit" class="btn btn-primary">
                                    <i class="icon-login"></i>
                                    &nbsp; 登 录
                                </button>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="submit" class="btn btn-danger">
                                    <i class="icon-arrow-down"></i> &nbsp; 取 消
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="main_bot"></div>
    </div>
    <div class="clear"></div>
</div>

<%@ include file="common/footer.jsp" %>

</body>
</html>

