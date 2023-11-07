<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2023/3/28
  Time: 22:56
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
    <script type="text/javascript" src="../js/xlsx.js"></script>
    <script type="text/javascript" src="../js/xls.js"></script>

    <style>
        .head_logo_nav .head_nav .head_nav_ul li.li_disabled {
            float: left;
            *display: inline;
            height: 24px;
            line-height: 24px;
            margin: 0px 60px 0px 0px;
            position: relative;
            opacity: 0.6;
        }
        .head_nav_ul li.li_disabled a {
            height: 24px;
            float: left;
            color: gray;
            text-decoration: none;
        }
        .head_nav_ul li.li_disabled a span {
            font-size: 18px;
            float: left;
            height: 24px;
            cursor: pointer;
            padding: 0;
            margin: 0;
            background: #FFF;
            cursor: default;
        }
        table,td,th{
            border: 1px solid #000000;
            border-collapse: collapse;
        }
    </style>
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
                            <a href="uploadInnoScoreTableJSP" id="column_8" class="selected">
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
                        <form action="uploadInnoScoreTable" id="uploadScoreForm" method="post">
                            <div>
                                <fieldset>
<%--                                    <input type="file" id="uploadScoreFile" style="display:none;" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">--%>
                                    <input type="file" id="uploadScoreFile" style="display:none;" accept=".xlsx,.xls">
                                    <c:if test="${scoreStageControl=='2'}">
                                        <a href="javascript:selectFile()" id="uploadScoreButton" class="btn btn-default">上传创新大赛网络评分表</a>
                                    </c:if>
                                    <c:if test="${scoreStageControl=='3'}">
                                        <a href="javascript:selectFile()" id="uploadScoreButton" class="btn btn-default">上传创新大赛现场评分表</a>
                                    </c:if>
                                    <div id="scoreResult" contenteditable></div>
                                    <s:textfield type="text" id="jsonStr" name="jsonStr" style="display:none"/>
                                    <s:textfield type="text" id="comment" name="comment" style="display:none"/>

                                    <div style="">
                                        <button type="submit" class="btn blue-madison" id="submitId">
                                            <i class="glyphicon glyphicon-ok"></i>
                                            &nbsp; 提 交
                                        </button>
                                        <!--<button type="reset" class="btn blue-madison">
                                            <i class="glyphicon glyphicon-remove"></i>
                                            &nbsp; 取 消
                                        </button>-->
                                    </div>
                                </fieldset>
                            </div>
                        </form>
                        <s:textfield id="scoreStageControl" style="height:24px; width:70px;
                                                                        float:right;" class="form-control" name="scoreStageControl"
                                     readonly = "true"
                                     required="true" type="hidden"/>
                    </div>
                </div>

                <div class="list_plate_bg main_bot"></div>

            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>

<script>
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
    function selectFile(){
        document.getElementById("uploadScoreFile").click();
    }
    function readWorkbookFromLocalFile(file,callback){
        let reader = new FileReader();
        reader.onload=function(e){
            let data = e.target.result;
            let workbook = "";
            if(file.type=="application/vnd.ms-excel"){
                // xls
                workbook = XLS.read(data,{type:"binary"});
            }else{
                // xlsx
                workbook = XLSX.read(data,{type:"binary"});
            }
            if(callback) callback(workbook);
        };
        reader.readAsBinaryString(file);
    }
    function readWorkbook(workbook){
        let sheetNames = workbook.SheetNames;
        let worksheet = workbook.Sheets[sheetNames[0]];
        let csv = XLSX.utils.sheet_to_csv(worksheet);
        document.getElementById("scoreResult").innerHTML = csv2table(csv);
        let scoreStage = $("#scoreStageControl").val();
        if(scoreStage=="2"){
            document.getElementById("jsonStr").value = uploadPreScores(csv);
        }else if(scoreStage=="3"){
            document.getElementById("jsonStr").value = uploadPPTScores(csv);
        }
    }
    function csv2table(csv){
        let html = "<table>";
        let rows = csv.split("\n");
        rows.forEach((row,idx)=>{
            let columns = row.split(',');
            columns.unshift(idx+1);
            if(idx==0){
                html+="<tr>";
                for(let i = 0; i < columns.length;i++){
                    html+="<th>"+(i==0?"":String.fromCharCode(65+i-1))+"</th>";
                }
                html+="</tr>";
            }
            html+="<tr>";
            columns.forEach((column)=>{
                html+="<td>"+column+"</td>";
            });
            html+="</tr>";
        });
        html+="</table>";
        return html;
    }

    // 上传网评评分表
    function uploadPreScores(csv){
        let scoreMap = {};
        let rows = csv.split("\n");
        rows.forEach((row,idx)=>{
            let columns = row.split(',');
            columns.unshift(idx+1);
            if(idx===0) return;
            let scores = [];
            let user_id = columns[7];
            for(let i = 0; i < 5; i++){
                scores.push(columns[i+12]);
            }
            for(let i = 0; i < 4; i++){
                scores.push(columns[i+18]);
            }
            let comment = columns[25];
            let project_id = columns[26];
            scoreMap[user_id]={"scores":scores,"comment":comment,"id":project_id};
        });
        console.log(scoreMap);
        return JSON.stringify(scoreMap);
    }

    // 上传现评评分表
    function uploadPPTScores(csv){
        let scoreMap = {};
        let rows = csv.split("\n");
        rows.forEach((row,idx)=>{
            let columns = row.split(',');
            columns.unshift(idx+1);
            if(idx===0) return;
            let scores = [];
            let user_id = columns[7];
            for(let i = 0; i < 5; i++){
                scores.push(columns[i+12]);
            }
            let comment = columns[19];
            let project_id = columns[20];
            scoreMap[user_id]={"scores":scores,"comment":comment,"id":project_id};
        });
        console.log(scoreMap);
        return JSON.stringify(scoreMap);
    }

    $(()=>{
        document.getElementById("uploadScoreFile").addEventListener("change",(e)=>{
            let files = e.target.files;
            if(files.length==0)return;
            let f = files[0];
            document.getElementById("uploadScoreButton").style.display="none";
            readWorkbookFromLocalFile(f,(workbook)=>{readWorkbook(workbook)});
            //document.getElementById("submitId").click();
            alert("上传评分表成功！");
        });
    })
</script>


<%@ include file="common/footer.jsp" %>

</body>
</html>
