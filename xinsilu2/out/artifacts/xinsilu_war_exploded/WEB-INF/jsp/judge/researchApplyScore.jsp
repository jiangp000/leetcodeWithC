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
    <script type="text/javascript" src="../plugins/bootstrap/js/bootstrap-paginator.js"></script>
    <script type="text/javascript" src="../js/xinsilu.js"></script>

    <script>
        $(document).ready(function () {
            $("input").change(function () {
                var sum = 0;
                var scores=new Array(10,10,10,10,10,10,10,10);
                for (i = 1; i <= 8; i++) {
                    var id = "#judgeScore" + i;
                    var applyScore = $(id).val();
                    if (isNaN(applyScore)){
                        //alert("请输入数字!");
                        //$("#error_msg").text("请输入数字!");
                        //$("#error_msg").show();
                        return
                    }else if (applyScore > scores[i-1] || applyScore < 0) {
                        $(id).val("");
                        alert("您输入的分数为:" + applyScore + ", 但是评分范围为[0-"+scores[i-1]+"]的整数");
                        //$("#error_msg").text("总分需为0-100之间的整数!");
                        //$("#error_msg").show();
                        return
                    }else{
                        sum = sum + Number(applyScore);
                        $("#preScore").val(sum);
                    }
                }

            });
        });

        $(document).ready(function () {
            $("input").change(function () {
                var id = "#judgeScore9";
                var judgeScore9 = $(id).val();
                if(isNaN(judgeScore9)){
                    //alert("请输入数字!");
                }else if(judgeScore9 > 20 || judgeScore9 < 0){
                    $(id).val("");
                    alert("您输入的分数为:" + judgeScore9 + ", 但是评分范围为[0-20]的整数");
                    return;
                }
            })
        });
        $(document).ready(function () {
            $("input").change(function () {
                var preScore = $("#preScore").val();
                var displayScore = $("#judgeScore9").val();
                var sum = Number(preScore)  + Number(displayScore) ;
                $("#score").val(sum);
            });
        });

        $(document).ready(function(){
            var options = {
                totalPages : ${fn:length(researchEnrollList)},
                numberOfPages: 10,
                currentPage: ${id},
                pageUrl: function(type, page, current){
                    if (page==current) {
                        return "javascript:void(0)";
                    } else {
                        return "applyScoreJSP?id="+page;
                    }
                }
            }
            $('#pagination').bootstrapPaginator(options);
        })

        $(document).ready(function () {
            var eleSelect = document.getElementById('selRate');
            var video = document.getElementById('research_video');
            // 改变播放速率
            eleSelect.addEventListener('change', function () {
                video.playbackRate = this.value;
            });
        })

        $(function(){
            $(".li_nav span:contains('项目申报')").attr("style", "font-weight:bold");
        });

        $(function(){
            if($("#submitSave").val() == 1) {
                $("#submitId").attr('disabled','true');
                $("#saveId").attr('disabled','true');
                $("#submit2").attr("disabled","true");
                $("#save2").attr("disabled","true");
            }
        })
        $(function(){
            if($("#isPass").val() == '未通过') {
                $("#judgeScore1").attr('readonly','true');
                $("#judgeScore2").attr('readonly','true');
                $("#judgeScore3").attr('readonly','true');
                $("#judgeScore4").attr('readonly','true');
                $("#judgeScore5").attr('readonly','true');
                $("#judgeScore6").attr('readonly','true');
                $("#judgeScore7").attr('readonly','true');
                $("#judgeScore8").attr('readonly','true');
                $("#judgeScore9").attr('readonly','true');

                $("#submitId").attr('disabled','true');
                $("#saveId").attr('disabled','true');
            }
        })
        $(function(){
            if($("#scoreStageControl").val() == 0){
                document.getElementById("saveId").style.display="none";
                document.getElementById("submitId").style.display="none";
                document.getElementById("save2").style.display="none";
                document.getElementById("submit2").style.display="none";
                //document.getElementById("uploadPptId").style.display="none";
            }else if($("#scoreStageControl").val() == 1){
                $("#preDataId").attr('disabled','true');
                document.getElementById("preDataId").style.display="none";
            }else if($("#scoreStageControl").val() == 2) {
                $("#judgeScore9").attr('readonly','true');
                $("#preDataId").attr('disabled','true');
                document.getElementById("preDataId").style.display="none";
            }else if($("#scoreStageControl").val() == 3){
                $("#judgeScore1").attr('readonly','true');
                $("#judgeScore2").attr('readonly','true');
                $("#judgeScore3").attr('readonly','true');
                $("#judgeScore4").attr('readonly','true');
                $("#judgeScore5").attr('readonly','true');
                $("#judgeScore6").attr('readonly','true');
                $("#judgeScore7").attr('readonly','true');
                $("#judgeScore8").attr('readonly','true');
            }
        })
        function myValidate(){
            if(!confirm("提交后本条评分不可修改，是否确认提交？")) {
                return false;
            }
            if(validate_researchScoreForm()){
                $("#submitSave").val("1");
                return true;
            }
            return false;
        }
        $(document).ready(function () {
            if ($("#projectEthicId").val() != "否") {
                // 显示
                $(".basic-info").attr("style", "display: ;");
            }else{
                $(".basic-info").attr("style", "display:none ;");
            }
        });


    </script>
