//导航
$(function () {
    $('#head_nav_ul > li.li_nav:has(".li_nav_div")').hover(
        function () {
            $(this).addClass('li_hover');
        }, function () {
            $(this).removeClass('li_hover');
        }
    );
});


/**
 * 加入收藏
 */
function addFavorite(url, title) {
    try {
        window.external.addfavorite(url, title);
    }
    catch (e) {
        try {
            window.sidebar.addPanel(title, url, "");
        }
        catch (e) {
            alert("加入收藏失败，请使用ctrl+d进行添加");
        }
    }
}
