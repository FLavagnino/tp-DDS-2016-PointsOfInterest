/**
 * Created by flavagnino on 12/10/16.
 */
var inicializar = function() {

    $("#add").click(function(){
        event.preventDefault();
        var newInput = "<tr><td><input type=\"text\" class=\"inputKeyWord\"></td></tr>";
        $("#inputTable").append(newInput);
    });

    $("input.available").click(function(){
        event.preventDefault();
        $("#poisResult").empty();
        $("#search").removeClass('available');

        var inputKeywords = $("input.inputKeyWord");
        var keyword = "[" + inputKeywords[0].value;
        for (i = 1; i < inputKeywords.length; i++) {
            var input = inputKeywords[i].value;
            if (isValidInput(input)) {
                keyword += "," + input;
            }
        }
        keyword += "]";
        console.log(getUrl(keyword));
        $.ajax({
            url: getUrl(keyword),
        }).done(function(searchData) {
            if(searchData) {
                if(searchData.items.length != 0) {
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
    return "http://localhost/search?keyword=" + keyword;
}

function showPOIs(searchData) {
    var source   = $("#poisResultTemplate-template").html();
    var template = Handlebars.compile(source);
    var html = $(template(data));
    html.appendTo("#poisResult");
}

$(document).ready(inicializar);
