/**
 * Created by flavagnino on 12/10/16.
 */
var init = function() {

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
                if(searchData.search_result.pois.length != 0) {
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
    return "http://localhost:4567/poi/search/all/" + keyword;
}

function showPOIs(searchData) {
    var source   = $("#poisResultTemplate").html();
    var template = Handlebars.compile(source);
    var html = $(template(searchData));
    html.appendTo("#poisResult");
}
function openDetail(element) {
    var detail_window = window.open('poi_detail.html', '_blank');
    sessionStorage.setItem("type", element.attributes['data-type'].value);
    sessionStorage.setItem("id", element.attributes['data-id'].value);
    sessionStorage.setItem("search-key", element.parentElement.parentElement.attributes['data-search-key'].value);
    if (detail_window) {
        detail_window.focus();
    } else {
        alert('Please allow popups for this website');
    }
}


$(document).ready(init);
