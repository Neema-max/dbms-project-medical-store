$(document).on('submit', "#registerCust", function(e) {
    e.preventDefault();
    var fd = new FormData(document.getElementById("registerCust"));
    $.ajax({
        type: "POST",
        url: "/register-customer",
        cache: false,
        contentType: false,
        processData: false,
        data: fd,
        success: function(data) {
            if (data.result == "success") {
                document.cookie = 1;
                window.location.reload();
            } else {
                $(".errRegCust").html(data.errmsg);
            }
        }
    });
});