<%--
  Created by IntelliJ IDEA.
  User: jinggu
  Date: 19/5/26
  Time: 下午2:30

  Changed by IntelliJ IDEA.
  User: HongweiHe
  Date: 21/6/14
  Time: 下午16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="list_sider f_l">
    <h2 class="h2_title list_plate_bg f_family f_normal">
        <span class="more"></span>课题研究</h2>
    <div class="content list_plate_bg">
        <ul class="list_sid_nav">
            <li>
                <a href="midResearchEnroll.action" id="column_1">
                    <i class="icon_a icon_all"></i>我的提交
                </a>
            </li>
            <%--<li>
                <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.5" href="javascript:;" id="column_2">
                    <i class="icon_a icon_all"></i>我的成绩
                </a>
            </li>
            <li>
                <a style="text-decoration: none; color:gray; cursor:default;opacity: 1.5" href="javascript:;" id="column_3">
                    <i class="icon_a icon_all"></i>资料下载
                </a>
            </li>--%>
            <li>
                <a href="researchMiddleScoreJSP.action" id="column_2">
                    <i class="icon_a icon_all"></i>我的成绩
                </a>
            </li>
            <li>
                <a href="researchMiddleFileJSP.action" id="column_3">
                    <i class="icon_a icon_all"></i>我的下载
                </a>
            </li>
        </ul>
        <div class="clear"></div>
    </div>
    <div class="list_sider_bot list_plate_bg"></div>
</div>
