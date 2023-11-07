<%@ page import="action.research.ResearchAction" %>
<%@ page import="domain.ResearchEnroll" %><%--
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

<%
//    String userId = ((String) session.getAttribute("teacher"));
//    String name = ((String) session.getAttribute("name"));
//    String fileType = "创新大赛";
//    String fileRootPath = "researchEnroll/2023/创新大赛/" + userId + "-" + name + "/";
//    String fileNamePrefix = userId + "-" + name + "_";
//    String path0 = fileRootPath + fileNamePrefix + "申报书.docx";
//    String path1 = fileRootPath + fileNamePrefix + "申报书.pdf";
//    String path2 = fileRootPath + fileNamePrefix + "创新成果报告.pdf";
//    String path3 = fileRootPath + fileNamePrefix + "创新成果支撑材料目录.pdf";
//    String path4 = fileRootPath + fileNamePrefix + "案例视频1.mp4";
//    String path5 = fileRootPath + fileNamePrefix + "案例视频2.mp4";
//    String path6 = fileRootPath + fileNamePrefix + "视频信息表.pdf";
//    String path7 = fileRootPath + fileNamePrefix + "案例设计.pdf";
//    String path8 = fileRootPath + fileNamePrefix + "案例课件.pptx";
//    String path9 = fileRootPath + fileNamePrefix + "案例课件.pdf";
//    String path10 = fileRootPath + fileNamePrefix + "教学大纲.pdf";
//    String path11 = fileRootPath + fileNamePrefix + "相关材料1.pdf";
//    String path12 = fileRootPath + fileNamePrefix + "相关材料2.pdf";
//    String path13 = fileRootPath + fileNamePrefix + "相关材料3.zip";
//    String path14 = fileRootPath + fileNamePrefix + "创新大赛现场汇报.pptx";
//    String path15 = fileRootPath + fileNamePrefix + "创新大赛现场汇报.pdf";

    String fileType = "创新大赛";
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
    String path10 = ResearchAction.getFilePathByIndex(10,fileType);
    path10 = path10.replace("\\","/");
    String path11 = ResearchAction.getFilePathByIndex(11,fileType);
    path11 = path11.replace("\\","/");
    String path12 = ResearchAction.getFilePathByIndex(12,fileType);
    path12 = path12.replace("\\","/");
    String path13 = ResearchAction.getFilePathByIndex(13,fileType);
    path13 = path13.replace("\\","/");
    String path14 = ResearchAction.getFilePathByIndex(14,fileType);
    path14 = path14.replace("\\","/");
    String path15 = ResearchAction.getFilePathByIndex(15,fileType);
    path15 = path15.replace("\\","/");
    String path16 = ResearchAction.getFilePathByIndex(16,fileType);
    path16 = path16.replace("\\","/");
