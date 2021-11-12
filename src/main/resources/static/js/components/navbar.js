$("#loginBtn").click(() => {
    $("#registerCustFormModal").css("display", "none");
    $("#loginFormModal").css("display", "flex");
});


$("#regCustBtn").click(() => {
    $("#registerCustFormModal").css("display", "flex");
    $("#loginFormModal").css("display", "none");
});

$("#logout").click(() => {
    $.ajax({
        type: "POST",
        url: "/logout",
        success: function(data) {
            $.ajax({
                type: "POST",
                url: data.sendPostTo,
                success: function(data) {
                    window.location.href = "/";
                }
            });
        }
    });
});


$(document).on('submit', "#searchForm", (e) => {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "/search",
        data: {
            search: $("#searchBox").val()
        },
        success: function(data) {
            window.location.reload();
        }
    });
});

function addToWishlist(idd) {
    $.ajax({
        type: "POST",
        url: "/addToWishlist",
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


document.addEventListener("DOMContentLoaded", function(event) {

    const showNavbar = (toggleId, navId, bodyId, headerId) => {
        const toggle = document.getElementById(toggleId),
            nav = document.getElementById(navId),
            bodypd = document.getElementById(bodyId),
            headerpd = document.getElementById(headerId)

        // Validate that all variables exist
        if (toggle && nav && bodypd && headerpd) {
            toggle.addEventListener('click', () => {
                // show navbar
                nav.classList.toggle('show')
                    // change icon
                toggle.classList.toggle('bx-x')
                    // add padding to body
                bodypd.classList.toggle('body-pd')
                    // add padding to header
                headerpd.classList.toggle('body-pd')
            })
        }
    }

    showNavbar('header-toggle', 'nav-bar', 'body-pd', 'header')

    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')

    function colorLink() {
        if (linkColor) {
            linkColor.forEach(l => l.classList.remove('active'))
            this.classList.add('active')
        }
    }
    linkColor.forEach(l => l.addEventListener('click', colorLink))

    // Your code to run since DOM is loaded and ready
});



window.onload = () => {
    hideAll();
    if (document.cookie == '') {
        document.cookie = 1;
    }
    var x = parseInt(document.cookie);
    if (parseInt(x / 10) == 0) {
        showclick(x % 10);
    } else {

        if (parseInt(x / 10) == 7) {
            showclick(parseInt(document.cookie) % 10);
            hideAll();
            $("#itemsComp").show();
        } else if (parseInt(x / 10) == 8) {
            showclick(parseInt(document.cookie) % 10);
            hideAll();
            $("#dashItemShow").show();
        } else if (parseInt(x / 10) == 9) {
            showclick(parseInt(document.cookie) % 10);
            hideAll();
            $("#checkout").show();
        } else {
            showclick(parseInt(document.cookie));
        }
    }
};

function checkout() {
    $.ajax({
        type: "POST",
        url: "/checkout",
        success: function(data) {
            document.cookie = 9 * 10 + parseInt(document.cookie) % 10;
            window.location.reload();
        }
    });
}

function showItemsOfCategory(iid) {
    $.ajax({
        type: "POST",
        url: "/showItemsWithCateogry",
        data: {
            id: iid
        },
        success: function(data) {
            document.cookie = 8 * 10 + parseInt(document.cookie) % 10;
            window.location.reload();
        }
    });
}
//$(document).ready(() => {

//});
function showclick(x) {
    document.getElementById("click" + x).click();
}

function showItem(id) {
    $.ajax({
        type: "POST",
        url: "/itemShow",
        data: {
            id: id
        },
        success: function(data) {
            document.cookie = 7 * 10 + parseInt(document.cookie) % 10;
            window.location.reload();
        }
    });
}

function hideAll() {
    $("#loginComp").hide();
    $("#dashboard").hide();
    $("#searchComp").hide();
    $("#cartComp").hide();
    $("#ordersComp").hide();
    $("#wishlistComp").hide();
    $("#profileComp").hide();
    $("#regcustComp").hide();
    $("#itemsComp").hide();
    $("#dashItemShow").hide();
    $("#checkout").hide();
    $("#manageEmpComp").hide();
    $("#custManageComp").hide();
    $("#catManageComp").hide();
    $("#itemManageComp").hide();
    $("#ordersManageComp").hide();
    $("#accountsComp").hide();
    return;
}

function show(x) {
    hideAll();
    document.cookie = x;
    if (x == 0) {
        $("#loginComp").show();
    } else if (x == 1) {
        $("#dashboard").show();
    } else if (x == 2) {
        $("#searchComp").show();
    } else if (x == 3) {
        $("#cartComp").show();
    } else if (x == 4) {
        $("#ordersComp").show();
    } else if (x == 5) {
        $("#wishlistComp").show();
    } else if (x == 6) {
        $("#profileComp").show();
    } else if (x == -1) {
        $("#regcustComp").show();
    } else if (x == 11) {
        $("#manageEmpComp").show();
    } else if (x == 12) {
        $("#catManageComp").show();
    } else if (x == 13) {
        $("#ordersManageComp").show();
    } else if (x == 14) {
        $("#itemManageComp").show();
    } else if (x == 16) {
        $("#custManageComp").show();
    } else if (x == 17) {
        $("#accountsComp").show();
    }
}

function addToCart(idd, from) {
    $.ajax({
        type: "POST",
        url: "/addToCart",
        data: {
            id: idd,
            quantity: $("#" + idd + "quantity" + from).val()
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

function increase(id) {
    var x = $("#" + id).val();
    x = parseInt(x);
    if (x + 1 <= $("#" + id).attr('max'))
        $("#" + id).val(x + 1);
    if (id.includes("cart")) {
        id = id.replace("cart", "");
        changes(id);
    }
}

function decrease(id) {
    var x = $("#" + id).val();
    x = parseInt(x);
    if (x - 1 > 0)
        $("#" + id).val(x - 1);

    if (id.includes("cart")) {
        id = id.replace("cart", "");
        changes(id);
    }
}