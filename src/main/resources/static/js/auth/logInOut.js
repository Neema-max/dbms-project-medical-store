$(document).on('submit', "#loginForm", function(e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "/login",
        data: {
            username: $("#uname").val(),
            password: $("#pass").val()
        },
        success: function(data) {
            if (data.result == "success") {
                $.ajax({
                    type: "POST",
                    url: data.sendPostTo,
                    data: {
                        uname: $("#uname").val()
                    },
                    success: function(data) {
                        document.cookie = 1;
                        window.location.href = "/";
                    }
                });
            } else {
                $("#loginErr").html(data.error);
            }
        }
    });
});

$("#regCustBtn").click(() => {
    show(-1);
});
$("#loginBtn").click(() => {
    show(0);
});





const inputContainers = document.querySelectorAll(".app-input");

inputContainers.forEach((container) => {
    const input = container.querySelector("input");
    const events = ["focus", "blur"];

    events.forEach((event) => {
        input.addEventListener(event, () => {
            if (!input.value) {
                toggleClass(container, "input-active");
            }
        });
    });
});

function toggleClass(element, className) {
    const isActive = element.classList.contains(className);

    if (isActive) {
        element.classList.remove(className);
    } else {
        element.classList.add(className);
    }
}