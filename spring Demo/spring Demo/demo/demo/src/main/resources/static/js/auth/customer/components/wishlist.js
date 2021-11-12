function clearWishlist() {
    $.ajax({
        type: "POST",
        url: "/clearWishlist",

        success: function(data) {
            if (data.result == "success") {
                window.location.reload();
            } else {
                window.location.href = "/";
            }
        }
    });
}

function removeFromWishlist(idd) {
    $.ajax({
        type: "POST",
        url: "/removeFromWishlist",
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