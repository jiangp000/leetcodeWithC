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
                var scores=new Array(4,8,8,6,4);
                for (i = 1; i < 6; i++) {
                    var id = "#judgeScore" + i;
                    var videoScore = $(id).val();
                    if (isNaN(videoScore)){
                        alert("请输入数字!");
                        //$("#error_msg").text("请输入数字!");
                        //$("#error_msg").show();
                        return
                    }else if (videoScore > scores[i-1] || videoScore < 0) {
                        $(id).val("");
                        alert("您输入的分数为:" + videoScore + ", 但是评分范围为[0-"+scores[i-1]+"]的整数");
                        //$("#error_msg").text("总分需为0-100之间的整数!");
                        //$("#error_msg").show();
                        return
                    }else{
                        sum = sum + Number(videoScore);
                        $("#videoScore").val(sum);
                    }
                }

            });
        });
        $(document).ready(function () {
            $("input").change(function () {
                var sum = 0;
                var scores=new Array(8,8,8,6);
                for (i = 6; i < 10; i++) {
                    var id = "#judgeScore" + i;
                    var documentScore = $(id).val();
                    if (isNaN(documentScore)){
                        alert("请输入数字!");
                        //$("#error_msg").text("请输入数字!");
                        //$("#error_msg").show();
                        return
                    }else if (documentScore > scores[i-6] || documentScore < 0) {
                        $(id).val("");
                        alert("您输入的分数为:" + documentScore + ", 但是评分范围为[0-"+scores[i-6]+"]的整数");
                        //$("#error_msg").text("总分需为0-100之间的整数!");
                        //$("#error_msg").show();
                        return
                    }else{
                        sum = sum + Number(documentScore);
                        $("#documentScore").val(sum);
                    }
                }
            });
        });
        $(document).ready(function () {
            $("input").change(function () {
                var videoScore = $("#videoScore").val();
                var documentScore = $("#documentScore").val();
                var sum = Number(videoScore)  + Number(documentScore) ;
                $("#preScore").val(sum);
            });
        });
        $(document).ready(function () {
            $("input").change(function () {
                var sum = 0;
                var scores=new Array(6,8,8,8,10);
                for (i = 10; i < 15; i++) {
                    var id = "#judgeScore" + i;
                    var pptScore = $(id).val();
                    if (isNaN(pptScore)){
                        alert("请输入数字!");
                        //$("#error_msg").text("请输入数字!");
                        //$("#error_msg").show();
                        return
                    }else if (pptScore > scores[i-10] || pptScore < 0) {
                        $(id).val("");
                        alert("您输入的分数为:" + pptScore + ", 但是评分范围为[0-"+scores[i-10]+"]的整数");
                        //$("#error_msg").text("总分需为0-100之间的整数!");
                        //$("#error_msg").show();
                        return
                    }else{
                        sum = sum + Number(pptScore);
                        $("#pptScore").val(sum);
                    }
                }
            });
        });

        $(document).ready(function () {
            $("input").change(function () {
                var preScore = $("#preScore").val();
                var pptScore = $("#pptScore").val();
                var sum = Number(preScore)  + Number(pptScore) ;
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
                        return "innoScoreJSP?id="+page;
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
            $(".li_nav span:contains('创新大赛')").attr("style", "font-weight:bold");
        });

        $(function(){
            if($("#submitSave").val() == 1) {
                $("#submitId").attr('disabled','true');
                $("#saveId").attr('disabled','true');
                $("#submit2").attr("disabled","true");
                $("#save2").attr("disabled","true");
            }
        })
        /*$(function(){
            if($("#submitSave").val() == 1 || $("#submitSave").val() == 0) {
                var sumVideo = 0;
                var sumDoc = 0;
                var videoScore = 0;
                var documentScore = 0;
                for (i = 1; i < 6; i++) {
                    var id = "#judgeScore" + i;
                    videoScore = $(id).val();
                    sumVideo = sumVideo + Number(videoScore);
                    $("#videoScore").val(sumVideo);
                }
                for (i = 6; i < 10; i++) {
                    var id = "#judgeScore" + i;
                    documentScore = $(id).val();
                    sumDoc = sumDoc + Number(documentScore);
                    $("#documentScore").val(sumDoc);
                }
                var sum = Number(sumVideo)  + Number(sumDoc) ;
                $("#preScore").val(sum*0.3);
            }
        })*/
       /* $(function(){
            $("#preData").attr('disabled','true');
        });*/
        $(function(){
            if($("#scoreStageControl").val() == 0){
                document.getElementById("saveId").style.display="none";
                document.getElementById("submitId").style.display="none";
                document.getElementById("save2").style.display="none";
                document.getElementById("submit2").style.display="none";
                //document.getElementById("uploadPptId").style.display="none";
            }else if($("#scoreStageControl").val() == 1){
                // $("#judgeScore1").attr('readonly','true');
                // $("#judgeScore2").attr('readonly','true');
                // $("#judgeScore3").attr('readonly','true');
                // $("#judgeScore4").attr('readonly','true');
                // $("#judgeScore5").attr('readonly','true');
                // $("#judgeScore6").attr('readonly','true');
                // $("#judgeScore7").attr('readonly','true');
                // $("#judgeScore8").attr('readonly','true');
                // $("#judgeScore9").attr('readonly','true');
                // $("#judgeScore10").attr('readonly','true');
                // $("#judgeScore11").attr('readonly','true');
                // $("#judgeScore12").attr('readonly','true');
                // $("#judgeScore13").attr('readonly','true');
                // $("#judgeScore14").attr('readonly','true');
                $("#preData").attr('disabled','true');
                document.getElementById("preData").style.display="none";
            }else if($("#scoreStageControl").val() == 2) {
                $("#judgeScore10").attr('readonly','true');
                $("#judgeScore11").attr('readonly','true');
                $("#judgeScore12").attr('readonly','true');
                $("#judgeScore13").attr('readonly','true');
                $("#judgeScore14").attr('readonly','true');
                $("#preData").attr('disabled','true');
                document.getElementById("preData").style.display="none";
            }else if($("#scoreStageControl").val() == 3){
                $("#judgeScore1").attr('readonly','true');
                $("#judgeScore2").attr('readonly','true');
                $("#judgeScore3").attr('readonly','true');
                $("#judgeScore4").attr('readonly','true');
                $("#judgeScore5").attr('readonly','true');
                $("#judgeScore6").attr('readonly','true');
                $("#judgeScore7").attr('readonly','true');
                $("#judgeScore8").attr('readonly','true');
                $("#judgeScore9").attr('readonly','true');
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

        $(function(){
            // 网评阶段不可下载现评成绩
            if($("#scoreStageControl").val() == 3){
                $("#column_6").attr("disabled","true");
                $("#column_6").attr("style","text-decoration: none; color:gray; cursor:default;opacity: 1.5");
                $("#column_6").attr("href","javascript:;");
            }else if($("#scoreStageControl").val() == 2){
                $("#column_5").attr("disabled","true");
                $("#column_6").attr("disabled","true");
                $("#column_5").attr("style","text-decoration: none; color:gray; cursor:default;opacity: 1.5");
                $("#column_5").attr("href","javascript:;");
                $("#column_6").attr("style","text-decoration: none; color:gray; cursor:default;opacity: 1.5");
                $("#column_6").attr("href","javascript:;");
            }
        });

        $(function (){
            if ($("#delaySortId").val() == "是"){
                $(".basic-info").attr("style", "display: none;");
            }
            if ($("#delaySortId").val() == '是' && $("#isPass").attr('reverse') =='网评'){
                $(".ss-button").attr("style", "display: none;");
            }
            console.log($("#isPass").attr('reverse'));
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
                        <!--<li>
                            <a href="applyScoreJSP?id=1" id="column_1">
                                <i class="icon_a icon_all"></i>项目申报
                            </a>
                        </li>-->
                        <li>
                            <a href="innoScoreJSP?id=1" id="column_1"  class="selected">
                                <i class="icon_a icon_all"></i>大赛评分
                            </a>
                        </li>
                        <!--<li>
                            <a href="paperScoreJSP?id=1" id="column_2" class="selected">
                                <i class="icon_a icon_all"></i>论文评审
                            </a>
                        </li>-->
                        <!--<li>
                            <a href="finalScoreJSP?id=1" id="column_3">
                                <i class="icon_a icon_all"></i>结题评审
                            </a>
                        </li>-->
                        <li>
                            <a href="innoDownloadPreScoreJSP" id="column_4">
                                <i class="icon_a icon_all"></i>网评成绩
                            </a>
                        </li>
                        <li>
                            <a href="innoDownloadPptScoreJSP" id="column_5">
                                <i class="icon_a icon_all"></i>现评成绩
                            </a>
                        </li>
                        <li>
                            <a href="innoDownloadResultScoreJSP" id="column_6">
                                <i class="icon_a icon_all"></i>大赛成绩
                            </a>
                        </li>
                        <li>
                            <a href="innoDownloadFileJSP" id="column_7">
                                <i class="icon_a icon_all"></i>资料下载
                            </a>
                        </li>
                        <li>
                            <a href="uploadInnoScoreTableJSP" id="column_8">
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
                            <div style="float:right;margin-top:20px" class="ss-button">
                                <button type="submit" id="save2" class="btn blue-madison" onclick="document.getElementById('saveId').click()";
                                        style="display:inline-block;margin:0;padding:6px">保存</button>
                                <button type="submit" id="submit2" class="btn blue-madison" onclick="document.getElementById('submitId').click()"
                                        style="display:inline-block;margin:0;padding:6px">提交</button>
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <form action="submitInnovationScore?id=${id}"
                                  id="researchScoreForm" enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">

                                        <div class="form-group">
                                            <label>基本信息</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2" style="width: 34%">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                负责人号
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.userId"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 28%;">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 负责人姓名
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.username"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 38%;">
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
                                                    <td class="col-md-2 basic-info" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                案例分组
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectSort"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2 basic-info" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 案例类型
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.caseSort"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                大赛延期
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.delaySort" id="delaySortId"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="basic-info">
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                案例名称
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.title"
                                                                         style="width:563px" readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="basic-info">
                                                    <td class="col-md-2" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目号
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectNo"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 项目类型
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectApplySort"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr class="basic-info">
                                                    <td class="col-md-2" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目名称
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectTitle"
                                                                         readonly="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <div class="form-group">
                                            <label>大赛初审</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2" style="width: 40px">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                初审结果
                                                            </span>
                                                            <%--<s:textfield class="form-control" name="isPass" id="isPass" required="true"/>--%>
                                                            <c:if test="${sessionScope.judge2 == '0006170278'}">
                                                                <select class="selectpicker form-control" required="true" id="isPass" name="isPass" reverse="初审">
                                                                    <c:forEach var="item" items="${isPassList}">
                                                                        <c:choose>
                                                                            <c:when test="${isPass==item}">
                                                                                <option value="${item}"
                                                                                        selected="selected">${item}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${item}">${item}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                    <%--<option value ="尚未初审">尚未初审</option>
                                                                    <option value ="通过">通过</option>
                                                                    <option value ="未通过">未通过</option>
                                                                    <option value ="修改提交">修改提交</option>--%>
                                                                </select>
                                                            </c:if>
                                                            <c:if test="${sessionScope.judge2 != '0006170278'}">
                                                                <select class="selectpicker form-control" required="true" id="isPass"
                                                                        name="isPass" readonly="true" disabled="true" reverse="网评">
                                                                    <c:forEach var="item" items="${isPassList}">
                                                                        <c:choose>
                                                                            <c:when test="${isPass==item}">
                                                                                <option value="${item}"
                                                                                        selected="selected">${item}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${item}">${item}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </select>
                                                                <%--<s:textfield class="form-control" name="isPass" disabled="true" id="isPass" required="true"/>--%>
                                                            </c:if>

                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 40px;">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                 大赛编号
                                                            </span>
                                                            <%--<s:textfield class="form-control" name="applyId" required="true"/>--%>
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
                                                            <%--<s:textfield class="form-control" name="applyPreComment" required="true" style="width:563px"/>--%>

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

                                        <div class="form-group basic-info">
                                            <label>案例视频1</label>
                                            <video id="research_video"  controls   preload="auto"  style="width: 100%">
                                                <source src="${file4}" type='video/mp4'/>
                                            </video>
                                            <p>选择播放速率：<select id="selRate">
                                                <option value="0.5">0.5</option>
                                                <option value="1" selected>1.0</option>
                                                <option value="1.25">1.25</option>
                                                <option value="1.5">1.5</option>
                                                <option value="2">2.0</option>
                                                <option value="2.5">2.5</option>
                                            </select></p>
                                            <c:if test="${file5 != ''}">
                                                <label>案例视频2</label>
                                                <video id="research_video2"  controls   preload="auto"  style="width: 100%">
                                                    <source src="${file5}" type='video/mp4'/>
                                                </video>
                                                <p>选择播放速率：<select id="selRate2">
                                                    <option value="0.5">0.5</option>
                                                    <option value="1" selected>1.0</option>
                                                    <option value="1.25">1.25</option>
                                                    <option value="1.5">1.5</option>
                                                    <option value="2">2.0</option>
                                                    <option value="2.5">2.5</option>
                                                </select></p>
                                            </c:if>
                                            <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                <a href="${file6}" class="btn btn-default"
                                                style="margin-top: 10px;" target="_blank">视频信息表
                                                </a>
                                            </p>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>案例视频评分表（30分）</label>
                                            <div>
                                                <p style="color: red;margin-bottom: 10px"><span id="error_msg_video" style="display: none"></span></p>
                                            </div>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>教学理念</strong></span>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">1、案例符合“学生中心、产出导向、持续改进”的教育教学理念；课程案例符合学科专业与课程要求，体现立德树人思想。</p>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">（满分4分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore1" required="true" type="number" name="judgeScore1"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>案例内容</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、案例具备高阶性、创新性、挑战性；案例内容有深度、广度，既重视基础，又反映前沿，渗透专业思想；使用质量高的教学资源。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、系统完整，符合认知规律；重点难点突出、逻辑合理、结构清晰；</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、课程案例体现课程思政目标，促进学生家国情怀、科学与人文精神的培养。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分8分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore2" required="true" type="number" name="judgeScore2"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>教学模式</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、体现教师主导、学生主体的教学宗旨。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、课程案例教学目标科学、明确，重视学生发展需要，课堂活动与教学目标具有高度一致性。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、课程案例教学组织有序，教学过程安排合理。创新教学方法与策略，调动学生积极性，师生互动良好。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">4、合理有效运用辅助教学手段和现代信息技术，合理使用板书与教具，合理应用专业外语，支撑教学创新。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">5、考核评价的内容和方式创新，促进学习过程。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分8分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore3" required="true" type="number" name="judgeScore3"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>教学效果</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、课程案例教师学识深厚，准备充分，课堂讲授富有吸引力，课堂气氛融洽，学生思维活跃，深度参与课堂。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、体现学生对知识、能力与素质全面发展的要求，能够有效激发学生的学习兴趣，促进学生思考与提问，培养学生的自主学习能力。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、教学模式新颖，效果好，具有较大的借鉴和推广价值。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分6分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore4" required="true" type="number" name="judgeScore4"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>视频质量</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、教学视频清晰、流畅，能客观、真实，全面反映教学过程常态 或 教学实际应用场景 。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分4分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore5" required="true" type="number" name="judgeScore5"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr style="margin-top: 10px;">
                                                    <td class="col-md-2" style="font-size: 16px ">
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <i class="glyphicon glyphicon-bookmark"
                                                               style="color:#8C0000"></i>
                                                            &nbsp;&nbsp;案例实录视频评分总计(30分)

                                                            <s:textfield id="videoScore" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="videoScore"
                                                                         readonly = "true"
                                                                         required="true" type="number"/>
                                                        </p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="form-group basic-info">
                                            <%-- <iframe src='https://view.officeapps.live.com/op/view.aspx?src=http://jpk.pku.edu.cn${researchEnroll.filePath}' width='100%' height='100%' frameborder='1'>
                                             </iframe>--%>
                                        </div>

                                        <div class="form-group">
                                            <label>创新成果报告等材料</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <c:if test="${file1 != ''}">
                                                                <a href="${file1}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">申报书
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file2 != ''}">
                                                                <a href="${file2}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">创新成果报告
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file3 != ''}">
                                                                <a href="${file3}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">创新成果支撑材料目录
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file7 != ''}">
                                                                <a href="${file7}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">案例设计（教案）
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file8 != ''}">
                                                                <a href="${file8}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">案例课件(ppt)
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file9 != ''}">
                                                                <a href="${file9}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    教学大纲
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file10 != ''}">
                                                                <a href="${file10}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    相关材料1
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file11 != ''}">
                                                                <a href="${file11}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    相关材料2
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file12 != ''}">
                                                                <a href="${file12}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    相关材料3
                                                                </a>
                                                            </c:if>
                                                            <c:if test="${file14 != ''}">
                                                                <a href="${file14}" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">
                                                                    延期申请
                                                                </a>
                                                            </c:if>
                                                        </p>
                                                    </td >
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="form-group basic-info">
                                            <label>创新成果报告等材料评分表（30分）</label>
                                            <div>
                                                <p style="color: red;margin-bottom: 10px"><span id="error_msg_doc" style="display: none"></span></p>
                                            </div>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>案例目标及学情分析</strong></span>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">1、案例目标的高阶性、创新性、挑战度；对教学难点和痛点问题进行深刻准确地剖析；课程案例强调课程思政目标与学科教学目标的创新结合，促进学生家国情怀、科学与人文精神的培养。</p>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">（满分8分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore6" required="true" type="number" name="judgeScore6"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>创新理念及思路</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、课程案例强调体现以学生发展为中心、以学生能力培养为目标的教学理念，对教学内容、活动、评价、方法等教学要素分析全面、透彻，与教学目标匹配度高，能够凸显课程教学创新点。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、综合案例在支持改进教学、科研和管理上， 具有先进的创新改进理念和独特可行的问题解决思路。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分8分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore7" required="true" type="number" name="judgeScore7"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>创新方法及途径</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、能够把握新时代下学生学习特点，凸显多种教学方法在学科教学中的创新实践，合理选择并充分利用现代信息技术手段开展教学活动和学习评价。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分8分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="judgeScore8" required="true" type="number" name="judgeScore8"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>创新效果及成果</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、能够对课程教学的创新实践成效开展基于证据的有效分析与总结，形成具有较强辐射推广价值的教学新方法、新模式，甚至新平台、新技术、新设备等。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分6分）
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
                                                            &nbsp;&nbsp;创新成果报告等材料评分总计(30分)

                                                            <s:textfield id="documentScore" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="documentScore"
                                                                         readonly = "true"
                                                                         required="true" type="number"/>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr style="margin-top: 10px;">
                                                    <td class="col-md-2" style="font-size: 16px ">
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <i class="glyphicon glyphicon-bookmark"
                                                               style="color:#8C0000"></i>
                                                            &nbsp;&nbsp;网评成绩(60分)：

                                                            <s:textfield id="preScore" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="preScore"
                                                                         readonly = "true"
                                                                         required="true" type="number"/>
                                                        </p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="form-group basic-info">

                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <c:if test="${file13 != ''}">
                                                                <a href="${file13}" id="preData" class="btn btn-default"
                                                                   style="margin-top: 10px; float: left" target="_blank">${researchEnroll.applyId}_${researchEnroll.username}
                                                                </a>
                                                            </c:if>
                                                        </p>
                                                    </td >
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="form-group basic-info">
                                                <div>
                                                    <p style="color: red;margin-bottom: 10px"><span style="display: none"></span></p>
                                                </div>
                                                <label>创新设计现场汇报评分表（40分）</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong style="color: #90000a">理念与目标</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、课程教学贴合“以学生发展为中心”的理念，强调高阶能力以及情感价值的课程目标。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分6分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control"
                                                                            id="judgeScore10" required="true" type="number" name="judgeScore10"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong style="color: #90000a">教学内容</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、课程内容有深度、广度，反映学科前沿，渗透专业思想，使用质量高的教学资源。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、将思想政治教育与专业教育有机融合。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、将学科研究新进展、实践发展新经验、社会需求新变化纳入教学内容。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分8分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control"
                                                                            id="judgeScore11" required="true" type="number" name="judgeScore11"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong style="color: #90000a">过程与方法</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、教学活动丰富多样，能体现各等级水平的知识、技能和情感价值目标。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、能根据课程特点，用创新的教学策略、方法、技术解决课堂教学中存在的各种问题和困难。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、强化师生和生生互动，教学活动应循序渐进，教师提供必要的支持和指导，帮助学生成为自主学习者。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分8分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control"
                                                                            id="judgeScore12" required="true" type="number" name="judgeScore12"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong style="color: #90000a">考评与反馈</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、考核方式科学合理，能合理评价学生知识、技能的掌握情况。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、过程性评价与结果性评价相结合，以促成学生进步为出发点设计多元的评价方式，且给与及时反馈。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、提供清晰合理的评价规则和标准，积极创造学生自我评价和同伴互评的机会。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分8分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control"
                                                                            id="judgeScore13" required="true" type="number" name="judgeScore13"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong style="color: #90000a">创新应用与现场表现</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、案例创意创新特点突出，运用于实际教学中，具有较强辐射推广价值；</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、现场表述清晰、语言规范、材料充实、重点突出；</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、体现北大教学风范、快速准确回答问题，熟练运用现代化教具或系统演示案例。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control"
                                                                            id="judgeScore14" required="true" type="number" name="judgeScore14"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr style="margin-top: 10px;">
                                                    <td class="col-md-2" style="font-size: 16px ">
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <i class="glyphicon glyphicon-bookmark"
                                                               style="color:#8C0000"></i>
                                                            &nbsp;&nbsp;现评成绩(40分)：

                                                            <s:textfield id="pptScore" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="pptScore"
                                                                         readonly = "true"
                                                                         required="true" type="number"/>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr style="margin-top: 10px;">
                                                    <td class="col-md-2" style="font-size: 16px ">
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <i class="glyphicon glyphicon-bookmark"
                                                               style="color:#8C0000"></i>
                                                            &nbsp;&nbsp;大赛评分总计(100分)
                                                            <s:textfield id="score" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="score" readonly="true" required="true" type="number"/>
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

                                        <div class="form-group basic-info">
                                            <label>专家评语</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textarea class="form-control" name="researchScore.comment"
                                                            required="true" maxlength="500"
                                                            style="min-height:90px; border:2px solid;" id="comment"/>
                                            </div>
                                        </div>


                                    </div>
                                    <br>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center" class="ss-button">
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

                                        <%--<button type="submit" class="btn blue-madison"
                                                onclick="validate_researchScoreForm()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 提 交
                                        </button>
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
<%--<script type="text/javascript">--%>
<%--    function findStrIndex(str,cha,num){--%>
<%--        let x = str.indexOf(cha);--%>
<%--        for(let i=0;i<num;i++){--%>
<%--            x = str.indexOf(cha,x+1);--%>
<%--        }--%>
<%--        return x;--%>
<%--    }--%>
<%--    $(()=>{--%>
<%--        let videos = $("video>source");--%>
<%--        for(let i = 0; i < videos.length; i++){--%>
<%--            let file_path = videos[i].getAttribute("src");--%>
<%--            let virtual_path = "http://localhost:8080/file/"+file_path.slice(findStrIndex(file_path,"/",1)+1);--%>
<%--            videos[i].setAttribute("src",virtual_path);--%>
<%--        }--%>
<%--        let files = $("p>a");--%>
<%--        for(let i = 0; i < files.length; i++){--%>
<%--            let file_path = files[i].getAttribute("href");--%>
<%--            let virtual_path = "http://localhost:8080/file/"+file_path.slice(findStrIndex(file_path,"/",1)+1);--%>
<%--            files[i].setAttribute("href",virtual_path);--%>
<%--        }--%>
<%--    })--%>

<%--</script>--%>
<%@ include file="common/footer.jsp" %>

</body>
</html>
