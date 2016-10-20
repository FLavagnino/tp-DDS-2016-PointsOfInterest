var indexFlag;
var init = function() {

    window.onclick = function(event) {
        if (event.target == $(".poiDetail")[indexFlag]) {
            $(".poiDetail")[indexFlag].style.display = "none";
        }
    }

    $("#add").click(function(){
        event.preventDefault();
        var newInput = "<tr><td><input type=\"text\" class=\"inputKeyWord\"></td></tr>";
        $("#inputTable").append(newInput);
    });

    $("input.available").click(function(){
        event.preventDefault();
        $("#search").removeClass('available');
        $("#poisResult").empty();

        var inputKeywords = $("input.inputKeyWord");
        var keyword = "[" + inputKeywords[0].value;
        for (i = 1; i < inputKeywords.length; i++) {
            var input = inputKeywords[i].value;
            if (isValidInput(input)) {
                keyword += "," + input;
            }
        }
        keyword += "]";
        $.ajax({
            url: getUrl(keyword),
        }).done(function(searchData) {
            if(searchData) {
                if(searchData.pois.length != 0) {
                    showPOIs(searchData);
                } else {
                    alert("No se pudo encontrar ningun POI")
                }
            } else {
                alert("No se obtuvo respuesta de ajax")
            }
        });
        $("#search").addClass('available');
    });

};

function isValidInput(input) {
    return input != "";
};

function getUrl(keyword) {
    return "http://localhost:4567/poi/search/" + keyword;
};

function showPOIs(searchData) {
    var source   = $("#poisResultTemplate").html();
    var template = Handlebars.compile(source);
    var html = $(template(searchData));
    html.appendTo("#poisResult");
};

function openDetail(index) {
    indexFlag = index;
    $(".poiDetail")[index].style.display = "block";
};

Handlebars.registerHelper('isBankType', function(type) {
    return type == "bank";
});

Handlebars.registerHelper('isBusStopType', function(type) {
    return type == "bus_stop";
});

Handlebars.registerHelper('isCGPType', function(type) {
    return type == "cgp";
});

Handlebars.registerHelper('isShopType', function(type) {
    return type == "shop";
});


$(document).ready(init);
