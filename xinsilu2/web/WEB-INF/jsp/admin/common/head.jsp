<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="now" class="java.util.Date"/>
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
            <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy" var="current_year"/>
            <%--当前年份为${current_year}--%>
            <ul class="head_nav_ul" id="head_nav_ul" >
                <li class="li_nav">
                    <a href="index"><span>首页</span></a>
                </li>
                <li class="li_nav">
                    <a href="news"><span>新闻管理</span></a>
                    <div class="li_nav_div">
                        <ul class="li_nav_ul">
                            <li>
                                <a href="news">管理新闻</a>
                            </li>
                            <li>
                                <a href="addNewsJSP">新增新闻</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="li_nav">
                    <a href="files"><span>附件管理</span></a>
                    <div class="li_nav_div">
                        <ul class="li_nav_ul">
                            <li>
                                <a href="files">管理附件</a>
                            </li>
                            <li>
                                <a href="addFileJSP">新增附件</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="li_nav">
                    <a href="train?year=${current_year}"><span>培训管理</span></a>
                    <div class="li_nav_div">
                        <ul class="li_nav_ul">
                            <li>
                                <a href="train?year=${current_year}">管理培训</a>
                            </li>
                            <li>
                                <a href="addTrainJSP">新增培训</a>
                            </li>
                            <li>
                                <a href="outputTrainJSP">申请表导出</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <!-- 添加申请表管理和网评分数，注释掉了大赛管理  开始处-->
                <li class="li_nav">
                    <a href="outputResearchApplyJSP"><span>系统管理</span></a>
                    <div class="li_nav_div">
                        <ul class="li_nav_ul">
                            <li>
                                <a href="outputResearchApplyJSP">申请表导出</a>
                            </li>
                            <li>
                                <a href="outputResultScoreJSP">网评结果</a>
                            </li>
                            <li>
                                <a href="submitStageControlJSP">提交阶段</a>
                            </li>
                            <li>
                                <a href="scoreStageControlJSP">评分阶段</a>
                            </li>
                            <li>
                                <a href="revokeTeacherSubmitJSP">提交撤回</a>
                            </li>
                            <li>
                                <a href="revokeJudgeSubmitJSP">评分撤回</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <%--<li class="li_nav">
                    <a href="outputPaperScoreJSP"><span>大赛管理</span></a>
                    <div class="li_nav_div">
                        <ul class="li_nav_ul">
                            <li>
                                <a href="outputPaperScoreJSP">网评结果</a>
                            </li>
                        </ul>
                    </div>
                </li>--%>
                <!-- 添加申请表管理和网评分数，注释掉了大赛管理 结束处-->
                <li class="li_nav">
                    <a href="addJudgerJSP"><span>评委管理</span></a>
                    <div class="li_nav_div"  style="width: 130px">
                        <ul class="li_nav_ul">
                            <li>
                                <a href="addJudgerJSP">新增校内评委</a>
                            </li>
                            <li>
                                <a href="addExternalJudgerJSP">新增校外评委</a>
                            </li>
                            <li>
                                <a href="judgeManagerJSP">管理评委</a>
                            </li>
                            <li>
                                <a href="removeJudgerJSP">移除评委</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <div class="clear"></div>
    </div>

</div>
