$(document).ready(function () {
    $('#gallery ul li').css('cursor', 'pointer');

    pseudoMason();

    function pseudoMason() {
        $('#gallery ul li').css({
            float: 'left',
            margin: '4px',
            cursor: 'pointer'
        });
        $('#gallery ul li:gt(23)').hide(0);
        var index = 0;
        var unit8 = $('#gallery ul li:eq(' + index + ')').addClass('unit8');
        var src = unit8.find('img').attr('src');
        //unit8.find('img').attr('src', src.replace(/width\/[0-9]+/, 'width/592').replace(/height\/[0-9]+/, 'height/292'));
        if (Math.round(+new Date()) % 2) {
            unit8.css('float', 'right');
        }
        index += 1;
        var unit41 = $('#gallery ul li:eq(' + index + ')').addClass('unit4');
        var src = unit41.find('img').attr('src');
        //unit41.find('img').attr('src', src.replace(/width\/[0-9]+/, 'width/292').replace(/height\/[0-9]+/, 'height/292'));
        if (Math.round(+new Date()) % 2) {
            unit41.css('float', 'right');
        }
        /*   index += 4;
         if (Math.round(+new Date()) % 3) {
         index += 6;
         }*/
        index += 5;
        var unit41 = $('#gallery ul li:eq(' + index + ')').addClass('unit4');
        var src = unit41.find('img').attr('src');
        //unit41.find('img').attr('src', src.replace(/width\/[0-9]+/, 'width/292').replace(/height\/[0-9]+/, 'height/292'));
        if (Math.round(+new Date()) % 2) {
            unit41.css('float', 'right');
        }
        index += 1;
        var unit42 = $('#gallery ul li:eq(' + index + ')').addClass('unit4').css('float', 'right');
        var src = unit42.find('img').attr('src');
        //unit42.find('img').attr('src', src.replace(/width\/[0-9]+/, 'width/292').replace(/height\/[0-9]+/, 'height/292'));
        if (Math.round(+new Date()) % 2) {
            unit42.css('float', 'right');
        }
    }

    function resetMason() {
        $('#gallery ul li').each(function () {
            if ($(this).hasClass('unit8') || $(this).hasClass('unit4')) {
                var src = $(this).find('img').attr('src');
                //$(this).find('img').attr('src', src.replace(/width\/[0-9]+/, 'width/142').replace(/height\/[0-9]+/, 'height/142'));
                $(this).removeClass('unit8').removeClass('unit4');
            }
            $(this).css({
                display: '',
                float: '',
                margin: ''
            });
        });
    }

    var lbTimer = null;
    $(window).resize(function () {
        if ($('#gallery .wrapper').width() == 1200) {
            if (!$('#gallery ul li.unit8, #gallery ul li.unit4').length) {
                pseudoMason();
            }
        } else {
            if ($('#gallery ul li.unit8, #gallery ul li.unit4').length) {
                resetMason();
            }
        }
        if ($('#lightbox-content').length) {
            if (lbTimer) {
                clearTimeout(lbTimer);
            }
            lbTimer = setTimeout(function () {
                $('#gallery ul li').eq($('#lightbox-content img').attr('index')).find('img').trigger('click');
            }, 250);
        }
    }).trigger('resize');
    $('#gallery ul li img').on('click', function (event) {
        event.preventDefault();
        var index = $(this).closest('li').index();
        var total = $(this).closest('ul').find('li:visible').length;
        if (!$('#lightbox').length) {
            $('<div />').attr('id', 'lightbox').css('display', 'none').appendTo('body').after('<div id="lightbox-content" style="display: none;" class="loading" />');
        } else {
            $('#lightbox-content > *').stop(true, true).fadeOut(function () {
                $(this).remove();
            });
            $('#lightbox-content').addClass('loading');
        }
        var pIndex = index - 1;
        var nIndex = index + 1;
        var previousHtml = '<li class="prev"><i index="' + pIndex + '" class="fa fa-arrow-left"></i></li>';
        if (pIndex < 0) {
            previousHtml = '<li class="prev"><i index="' + (total - 1) + '" class="fa fa-arrow-left"></i></li>';
        }
        var nextHtml = '<li class="next"><i index="' + nIndex + '" class="fa fa-arrow-right"></i></li>';
        if (nIndex >= total) {
            nextHtml = '<li class="next"><i index="0" class="fa fa-arrow-right"></i></li>';
        }
        $('<ul id="lightbox-navigation" />').css('display', 'none').append(previousHtml + nextHtml).appendTo('#lightbox-content');
        $('<ul id="lightbox-navigation-close" />').css('display', 'none').append('<li class="close"><i class="fa fa-times"></i></li>').appendTo('#lightbox-content');
        var winHeight = window.innerHeight ? window.innerHeight : $(window).height();
        var winWidth = $(window).width();
        var height = $('#lightbox-content').height();
        var width = $('#lightbox-content').width();
        var lbBorderH = $('#lightbox-content').height() - $('#lightbox-content').innerHeight();
        lbBorderH = lbBorderH ? lbBorderH : 2;
        var lbBorderW = $('#lightbox-content').width() - $('#lightbox-content').innerWidth();
        lbBorderW = lbBorderW ? lbBorderW : 2;
        $('#lightbox-content').css({
            top: (winHeight - height - lbBorderH) / 2,
            left: (winWidth - width - lbBorderW) / 2
        });
        width = winWidth - (winWidth * .01) - lbBorderW;
        if (width > 1000) {
            width = 1000;
        }
        height = winHeight - (winHeight * .01) - lbBorderH;
        if (height > 1000) {
            height = 1000;
        }
        var imgSrc = $(this).attr('src');
        imgSrc = imgSrc.substring(0, imgSrc.indexOf('.')) + '_large' + '.jpg';
        //imgSrc = imgSrc.replace(/\/(width|height)\/[0-9]+/g, '');
        //imgSrc = imgSrc.replace(/\/mode\/[a-z]+/g, '');
        //imgSrc += 'width/' + width + '/height/' + height + '/mode/bestfit/';
        var caption = $(this).attr('alt');
        $('#lightbox-content').append('<p class="caption" style="display: none;">' + caption + '</p>');
        var credits = $(this).attr('data-credits');
        if (credits) {
            $('#lightbox-content .caption').addClass('credits');
            $('#lightbox-content .caption').append(' <span class="credits">' + credits + '</span>');
        }
        $('#lightbox').fadeIn('slow');
        $('#lightbox-content').fadeIn('slow', function () {
            $('<img/>').load(function () {
                height = $(this).outerHeight() + lbBorderH;
                if (height > winHeight) {
                    $(this).height(height);
                }
                width = $(this).outerWidth() + lbBorderW;
                if (width > winWidth) {
                    $(this).width(width);
                }
                topPos = (winHeight - height) / 2;
                leftPos = (winWidth - width) / 2;
                $('#lightbox-content').removeClass('loading').animate({
                    'top': topPos,
                    'left': leftPos,
                    'height': $(this).height(),
                    'width': $(this).width()
                }, 500, function () {
                    $(this).find('img, .caption').fadeIn('slow');
                    $('#lightbox-content').hover(function () {
                        $('#lightbox-navigation, #lightbox-navigation-close').fadeIn();
                        if (parseInt($(this).find('.caption').css('marginBottom'), 10) < 0) {
                            $(this).find('.caption').animate({
                                marginBottom: 0
                            }, 'fast');
                        }
                    }, function () {
                        $('#lightbox-navigation, #lightbox-navigation-close').fadeOut();
                        if (($(this).find('.caption').height() / $(this).height()) > 0.10) {
                            $(this).find('.caption').animate({
                                marginBottom: '-' + $(this).find('.caption').height()
                            }, 'fast');
                        }
                    }).trigger('mouseover').delay(1000).queue(function (next) {
                        $(this).trigger('mouseout');
                        next();
                    });
                });
            }).error(function () {
                //$(this).attr('src', $(this).attr('src') + '/');
            }).attr('src', imgSrc).css('display', 'none').attr('index', index).prependTo('#lightbox-content');
        });
    });
    $(document).on('click touchstart', '#lightbox-navigation li i', function (event) {
        event.preventDefault();
        index = parseInt($(this).attr('index').replace('#', ''));
        $('#gallery ul li').eq(index).find('img').trigger('click');
    });
    $(document).on('touchstart', '#lightbox-content', function (event) {
        event.preventDefault();
        if ($('#lightbox-navigation').is(':visible')) {
            $(this).trigger('mouseout');
        } else {
            $(this).trigger('mouseover');
        }
    });
    $(document).on('click touchstart', '#lightbox, #lightbox-navigation-close', function () {
        $('#lightbox, #lightbox-content').fadeOut('slow', function () {
            $(this).remove();
        });
    });
    $(document).keydown(function (event) {
        if ($('#lightbox').length) {
            switch (event.keyCode) {
                case 27:
                    $('#lightbox, #lightbox-content').fadeOut('slow', function () {
                        $(this).remove();
                    });
                    break;
                case 39:
                    $('#lightbox-navigation li.next i').trigger('click');
                    break;
                case 37:
                    $('#lightbox-navigation li.prev i').trigger('click');
                    break;
            }
        }
    });
});