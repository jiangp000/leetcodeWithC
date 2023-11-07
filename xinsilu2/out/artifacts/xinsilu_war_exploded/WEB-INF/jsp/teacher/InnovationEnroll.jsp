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
    });
  </script>
</head>
<body>

<%@ include file="common/head.jsp" %>

<div class="list_box">
  <div class="list_bor">
    <div class="site_nav w_980">
      你现在的位置：<a href="index">首页</a> &gt;
      <a href="InnovationEnroll.action">创新大赛提交</a>
    </div>
    <div class="w_986">
      <%@ include file="sider.jsp" %>
      <div class="list_main f_r">

        <div class="list_plate_bg main_top"></div>

        <div class="content list_plate_bg" style="width: 100%;">
          <div class="list_content">
            <div class="portlet-body form">
              <form action="submitInnovationEnroll" id="researchEnrollForm"
                    enctype="multipart/form-data"
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
                                     required="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>教师姓名</label>
                      <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                        <s:textfield class="form-control" name="researchEnroll.username"
                                     required="true"/>
                      </div>
                    </div>

                    <!-- 2021年10月02日 新添加字段 -->
                    <div class="form-group">
                      <label>项目编号</label>
                      <p style="color: #6C6C6C;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;仅2021年立项项目须填写编号；例如：2021YB001 ；</p>
                      <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-user"></i>
                                            </span>
                        <s:textfield class="form-control" name="researchEnroll.projectNo"
                                     required="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>参赛课程名</label>
                      <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                        <s:textfield class="form-control" name="researchEnroll.title"
                                     required="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>申报书(带签名及公章的扫描件)</label>
                      <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_申报书.pdf</p>
                      <div class="input-group">
                        <s:file name="uploadFile" id="uploadFile0"
                                required="true" data-show-caption="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>教学大纲</label>
                      <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_教学大纲.pdf</p>
                      <div class="input-group">
                        <s:file name="uploadFile" id="uploadFile1"
                                required="true" data-show-caption="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>案例概述</label>
                      <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_案例概述.pdf</p>
                      <div class="input-group">
                        <s:file name="uploadFile" id="uploadFile2"
                                required="true" data-show-caption="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>案例实录</label>
                      <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_授课内容.mp4</p>
                      <div class="input-group">
                        <s:file name="uploadFile" id="uploadFile3"
                                required="true" data-show-caption="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>实录信息表</label>
                      <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_实录信息表.pdf</p>
                      <div class="input-group">
                        <s:file name="uploadFile" id="uploadFile4"
                                required="true" data-show-caption="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>案例设计</label>
                      <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_案例设计.pdf</p>
                      <div class="input-group">
                        <s:file name="uploadFile" id="uploadFile5"
                                required="true" data-show-caption="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>案例课件(pptx)</label>
                      <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_课件.pptx</p>
                      <div class="input-group">
                        <s:file name="uploadFile" id="uploadFile6"
                                required="true" data-show-caption="true"/>
                      </div>
                    </div>

                    <div class="form-group">
                      <label>其他材料</label>
                      <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课程名_主讲教师_其他材料.zip</p>
                      <div class="input-group">
                        <s:file name="uploadFile" id="uploadFile7"
                                required="false" data-show-caption="true"/>
                      </div>
                    </div>

                    <%--                                        <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;上传文件命名格式:--%>
                    <%--                                            工号/学号_材料类型（申报书、课程案例、证明材料等）_参赛课程名_姓名</p>--%>

                  </div>
                  <br>
                  <div>
                    <p style="color: red;margin-bottom: 10px"><span id="error_msg"
                                                                    style="display: none"></span>
                    </p>
                  </div>
                  <p style="color: firebrick;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                  <p style="color: firebrick;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;除授课内容视频和pptx课件，其余上传文件提交类型均为pdf</p>
                  <div style="margin-top: 10px; margin-bottom: 10px; text-align: center">
                    <button type="submit"
                            class="btn blue-madison"
                            onclick="return validate_enroll_exist()">
                      <i class="glyphicon glyphicon-ok"></i>
                      &nbsp; 提 交
                    </button>
                    <%--                                        <button type="submit" class="btn blue-madison"--%>
                    <%--                                                onclick="window.location.href='http://jpk.pku.edu.cn/xinsilu2/index.action'">--%>
                    <%--                                            <i class="glyphicon glyphicon-ok"></i>--%>
                    <%--                                            &nbsp; 提 交--%>
                    <%--                                        </button>--%>
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
