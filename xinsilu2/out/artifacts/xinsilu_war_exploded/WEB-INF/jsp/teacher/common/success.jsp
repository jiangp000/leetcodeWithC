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
    <link rel="stylesheet" type="text/css" href="../plugins/simple-line-icons/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="../css/front.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <script type="text/javascript" src="../js/jquery.pack.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
</head>
<body>

<%@ include file="../common/head.jsp" %>

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
                            <p><span id="success_msg">提交成功!</span></p>
                            <svg t="1680526463016" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2758" width="40" height="40"><path d="M512 1024A512 512 0 1 1 512 0a512 512 0 0 1 0 1024z m-49.590857-377.197714L315.977143 498.614857 219.428571 590.848c70.217143 37.814857 168.594286 106.788571 252.854858 213.723429C531.821714 692.662857 715.337143 463.725714 804.571429 443.245714c-14.409143-57.709714-22.528-166.034286 0-223.817143-183.003429 120.685714-342.162286 427.373714-342.162286 427.373715z" fill="#029B00" p-id="2759"></path></svg>
                            <p><a href="index.action" id="return_button">返回首页</a></p>
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
<style type="text/css">
    #success_msg{
        font-size: 25px;
    }
    #return_button{
        margin-top: 20px;
        font-size: 25px;
    }
    svg{
        margin-left: 25px;
        margin-bottom: 10px;
    }
</style>

<%@ include file="../common/footer.jsp" %>
</body>
</html>
