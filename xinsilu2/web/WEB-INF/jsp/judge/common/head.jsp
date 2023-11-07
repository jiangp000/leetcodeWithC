<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="now" class="java.util.Date"/>
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
</style>

<script>
    $(function (){
        $("#innoOpen").attr("disabled",true).css("pointer-events","none");
        $("#midOpen").attr("disabled",true).css("pointer-events","none");
    })
</script>

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
                <!--<li class="li_nav">
                    <a href="paperScoreJSP?id=1"><span>评委评审</span></a>
                </li>
                <li class="li_nav">
                    <a href="researchEnrollList?type=1"><span>中期报告</span></a>
                </li>
                <li class="li_nav">
                    <a href="researchEnrollList?type=2"><span>教研论文</span></a>
                </li>
                <li class="li_nav">
                    <a href="researchEnrollList?type=3"><span>结题报告</span></a>
                </li>-->
                <%--全部显示--%>
                <li class="li_nav">
                    <a href="applyScoreJSP?id=1" id="applyOpen"><span>项目申报</span></a>
                </li>
                <li class="li_nav">
                    <a href="innoScoreJSP?id=1" id="innoOpen"><span>创新大赛</span></a>
                </li>
               <li class="li_nav">
                    <a href="middleScoreJSP?id=1" id="midOpen"><span>中期报告</span></a>
                </li>
                <li class="li_nav">
                    <a href="paperScoreJSP?id=1" id="paperOpen"><span>论文评审</span></a>
                </li>
                <li class="li_nav">
                    <a href="finalScoreJSP?id=1" id="finalOpen"><span>结题评审</span></a>
                </li>

                <%--<li class="li_nav">
                    <a href="applyScoreJSP?id=1"><span>项目申报</span></a>
                </li>
                <li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>创新大赛</span></a>
                </li>
                <li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>中期报告</span></a>
                </li>
                <li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>论文评审</span></a>
                </li>
                <li class="li_nav">
                    <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.0" href="javascript:;"><span>结题评审</span></a>
                </li>--%>


                <%--<c:if test="${sessionScope.judge2 != '0006170278' &&
                sessionScope.judge2 != '0006152012' && sessionScope.judge2 != '0006161023' &&
                sessionScope.judge2 != '0006168276' && sessionScope.judge2 != '0006167289' &&
                sessionScope.judge2 != '0006169222' && sessionScope.judge2 != '0016170088' &&
                sessionScope.judge2 != '0016154044' && sessionScope.judge2 != '0006164225' &&
                sessionScope.judge2 != '0006165271' && sessionScope.judge2 != '2101210152'}">
                    <li class="li_disabled" style="">
                        <a href="javascript:" ><span>论文评审</span></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.judge2 == '0006170278' ||
                sessionScope.judge2 == '0006152012' || sessionScope.judge2 == '0006161023' ||
                sessionScope.judge2 == '0006168276' || sessionScope.judge2 == '0006167289' ||
                sessionScope.judge2 == '0006169222' || sessionScope.judge2 == '0016170088' ||
                sessionScope.judge2 == '0016154044' || sessionScope.judge2 == '0006164225' ||
                sessionScope.judge2 == '0006165271' || sessionScope.judge2 == '2101210152'}">
                    &lt;%&ndash;<li class="li_nav">
                        &lt;%&ndash;<a href="paperScoreJSP?id=1"><span>论文评审</span></a>&ndash;%&gt;
                    </li>&ndash;%&gt;
                    <li class="li_disabled" style="">
                        <a href="javascript:" ><span>论文评审</span></a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.judge2 != '0006170278' &&
                sessionScope.judge2 != '0006172062' && sessionScope.judge2 != '0006166389' &&
                sessionScope.judge2 != '0006152012' && sessionScope.judge2 != '0016165139' &&
                sessionScope.judge2 != '0006174035' && sessionScope.judge2 != '0016170088' &&
                sessionScope.judge2 != '0006167390' && sessionScope.judge2 != '0006182077' &&
                sessionScope.judge2 != '0006183064' && sessionScope.judge2 != '0006181096'}">
                    <li class="li_disabled" style="">
                        <a href="javascript:" ><span>结题评审</span></a>
                    </li>
                </c:if>
                <c:if test="${sessionScope.judge2 == '0006170278' ||
                sessionScope.judge2 == '0006172062' || sessionScope.judge2 == '0006166389' ||
                sessionScope.judge2 == '0006152012' || sessionScope.judge2 == '0016165139' ||
                sessionScope.judge2 == '0006174035' || sessionScope.judge2 == '0016170088' ||
                sessionScope.judge2 == '0006167390' || sessionScope.judge2 == '0006182077' ||
                sessionScope.judge2 == '0006183064' || sessionScope.judge2 == '0006181096'}">
                    &lt;%&ndash;<li class="li_nav">
                        &lt;%&ndash;<a href="finalScoreJSP?id=1"><span>结题评审</span></a>&ndash;%&gt;
                    </li>&ndash;%&gt;
                    <li class="li_disabled" style="">
                        <a href="javascript:" ><span>结题评审</span></a>
                    </li>
                </c:if>--%>

            </ul>
        </div>
        <div class="clear"></div>
    </div>

</div>
