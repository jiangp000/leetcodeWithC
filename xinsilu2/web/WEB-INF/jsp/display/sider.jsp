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
        <span class="more"></span>项目展示</h2>
    <div class="content list_plate_bg">
        <ul class="list_sid_nav">
        <c:if test="${!empty years}">
            <c:forEach var="year" items="${years}">
                <li>
                    <a href="projectDisplay?year=${year}">
                        <i class="icon_a icon_all"></i>${year}
                    </a>
                </li>
            </c:forEach>
        </c:if>
        </ul>

        <div class="clear"></div>
    </div>
    <div class="list_sider_bot list_plate_bg"></div>
</div>
