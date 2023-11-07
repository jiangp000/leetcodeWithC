<%--
  Created by IntelliJ IDEA.
  User: husky
  Date: 2020/12/15
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
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
        $(document).ready(function () {
            $("input").change(function () {
                var sum = 0;
                for (i = 1; i < 5; i++) {
                    var id = "#value" + i;
                    var value = $(id).val();
                    if (!isNaN(value)) {
                        sum = sum + Number(value);
                    } else {
                        $("#error_msg").text("请输入数字!");
                        $("#error_msg").show();
                        return
                    }
                }
                if (sum > 100 || sum < 0) {
                    $("#error_msg").text("总分需为0-100之间的整数!");
                    $("#error_msg").show();
                    return
                } else {
                    $("#score").val(sum);
                }
            });
        });
    </script>
</head>
<body>

<%@ include file="common/head.jsp" %>

<div class="list_box">
    <div class="list_bor">
        <div class="w_986">
            <%@ include file="sider.jsp" %>
            <div class="list_main f_r">
                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitJudgeScore?projectScore.projectEnrollId=${projectEnroll.id}"
                                  id="judgeScoreForm" enctype="multipart/form-data"
                                  method="post">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label>工号/学号</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="projectEnroll.userId"
                                                             disabled="true"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label>姓名</label>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                                                <s:textfield class="form-control" name="projectEnroll.username"
                                                             disabled="true"/>
                                            </div>
                                        </div>

                                        <c:if test="${! empty projectEnroll.userCV}">
                                            <div class="form-group">
                                                <label>个人简介</label>
                                                <p style="color: #6C6C6C;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;不超过150字。需包含工号/学号、姓名、性别、出生年月、学历学位、
                                                    工作单位、职务职称、主要成果、研究方向、通讯地址、联系电话、E-mail等信息。</p>
                                                <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="glyphicon glyphicon-tag"></i>
                                                </span>
                                                    <s:textarea class="form-control" name="projectEnroll.userCV"
                                                                maxlength="500"
                                                                style="min-height:200px; border:2px solid;"
                                                                disabled="true"/>
                                                </div>
                                            </div>
                                        </c:if>
                                        <div class="form-group">
                                            <%--<div class="input-group">--%>
                                            <%--<span class="input-group-addon">--%>
                                            <%--<i class="glyphicon glyphicon-tag"></i>--%>
                                            <%--</span>--%>
                                            <%--<s:textfield class="form-control" name="projectEnroll.title"--%>
                                            <%--disabled="true"/>--%>
                                            <%--</div>--%>
                                            <a href="${projectEnroll.filePath}" class="btn btn-default"
                                               style="margin-top: 10px;" target="_blank">浏览项目申请报告</a>
                                        </div>
                                        <h5 class="page-header"></h5>

                                        <div class="form-group">
                                            <label>评分</label>
                                            <div>
                                                <p style="color: red;margin-bottom: 10px"><span id="error_msg" style="display: none"></span></p>
                                            </div>
                                            <table class="table" style="width:1450px">
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i>1.教学目标与学情分析</span>
                                                        <p>1.1阐述了学习目标如何符合本校办学定位和专业人才培养目标，价值塑造、知识传授、能力培养是如何有机融合的。（5）</p>
                                                        <p>1.2分层描述的学习目标（课程教学目标-所选教学单元的教学目标）准确具体，逻辑关系清晰，体现了可以如何通过考核来检验教学的有效性。（5）</p>
                                                        <p>1.3基于目标对学情进行了恰当分析，明确阐述了所选教学单元采用混合式教学改革需要解决的重点问题。（5）</p>
                                                        <p>（满分15分）</p>
                                                    </td>
                                                    <td class="col-md-4">
                                                        <span>得分：<s:textfield style="height:24px" class="form-control" id="value1" required="true" type="number"/></span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check">2.教学理念与过程设计</i></span>
                                                        <p>2.1教为中心向学为中心转变，阐述了引导学生达成学习目标的教学理念和教学路径，线上、线下教学活动互补，充分体现混合式教学优势。（8）</p>
                                                            <p>2.2阐述了教学中使用各类教学资源的教学设计理由，体现了多种媒体使用的恰当性、合理性和必要性，优先选择国家级和省级精品在线开放课程等高质量在线课程资源（混合式课程），结合本校实际重塑课程内容。（10）</p>
                                                        <p>2.3体现了前沿性与时代性，聚焦新工科、新医科、新农科、新文科建设，体现了多学科思维融合、产学融合、跨专业能力融合、多学科项目实践融合。（7）</p>
                                                        <p>2.4 加强研究型、项目型学习，注重培养学生解决问题、思辨等高阶思维能力。合理分配课内、课外教学活动，学生学习负担适度。（10）</p>
                                                        <p>（满分35分）</p>
                                                    </td>
                                                    <td class="col-md-4">
                                                        <span>得分：<s:textfield style="height:24px" class="form-control" id="value2" required="true" type="number"/></span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check">3.教学实施与效果评价</i></span>
                                                        <p>3.1运用适当的数字化教学工具，创新方式方法，有证据证明有效地开展了线上与线下密切衔接的全过程教学活动。（8）</p>
                                                        <p>3.2有证据显示多途径提供及时的学习活动支持，增进师生互动、生生互动，包括学习引导、答疑互动等。（5）</p>
                                                        <p>3.3评价方式多元，评价手段契合人才培养目标，强化探究式、论文式、报告答辩式等作业评价方式，加强非标准化、综合性等评价。评价严格，具有清晰、合理的学习评价量规。（7）</p>
                                                        <p>3.4线上、线下学习评价连贯完整，过程可回溯，诊断反馈及时，有证据证明对学习改进积极有效。（5）</p>
                                                        <p>（满分25分）</p>
                                                    </td>
                                                    <td class="col-md-4">
                                                        <span>得分：<s:textfield style="height:24px" class="form-control" id="value3" required="true" type="number"/></span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2">
                                                        <span><i class="glyphicon glyphicon-check"></i>4.教学特色与创新</span>
                                                            <p>4.1专业知识教育与思想政治教育的紧密结合，充分挖掘思想政治教育元素，内容、过程和评价等方面有科学有效的课程思政落实方案。	（5）</p>
                                                            <p>4.2有效达成教学目标，具有提升学生专业忠诚度、课程参与度、学习获得感、课程及教师教学满意度的独特做法，并提供科学合理的佐证。	（8）</p>
                                                            <p>4.3有意识地收集数据开展基于数据的教学反思、教学研究和教学改进，有效解决了教改重点问题。	（7）</p>
                                                            <p>4.4课程建设理念具有先进性、创新性，已经在同行中产生了影响，对推进相应类型课程教学改革及教学效果提升具有推广价值。	（5）</p>
                                                            <p>（满分25分）
                                                    </td>
                                                    <td class="col-md-4">
                                                        <span>得分：<s:textfield style="height:24px" class="form-control" id="value4" required="true" type="number"/></span>
                                                    </td>
                                                </tr>
                                                <tr style="margin-top: 10px;">
                                                    <td class="col-md-2" style="font-size: 16px ">
                                                        <i class="glyphicon glyphicon-bookmark"
                                                           style="color:#8C0000"></i>
                                                        &nbsp;&nbsp;总计(100分)
                                                    </td>
                                                    <td class="col-md-4">
                                                        <s:textfield id="score" style="height:24px" class="form-control"
                                                                     name="judgeScore.score"
                                                                     required="true" type="number"/>
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
                                                <s:textarea class="form-control" name="projectScore.comment"
                                                            required="true" maxlength="500"
                                                            style="min-height:200px; border:2px solid;" id="comment"/>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                    <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                                        <button type="submit" class="btn blue-madison"
                                                onclick="return validate_projectScoreForm()">
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
