<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教学新思路</title>
    <link rel="stylesheet" type="text/css" href="css/front.css">
    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="stylesheet" type="text/css" href="css/link.css">
    <link rel="stylesheet" type="text/css" href="plugins/simple-line-icons/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="plugins/bootstrap/css/bootstrap.css">

    <script type="text/javascript" src="js/jquery.pack.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/jq.js"></script>
    <script type="text/javascript" src="js/jquery.easing-1.3.js"></script>
    <script type="text/javascript" src="js/jquery.iosslider.js"></script>
    <script type="text/javascript" src="js/visualMatch.js"></script>
    <script type="text/javascript" src="js/slider.js"></script>
    <script type="text/javascript" src="js/banner.js"></script>
    <%--<script type="text/javascript" src="plugins/jquery/jquery.min.js"></script>--%>
    <%--<script type="text/javascript" src="plugins/bootstrap/js/bootstrap.js"></script>--%>
</head>
<body>

<%@ include file="./WEB-INF/jsp/common/head.jsp" %>

<!--head end-->
<!--幻灯片-->
<article class="content">
    <section class="iosSliderDemo" style="padding-bottom: 460px;">
        <div class="fluidHeight" style="height: 460px;">
            <div class="sliderContainer" style="max-height: 460px;">
                <div class="iosSlider"
                     style="position: relative; top: 0px; left: 0px; overflow: hidden; z-index: 1; perspective: 1000px; backface-visibility: hidden; width: 1903px; height: 460px;">
                    <div class="slider"
                         style="position: relative; cursor: -webkit-grab; backface-visibility: hidden; transform: matrix(1, 0, 0, 1, -6900, 0); width: 5520px;">
                        <div class="item item1"
                             style="backface-visibility: hidden; position: absolute; top: 0px; transform: matrix(1, 0, 0, 1, 5781.5, 0); width: 1380px;">
                            <div class="inner">
                                <a href="index" target="_blank">
                                    <img src="images/carousel/1.jpg">
                                </a>
                                <div class="selectorShadow"></div>
                                <div class="text1-bg" style="left: 10%; opacity: 0.4; width: 556px;"></div>
                                <div class="text1" style="left: 10%; opacity: 1;"><span>教学新思路2.0</span></div>
                            </div>
                        </div>
                        <div class="item item1"
                             style="backface-visibility: hidden; position: absolute; top: 0px; transform: matrix(1, 0, 0, 1, 7161.5, 0); width: 1380px;">
                            <div class="inner">
                                <a href="index" target="_blank">
                                    <img src="images/carousel/2.jpg">
                                </a>
                                <div class="selectorShadow"></div>
                                <div class="text1-bg" style="left: 10%; opacity: 0.4; width: 556px;"></div>
                                <div class="text1" style="left: 10%; opacity: 1;"><span>教学新思路2.0</span>
                                </div>
                            </div>
                        </div>
                        <div class="item item1"
                             style="backface-visibility: hidden; position: absolute; top: 0px; transform: matrix(1, 0, 0, 1, 8541.5, 0); width: 1380px;">
                            <div class="inner">
                                <a href="index" target="_blank">
                                    <img src="images/carousel/3.jpg" title="">
                                </a>
                                <div class="selectorShadow"></div>
                                <div class="text1-bg" style="left: 10%; opacity: 0.4; width: 556px;"></div>
                                <div class="text1" style="left: 10%; opacity: 1;"><span>教学新思路2.0</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="slideSelectors">
                        <div class="prev" style="cursor: pointer;"></div>
                        <div class="item" style="cursor: pointer;"></div>
                        <div class="item selected" style="cursor: pointer;"></div>
                        <div class="item" style="cursor: pointer;"></div>
                        <div class="next" style="cursor: pointer;"></div>
                    </div>
                    <div class="scrollbarContainer"></div>
                </div>
            </div>
        </div>
    </section>
</article>
<!--幻灯片 end-->

