$(document).on('submit', "#addEmployeeFrom", (e) => {
    e.preventDefault();
    var fd = new FormData(document.getElementById("addEmployeeFrom"));
    $.ajax({
        type: "POST",
        url: "/addEmployee",
        cache: false,
        contentType: false,
        processData: false,
        data: fd,
        success: function(data) {
            if (data.result == "success")
                window.location.href = ".";
            else {
                $("#errorAddEmployee").html(data.error);
            }
        }
    });
});