// JavaScript Document
//ad img 幻灯片
$(function () {
    var index = 0;
    $("#home_pho_btn span").mouseover(function () {
        index = $("#home_pho_btn span").index(this);
        showImg(index);
    });
    //滑入 停止动画，滑出开始动画.
    $('.home_pho_stop').hover(function () {
        if (MyTime) {
            clearInterval(MyTime);
        }
    }, function () {
        MyTime = setInterval(function () {
            showImg(index)
            index++;
            if (index == 5) {
                index = 0;
            }
        }, 4000);
    });
    //自动开始
    var MyTime = setInterval(function () {
        showImg(index)
        index++;
        if (index == 5) {
            index = 0;
        }
    }, 4000);
});
//关键函数：通过控制i ，来显示不通的幻灯片
function showImg(i) {
    $("#home_pho_img img")
        .eq(i).stop(true, true).fadeIn(800)
        .parent().siblings().find("img").hide();
    $("#home_pho_msg li")
        .eq(i).stop(true, true).fadeIn(800)
        .siblings().hide();
    $("#home_pho_btn span")
        .eq(i).addClass("hov")
        .siblings().removeClass("hov");
}

//首页出版物
$(function () {
    var li_index = 0;
    $('#book_icon_left').click(function () {
        li_index--;
        if (li_index < 0) {
            li_index = 2;
        }
        $('#book_mid_ul li').eq(li_index).show().siblings().hide();
    });
    $('#book_icon_right').click(function () {
        li_index++;
        if (li_index == 3) {
            li_index = 0;
        }
        $('#book_mid_ul li').eq(li_index).show().siblings().hide();
    });
});

var teacher_t = setInterval(teacherMarquee, 50);

$(function () {

    $('#index_ltrw_top').bind("mouseenter", function () {
        clearInterval(teacher_t);
    }).bind("mouseleave", function () {
        teacher_t = setInterval(teacherMarquee, 50);
    });

});
/**
 * 首页 跑马灯
 */
function teacherMarquee() {
    var obj = $('#index_ltrw_top');

    var top = parseInt(obj.css('top'));
    if (isNaN(top))
        top = 0;
    top = top - 1;
    if (Math.abs(top) > obj.height() - 150)
        top = 0;

    obj.css('top', top);
}

var xPos = document.body.clientWidth / 2;
var yPos = document.body.clientHeight / 2;
var step = 1;
var delay = 30;
var height = 0;
var Hoffset = 0;
var Woffset = 0;
var yon = 0;
var xon = 0;
var pause = false;
var interval_im;

function changePos() {
    if (pause)
        return;

    var img1 = document.getElementById("index_image");

    width = document.body.clientWidth;
    height = document.body.clientHeight;
    Hoffset = img1.offsetHeight;
    Woffset = img1.offsetWidth;
    img1.style.left = xPos + document.body.scrollLeft + "px";
    img1.style.top = yPos + document.body.scrollTop + "px";

    if (yon) {
        yPos = yPos + step;
    }
    else {
        yPos = yPos - step;
    }
    if (yPos < 0) {
        yon = 1;
        yPos = 0;
    }
    if (yPos >= (height - Hoffset)) {
        yon = 0;
        yPos = (height - Hoffset);
    }
    if (xon) {
        xPos = xPos + step;
    }
    else {
        xPos = xPos - step;
    }
    if (xPos < 0) {
        xon = 1;
        xPos = 0;
    }
    if (xPos >= (width - Woffset)) {
        xon = 0;
        xPos = (width - Woffset);
    }
}

function start() {
    var img1 = document.getElementById("index_image");
    img1.visibility = "visible";
    img1.style.top = yPos + "px";
    img1.style.left = xPos + "px";
    interval_im = setInterval('changePos()', delay);

    $("#index_image").mouseover(function () {
        pause = true;
    }).mouseout(function () {
        pause = false;
    });
}