<div class="home_main">
    <div class="w_980" style="padding-top: 15px">
        <div style="width: 100%; height: 250px; border: #ccc solid 1px; border-radius: 4px; background-color: white">
            <h2 style="height:36px; line-height: 36px; margin: 10px 18px; font-size: 24px; color:#8c0000; border-bottom: #999 dotted 1px">
                <span class="f_l">教学新思路2.0</span>
            </h2>
            <p style="margin:10px 18px;font-size: 16px;line-height: 27px;">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了落实国家和北大“十四五”发展规划和实现2035年远景目标，构建“互联网+”、“ 智能+”条件下的人才培养、教育服务及教育治理的新模式，
                积极推动信息技术与教育创新融合、教育信息化创新发展、教育质量持续提升，由教务长办公室、教务部、研究生院、人事部、网信办、校工会、教育学院、教师教学发展中心
                联合启动2022年“教学新思路2.0”项目，通过面向全校师生开展的一系列前沿学术讲座和信息技术培训，为北大师生提供全方位技术支持和服务，
                组织师生进行研究课题申报、创新应用大赛、教研论文征集、教学研讨会等活动，以进一步指导教师队伍把握前沿趋势、激发教学创新和实践突破，
                切实提升教学质量和人才培养能力。<br>
            </p>
            <%--<p style="margin:10px 18px;font-size: 16px;line-height: 27px;">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了落实《教育信息化2.0行动计划》、《教育部关于加强网络学习空间建设与应用的指导意见》，努力构建“互联网+”条件下的人才培养、教育
                服务及教育治理的新模式，积极推动教育大资源、师生信息素养、教育融合创新的持续发展，教师教学发展中心启动2019年“教学新思路”项目，通
                过面向全校师生开展的一系列前沿学术讲座和信息技术培训，为北大师生提供项目组织和指导、技术支持和服务，组织师生进行研究课题申报、创新
                应用大赛、教学研讨等活动，以进一步指导教师队伍把握前沿趋势、激发教学创新和实践突破，切实提升教学质量和人才培养能力。<br>
            </p>--%>
            <div style="float: right">