%>

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
            initFileInput("uploadFile0"); //
            initFileInput("uploadFile1");
            initFileInput("uploadFile2");
            initFileInput("uploadFile3");
            initFileInput("uploadFile4");
            initFileInput("uploadFile5");
            initFileInput("uploadFile6");
            initFileInput("uploadFile7");
            initFileInput("uploadFile8");
            initFileInput("uploadFile9");
            initFileInput("uploadFile10");
            initFileInput("uploadFile11");
            initFileInput("uploadFile12");
            initFileInput("uploadFile13");
            initFileInput("uploadFile14");
            initFileInput("uploadFile15");
            initFileInput("uploadFile16");
            $(".li_nav span:contains('创新大赛')").attr("style", "font-weight:800");
            $(".delayBlock").attr("style", "display: none;");
            $("#continueSubmitButton").attr("style", "display:none ;")
            // document.getElementById("caseSort").value = "正常";
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

        $(document).ready(function () {

            $("#uploadFile0").on("filebatchselected", function () {
                var id = "#uploadFile0";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if((ext != "docx" )){
                    $(".fileinput-remove-button:eq(0)").click();
                    alert("文件命名格式为 教师号_教师名_申报书.docx,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile1").on("filebatchselected", function () {
                var id = "#uploadFile1";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" ){
                    $(".fileinput-remove-button:eq(1)").click();
                    alert("文件命名格式为 教师号_教师名_申报书.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile2").on("filebatchselected", function () {
                var id = "#uploadFile2";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" ){
                    $(".fileinput-remove-button:eq(2)").click();
                    alert("文件命名格式为 教师号_教师名_创新报告.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile3").on("filebatchselected", function () {
                var id = "#uploadFile3";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" ){
                    $(".fileinput-remove-button:eq(3)").click();
                    alert("文件命名格式为 教师号_教师名_创新成果支撑材料目录.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile4").on("filebatchselected", function () {
                var id = "#uploadFile4";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext!="mp4"){
                    $(".fileinput-remove-button:eq(4)").click();
                    alert("文件命名格式为 教师号_教师名_案例视频1.mp4,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile5").on("filebatchselected", function () {
                var id = "#uploadFile5";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext!="mp4"){
                    $(".fileinput-remove-button:eq(5)").click();
                    alert("文件命名格式为 教师号_教师名_案例视频2.mp4,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile6").on("filebatchselected", function () {
                var id = "#uploadFile6";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" ){
                    $(".fileinput-remove-button:eq(6)").click();
                    alert("文件命名格式为 教师号_教师名_视频信息表.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile7").on("filebatchselected", function () {
                var id = "#uploadFile7";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" ){
                    $(".fileinput-remove-button:eq(7)").click();
                    alert("文件命名格式为 教师号_教师名_案例设计.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile8").on("filebatchselected", function () {
                var id = "#uploadFile8";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if( ext != "pptx" ){
                    $(".fileinput-remove-button:eq(8)").click();
                    alert("文件命名格式为 教师号_教师名_案例课件.pptx,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile9").on("filebatchselected", function () {
                var id = "#uploadFile9";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf" ){
                    $(".fileinput-remove-button:eq(9)").click();
                    alert("文件命名格式为 教师号_教师名_案例课件.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile10").on("filebatchselected", function () {
                var id = "#uploadFile10";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext!="pdf"){
                    $(".fileinput-remove-button:eq(10)").click();
                    alert("文件命名格式为 教师号_教师名_教学大纲.pdf,请重新上传正确的文件命名格式!");
                    return false;
                }
            })

            $("#uploadFile11").on("filebatchselected", function () {
                var id = "#uploadFile11";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf"){
                    $(".fileinput-remove-button:eq(11)").click();
                    alert("文件格式为 pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile12").on("filebatchselected", function () {
                var id = "#uploadFile12";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf"){
                    $(".fileinput-remove-button:eq(12)").click();
                    alert("文件格式为 pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile13").on("filebatchselected", function () {
                var id = "#uploadFile13";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "zip" ){
                    $(".fileinput-remove-button:eq(13)").click();
                    alert("文件格式为 zip,请重新上传正确的文件格式!");
                    return false;
                }
            })

            $("#uploadFile14").on("filebatchselected", function () {
                var id = "#uploadFile14";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pptx"){
                    $(".fileinput-remove-button:eq(14)").click();
                    alert("文件命名格式为 教师号_教师名_现场汇报.pptx,请重新上传正确的文件格式!");
                    return false;
                }
            })
            $("#uploadFile15").on("filebatchselected", function () {
                var id = "#uploadFile15";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf"){
                    $(".fileinput-remove-button:eq(15)").click();
                    alert("文件命名格式为 教师号_教师名_现场汇报.pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })


            $("#uploadFile16").on("filebatchselected", function () {
                var id = "#uploadFile16";
                var filePath = $(id).val();
                var index = filePath.lastIndexOf(".");
                var ext = filePath.substr(index+1);
                if(ext != "pdf"){
                    $(".fileinput-remove-button:eq(15)").click();
                    alert("文件格式为 pdf,请重新上传正确的文件格式!");
                    return false;
                }
            })

            // $(".uploadFiles").on("change", function (){
            //     $("#submitId").prop('disabled', false);
            //     $("#saveId").prop('disabled', false);
            // })

            $("#innoDelayId").on('change', function() {
                delaySortProcess();
            })

        });


        function delaySortProcess(){
            // 如果选择了延期申请这一选项
            if ($("#innoDelayId").val() == "是") {
                // 不显示
                $(".basic-info").attr("style", "display:none ;");
                $(".delayBlock").attr("style", "display: ;");
                $("#uploadFile0").attr('required', false);
                $("#uploadFile1").attr('required', false);
                $("#uploadFile0").attr('required', false);
                $("#uploadFile1").attr('required', false);
                $("#uploadFile2").attr('required', false);
                $("#uploadFile3").attr('required', false);
                $("#uploadFile4").attr('required', false);
                $("#uploadFile6").attr('required', false);
                $("#uploadFile7").attr('required', false);
                $("#uploadFile8").attr('required', false);
                $("#uploadFile9").attr('required', false);
                $("#uploadFile10").attr('required', false);
                $("#uploadFile16").attr('required', 'true');
                $("#userCVId").attr('required', false);

                $("#titleId").attr('required', false);
                $("#researhGroupId").attr('required', false);
                $("#researhSortId").attr('required', false);
                $("#projectApplySortId").attr('required', false);
                $("#projectNoId").attr('required', false);
                $("#projectTitleId").attr('required', false);
            } else { // 如果不延期申请
                // 隐藏
                $(".basic-info").attr("style", "display: ;");
                $(".delayBlock").attr("style", "display: none;");
                $("#uploadFile0").attr('required','true');
                $("#uploadFile1").attr('required','true');
                $("#uploadFile2").attr('required','true');
                $("#uploadFile3").attr('required','true');
                $("#uploadFile4").attr('required','true');
                $("#uploadFile6").attr('required','true');
                $("#uploadFile7").attr('required','true');
                $("#uploadFile8").attr('required','true');
                $("#uploadFile9").attr('required','true');
                $("#uploadFile16").attr('required', false);
                $("#userCVId").attr('required', true);
                $("#continueSubmitButton").attr("style", "display:none ;")

                $("#titleId").attr('required','true');
                $("#researhGroupId").attr('required','true');
                $("#researhSortId").attr('required','true');
                $("#projectApplySortId").attr('required','true');
                $("#projectNoId").attr('required', 'true');
                $("#projectTitleId").attr('required', 'true');
            }
        }

        $(function(){
            if($("#submitSave").val() == 1&&$("#submitStageControl").val()==1) {
                $("#submitId").attr('disabled','true');
                $("#saveId").attr('disabled','true');
            }else if($("#submitSave").val() == 2&&$("#submitStageControl").val()==2){
                $("#submitId").attr('disabled','true');
                $("#saveId").attr('disabled','true');
            }
        })
        $(function(){
            if($("#submitStageControl").val() == 0){
                //关闭状态
                document.getElementById("saveId").style.display="none";
                document.getElementById("submitId").style.display="none";
                //document.getElementById("uploadPptId").style.display="none";
            }else if($("#submitStageControl").val() == 1) {
                //提交资料
                document.getElementById("uploadPptId").style.display="none";
                $("#uploadFile0").attr('required','true');
                $("#uploadFile1").attr('required','true');
                $("#uploadFile2").attr('required','true');
                $("#uploadFile3").attr('required','true');
                $("#uploadFile4").attr('required','true');
                $("#uploadFile6").attr('required','true');
                $("#uploadFile7").attr('required','true');
                $("#uploadFile8").attr('required','true');
                $("#uploadFile9").attr('required','true');
            }else if($("#submitStageControl").val() == 2){
                //提交现场汇报PPT
                $("#innoDelayId").attr('disabled', 'true');
                $("#researchEnroll_userId").attr('readonly','true');
                $("#researchEnroll_username").attr('readonly','true');
                $("#deptId").attr('disabled','true');
                $("#researhGroupId").attr('disabled','true');
                $("#researhSortId").attr('disabled','true');
                $("#projectApplySortId").attr('disabled','true');
                $("#userTitleId").attr('readonly','true');
                $("#userPhoneId").attr('readonly','true');
                $("#userMailId").attr('readonly','true');
                $("#titleId").attr('readonly','true');
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
                $("#uploadFile6").attr('disabled','true');
                $("#uploadFile7").attr('disabled','true');
                $("#uploadFile8").attr('disabled','true');
                $("#uploadFile9").attr('disabled','true');
                $("#uploadFile10").attr('disabled','true');
                $("#uploadFile11").attr('disabled','true');
                $("#uploadFile12").attr('disabled','true');
                $("#uploadFile13").attr('disabled','true');
                $("#userCVId").attr('readonly','true');

                $("#uploadFile14").attr('required','true');
                $("#uploadFile15").attr('required','true');

                $("#uploadFile16").attr('disabled','true');
            }
        })

        $(function (){
            if ($("#researhSortId").val() == "课程案例"){
                $("#uploadFile10").attr('required', 'true');
            }
        })

        $(function (){
            $("#researhSortId").on('change', function() {
                if ($("#researhSortId").val() == "课程案例"){
                    $("#uploadFile10").attr('required', 'true');
                }
                else
                    $("#uploadFile10").attr('required', false);
            })
        })


        /*$(function(){
            $("#saveId").attr('disabled','true');
        })*/
        $(document).ready(function () {
            $("#submitId").click(function (){
                if($("#submitStageControl").val()==2){
                    $("#submitSave").val("2");
                }
                if($("#submitStageControl").val()==1){
                    $("#submitSave").val("1");
                }
            })
        })

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
                case 10: file_path = "<%= path10%>"; break;
                case 11: file_path = "<%= path11%>"; break;
                case 12: file_path = "<%= path12%>"; break;
                case 13: file_path = "<%= path13%>"; break;
                case 14: file_path = "<%= path14%>"; break;
                case 15: file_path = "<%= path15%>"; break;
                case 16: file_path = "<%= path16%>"; break;
            }// 对应index找不到文件 直接return
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
            if($("#submitStageControl").val()==2&&index<14){
                deleteButton.setAttribute("style","display:none");
            }


            deleteButton.onclick=(()=>{
                if(!confirm("是否删除该文件？")) return;
                // $("#submitId").prop('disabled', false);
                // $("#saveId").prop('disabled', false);

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
            console.log(fileName);
            console.log(fileType);
            indexMap[index] = {"name":fileName,"type":fileType};
        }

        $(()=>{
            for(let i=0;i<17;i++){
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
            for(let i=0;i<17;i++){
                getUploadedFileOrder(i,indexMap);
            }
            let fileInfoStr = JSON.stringify(indexMap);
            // alert(fileInfoStr)
            document.getElementById("filesNewName").value = fileInfoStr;
            return validate_enroll_exist();
        }

        function continueSubmit(){
            $(".basic-info").attr("style", "display: ;");
            $(".delayBlock").attr("style", "display: none;");
            $("#uploadFile0").attr('required','true');
            $("#uploadFile1").attr('required','true');
            $("#uploadFile2").attr('required','true');
            $("#uploadFile3").attr('required','true');
            $("#uploadFile4").attr('required','true');
            $("#uploadFile6").attr('required','true');
            $("#uploadFile7").attr('required','true');
            $("#uploadFile8").attr('required','true');
            $("#uploadFile9").attr('required','true');
            $("#uploadFile16").attr('required', false);
            $("#userCVId").attr('required', true);

            $("#submitId").prop('disabled', false);
            $("#saveId").prop('disabled', false);

            $("#continueSubmitButton").attr("style", "display:none ;")
        }
    </script>
</head>
<body>

<%@ include file="../teacher/common/head.jsp" %>


<div class="list_box">
    <div class="list_bor">
        <div class="site_nav w_980">
            你现在的位置：<a href="index">首页</a> &gt;
            <a href="innovationResearchEnroll.action">创新大赛提交</a>
        </div>
        <div class="w_986">
            <%@ include file="siderInnovation.jsp" %>
            <div class="list_main f_r">

                <div class="list_plate_bg main_top"></div>

                <div class="content list_plate_bg" style="width: 100%;">
                    <div class="list_content">
                        <div class="portlet-body form">
                            <form action="submitInnoPPTResearchEnroll" id="researchEnrollForm"
                                  enctype="multipart/form-data"
                                  method="post" onsubmit="submitForm()">
                                <fieldset>
                                    <div class="form-body">
                                        <div class="form-group">
                                            <table class="table" style="width:680px">
                                                <s:textfield type="text" id="filesNewName" name="filesNewName" style="display:none"/>
                                                <label>项目信息 <span class="sign_must">*</span></label>
                                                <tr>
                                                    <td class="col-md-2" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                大赛延期
                                                            </span>
                                                            <select class="selectpicker form-control" name="delaySort" id="innoDelayId" value="delaySort">
                                                                <c:forEach var="item" items="${delaySorts}">
                                                                    <c:choose>
                                                                        <c:when test="${delaySort==item}">
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
                                                    <td class="col-md-2 basic-info" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                案例类型
                                                            </span>
                                                            <select class="selectpicker form-control" name="caseSort" required="true" id="researhSortId" value="caseSort">
                                                                <c:forEach var="item" items="${caseSorts}">
                                                                    <c:choose>
                                                                        <c:when test="${caseSort==item}">
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
                                                    <td class="col-md-2 basic-info" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                案例分组
                                                            </span>
                                                            <select class="selectpicker form-control" name="projectSort" required="true"
                                                                    value="projectSort" id="researhGroupId">
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
                                                            </select>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2 basic-info" colspan="3">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                参赛案例名
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.title" id="titleId"
                                                                         required="true"/>
                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="col-md-2 basic-info" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目号
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectNo" id="projectNoId"
                                                                         required="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2 basic-info" colspan="1">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目类型
                                                            </span>
                                                            <select class="selectpicker form-control" name="projectApplySort" id="projectApplySortId"
                                                                    required="true" value="projectApplySort">
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
                                                                <%--<option value ="重点">重点</option>
                                                                <option value ="优先">优先</option>
                                                                <option value="一般">一般</option>
                                                                <option value="其他">其他</option>--%>
                                                            </select>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2 basic-info" colspan="2">
                                                        <div class="input-group">
                                                            <span class="input-group-addon">
                                                                项目名称
                                                            </span>
                                                            <s:textfield class="form-control" name="researchEnroll.projectTitle" id="projectTitleId"
                                                                         required="true"/>
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
                                                            <s:textfield class="form-control" name="researchEnroll.userId"
                                                                         value="%{#session.teacher}" required="true"/>
                                                        </div>
                                                    </td>
                                                    <td class="col-md-2" style="width: 27%">
                                                        <div class="input-group">
                                                        <span class="input-group-addon">
                                                            负责人
                                                        </span>
                                                            <s:textfield class="form-control" name="researchEnroll.username"
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

                                        <div class="form-group basic-info">
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
                                                </div>

                                            </div>
                                            <div class="form-group" id = "group_4" >
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <span>成员四</span>
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
                                                </div>
                                            </div>
                                            <div class="input-group" >
                                                <s:textfield name="researchEnroll.teamMember"  class = "form-control u_teamMember" style="display:none;"></s:textfield>
                                            </div>

                                        </div>

                                        <div class="form-group basic-info">
                                            <label>大赛申报书 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                                            <p style="margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;大赛申报书格式:&nbsp;&nbsp;<a
                                                    href="/upload/xinsilu/files/附件1 北京大学创新教学应用大赛_申报书.docx" target="_blank">附件1大赛申报书.docx</a></p>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_申报书.docx</p>--%>
                                            <p style="color: #6C6C6C;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;申报书docx文件：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted0">
                                                <s:file name="uploadFile" id="uploadFile0" class="uploadFiles"
                                                        data-show-caption="true" accept=".docx"/>
                                            </div>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_申报书.pdf（单位签名盖章）</p>--%>
                                            <p style="color: #6C6C6C;margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;申报书pdf文件（单位签名盖章）：</p>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted1">
                                                <s:file name="uploadFile" id="uploadFile1" class="uploadFiles"
                                                        data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <%--<label>上传大赛必备材料 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">上传的mp4、zip等文件建议不超过300MB</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>--%>
                                            <label>创新成果报告 <span class="sign_must">*</span></label>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_创新报告.pdf</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted2">
                                                <s:file name="uploadFile" id="uploadFile2" class="uploadFiles"
                                                         data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <%--<label>上传大赛必备材料 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">上传的mp4、zip等文件建议不超过300MB</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>--%>
                                            <label>创新成果支撑材料目录 <span class="sign_must">*</span></label>
                                            <p style="margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;创新成果支撑材料目录格式:&nbsp;&nbsp;<a
                                                    href="/upload/xinsilu/files/附件2  北京大学创新教学应用大赛_支撑材料目录.docx" target="_blank">附件2创新成果支撑材料目录.docx</a></p>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_创新成果支撑材料目录.pdf</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted3">
                                                <s:file name="uploadFile" id="uploadFile3" class="uploadFiles"
                                                         data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>

                                        <%--<div class="form-group">
                                            <label>案例概述 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_案例概述.pdf</p>
                                            <div class="input-group">
                                                <s:file name="uploadFile" id="uploadFile3"
                                                        required="true" data-show-caption="true"/>
                                            </div>
                                        </div>--%>

                                        <div class="form-group basic-info">
                                            <label>案例视频1 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;上传的mp4文件建议不超过500MB。</p>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_案例视频1.mp4</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted4">
                                                <s:file name="uploadFile" id="uploadFile4" class="uploadFiles"
                                                         data-show-caption="true" accept=".mp4"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>案例视频2（可选） </label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;上传的mp4文件建议不超过500MB。</p>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_案例视频2.mp4</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted5">
                                                <s:file name="uploadFile" id="uploadFile5" class="uploadFiles"
                                                        data-show-caption="true" accept=".mp4"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>视频信息表 <span class="sign_must">*</span></label>
                                            <p style="margin: 10px 0 5px 0">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;视频信息表格式:&nbsp;&nbsp;<a
                                                    href="/upload/xinsilu/files/附件3  北京大学创新教学应用大赛_案例视频信息表.docx" target="_blank">附件3视频信息表.docx</a></p>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_视频信息表.pdf</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted6">
                                                <s:file name="uploadFile" id="uploadFile6" class="uploadFiles"
                                                        data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>案例设计（教案） <span class="sign_must">*</span></label>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_案例设计.pdf</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted7">
                                                <s:file name="uploadFile" id="uploadFile7" class="uploadFiles"
                                                         data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>案例课件(pptx) <span class="sign_must">*</span></label>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_案例课件.pptx</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted8">
                                                <s:file name="uploadFile" id="uploadFile8" class="uploadFiles"
                                                         data-show-caption="true" accept=".pptx"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>案例课件(pdf) <span class="sign_must">*</span></label>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_案例课件.pdf</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted9">
                                                <s:file name="uploadFile" id="uploadFile9" class="uploadFiles"
                                                        data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>


                                        <div class="form-group basic-info">
                                            <%--<label>上传大赛必备材料 <span style="color: #6C6C6C;margin-bottom: 10px;font-size: small">上传的mp4、zip等文件建议不超过300MB</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>--%>
                                            <label>教学大纲（可选，课程案例必备） </label>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;若提交该文件，请确保文件命名格式正确：教师号_教师名_教学大纲.pdf</p>--%>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_教学大纲.pdf</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted10">
                                                <s:file name="uploadFile" id="uploadFile10" class="uploadFiles"
                                                        data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>
                                        <div class="form-group basic-info">
                                            <label>相关材料1（可选） </label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;可提交 案例素材、案例产品、教学工具、学生成果、研究论文等</p>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_相关材料1.pdf</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted11">
                                                <s:file name="uploadFile" id="uploadFile11" class="uploadFiles"
                                                        data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>
                                        <div class="form-group basic-info">
                                            <label>相关材料2（可选） </label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;可提交 案例素材、案例产品、教学工具、学生成果、研究论文等</p>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;教师号_教师名_相关材料2.pdf</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted12">
                                                <s:file name="uploadFile" id="uploadFile12" class="uploadFiles"
                                                        data-show-caption="true" accept=".pdf"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>相关材料3（可选） </label>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;可提交 案例素材、案例产品、教学工具、学生成果、研究论文等</p>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;上传的zip文件建议不超过500MB。</p>
                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;文件过大点击提交后需等到出现success界面，否则上传失败</p>
<%--                                            <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;课程名_主讲教师_相关材料3.zip</p>--%>
                                            <div class="input-group">
                                                <input type="text" name="fileDeleted" id="fileDeleted13">
                                                <s:file name="uploadFile" id="uploadFile13" class="uploadFiles"
                                                        data-show-caption="true" accept=".zip"/>
                                            </div>
                                        </div>

                                        <div class="form-group basic-info">
                                            <label>负责人简介 <span class="sign_must">*</span></label>
                                            <p style="color: #6C6C6C;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;不超过500字。需包含工号/学号、姓名、性别、出生年月、学历学位、
                                                工作单位、职务职称、主要成果、研究方向、通讯地址、联系电话、E-mail等信息。</p>
                                            <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-tag"></i>
                                            </span>
                                                <s:textarea class="form-control uploadFiles" name="researchEnroll.userCV" id="userCVId" required="true"
                                                            maxlength="300"
                                                            style="min-height:200px; border:2px solid;" />
                                            </div>
                                            <s:textfield id="submitSave" style="height:2px; width:70px;
                                                                        float:right;" class="form-control" name="submitSave"
                                                         readonly = "true"
                                                         type="hidden"/>
                                            <s:textfield id="submitStageControl" style="height:2px; width:70px;
                                                                        float:right;" class="form-control" name="submitStageControl"
                                                         readonly = "true"
                                                         type="hidden"/>
                                        </div>
                                    </div>

                                    <div class="form-group delayBlock" style="display: none">
                                        <label>延期申请 <span class="sign_must">*</span></label>
                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
                                        <p style="color: firebrick;margin-bottom: 10px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;项目负责人签字,单位盖公章:
                                            申请报告_项目编号_负责人姓名_单位名称.pdf</p>
                                        <div class="input-group">
                                            <input type="text" name="fileDeleted" id="fileDeleted16">
                                            <s:file name="uploadFile" id="uploadFile16" class="uploadFiles"
                                                    data-show-caption="true" accept=".pdf"/>
                                        </div>
                                    </div>

                                    <div class="form-group" id="uploadPptId">
                                        <label>上传大赛现场汇报材料 </label>
                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;重复提交会自动覆盖</p>
<%--                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;创新大赛现场汇报PPT：教师号_教师名_现场汇报.pptx</p>--%>
                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;创新大赛现场汇报pptx：</p>
                                        <div class="input-group">
                                            <input type="text" name="fileDeleted" id="fileDeleted14">
                                            <s:file name="uploadFile" id="uploadFile14" class="uploadFiles"
                                                    data-show-caption="true" accept=".pptx"/>
                                        </div>
<%--                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;创新大赛现场汇报PDF：教师号_教师名_现场汇报.pdf</p>--%>
                                        <p style="color: #6C6C6C;margin-bottom: 5px">&nbsp;&nbsp;&nbsp;&nbsp;*&nbsp;创新大赛现场汇报pdf：</p>
                                        <div class="input-group">
                                            <input type="text" name="fileDeleted" id="fileDeleted15">
                                            <s:file name="uploadFile" id="uploadFile15" class="uploadFiles"
                                                    data-show-caption="true" accept=".pdf"/>
                                        </div>
                                    </div>

                                    <br>
                                    <div>
                                        <p style="color: red;margin-bottom: 10px"><span id="error_msg"
                                                                                        style="display: none"></span>
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

<%@ include file="../teacher/common/footer.jsp" %>

</body>
</html>