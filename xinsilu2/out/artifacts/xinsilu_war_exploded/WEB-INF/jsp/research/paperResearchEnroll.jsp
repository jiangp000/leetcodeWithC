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
            $(".li_nav span:contains('教研论文')").attr("style", "font-weight:800");
        });

        $(document).ready(function () {
            $("#projectNameId").change(function () {
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
            $("#userMailId").change(function () {
                var id = "#paperDeptId";
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
                    alert("文件命名格式为 论文报名表_校园卡号_负责人姓名_单位名称.docx,请重新上传正确的文件格式!");
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
                    alert("文件命名格式为 论文报名表_项目编号/无_负责人姓名_单位名称.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile2").on("filebatchselected", function () {
                var id = "#uploadFile2";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "docx"){
                    $(".fileinput-remove-button:eq(2)").click();
                    alert("文件命名格式为 教研论文_项目编号/无_负责人姓名_单位名称.docx,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile3").on("filebatchselected", function () {
                var id = "#uploadFile3";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf"){
                    $(".fileinput-remove-button:eq(3)").click();
                    alert("文件命名格式为 教研论文_项目编号/无_负责人姓名_单位名称.pdf,请重新上传正确的文件格式!");
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
                $("#uploadFile2").attr('required','true');
                $("#uploadFile3").attr('required','true');
            }else if($("#submitStageControl").val() == 2){
                $("#deptId").attr('disabled','true');
                $("#paperDeptId").attr('readonly','true');
                $("#projectNumId").attr('readonly','true');
                $("#projectNameId").attr('readonly','true');
                $("#projectSortId").attr('disabled','true');
                $("#projectIdJudge").attr('disabled','true');
                $("#userTitleId").attr('readonly','true');
                $("#userPhoneId").attr('readonly','true');
                $("#userMailId").attr('readonly','true');
                $("#teamMemberId").attr('readonly','true');
                $("#paperAbstractId").attr('readonly','true');
                $("#projectCaseId").attr('readonly','true');
                $("#userCvId").attr('readonly','true');
                $("#u_id1Id").attr('readonly','true');
                $("#u_name1Id").attr('readonly','true');
                $("#u_dept1Id").attr('disabled','true');
                $("#u_author1Id").attr('disabled','true');
                $("#u_id2Id").attr('readonly','true');
                $("#u_name2Id").attr('readonly','true');
                $("#u_dept2Id").attr('disabled','true');
                $("#u_author2Id").attr('disabled','true');
                $("#u_id3Id").attr('readonly','true');
                $("#u_name3Id").attr('readonly','true');
                $("#u_dept3Id").attr('disabled','true');
                $("#u_author3Id").attr('disabled','true');
                $("#u_id4Id").attr('readonly','true');
                $("#u_name4Id").attr('readonly','true');
                $("#u_dept4Id").attr('disabled','true');
                $("#u_author4Id").attr('disabled','true');
                $("#u_id5Id").attr('readonly','true');
                $("#u_name5Id").attr('readonly','true');
                $("#u_dept5Id").attr('disabled','true');
                $("#u_author5Id").attr('disabled','true');
                $("#uploadFile0").attr('disabled','true');
                //document.getElementById("uploadFile1")
                //document.getElementById("uploadFile1").disabled=true;
                $("#uploadFile1").attr('disabled','true');
                $("#uploadFile2").attr('disabled','true');
                $("#uploadFile3").attr('disabled','true');

            }
        })
        $(document).ready(function () {
            if ($("#projectIdJudge").val() != "否") {
                // 显示
                //$(".basic-info").attr("style", "display: ;");
                $(".projectIdBlock").attr("style", "display: ;");
                //$(".projectNoBlock").attr("style", "display:none ;");
            }else{
                $(".basic-info").attr("style", "display:none ;");
                $(".projectIdBlock").attr("style", "display:none ;");
                $("#projectNumId").val("无");
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
            <a href="paperResearchEnroll.action">教研论文提交</a>
        </div>
        <div class="w_986">
            <%@ include file="siderPaper.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitPaperResearchEnroll" id="researchEnrollForm" enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <table class="table" style="width:640px">
                                                <label>填报人信息 <span class="sign_must">*</span></label>
                                                <tr >
                                                    <td class="col-md-2" style="width: 30%">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                校园卡号
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.userId" id="useridId"
                                                                         value="%{#session.teacher}" readonly="true" required="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 26%">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                教师姓名
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.username" id="usernameId"
                                                                         value="%{#session.name}" readonly="true" required="true"/>
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
                                            </table>
                                        </div>

                                        <div class="form-group">
                                            <table class="table" style="width:680px">
                                                <label>论文信息 <span class="sign_must">*</span></label>
                                                <tr>
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                论文标题
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
                                                                论文方向
                                                            </span>
                                                            <select class="selectpicker form-control" name="projectSort" id="projectSortId"
                                                                    required="true" value="projectSort">
                                                                <c:forEach var="item" items="${paperSorts}">
                                                                    <c:choose>
                                                                        <c:when test="${projectSort==item}">
                                                                            <option value="${item}"
                                                                                    selected="selected">${item}</option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${item}">${item}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
                                                                <option id = "otherProject" value="其他">其他（请自行填写）</option>
                                                            </select>
                                                            <div class="input-group" id = "otherProjectIdBlock" style="display: none;">
                                                                <span class="input-group-addon">
                                                                    具体方向
                                                                </span>
                                                                <input type="text" placeholder="请填写具体方向" id="otherProjectId" class="form-control">
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>

                                                <tr class="projectNoBlock">
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                是否是北大教学新思路2.0项目
                                                            </span>
                                                            <select class="selectpicker form-control" name="projectNoSort" id="projectIdJudge" value="projectNoSort">
                                                                <c:forEach var="item" items="${projectNoSorts}">
                                                                    <c:choose>
                                                                        <c:when test="${projectNoSort==item}">
                                                                            <option value="${item}"
                                                                                    selected="selected">${item}</option>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <option value="${item}">${item}</option>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:forEach>
<%--                                                               <option value="有">有</option>--%>
<%--                                                                <option value="无" selected="selected">无</option>--%>
                                                            </select>
                                                        </div>
                                                    </td>
                                                </tr>

                                                <tr class="projectIdBlock" style="display: none">
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目编号
                                                            </span>
                                                            <s:textfield class="form-control" id="projectNumId" name="researchEnroll.projectNo"
                                                                         placeholder="请填写 项目号 负责人 项目名称"
                                                            />
                                                        </div>
                                                    </td>
                                                </tr>


                                            </table>
                                        </div>

                                        <div class="form-group">
                                            <table class="table" style="width:640px">
                                                <label>初审结果</label>
                                                <tr >
                                                    <td class="col-md-2" style="width: 30%">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                初审结果
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.isPass" id="isPass"
                                                                         readonly="true"  />
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 26%">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                初审意见
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.applyPreComment" id="applyPreComment"
                                                                         readonly="true" />
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <div class="form-group basic-info" style="display: none;">
                                            <table class="table" style="width:680px">
                                            <label>负责人信息</label>
                                                <tr>
                                                    <td class="col-md-2" style="width: 28%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            校园卡号
                                                        </span>
                                                            <s:textfield class="form-control" name="researchEnroll.paperUserid"
                                                            />
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 28%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            负责人姓名
                                                        </span>
                                                            <s:textfield class="form-control" name="researchEnroll.paperUsername"
                                                                         />
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 34%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            院系
                                                        </span>
                                                            <select class="selectpicker form-control" id="paperDeptId"
                                                                    name="paperUserdept">
                                                                <c:forEach var="item" items="${paperUserdepts}">
                                                                    <c:choose>
                                                                        <c:when test="${paperUserdept==item}">
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
                                                    <td class="col-md-2" style="width: 28%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            职称
                                                        </span>
                                                            <s:textfield class="form-control" id="userTitleId" name="researchEnroll.userTitle"
                                                                         />
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 28%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            手机号
                                                        </span>
                                                            <s:textfield class="form-control" id="userPhoneId" name="researchEnroll.userPhone"
                                                                         />
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 34%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            邮箱
                                                        </span>
                                                            <s:textfield class="form-control" id="userMailId" name="researchEnroll.userMail"
                                                                         />
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>




                                        <div class="form-group">
                                            <label>论文作者 <span class="sign_must">*</span></label>
                                            <div class="form-group" id = "group_1">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>一作</span>
                                                    </span><%--value="%{#session.teacher}" value="%{#session.name}"--%>
                                                    <s:textfield class="form-control u_id" name="u_id1" placeholder="校园卡号" id="u_id1Id"
                                                                 />
                                                    <s:textfield class="form-control u_name" name="u_name1" placeholder="姓名" id="u_name1Id"
                                                                 />
<%--                                                    <s:textfield class="form-control u_dept" name="u_dept" placeholder="院系"--%>
<%--                                                                 required="true"/>--%>
                                                    <select class="selectpicker form-control u_dept" id="u_dept1Id" name="u_dept1">
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
                                                    <select class="selectpicker form-control u_author" id="u_author1Id" name="u_author1">
                                                        <c:forEach var="item" items="${authorSorts1}">
                                                            <c:choose>
                                                                <c:when test="${u_author1==item}">
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
                                                        <span>二作</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id2" placeholder="校园卡号" id="u_id2Id"
                                                                 />
                                                    <s:textfield class="form-control u_name" name="u_name2" placeholder="姓名" id="u_name2Id"
                                                                 />
                                                    <select class="selectpicker form-control u_dept" id="u_dept2Id" name="u_dept2">
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
                                                    <select class="selectpicker form-control u_author" id="u_author2Id" name="u_author2">
                                                        <c:forEach var="item" items="${authorSorts2}">
                                                            <c:choose>
                                                                <c:when test="${u_author2==item}">
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
                                                        <span>三作</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id3" placeholder="校园卡号" id="u_id3Id"
                                                                 />
                                                    <s:textfield class="form-control u_name" name="u_name3" placeholder="姓名" id="u_name3Id"
                                                                 />
                                                    <select class="selectpicker form-control u_dept" id="u_dept3Id" name="u_dept3">
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
                                                    <select class="selectpicker form-control u_author" id="u_author3Id" name="u_author3">
                                                        <c:forEach var="item" items="${authorSorts3}">
                                                            <c:choose>
                                                                <c:when test="${u_author3==item}">
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
                                                        <span>四作</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id4" placeholder="校园卡号" id="u_id4Id"
                                                                 />
                                                    <s:textfield class="form-control u_name" name="u_name4" placeholder="姓名" id="u_name4Id"
                                                                 />
                                                    <select class="selectpicker form-control u_dept" id="u_dept4Id" name="u_dept4">
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
                                                    <select class="selectpicker form-control u_author" id="u_author4Id" name="u_author4">
                                                        <c:forEach var="item" items="${authorSorts4}">
                                                            <c:choose>
                                                                <c:when test="${u_author4==item}">
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
                                            <div class="form-group" id = "group_5">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>五作</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id5" placeholder="校园卡号" id="u_id5Id"
                                                    />
                                                    <s:textfield class="form-control u_name" name="u_name5" placeholder="姓名" id="u_name5Id"
                                                    />
                                                    <select class="selectpicker form-control u_dept" id="u_dept5Id" name="u_dept5">
                                                        <c:forEach var="item" items="${stuDeptNames5}">
                                                            <c:choose>
                                                                <c:when test="${u_dept5==item}">
                                                                    <option value="${item}"
                                                                            selected="selected">${item}</option>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <option value="${item}">${item}</option>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </select>
                                                    <select class="selectpicker form-control u_author" id="u_author5Id" name="u_author5">
                                                        <c:forEach var="item" items="${authorSorts5}">
                                                            <c:choose>
                                                                <c:when test="${u_author5==item}">
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
                                                <s:textfield name="researchEnroll.teamMember"  class = "form-control u_teamMember" required="true" style="display:none;"></s:textfield>
                                            </div>

                                        </div>

                                        <div class="form-group">
                                            <label>上传论文报名表 <span class="sign_must">*</span></label>

                                            <p style="margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;论文报名表格式：&nbsp;&nbsp;<a
                                                    href="/upload/xinsilu/files/附件1论文报名表.docx" target="_blank">附件1论文报名表.docx</a></p>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖                                          </p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;报名表命名格式：
                                                论文报名表_项目编号_第一作者姓名_单位名称.docx</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile0" required="true"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;报名表签名盖章pdf（扫描版）：论文报名表_项目编号/无_第一作者姓名_单位名称.pdf</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile1" required="true"
                                                        data-show-caption="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>上传教研论文 <span class="sign_must">*</span></label>
                                            <p style="margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教研论文格式：&nbsp;&nbsp;<a
                                                    href="/upload/xinsilu/files/附件2论文要求.pdf" target="_blank">附件2论文格式要求.pdf</a></p>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;论文doc版本：
                                                教研论文_项目编号/无_第一作者姓名_单位名称.docx</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile2" required="true"
                                                        data-show-caption="true"/>
                                            </div>
<%--                                            <p style="color: #b22222;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;论文pdf版本：--%>
<%--                                                教研论文_项目编号/无__负责人姓名_单位名称.pdf</p>--%>
<%--                                            <div class="input-group">--%>
<%--                                                <s:file name="uploadFile" id="uploadFile3"--%>
<%--                                                        required="true" data-show-caption="true"/>--%>
<%--                                            </div>--%>
                                        </div>

                                        <div class="form-group">
                                            <p style="color: #b22222;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;论文pdf版本：
                                                教研论文_项目编号/无_第一作者姓名_单位名称.pdf</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile3" required="true"
                                                        data-show-caption="true"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>论文摘要 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;不超过300字。</p>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textarea class="form-control" name="researchEnroll.paperAbstract" id="paperAbstractId"
                                                            required="true"  maxlength="500"
                                                            style="min-height:200px; border:2px solid;" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>作者简介 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;不超过500字。需包含工号/学号、姓名、性别、出生年月、学历学位、
                                                工作单位、职务职称、主要成果、研究方向、通讯地址、联系电话、E-mail等信息。</p>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textarea class="form-control" name="researchEnroll.userCV" id="userCvId"
                                                            required="true"  maxlength="500"
                                                            style="min-height:200px; border:2px solid;" />
                                            </div>
                                                <s:textfield id="submitStageControl" style="height:2px; width:70px;
                                                                        float:right;" class="form-control" name="submitStageControl"
                                                             readonly = "true"
                                                             type="hidden"/>

                                        </div>
                                    </div>
                                    <br>
                                    <div>
                                        <p style="color: red;margin-bottom: 10px"><span id="error_msg" style="display: none"></span></p>
                                    </div>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison" id="saveId" onclick="return validate_paperEnroll_exist()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 保 存
                                        </button>


                                        <p style="color: #6C6C6C;margin-bottom: 10px;text-align: justify">&nbsp;&nbsp;&nbsp;&nbsp;温馨提示：请老师退出系统前点“保存”；提交截止时间：10月31日24点；
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
    // 监听下拉框选项的变化
    $("#projectSortId").on('change', function (){
        // 如果用户选择了 "其他" 这个 option，则显示给用户输入自定义内容的输入框
        if ($("#projectSortId").val() == "其他") {
            $("#otherProjectIdBlock").attr("style", "display: ;");
        } else { // 否则隐藏自定义输入框
            $("#otherProjectIdBlock").attr("style", "display: none;");
        }
    })
    // 监听自定义输入框的内容变化
    $("#otherProjectId").on('change', function () {
        // 如果用户在下拉菜单中选择了 "其他" 选项
        if ($("#projectSortId").val() == "其他") {
            // 遍历下拉框中的 option，删除之前自定义添加的 option，保证用户自定义的 option 始终只有一个
            $("#projectSortId option").each(function(){
                if($(this).attr("name") == "new_add_option"){
                    $(this).remove();
                }
            });
            // 保存用户输入的内容
            var opValue = $("#otherProjectId").val();

            // opValue != "其他"： 防止用户输入的新 option 的值为 "其他"，即出现两个其他的情况
            if (opValue != "其他") {
                // 将用户输入的内容作为新的 option 插入到 select 头部
                var option = '<option name = "new_add_option"' + 'value="'+ opValue + '">' + opValue + '</option>';
                $("#projectSortId").prepend(option);

                $("#projectSortId").find("option[name='new_add_option']").attr("selected", true);
            }
            // 将自定义内容的输入框隐藏
            $("#otherProjectIdBlock").attr("style", "display: none;");
        }
    })
</script>
<script>
    /*
    * @ function: 项目编号关联基本信息的显示：有项目编号则显示基本信息，并让用户填写；否则不显示基本信息并将其中内容置空
    * */
    $("#projectIdJudge").on('change', function() {
        // 如果选择了有项目编号这一选项
        if ($("#projectIdJudge").val() != "否") {
            // 显示
            // $(".basic-info").attr("style", "display: ;");
            $("#projectNumId").val("");
            $(".projectIdBlock").attr("style", "display: ;");
        } else { // 如果没有项目编号
            // 隐藏
            $(".basic-info").attr("style", "display: none;");
            $(".projectIdBlock").attr("style", "display: none;");
            // 将项目编号置空
            $("#projectNumId").val("无");
            // 将 "基本信息" 这一块置空
            // 将 "基本信息" 中的校园卡号置空
            $("#researchEnroll_userId").val("")
            // 将 "基本信息" 中的项目负责人置空
            $("#researchEnroll_username").val("")
            // 将 "基本信息" 中的院系置为无
            $("#paperDeptId").val("无")
            // 将 "基本信息" 中的职称置空
            $("#userTitleId").val("")
            // 将 "基本信息" 中的 置空
            $("#userPhoneId").val("")
            // 将 "基本信息" 中的 置空
            $("#userMailId").val("")
        }
    })
</script>

<script>
    const projectData = [
        { projectNumber: "2023ZD01", projectName: "基于人工智能协同的新型教学方法研究" },
        { projectNumber: "2023ZD02", projectName: "以人工智能赋能社工实习教学实践基地建设与教学成效提升" },
        { projectNumber: "2023ZD03", projectName: "融合 Canvas 和 Teams 的系统数据挖掘" },
        { projectNumber: "2023YX01", projectName: "北京大学人体解剖学共享图库的建设" },
        { projectNumber: "2023YX02", projectName: "面向自主学习能力培养的 3J 教学法研究" },
        { projectNumber: "2023YX03", projectName: "基于增强现实(AR)技术的药理学教学软件开发" },
        { projectNumber: "2023YX04", projectName: "认识我们面对的 SARS-CoV-2" },
        { projectNumber: "2023YX05", projectName: "《流行病学》智能数字化教材建设与探索性应用" },
        { projectNumber: "2023YX06", projectName: "“全球健康传播”慕课及语料库建设" },
        { projectNumber: "2023YX07", projectName: "太极拳智能数字化教材建设应用" },
        { projectNumber: "2023YB01", projectName: "基于多模态影像和人工智能的脊柱肿瘤教学病例库的建设和应用" },
        { projectNumber: "2023YB02", projectName: "基于人工智能标准化病人的 CBL 教学模式构建与效果研究--以针对临床研究生的大肠癌教学为例" },
        { projectNumber: "2023YB03", projectName: "基于决策树的消化性溃疡诊疗互动视频课程开发与效果评价" },
        { projectNumber: "2023YB04", projectName: "临床胜任力导向的《医学生临床思维训练》混合式课程建设" },
        { projectNumber: "2023YB05", projectName: "“双一流”学科自主建设背景下，国内外营养与食品卫生学研究生课程体系的比较研究" },
        { projectNumber: "2023YB06", projectName: "“互联网+”和置信职业行为在普通外科专科医师腹腔镜手术技能精准评价中的运用" },
        { projectNumber: "2023YB07", projectName: "《耳鼻咽喉科学》数字化教材建设及应用" },
        { projectNumber: "2023YB08", projectName: "“以学生为中心”的医学生美育课程体系研究" },
        { projectNumber: "2023YB09", projectName: "北京大学大学英语多课程体系等值题库的建设" },
        { projectNumber: "2023YB10", projectName: "结合虚拟现实技术的放疗课程教学" },
        { projectNumber: "2023YB11", projectName: "基于药学专业培养需求的 '人体结构与功能' 整合课程建设和实践" },
        { projectNumber: "2023YB12", projectName: "基于国际培养方案解析的神经外科专科医师结构化课程体系构建与评估"},
        { projectNumber: "2023YB13", projectName: "基于数字医学和高仿真肺模型的胸腔镜手术教学新模式探索与实践" },
        { projectNumber: "2023YB14", projectName: "基于实践技能培养的智慧化运动康复专科移动教学考核系统建设" },
        { projectNumber: "2023YB15", projectName: "医工融合——混合现实（MR）技术在住院医师后腹腔镜手术学习中的应用研究"},
        ];
    $("#projectNumId").on('input', function() {
        // 获取用户输入的项目编号
        var projectNum = $(this).val();
        console.log(projectNum);
        // 在projectData中查找对应的项目编号
        var title = "";
        for (var i = 0; i < projectData.length; i++) {
            if (projectData[i].projectNumber === projectNum) {
                title = projectData[i].projectName;
                break;
            }
        }

        $("#projectNameId").val(title);
    });
</script>

<%@ include file="../teacher/common/footer.jsp" %>

</body>
</html>
