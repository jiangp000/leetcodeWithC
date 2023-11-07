<%--
  Created by IntelliJ IDEA.
  User: HongweiHe
  Date: 21/6/14
  Time: 下午16:52
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
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">
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
            $("#column_3").attr("class", "selected");
            initFileInput("uploadFile0");
            initFileInput("uploadFile1");
            initFileInput("uploadFile2");
            $(".li_nav span:contains('创新大赛')").attr("style", "font-weight:800");
        });

        $(document).ready(function () {

            $("#uploadFile0").on("filebatchselected", function () {
                var id = "#uploadFile0";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if((ext != "ppt" && ext != "pptx") ||
                    (filePath.search("现场汇报.ppt") == -1 && filePath.search("现场汇报.pptx") == -1)){
                    $(".fileinput-remove-button:eq(0)").click();
                    alert("文件命名格式为 教师号_教师名_现场汇报.ppt/pptx,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile1").on("filebatchselected", function () {
                var id = "#uploadFile1";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" || filePath.search("现场汇报.pdf") == -1){
                    $(".fileinput-remove-button:eq(1)").click();
                    alert("文件命名格式为 教师号_教师名_现场汇报.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
        });
        $(function(){
            if($("#submitSave").val() == 1) {
                $("#submitId").attr('disabled','true');
                $("#saveId").attr('disabled','true');
            }
        })
        /*$(function(){
            $("#saveId").attr('disabled','true');
        })*/
        $(document).ready(function () {
            $("#submitId").click(function (){
                $("#submitSave").val("1");
            })
        })
    </script>
</head>
<body>

<%@ include file="../teacher/common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="innovationResearchPPT.action">创新大赛现场汇报提交</a>
        </div>
        <div class="w_986">
            <%@ include file="siderInnovation.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitInnoPPTResearchEnroll" id="researchEnrollForm"
                                  enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>校园卡号 <span class="sign_must">*</span></label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="researchEnroll.userId" readonly="true"
                                                             value="%{#session.teacher}" required="true"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>负责人姓名 <span class="sign_must">*</span></label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="researchEnroll.username" readonly="true"
                                                             value="%{#session.name}" required="true"/>
                                            </div>
                                        </div>

                                    <div class="form-group">
                                        <label>上传大赛现场汇报材料 </label>
                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;创新大赛现场汇报PPT：教师号_教师名_现场汇报.pptx</p>
                                        <div class="input-group">
                                            <s:file name="uploadFile" id="uploadFile0" required="true"
                                                    data-show-caption="true"/>
                                        </div>
                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;创新大赛现场汇报PDF：教师号_教师名_现场汇报.pdf</p>
                                        <div class="input-group">
                                            <s:file name="uploadFile" id="uploadFile1" required="true"
                                                    data-show-caption="true"/>
                                        </div>
                                    </div>

                                        <s:textfield id="submitSave" style="height:2px; width:70px;
                                                                        float:right;" class="form-control" name="submitSave"
                                                     readonly = "true"
                                                     required="true" type="hidden"/>

                                    <br>
                                    <div>
                                        <p style="color: red;margin-bottom: 10px"><span id="error_msg"
                                                                                        style="display: none"></span>
                                        </p>
                                    </div>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison" id="saveId"
                                                onclick="return validate_enroll_exist()" >
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 保 存
                                        </button>

                                        <%--<button type="submit" class="btn blue-madison" id="submitId"
                                                onclick="return validate_enroll_exist()" >
                                            <i class="glyphicon glyphicon-saved"></i>
                                            &nbsp; 提 交
                                        </button>--%>
                                        <%--<button type="submit"
                                                class="btn blue-madison"
                                                onclick="return validate_enroll_exist()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 提 交
                                        </button>
                                        &lt;%&ndash;                                        <button type="submit" class="btn blue-madison"&ndash;%&gt;
                                        &lt;%&ndash;                                                onclick="window.location.href='http://jpk.pku.edu.cn/xinsilu2/index.action'">&ndash;%&gt;
                                        &lt;%&ndash;                                            <i class="glyphicon glyphicon-ok"></i>&ndash;%&gt;
                                        &lt;%&ndash;                                            &nbsp; 提 交&ndash;%&gt;
                                        &lt;%&ndash;                                        </button>&ndash;%&gt;
                                        <button type="reset" class="btn blue-madison">
                                            <i class="glyphicon glyphicon-remove"></i>
                                            &nbsp; 取 消
                                        </button>--%>
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

<%@ include file="../teacher/common/footer.jsp" %>

</body>
</html>