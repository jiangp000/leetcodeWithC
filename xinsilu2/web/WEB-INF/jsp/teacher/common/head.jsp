<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="now" class="java.util.Date"/>

<head>
    <script>
        $(document).ready(function () {
            $("#applyDisplayId").change(function () {
                var id = "#deptId";
                var deptVal = $(id).val();
                if(deptVal == "无"){
                    $(id).val("");
                    alert("请选择院系");
                    return;
                }
            })
        });

        $(function (){
            $("#applyOpen").attr("disabled",true).css("pointer-events","none");
            $("#innoOpen").attr("disabled",true).css("pointer-events","none");
            // $("#midOpen").attr("disabled",true).css("pointer-events","none");
            // $("#paperOpen").attr("disabled",true).css("pointer-events","none");
            // $("#finalOpen").attr("disabled",true).css("pointer-events","none");
        })

        // $(function(){
        //     var myDate = new Date();
        //     var myMonth = myDate.getMonth();
        //     myMonth = 10;
        //     if(myMonth == 0 || myMonth == 11){
        //         document.getElementById("applyHideId").style.display="none";
        //         document.getElementById("innoDisplayId").style.display="none";
        //         document.getElementById("midDisplayId").style.display="none";
        //         document.getElementById("paperDisplayId").style.display="none";
        //         document.getElementById("finalDisplayId").style.display="none";
        //         //document.getElementById("uploadPptId").style.display="none";
        //     }else if(myMonth == 5 || myMonth == 6) {
        //         document.getElementById("applyDisplayId").style.display="none";
        //         document.getElementById("innoHideId").style.display="none";
        //         document.getElementById("midDisplayId").style.display="none";
        //         document.getElementById("paperDisplayId").style.display="none";
        //         document.getElementById("finalDisplayId").style.display="none";
        //     }else if(myMonth == 9 || myMonth == 10){
        //         document.getElementById("applyDisplayId").style.display="none";
        //         document.getElementById("innoDisplayId").style.display="none";
        //         document.getElementById("midDisplayId").style.display="none";
        //         document.getElementById("paperHideId").style.display="none";
        //         document.getElementById("finalHideId").style.display="none";
        //     }else if(myMonth == 9 || myMonth == 10){
        //         document.getElementById("applyDisplayId").style.display="none";
        //         document.getElementById("innoDisplayId").style.display="none";
        //         document.getElementById("midDisplayId").style.display="none";
        //         document.getElementById("paperHideId").style.display="none";
        //         document.getElementById("finalHideId").style.display="none";
        //     }else{
        //         document.getElementById("applyDisplayId").style.display="none";
        //         document.getElementById("innoDisplayId").style.display="none";
        //         document.getElementById("midDisplayId").style.display="none";
        //         document.getElementById("paperDisplayId").style.display="none";
        //         document.getElementById("finalDisplayId").style.display="none";
        //     }
        // })
    </script>

</head>


<div class="header">
    <div class="head_top line_bg" style="background: #700005">
        <div class="head_top_content">
            <div class="top_l f_l">
            <span class="txt f_l" style="color:#e5e3e3">
                您好，欢迎访问教学新思路！
            </span>
            </div>
            <div class="f_r top_r">
                <ul class="top_r_ul">
                    <li class="f_l">
                        <i class="icon-logout" style="color: white"></i>
                        <a class="txt f_l" href="logout" style="color:#e5e3e3">
                            退出登录&nbsp;
                        </a>
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>

    <div class="head_logo_nav1">
        <div style="width:980px; margin:0 auto">
            <img src="../images/banner2.jpg" width="979px" height="100px">
        </div>
    </div>
    <div class="head_logo_nav">
        <div class="head_nav f_l">
            <ul class="head_nav_ul" id="head_nav_ul">
                <li class="li_nav">
                    <a href="index"><span>首页</span></a>
                </li>

                <%--<li class="li_nav" id="applyHideId">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>项目申报</span></a>
                </li>
                <li class="li_nav" id="applyDisplayId">
                    <a href="applyResearchEnroll.action" disabled="true"><span>项目申报</span></a>
                </li>

                <li class="li_nav" id="innoHideId">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>创新大赛</span></a>
                </li>
                <li class="li_nav" id="innoDisplayId">
                    <a href="innovationResearchEnroll.action" disabled="true"><span>创新大赛</span></a>
                </li>

                <li class="li_nav" id="midHideId">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>中期报告</span></a>
                </li>
                <li class="li_nav" id="midDisplayId">
                    <a href="midResearchEnroll.action" disabled="true"><span>中期报告</span></a>
                </li>

                <li class="li_nav" id="paperHideId">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>教研论文</span></a>
                </li>
                <li class="li_nav" id="paperDisplayId">
                    <a href="paperResearchEnroll.action" disabled="true"><span>教研论文</span></a>
                </li>

                <li class="li_nav" id="finalHideId">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>结题报告</span></a>
                </li>
                <li class="li_nav" id="finalDisplayId">
                    <a href="finalResearchEnroll.action" disabled="true"><span>结题报告</span></a>
                </li>--%>
                <%--<li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>项目申报</span></a>
                </li>--%>
                <%--<li class="li_nav">
                    <a href="applyResearchEnroll.action" disabled="true"><span>项目申报</span></a>
                </li>
                <li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>创新大赛</span></a>
                </li>
                <li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>中期报告</span></a>
                </li>
                <li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>教研论文</span></a>
                </li>
                <li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>结题报告</span></a>
                </li>--%>
                <%--<li class="li_nav">
                    <a href="paperResearchEnroll.action" disabled="true"><span>教研论文</span></a>
                </li>--%>
                <%--<li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>结题报告</span></a>
                </li>--%>

                <%--全部显示--%>
                <li class="li_nav">
                    <a href="applyResearchEnroll.action" id="applyOpen"><span>项目申报</span></a>
                </li>
                <li class="li_nav">
                     <a href="innovationResearchEnroll.action" id="innoOpen"><span>创新大赛</span></a>
                 </li>
                 <li class="li_nav">
                     <a href="midResearchEnroll.action" id="midOpen"><span>中期报告</span></a>
                 </li>
                 <li class="li_nav">
                     <a href="paperResearchEnroll.action" id="paperOpen"><span>教研论文</span></a>
                 </li>
                 <li class="li_nav">
                     <a href="finalResearchEnroll.action" id="finalOpen"><span>结题报告</span></a>
                 </li>
                <%--
                <li class="li_nav">
                    <a href="researchEnrollList?type=1"><span>中期报告</span></a>
                </li>
                <li class="li_nav">
                    <a href="researchEnrollList?type=2"><span>教研论文</span></a>
                </li>
                <li class="li_nav">
                    <a href="researchEnrollList?type=3"><span>结题报告</span></a>
                </li>
                --%>

            </ul>
        </div>
        <div class="clear"></div>
    </div>

</div>
