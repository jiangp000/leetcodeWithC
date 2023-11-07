<%--
  Created by IntelliJ IDEA.
  User: jinggu
  Date: 19/5/26
  Time: 下午2:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="list_sider f_l">
    <h2 class="h2_title list_plate_bg f_family f_normal">
        <span class="more"></span>培训管理</h2>
    <div class="content list_plate_bg">
        <ul class="list_sid_nav">
            <li>
                <a href="train?year=${current_year}" id="column_1">
                    <i class="icon_a icon_all"></i>管理培训
                </a>
            </li>
            <li>
                <a href="addTrainJSP" id="column_2">
                    <i class="icon_a icon_all"></i>新增培训
                </a>
            </li>
            <li>
                <a href="outputTrainJSP" id="column_3">
                    <i class="icon_a icon_all"></i>申请表导出
                </a>
            </li>
        </ul>
        <div class="clear"></div>
    </div>
    <div class="list_sider_bot list_plate_bg"></div>
</div>

