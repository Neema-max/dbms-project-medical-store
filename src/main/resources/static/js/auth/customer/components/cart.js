function clearCart() {
    $.ajax({
        type: "POST",
        url: "/clearCart",
        success: function(data) {
            if (data.result == "success") {
                window.location.reload();
            } else {
                window.location.href = "/";
            }
        }
    });
}
var fromData = [];

function changes(id) {
    var x = {};
    var ok = 0;
    var quantity = $("#" + id + "cart").val();
    x.id = id;
    x.quantity = quantity;
    for (var i = 0; i < fromData.length; i++) {
        if (fromData[i].id == id) {
            fromData[i].quantity = quantity;
            ok = 1;
            break;
        }
    }
    if (ok == 0)
        fromData.push(x);
}

function postToUpdateCart(i) {
    if (i == fromData.length) {
        window.location.reload();
        return;
    }
    $.ajax({
        type: "POST",
        url: "/updateCartItem",
        data: fromData[i],
        success: () => {
            postToUpdateCart(i + 1);
        }
    });
}

function updateCart() {
    postToUpdateCart(0);
}