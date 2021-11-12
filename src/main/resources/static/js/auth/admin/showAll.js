function remove(x, id) {
    alert("hii");
    $.ajax({
        type: "POST",
        url: "remove",
        data: {
            what: x,
            id: id
        },
        success: function(data) {
            window.location.reload();
        }
    });
}

function removeItem(id) {
    remove("item", id);
}

function removeEmp(id) {
    remove("emp", id);
}

function removeCust(id) {
    remove("cust", id);
}

function removeCat(id) {
    remove("cat", id);
}

function increaseStatus(xid) {
    $.ajax({
        type: "POST",
        url: "increaseOrderStatus",
        data: {
            id: xid
        },
        success: function(data) {
            window.location.reload();
        }
    })
}

function cancelOrder(xid) {
    $.ajax({
        type: "POST",
        url: "cancelOrder",
        data: {
            id: xid
        },
        success: function(data) {
            window.location.reload();
        }
    })
}



$(document).ready(function() {
    $(".search").keyup(function() {
        var searchTerm = $(".search").val();
        var listItem = $('.results tbody').children('tr');
        var searchSplit = searchTerm.replace(/ /g, "'):containsi('")

        $.extend($.expr[':'], {
            'containsi': function(elem, i, match, array) {
                return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
            }
        });

        $(".results tbody tr").not(":containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'false');
        });

        $(".results tbody tr:containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'true');
        });

        var jobCount = $('.results tbody tr[visible="true"]').length;
        $('.counter').text(jobCount + ' item');

        if (jobCount == '0') {
            $('.no-result').show();
        } else {
            $('.no-result').hide();
        }
    });
    $(".search1").keyup(function() {
        var searchTerm = $(".search1").val();
        var listItem = $('.results1 tbody').children('tr');
        var searchSplit = searchTerm.replace(/ /g, "'):containsi('")

        $.extend($.expr[':'], {
            'containsi': function(elem, i, match, array) {
                return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
            }
        });

        $(".results1 tbody tr").not(":containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'false');
        });

        $(".results1 tbody tr:containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'true');
        });

        var jobCount = $('.results1 tbody tr[visible="true"]').length;
        $('.counter').text(jobCount + ' item');

        if (jobCount == '0') {
            $('.no-result1').show();
        } else {
            $('.no-result1').hide();
        }
    });
    $(".search2").keyup(function() {
        var searchTerm = $(".search2").val();
        var listItem = $('.results2 tbody').children('tr');
        var searchSplit = searchTerm.replace(/ /g, "'):containsi('")

        $.extend($.expr[':'], {
            'containsi': function(elem, i, match, array) {
                return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
            }
        });

        $(".results2 tbody tr").not(":containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'false');
        });

        $(".results2 tbody tr:containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'true');
        });

        var jobCount = $('.results2 tbody tr[visible="true"]').length;
        $('.counter').text(jobCount + ' item');

        if (jobCount == '0') {
            $('.no-result2').show();
        } else {
            $('.no-result2').hide();
        }
    });


    $(".search3").keyup(function() {
        var searchTerm = $(".search3").val();
        var listItem = $('.results3 tbody').children('tr');
        var searchSplit = searchTerm.replace(/ /g, "'):containsi('")

        $.extend($.expr[':'], {
            'containsi': function(elem, i, match, array) {
                return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
            }
        });

        $(".results3 tbody tr").not(":containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'false');
        });

        $(".results3 tbody tr:containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'true');
        });

        var jobCount = $('.results3 tbody tr[visible="true"]').length;
        $('.counter').text(jobCount + ' item');

        if (jobCount == '0') {
            $('.no-result3').show();
        } else {
            $('.no-result3').hide();
        }
    });
    $(".search4").keyup(function() {
        var searchTerm = $(".search4").val();
        var listItem = $('.results3 tbody').children('tr');
        var searchSplit = searchTerm.replace(/ /g, "'):containsi('")

        $.extend($.expr[':'], {
            'containsi': function(elem, i, match, array) {
                return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
            }
        });

        $(".results4 tbody tr").not(":containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'false');
        });

        $(".results4 tbody tr:containsi('" + searchSplit + "')").each(function(e) {
            $(this).attr('visible', 'true');
        });

        var jobCount = $('.results4 tbody tr[visible="true"]').length;
        $('.counter').text(jobCount + ' item');

        if (jobCount == '0') {
            $('.no-result4').show();
        } else {
            $('.no-result4').hide();
        }
    });
});