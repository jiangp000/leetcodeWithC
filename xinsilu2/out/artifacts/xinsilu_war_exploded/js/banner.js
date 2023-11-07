$(document).ready(function () {

    $('.iosSlider').iosSlider({
        desktopClickDrag: true,
        snapToChildren: true,
        navSlideSelector: '.sliderContainer .slideSelectors .item',
        onSlideComplete: slideComplete,
        onSliderLoaded: sliderLoaded,
        onSlideChange: slideChange,
        autoSlide: true,
        scrollbar: true,
        scrollbarContainer: '.sliderContainer .scrollbarContainer',
        scrollbarMargin: '0',
        scrollbarBorderRadius: '0'
    });

});

function slideChange(args) {

    $('.sliderContainer .slideSelectors .item').removeClass('selected');
    $('.sliderContainer .slideSelectors .item:eq(' + (args.currentSlideNumber - 1) + ')').addClass('selected');

}

function slideComplete(args) {

    if (!args.slideChanged) return false;

    $(args.sliderObject).find('.text1-bg,.text1, .text2').attr('style', '');
    var w = $(args.currentSlideObject).find('.text1').width() * 1.2;
    $(args.currentSlideObject).find('.text1-bg').animate({
        left: '10%',
        opacity: "0.4",
        width: w
    }, 400, 'easeOutQuint');
    $(args.currentSlideObject).find('.text1').animate({
        left: '10%',
        opacity: "1"
    }, 400, 'easeOutQuint');

    $(args.currentSlideObject).find('.text2').delay(200).animate({
        left: '2%'
    }, 400, 'easeOutQuint');

    /*$(args.currentSlideObject).find('.more').animate({
     right: '0px',
     opacity: '0.3'
     },400, 'easeOutQuint');*/
}

function sliderLoaded(args) {

    $(args.sliderObject).find('.text1-bg,.text1, .text2').attr('style', '');
    var w = $(args.currentSlideObject).find('.text1').width() * 1.2;
    $(args.currentSlideObject).find('.text1-bg').animate({
        left: '10%',
        opacity: "0.4",
        width: w
    }, 400, 'easeOutQuint');
    $(args.currentSlideObject).find('.text1').animate({
        left: '10%',
        opacity: "1"
    }, 400, 'easeOutQuint');

    $(args.currentSlideObject).find('.text2').delay(200).animate({
        left: '2%'
    }, 400, 'easeOutQuint');


    /*$(args.currentSlideObject).find('.more').animate({
     right: '0px',
     opacity: '0.3'
     },400, 'easeOutQuint');*/

    slideChange(args);

}
			