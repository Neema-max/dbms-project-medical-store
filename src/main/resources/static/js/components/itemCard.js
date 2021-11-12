function removeFromCart(idd) {
    $.ajax({
        type: "POST",
        url: "/removeFromCart",
        data: {
            id: idd
        },
        success: function(data) {
            if (data.result == "success") {
                window.location.reload();
            } else {
                window.location.href = "/";
            }
        }
    });
}