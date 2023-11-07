$(document).ready(function () {
    $('.iosSlider').iosSlider({
        desktopClickDrag: true,
        snapToChildren: true,
        infiniteSlider: true,
        snapSlideCenter: true,
        navSlideSelector: '.sliderContainer .slideSelectors .item',
        navPrevSelector: '.sliderContainer .slideSelectors .prev',
        navNextSelector: '.sliderContainer .slideSelectors .next',
        onSlideComplete: slideComplete,
        onSliderLoaded: sliderLoaded,
        onSlideChange: slideChange,
        autoSlide: true,
        autoSlideTransTimer: 2000,
        scrollbar: true,
        scrollbarContainer: '.sliderContainer .scrollbarContainer',
        scrollbarMargin: '0',
        scrollbarBorderRadius: '0',
        keyboardControls: true
    });
});

function slideChange(args) {
    $('.sliderContainer .slideSelectors .item').removeClass('selected');
    $('.sliderContainer .slideSelectors .item:eq(' + (args.currentSlideNumber - 1) + ')').addClass('selected');
}

function slideComplete(args) {
    if (!args.slideChanged) return false;

    $(args.sliderObject).find('.text1, .text2').attr('style', '');

    $(args.currentSlideObject).find('.text1').animate({
        left: '80px',
        opacity: '0.8'
    }, 1500, 'easeOutQuint');

    $(args.currentSlideObject).find('.text2').delay(100).animate({
        left: '80px',
        opacity: '0.8'
    }, 1500, 'easeOutQuint');

}

function sliderLoaded(args) {
    $(args.sliderObject).find('.text1, .text2').attr('style', '');

    $(args.currentSlideObject).find('.text1').animate({
        left: '80px',
        opacity: '0.8'
    }, 1000, 'easeOutQuint');

    $(args.currentSlideObject).find('.text2').delay(100).animate({
        left: '80px',
        opacity: '0.8'
    }, 1000, 'easeOutQuint');

    slideChange(args);
}