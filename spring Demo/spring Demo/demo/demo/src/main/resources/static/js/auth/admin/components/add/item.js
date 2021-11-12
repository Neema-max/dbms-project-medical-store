$(document).on('submit', "#addingItemForm", function(e) {
    e.preventDefault();
    var fd = new FormData(document.getElementById("addingItemForm"));
    $.ajax({
        type: "POST",
        url: "/addItem",
        cache: false,
        contentType: false,
        processData: false,
        data: fd,
        success: function(data) {
            if (data.result == "success") {
                window.location.href = "/";
            } else {
                $("#errAddItem").html(data.error);
            }
        }
    });
});