</head>
<body>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="w_986">
<%--            <%@ include file="sider.jsp" %>--%>
            <div class="list_sider f_l">
                <h2 class="h2_title list_plate_bg f_family f_normal">
                    <span class="more"></span>评委评审</h2>
                <div class="content list_plate_bg">
                    <ul class="list_sid_nav">
                        <li>
                            <a href="applyScoreJSP?id=1" id="column_1" class="selected">
                                <i class="icon_a icon_all"></i>项目申报
                            </a>
                        </li>
                        <!--<li>
                            <a href="scoreResearchJSP?id=1" id="column_1">
                                <i class="icon_a icon_all"></i>大赛评审
                            </a>
                        </li>
                        <li>
                            <a href="paperScoreJSP?id=1" id="column_2">
                                <i class="icon_a icon_all"></i>论文评审
                            </a>
                        </li>
                        <li>
                            <a href="finalScoreJSP?id=1" id="column_3">
                                <i class="icon_a icon_all"></i>结题评审
                            </a>
                        </li>-->
                        <li>
                            <a href="applyDownloadScoreJSP" id="column_4">
                                <i class="icon_a icon_all"></i>成绩下载
                            </a>
                        </li>
                        <li>
                            <a href="applyDownloadFileJSP" id="column_5">
                                <i class="icon_a icon_all"></i>资料下载
                            </a>
                        </li>
                        <li>
                            <a href="uploadApplyScoreTableJSP" id="column_6">
                                <i class="icon_a icon_all"></i>上传评分表
                            </a>
                        </li>
                    </ul>
                    <div class="clear"></div>
                </div>
                <div class="list_sider_bot list_plate_bg"></div>
            </div>


            <div class="list_main f_r">
                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div>
                            <ul id="pagination" class="pagination">
                            </ul>
                            <div style="float:right;margin-top:20px">
                                <button type="submit" id="save2" class="btn blue-madison" onclick="document.getElementById('saveId').click()";
                                        style="display:inline-block;margin:0;padding:6px">保存</button>
                                <button type="submit" id="submit2" class="btn blue-madison" onclick="document.getElementById('submitId').click()"
                                        style="display:inline-block;margin:0;padding:6px">提交</button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form action="submitApplyScore?id=${id}"
                                  id="researchScoreForm" enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <table class="table" style="width:680px">
                                                <label>基本信息 <span class="sign_must">*</span></label>
                                                <tr>
                                                    <td class="col-md-2" style="width: 32%">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                负责人号
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.userId"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 32%;">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 负责人姓名
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.username"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 36%;">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 院系
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.deptName"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" style="width: 32%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            职称
                                                        </span>
                                                            <s:textfield class="form-control" id="userTitleId" name="researchEnroll.userTitle"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 32%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            手机号
                                                        </span>
                                                            <s:textfield class="form-control" id="userPhoneId" name="researchEnroll.userPhone"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 36%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            邮箱
                                                        </span>
                                                            <s:textfield class="form-control" id="userMailId" name="researchEnroll.userMail"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                申请类型
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectApplySort" id="projectApplySortId" readonly="true"
                                                            />
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" colspan="2">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 团队成员
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.teamMember"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 申报项目的研究对象是否为"人、动物、人体"
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectEthicSort"
                                                                         readonly="true" id="projectEthicId"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="form-group basic-info" style="display: none;">
                                                    <td class="col-md-2" >
                                                        <div class="input-group">
                                                            <a href="${fileEthic1}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">伦理与科研诚信证书
                                                            </a>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" >
                                                        <div class="input-group">
                                                            <a href="${fileEthic2}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">伦理审核证明
                                                            </a>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 项目方向
                                                         </span>
                                                            <s:textfield class="form-control" name="researchEnroll.caseSort"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 项目名称
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.title"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <div class="form-group">
                                            <label>负责人简介</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textarea class="form-control" name="researchEnroll.userCV"
                                                            required="true" maxlength="500" readonly="true"
                                                            style="min-height:180px; border:2px solid;" id="userCV"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>项目初审</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2" style="width: 40px">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                是否通过
                                                            </span>
                                                            <%--<s:textfield class="form-control" name="isPass" readonly="true" id="isPass" required="true"/>--%>
                                                            <c:if test="${sessionScope.judge2 == '0006170278'}">
                                                                <select class="selectpicker form-control" required="true" id="isPass" name="isPass">
                                                                    <option value ="通过">通过</option>
                                                                    <option value ="未通过">未通过</option>
                                                                </select>
                                                            </c:if>
                                                            <c:if test="${sessionScope.judge2 != '0006170278'}">
                                                                <s:textfield class="form-control" name="isPass" readonly="true" id="isPass" required="true"/>
                                                            </c:if>

                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 40px;">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 申请编号(例:2023SQZD001)
                                                            </span>
                                                            <%--<s:textfield class="form-control" readonly="true" name="applyId" required="true"/>--%>
                                                           <c:if test="${sessionScope.judge2 == '0006170278'}">
                                                                <s:textfield class="form-control" name="applyId" required="true"/>
                                                            </c:if>
                                                            <c:if test="${sessionScope.judge2 != '0006170278'}">
                                                                <s:textfield class="form-control" readonly="true" name="applyId" required="true"/>
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" colspan="2">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                初审意见
                                                            </span>
                                                            <%--<s:textfield class="form-control" name="applyPreComment" readonly="true" required="true" style="width:563px"/>--%>

                                                           <c:if test="${sessionScope.judge2 == '0006170278'}">
                                                                <s:textfield class="form-control" name="applyPreComment" required="true" style="width:563px"/>
                                                            </c:if>
                                                            <c:if test="${sessionScope.judge2 != '0006170278'}">
                                                                <s:textfield class="form-control" name="applyPreComment" readonly="true" required="true" style="width:563px"/>
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <div class="form-group">
                                            <label>项目申报书等材料</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <a href="${file1}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">项目申报书
                                                            </a>

                                                            <c:if test="${file2 != file1}">
                                                                <a href="${file2}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    项目申报支撑材料1
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file3 != file1}">
                                                                <a href="${file3}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    项目申报支撑材料2
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file4 != file1}">
                                                                <a href="${file4}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    项目申报支撑材料3
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file5 != file1}">
                                                                <a href="${file5}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    项目申报支撑材料4
                                                                </a>
                                                            </c:if>
                                                        </p>
                                                    </td >
                                                </tr>
                                            </table>
                                        </div>


                                        <div class="form-group" id="judgeId">
                                            <label>申报评分</label>
                                            <div>
                                                <p style="color: red;margin-bottom: 10px"><span id="error_msg_video" style="display: none"></span></p>
                                            </div>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>问题认识清晰</strong></span>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;"
                                                                             class="form-control" id="judgeScore1" required="true" type="number" name="judgeScore1"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>项目目标明确</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore2" required="true" type="number" name="judgeScore2"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>创新意义重大</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore3" required="true" type="number" name="judgeScore3"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>有创新改进思路</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore4" required="true" type="number" name="judgeScore4"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>已取得显著成果</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore5" required="true" type="number" name="judgeScore5"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>预期成果丰富</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore6" required="true" type="number" name="judgeScore6"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>项目实施难度大</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore7" required="true" type="number" name="judgeScore7"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>经费预算合理</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore8" required="true" type="number" name="judgeScore8"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong style="color: #90000a">预评结果</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分80分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="preScore" readonly="true" required="true" type="number" name="preScore"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <a href="${file6}" class="btn btn-default" id="preDataId"
                                                               style="margin-top: 10px; float: left" target="_blank">${researchEnroll.username}
                                                            </a>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>现场评分</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分20分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore9" required="true" type="number" name="judgeScore9"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr style="margin-top: 10px;">
                                                    <td class="col-md-2" style="font-size: 16px ">
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <i class="glyphicon glyphicon-bookmark"
                                                               style="color:#8C0000"></i>
                                                            &nbsp;&nbsp;项目申报评分总计(100分)
                                                            <s:textfield id="score" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="score" readonly = "true" required="true" type="number"/>
                                                        </p>
                                                    </td>
                                                </tr>
                                                            <s:textfield id="submitSave" style="height:24px; width:70px;
                                                                        float:right;" class="form-control" name="submitSave"
                                                                         readonly = "true"
                                                                         required="true" type="hidden"/>
                                                             <s:textfield id="scoreStageControl" style="height:24px; width:70px;
                                                                        float:right;" class="form-control" name="scoreStageControl"
                                                                        readonly = "true"
                                                                        required="true" type="hidden"/>


                                            </table>
                                        </div>
                                        <div class="form-group">
                                            <%-- <iframe src='https://view.officeapps.live.com/op/view.aspx?src=http://jpk.pku.edu.cn${researchEnroll.filePath}' width='100%' height='100%' frameborder='1'>
                                             </iframe>--%>
                                        </div>


                                        <div class="form-group">
                                            <label>申报建议</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textarea class="form-control" name="researchScore.comment"
                                                            required="true" maxlength="500"
                                                            style="min-height:90px; border:2px solid;" id="comment"/>
                                            </div>
                                        </div>

                                        <%--<div class="form-group">
                                            <label>评审结束</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><strong>项目编号</strong></span>
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="glyphicon glyphicon-user"></i>
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectNo"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><strong>立项分类</strong></span>
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="glyphicon glyphicon-user"></i>
                                                            </span>
                                                            <select class="selectpicker form-control" required="true" name="researchEnroll.projectSort">
                                                                <option value ="重点">重点</option>
                                                                <option value ="优先">优先</option>
                                                                <option value="一般">一般</option>
                                                            </select>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><strong>立项时间</strong></span>
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                <i class="glyphicon glyphicon-tag"></i>
                                                            </span>
                                                            <input type="date" class="form-control" name="researchScore.comment"
                                                                        required="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>--%>

                                    </div>
                                    <br>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison" id="saveId"
                                                onclick="return validate_researchScoreForm()" >
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 保 存
                                        </button>

                                        <button type="submit" class="btn blue-madison" id="submitId"
                                                onclick="return myValidate()" >
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 提 交
                                        </button>
                                        <!--<button type="reset" class="btn blue-madison">
                                            <i class="glyphicon glyphicon-remove"></i>
                                            &nbsp; 取 消
                                        </button>-->
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

<%@ include file="common/footer.jsp" %>
</body>
</html>
