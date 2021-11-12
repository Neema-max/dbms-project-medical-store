$(document).on('submit', "#newCategoryForm", function(e) {
    e.preventDefault();
    var fd = new FormData(document.getElementById("newCategoryForm"));
    $.ajax({
        type: "POST",
        url: "/addCategory",
        cache: false,
        contentType: false,
        processData: false,
        data: fd,
        success: function(data) {
            window.location.href = ".";
        }
    });
});