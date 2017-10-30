$(document).ready(function() {
    $('.wrapper>article').not(':first').hide();
    $('.wrapper>h5').not(':first').css('border-bottom-left-radius', '10px');
    $('.wrapper>h5').not(':first').css('border-bottom-right-radius', '10px');
    $('.wrapper>h5').click(function() {
        var findArticle = $(this).next('article');
        var findWrapper = $(this).closest('.wrapper');
        findWrapper.find('>h5').stop();
        if (findArticle.is(':visible')) {
            findWrapper.find('>h5').stop();
            findArticle.slideUp('slow');
            $(this).delay(500).animate(
                {'border-bottom-left-radius': '10px',
                'border-bottom-right-radius': '10px'},
                'slow'
            );
        } else {
            findWrapper.find('>article').slideUp();
            findWrapper.find('>h5').stop();
            findWrapper.find('>h5').not(findArticle.prev()).stop().delay(500).animate(
                {'border-bottom-left-radius': '10px',
                'border-bottom-right-radius': '10px'},
                'slow'
            ).stop();
           /*findArticle.prev('h5').animate(
               {'border-bottom-left-radius': '0px',
               'border-bottom-right-radius': '0px'},
               'fast'
           );*/
            findArticle.prev().css('border-bottom-left-radius', '0px');
            findArticle.prev().css('border-bottom-right-radius', '0px');
            findArticle.slideDown();
        }
    });
});