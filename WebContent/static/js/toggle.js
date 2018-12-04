﻿$(document).ready(function(){
    var _list = $(".toggle-button");

    for (var i = 0; i < _list.size(); ++i) {
        const ci = i;

        _list[i].onclick = function(){
            var t =$("#" + _list[ci].id.replace("title-", "list-"));
            if (t.is(":hidden")) {
                t.slideDown(300);
                $("#" + _list[ci].id).css("transform", "rotate(0deg)");
            }
            else {
                t.slideUp(300);
                $("#" + _list[ci].id).css("transform", "rotate(-90deg)");

            }
        };
    }
});

