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

            $(".li_nav span:contains('结题报告')").attr("style", "font-weight:800");
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
                if(ext != "docx" && ext != "doc"){
                    $(".fileinput-remove-button:eq(0)").click();
                    alert("文件命名格式为 结题报告_项目编号_负责人姓名_单位名称.docx,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile1").on("filebatchselected", function () {
                var id = "#uploadFile1";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf"){
                    $(".fileinput-remove-button:eq(1)").click();
                    alert("文件命名格式为 结题报告_项目编号_负责人姓名_单位名称.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile2").on("filebatchselected", function () {
                var id = "#uploadFile2";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" && ext != "mp4"){
                    $(".fileinput-remove-button:eq(2)").click();
                    alert("文件命名格式为 结题支撑1_项目编号_负责人姓名_单位名称.pdf/mp4,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile3").on("filebatchselected", function () {
                var id = "#uploadFile3";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" && ext != "mp4"){
                    $(".fileinput-remove-button:eq(3)").click();
                    alert("文件命名格式为 结题支撑2_项目编号_负责人姓名_单位名称.pdf/mp4,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile4").on("filebatchselected", function () {
                var id = "#uploadFile4";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" && ext != "mp4"){
                    $(".fileinput-remove-button:eq(4)").click();
                    alert("文件命名格式为 结题支撑3_项目编号_负责人姓名_单位名称.pdf/mp4,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile5").on("filebatchselected", function () {
                var id = "#uploadFile5";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "zip"){
                    $(".fileinput-remove-button:eq(5)").click();
                    alert("文件命名格式为 结题支撑4_项目编号_负责人姓名_单位名称.zip,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile6").on("filebatchselected", function () {
                var id = "#uploadFile6";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pptx" || filePath.search("结题现场汇报")==-1){
                    $("#uploadFile6").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(6)").click();
                    alert("文件命名格式为 结题现场汇报_项目编号_负责人姓名_单位名称.pptx,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile7").on("filebatchselected", function () {
                var id = "#uploadFile7";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" || filePath.search("结题现场汇报")==-1){
                    $("#uploadFile7").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(7)").click();
                    alert("文件命名格式为 结题现场汇报_项目编号_负责人姓名_单位名称.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile8").on("filebatchselected", function () {
                var id = "#uploadFile8";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(filePath.search("申请报告")==-1 || ext != "pdf"){
                    $("#uploadFile8").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(8)").click();
                    alert("文件命名格式为 申请报告_项目编号_负责人姓名_单位名称.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile9").on("filebatchselected", function () {
                var id = "#uploadFile9";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(filePath.search("中期报告")==-1 || (ext != "pdf" && ext != zip)){
                    $("#uploadFile9").closest(".input-group-append").find(".fileinput-remove-button").click();
                    //$(".fileinput-remove-button:eq(9)").click();
                    alert("文件命名格式为 中期报告_项目编号_负责人姓名_单位名称.pdf/zip,请重新上传正确的文件格式!");
                    return false;
                }
            })
        });

        $(function(){
            if($("#submitStageControl").val() == 0){
                document.getElementById("saveId").style.display="none";
                //document.getElementById("uploadPptId").style.display="none";
            }else if($("#submitStageControl").val() == 1) {
                document.getElementById("uploadPptId").style.display="none";
                $("#uploadFile0").attr('required','true');
                $("#uploadFile1").attr('required','true');
            }else if($("#submitStageControl").val() == 2){
                $("#finalDelayId").attr('disabled','true');
                $("#deptId").attr('disabled','true');
                $("#projectNumId").attr('readonly','true');
                $("#projectSortId").attr('readonly','true');
                $("#projectNameId").attr('readonly','true');
                $("#userTitleId").attr('readonly','true');
                $("#userPhoneId").attr('readonly','true');
                $("#userMailId").attr('readonly','true');
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
            }
        })

        $(document).ready(function () {
            if ($("#finalDelayId").val() == "是") {
                // 不显示
                $(".basic-info").attr("style", "display:none ;");
                //$(".projectNoBlock").attr("style", "display:none ;");

            }else{
                //$(".basic-info").attr("style", "display:none ;");
                //$(".projectIdBlock").attr("style", "display:none ;");
                //$(".projectNoBlock").attr("style", "display: ;");
            }
        });
    </script>
</head>
<body>
<%@ include file="../teacher/common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="finalResearchEnroll.action">结题报告提交</a>
        </div>
        <div class="w_986">
            <%@ include file="siderFinal.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitFinalResearchEnroll" id="researchEnrollForm"
                                  enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <table class="table" style="width:680px">
                                                <label>基本信息 <span class="sign_must">*</span></label>
                                                <tr>
                                                    <td class="col-md-2" colspan="1"><%--colspan="3"--%>
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                结题延期申请
                                                            </span>
                                                            <select class="selectpicker form-control" name="delaySort" id="finalDelayId" value="finalDelaySort">
                                                                <c:forEach var="item" items="${delaySorts}">
                                                                    <c:choose>
                                                                        <c:when test="${finalDelaySort==item}">
                                                                            <option value="${item}"
                                                                                    selected="selected">${item}</option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${item}">${item}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                                <%--<option value="否" selected="selected">否</option>
                                                                <option value="是">是</option>--%>
                                                            </select>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目编号
                                                            </span>
                                                            <s:textfield class="form-control" id="projectNumId" name="researchEnroll.projectNo"
                                                                         placeholder="例:2023YB01" required="true"
                                                            />
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" colspan="2">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目名称
                                                            </span>
                                                            <s:textfield class="form-control" id="projectNameId" name="researchEnroll.title"
                                                                         required="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="form-group">
                                            <table class="table" style="width:680px">
                                                <label>负责人信息 <span class="sign_must">*</span></label>
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
                                        <div class="form-group basic-info">
                                            <label>项目成员 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">&nbsp;&nbsp;&nbsp;除了负责人外可添加0-4个成员</span></label>
                                            <div class="form-group" id = "group_1">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>成员一</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id1" placeholder="校园卡号" id="u_id1Id"
                                                    />
                                                    <s:textfield class="form-control u_name" name="u_name1" placeholder="姓名" id="u_name1Id"
                                                    />
                                                    <select class="selectpicker form-control u_dept"  name="u_dept1" placeholder="院系" id="u_dept1Id">
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
                                                    <select class="selectpicker form-control u_dept"  name="u_dept2" id="u_dept2Id">
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
                                                    <select class="selectpicker form-control u_dept"  name="u_dept3" id="u_dept3Id">
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
                                                <s:textfield name="researchEnroll.teamMember"  class = "form-control u_teamMember" style="display:none;"></s:textfield>
                                            </div>

                                        </div>
                                        <!--团队成员结束-->


                                        <div class="form-group basic-info">
                                            <label>上传结题报告 <span class="sign_must">*</span></label>
                                            <p style="margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;结题报告格式:&nbsp;&nbsp;<a
                                                    href="/upload/xinsilu/files/附件1结题报告.docx" target="_blank">附件1结题报告.docx</a></p>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;结题报告命名格式:
                                                结题报告_项目编号_负责人姓名_单位名称.docx</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile0"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;结题报告命名格式：
                                                结题报告_项目编号_负责人姓名_单位名称.pdf（单位签名盖章）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile1"
                                                        data-show-caption="true"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>上传结题支撑材料 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">上传的mp4、zip等文件建议不超过500MB，可为空</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料1格式（pdf/mp4）：结题支撑1_项目编号_负责人姓名_单位名称.pdf/mp4（可为空）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile2"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料2格式（pdf/mp4）：结题支撑2_项目编号_负责人姓名_单位名称.pdf/mp4（可为空）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile3"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料3格式（pdf/mp4）：结题支撑3_项目编号_负责人姓名_单位名称.pdf/mp4（可为空）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile4"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;其他材料格式（zip）：结题支撑4_项目编号_负责人姓名_单位名称.zip（可为空）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile5"
                                                        data-show-caption="true"/>
                                            </div>
                                        </div>

                                        <div class="form-group delayBlock" style="display: none">
                                            <label>延期申请 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目负责人签字,单位盖公章:
                                                申请报告_项目编号_负责人姓名_单位名称.pdf</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile8"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目负责人签字,单位盖公章:
                                                中期报告_项目编号_负责人姓名_单位名称.pdf/zip</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile9"
                                                        data-show-caption="true"/>
                                            </div>
                                        </div>
                                        <%--<div class="form-group" style="border-bottom: 1.5px dashed firebrick">
                                        </div>--%>
                                        <div class="form-group">
                                            <s:textfield id="submitStageControl" style="height:2px; width:70px;
                                                                        float:right;" class="form-control" name="submitStageControl"
                                                         readonly = "true"
                                                         type="hidden"/>
                                        </div>

                                        <div class="form-group" id="uploadPptId">
                                            <label>上传结题汇报材料 </label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;温馨提示：2022年11月18日14:00 — 22:00，提交结题汇报PPT</p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;结题现场汇报PPT：结题现场汇报_项目编号_负责人姓名_单位名称.pptx</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile6"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;结题现场汇报PDF：结题现场汇报_项目编号_负责人姓名_单位名称.pdf</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile7"
                                                        data-show-caption="true"/>
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
                                        <button type="submit" class="btn blue-madison" id="saveId" onclick="return validate_enroll_exist()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 保 存
                                        </button>
                                        <p style="color: #6C6C6C;margin-bottom: 10px;text-align: justify">&nbsp;&nbsp;&nbsp;&nbsp;温馨提示：请老师退出系统前点“保存”；提交截止时间：11月6日24点；
                                            截止时间前可修改后保存，以最后保存版本做为提交版本。</p>
                                        <%--<button type="reset" class="btn blue-madison">
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

<script>
    /*
    * @ function: 项目编号关联基本信息的显示：有项目编号则显示基本信息，并让用户填写；否则不显示基本信息并将其中内容置空
    * */
    $("#finalDelayId").on('change', function() {
        // 如果选择了延期申请这一选项
        if ($("#finalDelayId").val() == "是") {
            // 不显示
            $(".basic-info").attr("style", "display:none ;");
            $(".delayBlock").attr("style", "display: ;");
            $("#uploadFile0").attr('required', false);
            $("#uploadFile1").attr('required', false);
            $("#uploadFile8").attr('required','true');
            $("#uploadFile9").attr('required','true');
        } else { // 如果不延期申请
            // 隐藏
            $(".basic-info").attr("style", "display: ;");
            $(".delayBlock").attr("style", "display: none;");
            $("#uploadFile0").attr('required','true');
            $("#uploadFile1").attr('required','true');
            $("#uploadFile8").attr('required', false);
            $("#uploadFile9").attr('required', false);
        }
    })
    $("#projectNumId").on('change', function() {
        // 如果选择了延期申请这一选项
        if ($("#finalDelayId").val() == "否") {
            $("#uploadFile0").attr('required','true');
            $("#uploadFile1").attr('required','true');
            $("#uploadFile8").attr('required', false);
            $("#uploadFile9").attr('required', false);
        } else { // 如果不延期申请
            $("#uploadFile0").attr('required', false);
            $("#uploadFile1").attr('required', false);
            $("#uploadFile8").attr('required','true');
            $("#uploadFile9").attr('required','true');
        }
    })
</script>

<%--<script>
    const INFO = "上一版本附件已保存，重新提交，请按browse。";
    $(function(){
        if($("#deptId").val() != "院系" && $("#deptId").val() != null){
            $(".file-caption-name").attr("placeholder", INFO);
            $(".file-caption-main").attr("style", "width: 165%");
        }
    });
</script>--%>

<%@ include file="../teacher/common/footer.jsp" %>

</body>
</html>
