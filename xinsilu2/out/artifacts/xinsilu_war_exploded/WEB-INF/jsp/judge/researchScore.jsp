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
                var scores=new Array(10,40,20,20,10);
                for (i = 1; i < 6; i++) {
                    var id = "#videoScore" + i;
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
                var scores=new Array(25,30,25,20);
                for (i = 1; i < 5; i++) {
                    var id = "#documentScore" + i;
                    var documentScore = $(id).val();
                    if (isNaN(documentScore)){
                        alert("请输入数字!");
                        //$("#error_msg").text("请输入数字!");
                        //$("#error_msg").show();
                        return
                    }else if (documentScore > scores[i-1] || documentScore < 0) {
                        $(id).val("");
                        alert("您输入的分数为:" + documentScore + ", 但是评分范围为[0-"+scores[i-1]+"]的整数");
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
                $("#score").val(sum*0.3);
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
                        return "scoreResearchJSP?id="+page;
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
                            <a href="scoreResearchJSP?id=1" id="column_1" class="selected">
                                <i class="icon_a icon_all"></i>大赛评审
                            </a>
                        </li>-->
                        <li>
                            <a href="paperScoreJSP?id=1" id="column_2">
                                <i class="icon_a icon_all"></i>论文评审
                            </a>
                        </li>
                        <li>
                            <a href="finalScoreJSP?id=1" id="column_3">
                                <i class="icon_a icon_all"></i>结题评审
                            </a>
                        </li>
                        <li>
                            <a href="judgeDownloadScoreJSP" id="column_4">
                                <i class="icon_a icon_all"></i>成绩下载
                            </a>
                        </li>
                        <li>
                            <a href="judgeDownloadFileJSP" id="column_5">
                                <i class="icon_a icon_all"></i>资料下载
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
                        </div>
                        <div class="portlet-body form">
                            <form action="submitResearchScore?id=${id}"
                                  id="researchScoreForm" enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>校园卡号</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="researchEnroll.userId"
                                                             disabled="true"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>姓名</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="researchEnroll.username"
                                                             disabled="true"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>项目名称</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="researchEnroll.title"
                                                             disabled="true"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label>案例实录视频</label>
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
                                            <a href="${file5}" class="btn btn-default"
                                               style="margin-top: 10px;" target="_blank">实录信息表
                                            </a>
                                        </div>
                                        <div class="form-group">
                                            <label>案例实录视频评分表（占比为 30%）</label>
                                            <div>
                                                <p style="color: red;margin-bottom: 10px"><span id="error_msg_video" style="display: none"></span></p>
                                            </div>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>教学理念</strong></span>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">1、教学理念符合学科专业与课程要求，体现立德树人思想和“学生中心、产出导向、持续改进”的教育教学理念。</p>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="videoScore1" required="true" type="number" name="videoScore1"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>教学内容</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、具备“金课”特征：高阶性、创新性、挑战性。教学内容有深度、广度，既重视基础，又反映学科前沿，渗透专业思想，使用质量高的教学资源。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、系统完整，符合认知规律；重点难点突出、逻辑合理、结构清晰。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、体现课程思政目标，促进学生家国情怀、科学与人文精神的培养。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分40分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="videoScore2" required="true" type="number" name="videoScore2"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>教学模式</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、体现教师主导、学生主体的教学宗旨。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、教学目标科学、明确，重视学生发展需要，课堂活动与教学目标具有高度一致性。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、教学组织有序，教学过程安排合理。创新教学方法与策略，调动学生积极性，师生互动良好。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">4、合理有效运用辅助教学手段和现代信息技术，合理使用板书与教具，合理应用专业外语，支撑教学创新。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">5、考核评价的内容和方式创新，促进学习过程。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分20分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="videoScore3" required="true" type="number" name="videoScore3"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>教学效果</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、教师学识深厚，准备充分，课堂讲授富有吸引力，课堂气氛融洽，学生思维活跃，深度参与课堂。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">2、体现学生对知识、能力与素质全面发展的要求，能够有效激发学生的学习兴趣，促进学生思考与提问，培养学生的自主学习能力。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">3、教学风格突出，教学模式新颖，效果好，具有较大的借鉴和推广价值。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分20分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="videoScore4" required="true" type="number" name="videoScore4"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>视频质量</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、教学视频清晰、流畅，能客观、真实、全面反映教师和学生的教学过程常态。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分10分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="videoScore5" required="true" type="number" name="videoScore5"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr style="margin-top: 10px;">
                                                    <td class="col-md-2" style="font-size: 16px ">
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <i class="glyphicon glyphicon-bookmark"
                                                               style="color:#8C0000"></i>
                                                            &nbsp;&nbsp;案例实录视频评分总计(100分)

                                                            <s:textfield id="videoScore" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="researchScore.videoScore"
                                                                         readonly = "true"
                                                                         required="true" type="number"/>
                                                        </p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                        <div class="form-group">
                                            <%-- <iframe src='https://view.officeapps.live.com/op/view.aspx?src=http://jpk.pku.edu.cn${researchEnroll.filePath}' width='100%' height='100%' frameborder='1'>
                                             </iframe>--%>
                                        </div>

                                        <div class="form-group">
                                            <label>申报书等材料</label>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <a href="${file1}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">申报书
                                                            </a>
                                                            <a href="${file2}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">教学大纲
                                                            </a>
                                                            <a href="${file3}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">案例概述
                                                            </a>
                                                            <a href="${file6}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">案例设计
                                                            </a>
                                                            <a href="${file7}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">案例课件(ppt)
                                                            </a>
                                                            <a href="${file8}" class="btn btn-default"
                                                               style="margin-top: 10px; float: left" target="_blank">其他材料
                                                            </a>
                                                        </p>
                                                    </td >
                                                </tr>
                                            </table>
                                            <label>申报书等材料评分表（占比为 30%）</label>
                                            <div>
                                                <p style="color: red;margin-bottom: 10px"><span id="error_msg_doc" style="display: none"></span></p>
                                            </div>
                                            <table class="table" style="width:680px">
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>教学目标及学情分析</strong></span>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">1、突出教学目标的高阶性、创新性、挑战度，强调课程思政目标与学科教学目标的创新结合，促进学生家国情怀、科学与人文精神的培养。</p>
                                                        <p style="margin:5px 18px;font-size: 15px;line-height: 18px;">（满分25分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="documentScore1" required="true" type="number" name="documentScore1"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>教学创新理念及思路</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、强调体现以学生发展为中心、以学生能力培养为目标的教学理念，对教学内容、活动、评价、方法等教学要素分析全面、透彻，与教学目标匹配度高，能够凸显课程教学创新点。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分30分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="documentScore2" required="true" type="number" name="documentScore2"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"><strong>教学创新方法及途径</strong></i></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、能够把握新时代下学生学习特点，凸显多种教学方法在学科教学中的创新实践，合理选择并充分利用现代信息技术手段开展课程教学活动和学习评价。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分25分）
                                                            <span style="float:right;">得分：
                                                                <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="documentScore3" required="true" type="number" name="documentScore3"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2" >
                                                        <span><i class="glyphicon glyphicon-check"></i><strong>教学创新效果及成果</strong></span>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">1、能够对课程教学的创新实践成效开展基于证据的有效分析与总结，形成具有较强辐射推广价值的教学新方法、新模式。</p>
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">（满分20分）
                                                            <span style="float:right;">得分：
                                                               <s:textfield style="height:24px; width:70px; float:right;" class="form-control" id="documentScore4" required="true" type="number" name="documentScore4"/>
                                                            </span>
                                                        </p>
                                                    </td>
                                                </tr>
                                                <tr style="margin-top: 10px;">
                                                    <td class="col-md-2" style="font-size: 16px ">
                                                        <p style="margin:6px 18px;font-size: 15px;line-height: 18px;">
                                                            <i class="glyphicon glyphicon-bookmark"
                                                               style="color:#8C0000"></i>
                                                            &nbsp;&nbsp;申报书等材料评分总计(100分)

                                                            <s:textfield id="documentScore" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="researchScore.documentScore"
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
                                                            &nbsp;&nbsp;最终网评成绩(60分)：

                                                            <s:textfield id="score" style="height:24px; width:70px; float:right;" class="form-control"
                                                                         name="researchScore.score"
                                                                         readonly = "true"
                                                                         required="true" type="number"/>
                                                        </p>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <div class="form-group">
                                            <label>专家评语</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textarea class="form-control" name="researchScore.comment"
                                                            required="true" maxlength="500"
                                                            style="min-height:180px; border:2px solid;" id="comment"/>
                                            </div>
                                        </div>


                                    </div>
                                    <br>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison"
                                                onclick="validate_researchScoreForm()">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 提 交
                                        </button>
                                        <button type="reset" class="btn blue-madison">
                                            <i class="glyphicon glyphicon-remove"></i>
                                            &nbsp; 取 消
                                        </button>
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
