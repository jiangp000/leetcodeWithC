<%@ page import="util.UploadUtil" %>
<%@ page import="action.research.ResearchAction" %><%--
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
            $("#column_1").attr("class", "selected");
            initFileInput("uploadFile0");
            initFileInput("uploadFile1");
            initFileInput("uploadFile2");
            initFileInput("uploadFile3");
            initFileInput("uploadFile4");
            initFileInput("uploadFile5");
            initFileInput("uploadFile6");
            initFileInput("uploadFile7");
            initFileInput("uploadFile8");
            initFileInput("uploadFile9");

            $(".li_nav span:contains('项目申报')").attr("style", "font-weight:800");
        });
        $(document).ready(function () {
            $('input[name="projectSort"]').change(function () {
                var caseSort="";
                for(i = 0; i < 12; i++){
                    var id1 = "#projectSortId" + i;
                    var caseSortVal = $(id1).val();
                    if ($(id1).is(':checked') && isNaN(caseSortVal) && caseSortVal != null){
                        caseSort = caseSort + caseSortVal + ",";
                        $("#caseSortId").val(caseSort);
                    }
                }
            });
        });


        $(document).ready(function () {
            $("#userMailId").change(function () {
                var id = "#deptId";
                var deptVal = $(id).val();
                if(deptVal == "院系"){
                    $(id).val("");
                    alert("请选择院系");
                    return;
                }
            })
        });
        $(document).ready(function () {
            $("#projectNameId").change(function () {
                var id = "#projectNameId";
                var titleVal = $(id).val();
                if(titleVal.includes("/")){
                    $(id).val("");
                    alert("标题中不能包含 / 特殊字符");
                    return;
                }
            })
        });
        $(document).ready(function () {

            $("#uploadFile0").on("filebatchselected", function () {
                var id = "#uploadFile0";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "docx" ){
                    $("#uploadFile0").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(0)").click();
                    alert("项目申报命名格式: 项目申报书_负责人姓名_单位名称.docx,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile1").on("filebatchselected", function () {
                var id = "#uploadFile1";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" ){
                    $("#uploadFile1").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(1)").click();
                    alert("项目申报命名格式： 项目申报书_负责人姓名_单位名称.pdf（单位签名盖章）,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile2").on("filebatchselected", function () {
                var id = "#uploadFile2";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" && ext != "mp4"){
                    $("#uploadFile2").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(2)").click();
                    alert("文件命名格式为 申报支撑1_负责人姓名_单位名称.pdf/mp4,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile3").on("filebatchselected", function () {
                var id = "#uploadFile3";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" && ext != "mp4"){
                    $("#uploadFile3").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(3)").click();
                    alert("文件命名格式为 申报支撑2_负责人姓名_单位名称.pdf/mp4,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile4").on("filebatchselected", function () {
                var id = "#uploadFile4";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" && ext != "mp4"){
                    $("#uploadFile4").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(4)").click();
                    alert("文件命名格式为 申报支撑3_负责人姓名_单位名称.pdf/mp4,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile5").on("filebatchselected", function () {
                var id = "#uploadFile5";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" && ext != "mp4" && ext != "zip"){
                    $("#uploadFile5").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(5)").click();
                    alert("文件命名格式为 申报支撑4_负责人姓名_单位名称.pdf/mp4/zip,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile6").on("filebatchselected", function () {
                var id = "#uploadFile6";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pptx" ){
                    $("#uploadFile6").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(6)").click();
                    alert("文件命名格式为 申报现场汇报_负责人姓名_单位名称.pptx,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile7").on("filebatchselected", function () {
                var id = "#uploadFile7";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" ){
                    $("#uploadFile7").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(7)").click();
                    alert("文件命名格式为 申报现场汇报_负责人姓名_单位名称.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile8").on("filebatchselected", function () {
                var id = "#uploadFile8";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if( ext != "pdf" && ext != "jpg"){
                    $("#uploadFile8").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(8)").click();
                    alert("文件命名格式为 科研伦理与科研诚信培训证书_姓名_单位名称.jpg/pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile9").on("filebatchselected", function () {
                var id = "#uploadFile9";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" && ext != "jpg"){
                    $("#uploadFile9").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(9)").click();
                    alert("文件命名格式为 项目伦理审核证明_姓名_单位名称.jpg/pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
        });
        $(function(){
            if($("#submitSave").val() == 1&&$("#submitStageControl").val()==1) {
                $("#submitId").attr('disabled','true');
                $("#saveId").attr('disabled','true');
            }else if($("#submitSave").val() == 2&&$("#submitStageControl").val()==2){
                $("#submitId").attr('disabled','true');
                $("#saveId").attr('disabled','true');
            }
        })
        $(document).ready(function () {
            $("#submitId").click(function (){
                $("#submitSave").val($("#submitStageControl").val());
            })
        });
        $(function(){
            if($("#submitStageControl").val() == 0){
                //关闭状态
                document.getElementById("saveId").style.display="none";
                document.getElementById("submitId").style.display="none";
                //document.getElementById("uploadPptId").style.display="none";
            }else if($("#submitStageControl").val()==1) {
                //提交材料
                document.getElementById("uploadPptId").style.display="none";
                $("#uploadFile0").attr('required','true');
                $("#uploadFile1").attr('required','true');
            }else if($("#submitStageControl").val()==2){
                //提交现场汇报PPT
                $("#deptId").attr('disabled','true');
                $("#projectNameId").attr('readonly','true');
                $("#projectApplySortId").attr('disabled','true');
                for(i = 0; i < 12; i++){
                    var id1 = "#projectSortId" + i;
                    $(id1).attr('disabled','true');
                }
                $("#projectEthicJudge").attr('disabled','true');
                $("#userTitleId").attr('readonly','true');
                $("#userPhoneId").attr('readonly','true');
                $("#userMailId").attr('readonly','true');
                $("#userCvId").attr('readonly','true');
                $("#u_id1Id").attr('readonly','true');
                $("#u_name1Id").attr('readonly','true');
                $("#u_dept1Id").attr('disabled','true');
                $("#u_id2Id").attr('readonly','true');
                $("#u_name2Id").attr('readonly','true');
                $("#u_dept2Id").attr('disabled','true');
                $("#u_id3Id").attr('readonly','true');
                $("#u_name3Id").attr('readonly','true');
                $("#u_dept3Id").attr('disabled','true');
                $("#u_id4Id").attr('readonly','true');
                $("#u_name4Id").attr('readonly','true');
                $("#u_dept4Id").attr('disabled','true');
                $("#uploadFile0").attr('disabled','true');
                //document.getElementById("uploadFile1")
                //document.getElementById("uploadFile1").disabled=true;
                $("#uploadFile1").attr('disabled','true');
                $("#uploadFile2").attr('disabled','true');
                $("#uploadFile3").attr('disabled','true');
                $("#uploadFile4").attr('disabled','true');
                $("#uploadFile5").attr('disabled','true');

                $("#uploadFile6").attr('required','true');
                $("#uploadFile7").attr('required','true');

                $("#uploadFile8").attr('disabled','true');
                $("#uploadFile9").attr('disabled','true');
            }
        });
        $(document).ready(function () {
            if ($("#projectEthicJudge").val() != "否") {
                // 显示
                $(".basic-info").attr("style", "display: ;");
                $("#uploadFile8").attr('required','true');
                $("#uploadFile9").attr('required','true');
            }else{
                $(".basic-info").attr("style", "display:none ;");
                $("#uploadFile8").attr('required', false);
                $("#uploadFile9").attr('required', false);
            }
        });
        $(document).ready(function () {
            var caseSortData = $("#caseSortId").val();
            var datas = new Array("教育信息化创新发展研究","成果导向网课建设与应用","信息技术与智慧教学案例","在线混合课程的创新实践","学习科学与教学反思改进",
                "融合教学系统改进与创新","线上线下实质等效的研究","平台数据分析与教学指导","教师数字素养与能力评价","创新实践基地建设与优化","智能数字化教材建设应用",
                "其他创新教育与教学实践");

            if (caseSortData != "" && isNaN(caseSortData) && caseSortData != null) {
                for(i = 0; i < 12; i++){
                    var id1 = "#projectSortId" + i;
                    if(caseSortData.includes(datas[i])){
                        $(id1).prop('checked',true);
                    }
                }
            }
        });
        /*$(function(){
            if($("#sessionScope.judge2").val() != '0006174152' || sessionScope.judge2 != '1106579109' ||
                sessionScope.judge2 != '1264012194' || sessionScope.judge2 != '0006180100' ||
                sessionScope.judge2 != '0016170098' || sessionScope.judge2 != '0016179039' ||
                sessionScope.judge2 != '0006172212' || sessionScope.judge2 != '0016182021' ||
                sessionScope.judge2 != '0006176073' || sessionScope.judge2 != '0163175485' ||
                sessionScope.judge2 != '0061004340' || sessionScope.judge2 != '0016173048' ||
                sessionScope.judge2 != '0006171199' || sessionScope.judge2 != '0006177174' ||
                sessionScope.judge2 != '0016175042') {
                alert($("#sessionScope.judge2").val());

                $("#uploadFile6").attr('disabled','true');
                $("#uploadFile7").attr('disabled','true');
                $("#submitId").attr('disabled','true');
            }
        })*/



    </script>

</head>
<body>

<%--<%@ include file="../teacher/common/head.jsp" %>--%>
<%@ include file="../teacher/common/head.jsp" %>


<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="applyResearchEnroll.action">项目申报提交</a>
        </div>
        <div class="w_986">
            <%@ include file="siderApply.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitApplyResearchEnroll" id="researchEnrollForm"
                                  enctype="multipart/form-data"
                                  method="post"
                                  onsubmit="submitForm()">
                                <fieldset>
                                    <div class="form-body">
                                            <div class="form-group">
                                                <table class="table" style="width:680px">
                                                    <s:textfield type="text" id="filesNewName" name="filesNewName" style="display:none"/>
                                                    <label>项目信息 <span class="sign_must">*</span></label>
                                                    <tr>
                                                        <td class="col-md-2" colspan="3">
                                                            <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目名称
                                                            </span>
                                                                <s:textfield class="form-control" id="projectNameId" name="researchEnroll.title"
                                                                             required="true"/>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2" colspan="3">
                                                            <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目分类
                                                            </span>
                                                                <select class="selectpicker form-control" required="true" id="projectApplySortId" name="projectApplySort">
                                                                    <c:forEach var="item" items="${projectApplySorts}">
                                                                        <c:choose>
                                                                            <c:when test="${projectApplySort==item}">
                                                                                <option value="${item}"
                                                                                        selected="selected">${item}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${item}">${item}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>

                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2" style="width: 32%;">
                                                            <p style="size: A5;color: #252525; font-weight: bolder">教育教学课题</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2" style="width: 32%;">
                                                            <input type="checkbox" name="projectSort" id="projectSortId0" value="教育信息化创新发展研究">教育信息化创新发展研究
                                                        </td>
                                                        <td class="col-md-2" style="width: 34%;">
                                                            <input type="checkbox" name="projectSort" id="projectSortId1" value="成果导向网课建设与应用">成果导向网课建设与应用
                                                        </td>
                                                        <td class="col-md-2" style="width: 34%">
                                                            <input type="checkbox" name="projectSort" id="projectSortId2" value="信息技术与智慧教学案例">信息技术与智慧教学案例
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2" style="width: 32%">
                                                            <input type="checkbox" name="projectSort" id="projectSortId3" value="在线混合课程的创新实践">在线混合课程的创新实践
                                                        </td>
                                                        <td class="col-md-2" style="width: 34%">
                                                            <input type="checkbox" name="projectSort" id="projectSortId4" value="学习科学与教学反思改进">学习科学与教学反思改进
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td class="col-md-2" style="width: 32%;">
                                                            <p style="size: A5;color: #252525; font-weight: bolder">专项综合课题</p>
                                                        </td>
                                                    </tr>
                                                   <tr>
                                                        <td class="col-md-2" style="width: 32%;">
                                                            <input type="checkbox" name="projectSort" id="projectSortId5" value="融合教学系统改进与创新">融合教学系统改进与创新
                                                        </td>
                                                        <td class="col-md-2" style="width: 34%;">
                                                            <input type="checkbox" name="projectSort" id="projectSortId6" value="线上线下实质等效的研究">线上线下实质等效的研究
                                                        </td>
                                                       <td class="col-md-2" style="width: 34%;">
                                                           <input type="checkbox" name="projectSort" id="projectSortId7" value="平台数据分析与教学指导">平台数据分析与教学指导
                                                       </td>
                                                    </tr>
                                                    <tr>

                                                        <td class="col-md-2" style="width: 32%;">
                                                            <input type="checkbox" name="projectSort" id="projectSortId8" value="教师数字素养与能力评价">教师数字素养与能力评价
                                                        </td>
                                                        <td class="col-md-2" style="width: 34%;">
                                                            <input type="checkbox" name="projectSort" id="projectSortId9" value="创新实践基地建设与优化">创新实践基地建设与优化
                                                        </td>
                                                        <td class="col-md-2" style="width: 34%;">
                                                            <input type="checkbox" name="projectSort" id="projectSortId10" value="智能数字化教材建设应用">智能数字化教材建设应用
                                                        </td>
                                                    </tr>

                                                    <tr>
                                                        <td class="col-md-2" style="width: 32%;">
                                                            <p style="size: A5;color: #252525; font-weight: bolder">自拟研究课题</p>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2" style="width: 32%;">
                                                            <input type="checkbox" name="projectSort" id="projectSortId11" value="其他创新教育与教学实践">其他创新教育与教学实践
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <s:textfield class="form-control" id="caseSortId" name="caseSort"
                                                                     type="hidden"/>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2" colspan="3">
                                                            <div class="input-group">
                                                                <span class="input-group-addon">
                                                                    申报项目的研究对象是否为"人、动物、人体"
                                                                </span>
                                                                <select class="selectpicker form-control" name="projectEthicSort" id="projectEthicJudge" value="projectEthicSort">
                                                                    <c:forEach var="item" items="${projectEthicSorts}">
                                                                        <c:choose>
                                                                            <c:when test="${projectEthicSort==item}">
                                                                                <option value="${item}"
                                                                                        selected="selected">${item}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${item}">${item}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                    <%--<option value="是">是</option>
                                                                    <option value="否" selected="selected">否</option>--%>
                                                                </select>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>

                                        <div class="form-group basic-info" style="display: none;">
                                            <label>上传项目伦理材料(jpg或pdf文件) <span class="sign_must">*</span></label>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;&nbsp;&nbsp<a
                                                    href="http://www.research.pku.edu.cn/tzgg/1363651.htm" target="_blank">北京大学科研伦理与科研诚信培训证书</a></p>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件命名格式：伦理与科研诚信证书_姓名_单位名称.jpg/pdf</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;伦理与科研诚信证书：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted8">
                                                <s:file name="uploadFile" id="uploadFile8"
                                                        data-show-caption="true" accept=".jpg,.pdf"/>
                                            </div>

                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;&nbsp;&nbsp<a
                                                    href="https://research.bjmu.edu.cn/llwyh/llwyh_tzgg/064e490351134a7b9a11817520e803db.htm" target="_blank">项目伦理审核证明</a></p>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件命名格式：伦理审核证明_姓名_单位名称.jpg/pdf</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;伦理审核证明：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted9">
                                                <s:file name="uploadFile" id="uploadFile9"
                                                        data-show-caption="true" accept=".jpg,.pdf"/>
                                            </div>
                                        </div>




                                            <div class="form-group">
                                                <table class="table" style="width:680px">
                                                    <label>基本信息 <span class="sign_must">*</span></label>
                                                    <tr>
                                                        <td class="col-md-2" style="width: 29%">
                                                            <div class="input-group">
                                                        <span class="input-group-addon">
                                                            校园卡号
                                                        </span>
                                                                <s:textfield class="form-control" name="researchEnroll.userId" readonly="true"
                                                                             value="%{#session.teacher}" required="true"/>
                                                            </div>
                                                        </td>
                                                        <td class="col-md-2" style="width: 27%">
                                                            <div class="input-group">
                                                        <span class="input-group-addon">
                                                            负责人
                                                        </span>
                                                                <s:textfield class="form-control" name="researchEnroll.username" readonly="true"
                                                                             value="%{#session.name}" required="true"/>
                                                            </div>
                                                        </td>
                                                        <td class="col-md-2" style="width: 34%">
                                                            <div class="input-group">
                                                        <span class="input-group-addon">
                                                            院系
                                                        </span>
                                                                <select class="selectpicker form-control" id="deptId" required="true"
                                                                        name="deptName">
                                                                    <c:forEach var="item" items="${deptNames}">
                                                                        <c:choose>
                                                                            <c:when test="${deptName==item}">
                                                                                <option value="${item}"
                                                                                        selected="selected">${item}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${item}">${item}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td class="col-md-2" style="width: 29%">
                                                            <div class="input-group">
                                                        <span class="input-group-addon">
                                                            手机号
                                                        </span>
                                                                <s:textfield class="form-control" id="userPhoneId" name="researchEnroll.userPhone"
                                                                             required="true"/>
                                                            </div>
                                                        </td>
                                                        <td class="col-md-2" style="width: 27%">
                                                            <div class="input-group">
                                                        <span class="input-group-addon">
                                                            职称
                                                        </span>
                                                                <s:textfield class="form-control" id="userTitleId" name="researchEnroll.userTitle"
                                                                             required="true"/>
                                                            </div>
                                                        </td>
                                                        <td class="col-md-2" style="width: 34%">
                                                            <div class="input-group">
                                                        <span class="input-group-addon">
                                                            邮箱
                                                        </span>
                                                                <s:textfield class="form-control" id="userMailId" name="researchEnroll.userMail"
                                                                             required="true"/>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>

                                        <!--团队成员开始-->
                                        <div class="form-group" >
                                            <label>团队成员 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">&nbsp;&nbsp;&nbsp;除了负责人外可添加0-4个成员</span></label>
                                            <div class="form-group" id = "group_1" >
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>成员一</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id1" placeholder="校园卡号" id="u_id1Id"
                                                    />
                                                    <s:textfield class="form-control u_name" name="u_name1" placeholder="姓名" id="u_name1Id"
                                                    />
                                                    <select class="selectpicker form-control u_dept"  name="u_dept1" id="u_dept1Id">
                                                        <c:forEach var="item" items="${stuDeptNames1}">
                                                            <c:choose>
                                                                <c:when test="${u_dept1==item}">
                                                                    <option value="${item}"
                                                                            selected="selected">${item}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${item}">${item}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>
                                            <div class="form-group" id = "group_2">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>成员二</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id2" placeholder="校园卡号" id="u_id2Id"
                                                    />
                                                    <s:textfield class="form-control u_name" name="u_name2" placeholder="姓名" id="u_name2Id"
                                                    />
                                                    <select class="selectpicker form-control u_dept" name="u_dept2" id="u_dept2Id">
                                                        <c:forEach var="item" items="${stuDeptNames2}">
                                                            <c:choose>
                                                                <c:when test="${u_dept2==item}">
                                                                    <option value="${item}"
                                                                            selected="selected">${item}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${item}">${item}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>
                                            <div class="form-group" id = "group_3">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>成员三</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id3" placeholder="校园卡号" id="u_id3Id"
                                                    />
                                                    <s:textfield class="form-control u_name" name="u_name3" placeholder="姓名" id="u_name3Id"
                                                    />
                                                    <select class="selectpicker form-control u_dept" name="u_dept3" id="u_dept3Id">
                                                        <c:forEach var="item" items="${stuDeptNames3}">
                                                            <c:choose>
                                                                <c:when test="${u_dept3==item}">
                                                                    <option value="${item}"
                                                                            selected="selected">${item}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${item}">${item}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>
                                            <div class="form-group" id = "group_4">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>成员四</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id4" placeholder="校园卡号" id="u_id4Id"
                                                    />
                                                    <s:textfield class="form-control u_name"  name="u_name4" placeholder="姓名" id="u_name4Id"
                                                    />
                                                    <select class="selectpicker form-control u_dept" name="u_dept4" id="u_dept4Id">
                                                        <c:forEach var="item" items="${stuDeptNames4}">
                                                            <c:choose>
                                                                <c:when test="${u_dept4==item}">
                                                                    <option value="${item}"
                                                                            selected="selected">${item}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${item}">${item}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                            </div>
                                            <div class="input-group" >
                                                <s:textfield name="researchEnroll.teamMember"
                                                             required="true" class = "form-control u_teamMember" style="display:none;"></s:textfield>
                                            </div>

                                        </div>
                                        <!--团队成员结束-->


<%--                                        <div class="form-group">--%>
<%--                                            <label>测试下载材料--%>
<%--                                                <p>材料：<a href="testDownload.action"> 下载</a></p>--%>
<%--                                            </label>--%>
<%--                                        </div>--%>
<%--                                        <div class="form-group">--%>
<%--                                            <label>测试下载材料2--%>
<%--                                                <p>材料：<a href="testDownload2.action"> 下载</a></p>--%>
<%--                                            </label>1234--%>
<%--                                        </div>--%>
<%--                                        <div class="form-group">--%>
<%--                                            <label>测试找到上传的材料--%>
<%--                                                <p>材料<a id="asdfghjk">这是一个超链接</a>--%>
<%--                                                </p>--%>
<%--                                            </label>--%>
<%--                                        </div>--%>


                                        <div class="form-group">
                                            <label>上传申报材料 <span class="sign_must">*</span></label>
                                            <p style="margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报格式:&nbsp;&nbsp;<a
                                                    href="/upload/xinsilu/files/附件一 北京大学“教学新思路2.0”项目申请书_发布.docx" target="_blank">附件一 北京大学“教学新思路2.0”项目申请书_发布.docx</a></p>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报命名格式:--%>
<%--                                                项目申报书_负责人姓名_单位名称.docx</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报书docx文件：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted0">
                                                <s:file name="uploadFile" id="uploadFile0"
                                                        data-show-caption="true" accept=".docx"/>
                                            </div>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报命名格式：--%>
<%--                                                项目申报书_负责人姓名_单位名称.pdf（单位签名盖章）</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报书pdf文件（单位签名盖章）：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted1">
                                                <s:file name="uploadFile" id="uploadFile1"
                                                        data-show-caption="true" accept=".pdf"/>
                                            </div>

                                        </div>

                                        <div class="form-group">
                                            <label>上传申报支撑材料 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">上传的mp4、zip等文件建议不超过500MB，可为空</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料1格式（pdf/mp4）：申报支撑1_负责人姓名_单位名称.pdf/mp4（可为空）</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料1（pdf/mp4）（可为空）：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted2">
                                                <s:file name="uploadFile" id="uploadFile2"
                                                        data-show-caption="true" accept=".pdf,.mp4"/>
                                            </div>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料2格式（pdf/mp4）：申报支撑2_负责人姓名_单位名称.pdf/mp4（可为空）</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料2（pdf/mp4）（可为空）：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted3">
                                                <s:file name="uploadFile" id="uploadFile3"
                                                        data-show-caption="true" accept=".pdf,.mp4"/>
                                            </div>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料3格式（pdf/mp4）：申报支撑3_负责人姓名_单位名称.pdf/mp4（可为空）</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料3（pdf/mp4）（可为空）：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted4">
                                                <s:file name="uploadFile" id="uploadFile4"
                                                        data-show-caption="true" accept=".pdf,.mp4"/>
                                            </div>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;其他材料格式（pdf/mp4/zip）：申报支撑4_负责人姓名_单位名称.pdf/mp4/zip（可为空）</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料4（pdf/mp4）（可为空）：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted5">
                                                <s:file name="uploadFile" id="uploadFile5"
                                                        data-show-caption="true" accept=".pdf,.mp4,.zip"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>负责人简介 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;不超过300字。需包含工号/学号、姓名、性别、出生年月、学历学位、
                                                工作单位、职务职称、主要成果、研究方向、通讯地址、联系电话、E-mail等信息。</p>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textarea class="form-control" name="researchEnroll.userCV"
                                                            required="true"  maxlength="300" id="userCvId"
                                                            style="min-height:200px; border:2px solid;" />
                                            </div>
                                            <s:textfield id="submitStageControl" style="height:2px; width:70px;
                                                                        float:right;" class="form-control" name="submitStageControl"
                                                         readonly = "true"
                                                         type="hidden"/>
                                            <s:textfield id="submitSave" style="height:2px; width:70px;
                                                                        float:right;" class="form-control" name="submitSave"
                                                         readonly = "true"
                                                         type="hidden"/>
                                        </div>

                                        <%--<div class="form-group" style="border-bottom: 2px dashed firebrick">

                                        </div>

                                        <div class="form-group">
                                            <label>是否需要提交现场汇报PPT</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <c:if test="${session.teacher == '0006174152' || session.teacher == '1106579109' ||
                                                session.teacher == '1264012194' || session.teacher == '0006180100' ||
                                                session.teacher == '0016170098' || session.teacher == '0016179039' ||
                                                session.teacher == '0006172212' || session.teacher == '0016182021' ||
                                                session.teacher == '0006176073' || session.teacher == '0163175485' ||
                                                session.teacher == '0061004340' || session.teacher == '0016173048' ||
                                                session.teacher == '0006171199' || session.teacher == '0006177174' ||
                                                session.teacher == '0016175042' || session.teacher == '2101210152'}">
                                                    <s:textfield class="form-control" name="researchEnroll.isPass" value="需要" required="true" disabled="true"
                                                    />
                                                </c:if>
                                                <c:if test="${session.teacher != '0006174152' && session.teacher != '1106579109' &&
                                                session.teacher != '1264012194' && session.teacher != '0006180100' &&
                                                session.teacher != '0016170098' && session.teacher != '0016179039' &&
                                                session.teacher != '0006172212' && session.teacher != '0016182021' &&
                                                session.teacher != '0006176073' && session.teacher != '0163175485' &&
                                                session.teacher != '0061004340' && session.teacher != '0016173048' &&
                                                session.teacher != '0006171199' && session.teacher != '0006177174' &&
                                                session.teacher != '0016175042' && session.teacher != '2101210152'}">
                                                    <s:textfield class="form-control" name="researchEnroll.isPass" value="不需要" required="true" id="isPassId"
                                                    />
                                                </c:if>

                                            </div>
                                        </div>--%>

                                        <div class="form-group" id="uploadPptId">
                                            <label>上传项目申报现场汇报材料 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报现场汇报PPT:--%>
<%--                                                申报现场汇报_负责人姓名_单位名称.pptx</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报现场汇报PPT:</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted6">
                                                <s:file name="uploadFile" id="uploadFile6"
                                                        data-show-caption="true" accept=".pptx"/>
                                            </div>
<%--                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报现场汇报PDF：--%>
<%--                                                申报现场汇报_负责人姓名_单位名称.pdf</p>--%>
                                            <p style="color: firebrick;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目申报现场汇报PDF：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted7">
                                                <s:file name="uploadFile" id="uploadFile7"
                                                        data-show-caption="true" accept=".pdf"/>
                                            </div>

                                        </div>


                                    </div>
                                    <br>
                                    <div>
                                        <p style="color: red;margin-bottom: 10px"><span id="error_msg" style="display: none"></span>
                                        </p>
                                    </div>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" id="saveId" class="btn blue-madison"
                                                onclick="return myValidate()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 保 存
                                        </button>
                                        <button type="submit" id="submitId" class="btn blue-madison"
                                                onclick="return submitValidate()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 提 交
                                        </button>
                                        <p style="color: #6C6C6C;margin:5px 0 10px 0;text-align: justify">&nbsp;&nbsp;&nbsp;&nbsp;温馨提示：请老师退出系统前点“保存”；提交截止时间：1月3日17点；
                                            截止时间前可修改后保存，以最后保存版本做为提交版本。</p>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                        <div id="readBytes"></div>
                        <div id="totalBytes"></div>
                        <div class="progress progress-striped span4" id="bar_bg" style="border: 1px solid forestgreen; display:none">
                            <div id="progress" class="bar" style="width:0px; background: forestgreen">
                            </div>
                        </div>
                        <div class="modal fade" id="myModal" role="dialog" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">状态提示</h4>
                                    </div>
                                    <div class="modal-body">上传文件成功！正在提交请求，大文件请求处理时间会稍长，请稍等</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="list_plate_bg main_bot"></div>

            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<script>
    /*
    * @ function: 项目编号关联基本信息的显示：有项目编号则显示基本信息，并让用户填写；否则不显示基本信息并将其中内容置空
    * */
    $("#projectEthicJudge").on('change', function() {
        // 如果申报项目的研究对象为"人、动物、人体”
        if ($("#projectEthicJudge").val() != "否") {
            // 显示
            $(".basic-info").attr("style", "display: ;");
            $("#uploadFile8").attr('required', 'true');
            $("#uploadFile9").attr('required', 'true');
        } else { // 如果没有项目编号
            // 隐藏
            $(".basic-info").attr("style", "display: none;");
            $("#uploadFile8").attr('required', false);
            $("#uploadFile9").attr('required', false);
        }
    })
</script>
<%
    String fileType = "项目申报";
    String path0 = ResearchAction.getFilePathByIndex(0,fileType);
    path0 = path0.replace("\\","/");
    String path1 = ResearchAction.getFilePathByIndex(1,fileType);
    path1 = path1.replace("\\","/");
    String path2 = ResearchAction.getFilePathByIndex(2,fileType);
    path2 = path2.replace("\\","/");
    String path3 = ResearchAction.getFilePathByIndex(3,fileType);
    path3 = path3.replace("\\","/");
    String path4 = ResearchAction.getFilePathByIndex(4,fileType);
    path4 = path4.replace("\\","/");
    String path5 = ResearchAction.getFilePathByIndex(5,fileType);
    path5 = path5.replace("\\","/");
    String path6 = ResearchAction.getFilePathByIndex(6,fileType);
    path6 = path6.replace("\\","/");
    String path7 = ResearchAction.getFilePathByIndex(7,fileType);
    path7 = path7.replace("\\","/");
    String path8 = ResearchAction.getFilePathByIndex(8,fileType);
    path8 = path8.replace("\\","/");
    String path9 = ResearchAction.getFilePathByIndex(9,fileType);
    path9 = path9.replace("\\","/");
%>
<script type="text/javascript">
    let intervalID = 0;
    function showProgressBar(data){
        let uploaded = (parseFloat(data.uploaded)/1024/1024).toFixed(2);
        let totalBytes = (parseFloat(data.total)/1024/1024).toFixed(2);
        $("#readBytes").html("已上传文件大小：" + uploaded + " MB");
        $("#totalBytes").html("文件总大小：" + totalBytes + " MB");
        let percent = (uploaded/totalBytes*100).toFixed(2);
        $("#progress").attr("style","width:" + percent + "%; background:forestgreen");
        $("#progress").html(percent + "%");
    }
    function submitForm(){
        $("#bar_bg").attr("style","border: 1px solid forestgreen")
        intervalID = setInterval(progressBar,500);
    }
    function progressBar(){
        // console.log("询问进度");
        $.ajax({
            type:"GET",
            url:"loadBar",
            success:function(result){
                // console.log(result);
                if(result.isStarted){
                    if(result.completed){
                        clearInterval(intervalID);
                        $("#readBytes").attr("style","display:none");
                        $("#totalBytes").attr("style","display:none");
                        $("#bar_bg").attr("style","display:none");
                        $("#progress").attr("style","display:none");
                        $("#myModal").modal();
                    }else{
                        // console.log("已上传:"+result.uploaded);
                        // console.log("总大小:"+result.total);
                        // console.log("上传进度:"+result.percent);
                        // console.log("文件序号:"+result.fileCount);
                        showProgressBar(result);
                    }
                }else{
                    // console.log("文件还未提交");
                }
            },
            error:function(XMLHttpRequest,textStatus,errorThrown){
                console.log(XMLHttpRequest+"---"+textStatus+"---"+errorThrown);
            }
        });
    }



    // 获取java service中的文件绝对路径，进行文件路径虚拟映射
    // 从file域替换为当前的域，以绕过同源策略限制
    function findStrIndex(str,cha,num){
        let x = str.indexOf(cha);
        for(let i=0;i<num;i++){
            x = str.indexOf(cha,x+1);
        }
        return x;
    }
    function getUploadedFile(index){
        let labelID = "uploadFile"+index;
        let deleteLabelID = "fileDeleted"+index;
        document.getElementById(deleteLabelID).value = "no";
        document.getElementById(deleteLabelID).setAttribute("style","display:none");
        let file_path = "";
        switch (index){
            case 0: file_path = "<%= path0%>"; break;
            case 1: file_path = "<%= path1%>"; break;
            case 2: file_path = "<%= path2%>"; break;
            case 3: file_path = "<%= path3%>"; break;
            case 4: file_path = "<%= path4%>"; break;
            case 5: file_path = "<%= path5%>"; break;
            case 6: file_path = "<%= path6%>"; break;
            case 7: file_path = "<%= path7%>"; break;
            case 8: file_path = "<%= path8%>"; break;
            case 9: file_path = "<%= path9%>"; break;
        }
        // 对应index找不到文件 直接return
        if(file_path==="")return;
        let require_flag = false;
        if(document.getElementById(labelID).getAttribute("required")=="required"){
            require_flag = true;
            document.getElementById(labelID).removeAttribute("required");
        }
        // 创建a标签在对应上传窗口旁
        //let virtual_path = "http://localhost:8080/file/"+file_path.slice(findStrIndex(file_path,"/",1)+1);
        let virtual_path = file_path;

        let a = document.createElement("a");
        a.href=virtual_path;
        let fileName = file_path.slice(file_path.lastIndexOf("/")+1);
        a.setAttribute("download",fileName);
        a.innerText = fileName;

        let textLabel = document.createElement("div");
        textLabel.innerText = "* 已上传文件:";
        textLabel.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp"+textLabel.innerHTML+"&nbsp;&nbsp;";
        textLabel.setAttribute("style","font-weight:bold");
        textLabel.append(a);
        let parentNode = document.getElementById(labelID).parentNode.parentNode.parentNode.parentNode.parentNode;

        let deleteButton = document.createElement("button");
        deleteButton.setAttribute("type","button");
        let buttonID = "deleteButton"+index;
        deleteButton.setAttribute("id",buttonID);
        deleteButton.value = "删除文件";
        deleteButton.innerText = "删除文件";
        deleteButton.setAttribute("class","btn btn-default btn-secondary fileinput-remove fileinput-remove-button");
        deleteButton.setAttribute("style","display:inline;margin:0 0 5px 10px;");
        textLabel.append(deleteButton);
        parentNode.append(textLabel);
        textLabel.previousElementSibling.setAttribute("style","display:none");
        if($("#submitStageControl").val()==2&&(index!=6&&index!=7)){
            deleteButton.setAttribute("style","display:none");
        }

        deleteButton.onclick=(()=>{
            if(!confirm("是否删除该文件？")) return;
            textLabel.setAttribute("style","display:none");
            textLabel.previousElementSibling.setAttribute("style","display:block");
            if(require_flag) document.getElementById(labelID).setAttribute("required","required");
            document.getElementById(deleteLabelID).value = "yes";
        });
    }
    function getUploadedFileOrder(index,indexMap){
        let labelID = "uploadFile"+index;
        let uploadFile = document.getElementById(labelID);
        if(uploadFile.files.length==0){
            return "";
        }
        let fileName = uploadFile.files[0].name;
        let fileType = fileName.substring(fileName.lastIndexOf(".")+1);
        indexMap[index] = {"name":fileName,"type":fileType};
    }

    $(()=>{
        for(let i=0;i<10;i++){
            getUploadedFile(i);
        }
    })

    function submitValidate(){
        if(!confirm("提交后不可修改，是否确认提交？")){
            return false;
        }
        return myValidate();
    }

    function myValidate(){
        let indexMap = {};
        for(let i=0;i<10;i++){
            getUploadedFileOrder(i,indexMap);
        }
        let fileInfoStr = JSON.stringify(indexMap);
        document.getElementById("filesNewName").value = fileInfoStr;
        return validate_enroll_exist();
    }
</script>

<%--<script>
    const INFO = "上一版本附件已保存，重新提交，请按browse。";
    $(document).ready(function () {
        if($("#deptId").val() != "院系" && $("#deptId").val() != null){
            $(".file-caption-name").attr("placeholder", INFO);
            $(".file-caption-main").attr("style", "width: 165%");
        }
    });
</script>--%>

<%@ include file="../teacher/common/footer.jsp" %>

</body>
</html>
