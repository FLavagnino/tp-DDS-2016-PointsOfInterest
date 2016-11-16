var indexFlag;
var userName;

var init = function() {

    $("input.available-login").click(function(){
        event.preventDefault();
        $("#button-login").removeClass('available-login');
        userName = $("#input-user")[0].value;
        var data = "";
        data = data.concat("{\"user\":\"",$("#input-user")[0].value,"\",\"pass\":\"",$("#input-pass")[0].value,"\"}");

        $.ajax({
            type: "POST",
            url: getLoginUrl(),
            data: data,
        }).done(function(response) {
            $("#login-box")[0].style.display = "none";
            $("#page")[0].style.display = "block";
        });
        $("#button-login").addClass('available-login');
    });

    window.onclick = function(event) {
        if (event.target == $(".poiDetail")[indexFlag]) {
            $(".poiDetail")[indexFlag].style.display = "none";
        }
    };

    $("#add").click(function(){
        event.preventDefault();
        var newInput = "<tr><td><input type=\"text\" class=\"inputKeyWord\"></td></tr>";
        $("#inputTable").append(newInput);
    });

    $("#search-link").click(function(){
        $("#login-box")[0].style.display = "none";
        $("#page")[0].style.display = "block";
        $("#historical-box")[0].style.display = "none";
                $("#actions")[0].style.display = "none";
    });

    $("#historical-link").click(function(){
        $("#login-box")[0].style.display = "none";
        $("#page")[0].style.display = "none";
        $("#historical-box")[0].style.display = "block";
                $("#actions")[0].style.display = "none";
    });

    $("#action-link").click(function(){
        $("#login-box")[0].style.display = "none";
        $("#page")[0].style.display = "none";
        $("#historical-box")[0].style.display = "none";
        $("#actions")[0].style.display = "block";

    });

    $("#login-link").click(function(){
        $("#login-box")[0].style.display = "none";
        $("#page")[0].style.display = "block";
        $("#historical-box")[0].style.display = "none";
                $("#actions")[0].style.display = "none";
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

        var headers = "";
        headers = headers.concat("{\"user\":\"",userName,"\"}");

        $.ajax({
            type: "POST",
            data: headers,
            url: getSearchUrl(keyword),
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

    $("input.available-historical-search").click(function(){
        event.preventDefault();
        $("#button-historical-search").removeClass('available-historical-search');
        $("#historicalSearch").empty();

        var inputUser = $("#input-historical-user")[0].value;
        var inputDateFrom = formatDate($("#input-historical-date-from")[0].value);
        var inputDateTo = formatDate($("#input-historical-date-to")[0].value);

        var url = "";
        if(inputUser != "") {
            url = getHistoricalUserUrl(inputUser);
        } else {
            url = getHistoricalDateUrl(inputDateFrom, inputDateTo);
        }

        var headers = "";
        headers = headers.concat("{\"user\":\"",userName,"\"}");

        $.ajax({
            type: "POST",
            data: headers,
            url: url,
        }).done(function(searchData) {
            if(searchData) {
                if(searchData.historical_search.length != 0) {
                    showHistoricalSearches(searchData);
                } else {
                    alert("No se pudo encontrar ningun dato")
                }
            } else {
                alert("No se obtuvo respuesta de ajax")
            }
        });
        $("#button-historical-search").addClass('available-historical-search');
    });


};

function formatDate(date) {
    if(date != "") {
        var splitDateTime = date.split("-");
        var splitDate = splitDateTime[0].split("/");
        var splitTime = splitDateTime[1].split(":");
        return splitDate[0]+"-"+splitDate[1]+"-"+splitDate[2]+"-"+splitTime[0]+"-"+splitTime[1];
    }
    return "";
}

function isValidInput(input) {
    return input != "";
}

function getHistoricalUserUrl(user) {
    return "http://localhost:4567/poi/historical/" + user;
}

function getHistoricalDateUrl(from, to) {
    return "http://localhost:4567/poi/historical/" + from + "/" + to;
}

function getSearchUrl(keyword) {
    return "http://localhost:4567/poi/search/" + keyword;
}

function getLoginUrl() {
    return "http://localhost:4567/poi/login";
}

function showPOIs(searchData) {
    var source   = $("#poisResultTemplate").html();
    var template = Handlebars.compile(source);
    var html = $(template(searchData));
    html.appendTo("#poisResult");
}

function showHistoricalSearches(searchData) {
    var source   = $("#historicalSearchTemplate").html();
    var template = Handlebars.compile(source);
    var html = $(template(searchData));
    html.appendTo("#historicalSearch");
}

function openDetail(index) {
    indexFlag = index;
    $(".poiDetail")[index].style.display = "block";
}

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
