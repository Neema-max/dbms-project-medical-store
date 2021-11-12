function submitEditProfileForm() {
    var fd = new FormData(document.getElementById("editProfileForm"));
    $.ajax({
        type: "POST",
        url: "/employeeEditProfile",
        data: fd,
        cache: false,
        contentType: false,
        processData: false,
        success: function(data) {
            if (data.result == "success") {
                window.location.reload();
            } else {
                $("#editProfileErr").html(data.error);
            }
        }
    });
}

function submitChangePass() {
    var fd = new FormData(document.getElementById("changePassForm"));
    $.ajax({
        type: "POST",
        url: "/employeechangePass",
        data: fd,
        cache: false,
        contentType: false,
        processData: false,
        success: function(data) {
            if (data.result == "success") {
                window.location.reload();
            } else {
                $("#changePassErr").html(data.error);
            }
        }
    });
}