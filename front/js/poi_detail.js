/**
 * Created by facundolavagnino on 18/10/16.
 */

var init = function() {
    searchPoi();
};

function searchPoi(){
    $("#poisDetail").empty();
    $.ajax({
        url: getUrl(),
    }).done(function(poiData) {
        if(poiData) {
            if(poiData != null) {
                showDetail(poiData);
            } else {
                alert("No se pudo encontrar ningun POI")
            }
        } else {
            alert("No se obtuvo respuesta de ajax")
        }
    });
};

function getUrl() {
    return "http://localhost:4567/poi/search/" + sessionStorage.getItem("id") + "/" + sessionStorage.getItem("search-key");
}

function showDetail(poiData) {
    var source   = $("#poisDetailTemplate").html();
    var template = Handlebars.compile(source);
    var html = $(template(poiData));
    html.appendTo("#poisDetail");
}


$(document).ready(init);