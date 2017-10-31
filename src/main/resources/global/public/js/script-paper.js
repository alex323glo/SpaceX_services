$(document).ready(function() {
    $('#triangle-invisible, #triangle-visible, #hidden-square').hover(
        function() {
            $('#triangle-visible').stop().animate(
                {'border-width': '100px'},
                500
            );
            $('#triangle-invisible').stop().animate(
                {'border-width': '100px'},
                500
            );
            $('#hidden-square').stop().animate(
                {width: '80px',
                height: '80px'},
                500
            );
        },
        function() {
            $('#triangle-visible').stop().animate(
                {'border-width': '40px'},
                500
            );
            $('#triangle-invisible').stop().animate(
                {'border-width': '40px'},
                500
            );
            $('#hidden-square').stop().animate(
                {width: '20px',
                height: '20px'},
                500
            );
        }
    );
});