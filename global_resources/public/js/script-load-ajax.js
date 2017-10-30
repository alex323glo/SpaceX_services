function loadAjax(elementId, resourcePath) {
    $.ajax('/load-ajax', {
        data: {
            id: resourcePath
        }
    })
        .then(
            function success(text) {
                document.getElementById(elementId).innerHTML = text;
            },
            function fail(data, status) {
                alert('Request failed. \nData: ' + data +
                    '\nStatus: ' + status);
            }
        );
}