$(document).on('submit', "#addAdmin", (e) => {
    e.preventDefault();
    var fd = new FormData(document.getElementById("addAdmin"));
    $.ajax({
        type: "POST",
        url: "/addAdmin",
        cache: false,
        contentType: false,
        processData: false,
        data: fd
            /*{
                        uname: $("#unameAddAdmin").val(),
                        password: $("#passAddAdmin").val(),
                        name: $("#nameAddAdmin").val(),
                        email: $("#emailAddAdmin").val(),
                        phone: $("#phoneAddAdmin").val(),
                        dob: $("#dobAddAdmin").val(),
                        address: $("#addressAddAdmin").val()
                    }*/
            ,
        success: function(data) {
            if (data.result == "success")
                window.location.href = ".";
            else {
                $("#errorAddAdmin").html(data.error);
            }
        }
    });
});