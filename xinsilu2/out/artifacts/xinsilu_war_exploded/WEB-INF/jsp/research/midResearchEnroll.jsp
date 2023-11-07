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
            $(".li_nav span:contains('中期报告')").attr("style", "font-weight:800");
        });

        $(document).ready(function () {
            $("#projectNoId").change(function () {
                var id = "#deptId";
                var deptVal = $(id).val();
                if(deptVal == "无"){
                    $(id).val("");
                    alert("请选择院系");
                    return;
                }
            })
        });
        $(document).ready(function () {
            $("#titleId").change(function () {
                var id = "#titleId";
                var titleVal = $(id).val();
                if(titleVal.includes("/")){
                    $(id).val("");
                    alert("标题中不能包含 / 特殊字符");
                    return;
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
                $("#titleId").attr('readonly','true');
                $("#projectNumId").attr('readonly','true');
                $("#deptId").attr('disabled','true');
                $("#projectNoId").attr('readonly','true');
                $("#projectSortId").attr('disabled','true');
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

            }
        })
    </script>
</head>
<body>

<%--<%@ include file="../teacher/common/head.jsp" %>--%>
<%@ include file="../teacher/common/head.jsp" %>


<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="midResearchEnroll.action">中期报告提交</a>
        </div>
        <div class="w_986">
            <%@ include file="siderMiddle.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitMidResearchEnroll" id="researchEnrollForm"
                                  enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">

                                        <div class="form-group">
                                            <table class="table" style="width:680px">
                                                <label>项目信息 <span class="sign_must">*</span></label>
                                                <tr>
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目题目
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.title" id="titleId"
                                                                         required="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" style="width: 30%">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目分类
                                                            </span>
                                                            <select class="selectpicker form-control" name="projectSort" id="projectSortId"
                                                                    required="true" value="projectSort">
                                                                <c:forEach var="item" items="${projectSorts}">
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
                                                                <%--<option value ="重点">重点</option>
                                                                <option value ="优先">优先</option>
                                                                <option value="一般">一般</option>
                                                                <option value="其他">其他</option>--%>
                                                            </select>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 70%">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目编号
                                                            </span>
                                                            <s:textfield class="form-control" id="projectNumId" name="researchEnroll.projectNo"
                                                                         placeholder="是否为今年立项项目 例：2023YB01 默认“无”"
                                                            />
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
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
                                        <div class="form-group">
                                            <label>团队成员 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">&nbsp;&nbsp;&nbsp;除了负责人外可添加0-4个成员</span></label>
                                            <div class="form-group" id = "group_1">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>成员一</span>
                                                    </span>
                                                    <s:textfield class="form-control u_id" name="u_id1" placeholder="校园卡号" id="u_id1Id"
                                                    />
                                                    <s:textfield class="form-control u_name" name="u_name1" placeholder="姓名" id="u_name1Id"
                                                    />
                                                    <%--                                                    <s:textfield class="form-control u_dept" name="u_dept" placeholder="院系"--%>
                                                    <%--                                                                 required="true"/>--%>
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
                                                    <s:textfield class="form-control u_name" name="u_name4" placeholder="姓名" id="u_name4Id"
                                                    />
                                                    <select class="selectpicker form-control u_dept"  name="u_dept4" id="u_dept4Id">
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
                                                <s:textfield name="researchEnroll.teamMember"  class = "form-control u_teamMember" required="true" style="display:none;"></s:textfield>
                                            </div>

                                        </div>
                                        <!--团队成员结束-->

                                        <div class="form-group">
                                            <label>上传中期报告 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;中期报告命名格式：
                                                中期报告_项目编号_校园卡号_教师姓名_单位名称.docx</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile0"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;中期报告命名格式：
                                                中期报告_项目编号_校园卡号_教师姓名_单位名称.pdf</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile1"
                                                        data-show-caption="true"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>上传中期支撑材料 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">上传的mp4、zip等文件建议不超过500MB，可为空</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料1格式（pdf/mp4）：中期支撑1_项目编号_负责人姓名_单位名称.pdf/mp4（可为空）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile2"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料2格式（pdf/mp4）：中期支撑2_项目编号_负责人姓名_单位名称.pdf/mp4（可为空）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile3"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;支撑材料3格式（pdf/mp4）：中期支撑3_项目编号_负责人姓名_单位名称.pdf/mp4（可为空）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile4"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;其他材料格式（zip）：中期支撑4_项目编号_负责人姓名_单位名称.zip（可为空）</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile5"
                                                        data-show-caption="true"/>
                                            </div>
                                            <s:textfield id="submitStageControl" style="height:2px; width:70px;
                                                                        float:right;" class="form-control" name="submitStageControl"
                                                         readonly = "true"
                                                         type="hidden"/>
                                        </div>

                                        <%--
                                         <div class="form-group" style="border-bottom: 1.5px dashed firebrick">
                                        </div>

                                        <div class="form-group">
                                            <label>上传中期汇报材料 </label>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;中期现场汇报PPT：中期现场汇报_项目编号_负责人姓名_单位名称.PPT</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile6" required="true"
                                                        data-show-caption="true"/>
                                            </div>
                                            <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;中期现场汇报PDF：中期现场汇报_项目编号_负责人姓名_单位名称.PDF</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile7" required="true"
                                                        data-show-caption="true"/>
                                            </div>
                                        </div>--%>
                                    </div>
                                    <br>
                                    <div>
                                        <p style="color: red;margin-bottom: 10px"><span id="error_msg" style="display: none"></span></p>
                                    </div>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison" id="saveId" onclick="return validate_enroll_exist()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 保 存
                                        </button>
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
