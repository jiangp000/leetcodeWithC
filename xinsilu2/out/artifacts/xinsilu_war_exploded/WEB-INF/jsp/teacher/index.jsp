<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教学新思路</title>
    <link rel="icon" href="../images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="../images/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" type="text/css" href="../css/front.css">
    <link rel="stylesheet" type="text/css" href="../css/index.css">
    <link rel="stylesheet" type="text/css" href="../css/link.css">
    <link rel="stylesheet" type="text/css" href="../plugins/simple-line-icons/simple-line-icons.css"/>
    <link rel="stylesheet" type="text/css" href="../plugins/bootstrap/css/bootstrap.css">

    <script type="text/javascript" src="../js/jquery.pack.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../js/jq.js"></script>
    <script type="text/javascript" src="../js/jquery.easing-1.3.js"></script>
    <script type="text/javascript" src="../js/jquery.iosslider.js"></script>
    <script type="text/javascript" src="../js/visualMatch.js"></script>
    <script type="text/javascript" src="../js/slider.js"></script>
    <script type="text/javascript" src="../js/banner.js"></script>

    <script>
        $(function () {
            $(".li_nav span:contains('首页')").attr("style", "font-weight:800");
        });
    </script>
</head>
<body>

<%@ include file="common/head.jsp" %>

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
                                    <img src="../images/carousel/1.jpg">
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
                                    <img src="../images/carousel/2.jpg">
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
                                    <img src="../images/carousel/3.jpg" title="">
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

<%@ include file="common/footer.jsp" %>

</body>
</html>