<%--                <a href="trainEnroll.action" class="btn btn-warning">培训报名</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--                <a href="projectEnroll.action" class="btn btn-info">项目报名</a>&nbsp;&nbsp;&nbsp;&nbsp;--%>
                <%--<a href="#" class="btn btn-warning">培训报名</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="#" class="btn btn-info">项目报名</a>&nbsp;
                <a href="teacher.action"
                   class="btn btn-success">大赛报名</a>--%>&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </div>

    <div class="w_984" style="padding-top: 15px">
        <div class="f_l home_sider">
            <div class="index_plate_bg sid_top"></div>
            <div class="sid_content index_plate_bg">
                <h2 class="h2_title">
                    <span class="f_l">常用下载</span>
                    <span class="more">
                        <i class="icon_all icon_more"></i>
                        <a href="downloadFile" target="_self">更多</a>
                    </span>
                </h2>
                <div class="content">
                    <ul class="ul_list_a_26">
                        <c:if test="${!empty fileList}">
                            <c:forEach var="item" items="${fileList}">
                                <li>
                                    <i class="icon-doc" style="color:#8C0000;"></i>
                                    <a href="${item.filePath}">${item.fileName}</a>
                                </li>
                                </dl>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
            <div class="index_plate_bg sid_bot"></div>
        </div>


        <div class="f_l home_middle">
            <div class="index_plate_bg mid_top"></div>
            <div class="mid_content index_plate_bg">
                <h2 class="h2_title">
                    <span class="f_l">新闻通知</span>
                    <span class="more">
                        <i class="icon_all icon_more"></i><a href="moreNews">更多</a>
                    </span>
                </h2>

                <div class="content">
                    <ul class="ul_list_a_26">
                        <c:if test="${!empty newsList}">
                            <c:forEach var="item" items="${newsList}">
                                <li>

                                    <c:if test="${!empty item.link}">
                                        <i class="icon-flag" style="color:#8C0000;"></i>
                                        <a href="${item.link}" target="_blank">${item.title}</a>
                                    </c:if>
                                    <c:if test="${empty item.link}">
                                        <i class="icon-flag" style="color:#8C0000;"></i>
                                        <a href="newsInfo?news.id=${item.id}">
                                                ${item.title}
                                        </a>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>
            </div>
            <div class="index_plate_bg mid_bot"></div>
        </div>

        <div class="f_r home_sider">
            <div class="index_plate_bg sid_top"></div>
            <div class="sid_content index_plate_bg">
                <h2 class="h2_title">
                    <span class="f_l">项目日程</span>
                </h2>
                <div class="content">
                    <ul>
                        <li>
                            <strong>项目申报</strong><br>
                            &nbsp;&nbsp;2021年12月8日-2021年12月30日<br>
                        </li>
                        <li>
                            <strong>申报评审</strong><br>
                            &nbsp;&nbsp;2022年01月09日<br>
                        </li>
                        <li>
                            <strong>创新培训</strong><br>
                            &nbsp;&nbsp;2021年12月16号-2022年04月28号<br>
                        </li>
                        <li>
                            <strong>创新大赛</strong><br>
                            &nbsp;&nbsp;<%--2021年12月-2022年6月<br>--%>
                            &nbsp;&nbsp;2022年07月09日 理教318 现场决赛<br>
                        </li>
                        <li>
                            <strong>中期汇报（重点、优先课题）</strong><br>
                            &nbsp;&nbsp;2022年07月31日<br>
                        </li>
                        <%--<li>
                            <strong>论文征集</strong><br>
                            &nbsp;&nbsp;2022年09月15日-10月31日<br>
                        </li>--%>
                        <li>
                            <strong>论文评比</strong><br>
                            &nbsp;&nbsp;2022年11月01日-13日<br>
                        </li>
                        <%--<li>
                            <strong>论文会评、结题汇报</strong><br>
                            &nbsp;&nbsp;2022年11月08日 电教403<br>
                        </li>--%>
                        <li>
                            <strong>结题汇报</strong><br>
                            &nbsp;&nbsp;2022年11月14日 <br>
                        </li>
                        <li>
                            <strong>创新研讨</strong><br>
                            &nbsp;&nbsp;2022年11月28日 理教318<br>
                        </li>
                        <%--<li>--%>
                        <%--<strong>讲座培训</strong><br>--%>
                        <%--&nbsp;&nbsp;2020年10月19日 <br>--%>
                        <%--&nbsp;&nbsp;面向全校，项目组可选--%>
                        <%--</li>--%>

                        <%--<li>项目申请人应为学校在岗教职工或在校学生。</li>--%>
                    </ul>
                </div>
            </div>
            <div class="index_plate_bg sid_bot"></div>
        </div>

        <div class="clear"></div>
    </div>

    <div class="w_980" style="padding-top: 15px">
        <div style="width: 100%; height: 90px; border: #ccc solid 1px; border-radius: 4px; background-color: white">
            <h2 style="height:36px; line-height: 36px; margin:10px 18px; font-size: 16px; color:#8c0000; border-bottom: #999 dotted 1px">
                <span class="f_l">外部链接</span>
            </h2>
            <div class="link">
                <ul>
                    <li><a href="http://www.pku.edu.cn/" target="_blank">北京大学</a></li>
                    <li><a href="http://cetl.pku.edu.cn/" target="_blank">教师教学发展中心</a></li>
                    <li><a href="http://jpk.pku.edu.cn/" target="_blank">北大精品课</a></li>
                    <li><a href="http://course.pku.edu.cn/" target="_blank">北大教学网</a></li>
                    <li><a href="http://opencourse.pku.edu.cn/" target="_blank">北大公开课</a></li>
                    <li><a href=" http://llt.pku.edu.cn/" target="_blank">教学促进通讯</a></li>
                </ul>
            </div>
        </div>
    </div>

</div>

<%@ include file="./WEB-INF/jsp/common/footer.jsp" %>

</body>
</html>
