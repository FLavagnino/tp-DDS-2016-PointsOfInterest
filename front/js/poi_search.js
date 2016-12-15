var indexFlag;
var userName;
var guid;

var init = function() {
    $("input.available-login").click(function()
	{
		// Oculto el mensaje de error.
		guid = "";
		$("#loginError").css("visibility", "hidden");
		$("#loginError").html("Error Default");
		
        event.preventDefault();
        $("#button-login").removeClass('available-login');
        userName = $("#input-user")[0].value;
        var data = "";
        data = data.concat("{\"user\":\"",$("#input-user")[0].value,"\",\"pass\":\"",$("#input-pass")[0].value,"\"}");

        $.ajax({
            type: "POST",
            url: getLoginUrl(),
            data: data
        })
		.done(function(response) 
		{
			if (response != "")
			{
				guid = response;
				$("#login-box").hide();
				$("#page")[0].style.display = "block";
				$("#header").show();
			}
			else
			{
				$("#loginError").html("Usuario o clave invalidas, por favor intentelo de nuevo.");
				$("#loginError").css("visibility", "visible");	
			}
        })
		.fail(function(response) 
		{
            $("#loginError").html("Hubo un error, por favor intentelo nuevamente.");
			$("#loginError").css("visibility", "visible");
        });
		
        $("#button-login").addClass('available-login');
    });

    window.onclick = function(event) {
        if (event.target == $(".poiDetail")[indexFlag]) {
            $(".poiDetail").hide();
        }
		else if (event.target == $(".historicalDetail")[indexFlag]) {
			$(".historicalDetail").hide();
        }
    };

    $("#add").click(function(){
        event.preventDefault();
        var newInput = "<tr><td><input type=\"text\" class=\"inputKeyWord\"  style=\"width:92%\" placeholder=\"Ingrese un criterio de búsqueda..\"><input type=\"button\" value=\"X\" style=\"padding:1px 10px;\" onClick=\"javascript:removeFilter(this);\"></td></tr>";
        $("#inputTable").append(newInput);
    });

    $("#addAction").click(function(){
		$("#actionsError").html("Error default");
		$("#actionsError").css("visibility", "hidden");	
		var duplicate = false;
		
		if ($("#ddlUsers").val() != -1)
		{
			var value = $("#ddlActions option:selected").text()
			
			$.each($("#actionsTable tr"), function (i, row) {
				if (row.textContent == value)
				{
					$("#actionsError").html("No puede ingresar la misma accion dos veces.");
					$("#actionsError").css("visibility", "visible");

					duplicate = true;
				}
			});
			
			if (!duplicate)
			{
				var newRow = "<tr>" +
									"<td style=\"width:95%; font-size:14px;\">" + value + "</td>" +
									"<td style=\"width:5%\">" +
										"<input type=\"button\" value=\"X\" style=\"padding:1px 10px;\" onClick=\"javascript:removeAction(this);\">" +
									"</td>" +
								"</tr>";

				$("#actionsTable").append(newRow);
			}
		}
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
		loadUsers(userName, guid);
    });

    $("#logout-link").click(function(){
		guid = "";
		$("#input-user").val("");
		$("#input-pass").val("");
		$("#header").hide();
        $("#login-box").show();
        $("#page").hide();
        $("#historical-box").hide();
		$("#actions").hide();
    });
	
    $("#actionCancel").click(function(){
		$("#ddlUsers").removeAttr('disabled');
		$("#ddlUsers").val(-1);
		$("#actionsTable").hide();
		$("#actionsTable").empty();
		
		$("#actionsError").html("Error default");
		$("#actionsError").css("visibility", "hidden");	
    });

    $("input.available").click(function(){
        event.preventDefault();
        $("#search").removeClass('available');
        $("#poisResult").empty();
		
		$("#searchError").css("visibility", "hidden");
		$("#searchError").html("Error Default");

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
        })
		.done(function(searchData) {
            if(searchData) 
			{
                if(searchData.pois.length != 0) 
				{
                    showPOIs(searchData);
                } 
				else 
				{
					$("#searchError").html("No se pudo encontrar ningun POI, intentelo cambiando los parametros de busqueda.");
					$("#searchError").css("visibility", "visible");
                }
            } 
			else 
			{
				$("#searchError").html("No se obtuvo respuesta de ajax, intentelo nuevamente.");
				$("#searchError").css("visibility", "visible");
            }
        })
		.fail(function(searchData) 
		{
            $("#searchError").html("Hubo un error, por favor intentelo nuevamente.");
			$("#searchError").css("visibility", "visible");
        });
		
        $("#search").addClass('available');
    });

    $("input.available-historical-search").click(function(){
        event.preventDefault();
        $("#button-historical-search").removeClass('available-historical-search');
        $("#historicalSearch").empty();
		
		$("#historicalError").css("visibility", "hidden");
		$("#historicalError").html("Error Default");

        var inputUser = $("#input-historical-user")[0].value;
        var inputDateFrom = formatDate($("#input-historical-date-from")[0].value);
        var inputDateTo = formatDate($("#input-historical-date-to")[0].value);

        var url = "";
		
        if(inputUser != "") 
		{
            url = getHistoricalUserUrl(inputUser);
        } 
		else 
		{
			if (inputDateFrom != "" || inputDateTo != "")
			{
				url = getHistoricalDateUrl(inputDateFrom, inputDateTo);
			}
			else
			{
				return;
			}
        }

        var headers = "";
        headers = headers.concat("{\"user\":\"",userName,"\"}");

        $.ajax({
            type: "POST",
            data: headers,
            url: url,
        })
		.done(function(searchData) {
            if(searchData) 
			{
                if(searchData.historical_search.length != 0) 
				{
                    showHistoricalSearches(searchData);
                } 
				else 
				{
					$("#historicalError").html("No se pudo encontrar ningun dato, intentelo cambiando los parametros de busqueda.");
					$("#historicalError").css("visibility", "visible");
                }
            }
			else 
			{
				$("#historicalError").html("No se obtuvo respuesta de ajax, por favor intentelo nuevamente.");
				$("#historicalError").css("visibility", "visible");
            }
        })
		.fail(function(searchData) 
		{
            $("#historicalError").html("Hubo un error, por favor intentelo nuevamente.");
			$("#historicalError").css("visibility", "visible");
        });
		
        $("#button-historical-search").addClass('available-historical-search');
    });
};

function loadUsers(user, token)
{
	event.preventDefault();
		
	$("#actionsError").css("visibility", "hidden");
	$("#actionsError").html("Error Default");
	
	var data = "";
	data = data.concat("{\"user\":\"", user,"\",\"token\":\"", token,"\"}");

	$.ajax({
		type: "POST",
		url: getUsersUrl(),
		data: data,
	})
	.done(function(response) 
	{
		if (response != "")
		{
			$('#ddlUsers').append($('<option>', { 
				value: -1,
				text : "Un usuario.."
			}));
				
			$.each(response.names, function (i, name) {
				$('#ddlUsers').append($('<option>', { 
					value: name,
					text : name
				}));
			});
		}
		else
		{
			$("#actionsError").html("No se encontraron usuarios. Intentelo nuevamente haciendo refresh de la pagina.");
			$("#actionsError").css("visibility", "visible");	
		}
	})
	.fail(function(response) 
	{
		$("#actionsError").html("No se pudieron obtener los usuarios. Intentelo nuevamente haciendo refresh de la pagina.");
		$("#actionsError").css("visibility", "visible");	
	});
}

function formatDate(date) 
{
    if(date != "") 
	{
        var splitDateTime = date.split("-");
		
		if (splitDateTime.length != 2)
		{
			$("#historicalError").html("La fecha no es correcta, respete el formato aaaa/mm/dd-hh:MM e intentelo nuevamente.");
			$("#historicalError").css("visibility", "visible");
		}
		else
		{
			var splitDate = splitDateTime[0].split("/");
			var splitTime = splitDateTime[1].split(":");
			
			if (splitDate.length != 3 || splitTime.length != 2)
			{
				$("#historicalError").html("La fecha no es correcta, respete el formato aaaa/mm/dd-hh:MM e intentelo nuevamente.");
				$("#historicalError").css("visibility", "visible");
			}
			else
			{
				return splitDate[0] + "-" + splitDate[1] + "-" + splitDate[2] + "-" + splitTime[0] + "-" + splitTime[1];				
			}
		}
    }
	
    return "";
}

function isValidInput(input) {
    return input != "";
}

function getHistoricalUserUrl(user) {
    return "http://localhost:4567/poi/historical/" + user;
}

function getHistoricalDateUrl(from, to) 
{
	if (from == "")
	{
		return "http://localhost:4567/poi/historical/null/" + to;
	}
	else if (to == "")
	{
		return "http://localhost:4567/poi/historical/" + from + "/null";
	}
	else
	{
		return "http://localhost:4567/poi/historical/" + from + "/" + to;
	}
}

function getSearchUrl(keyword) {
    return "http://localhost:4567/poi/search/" + keyword;
}

function getLoginUrl() {
    return "http://localhost:4567/poi/login";
}

function getUserActionsUrl(user) 
{
    return "http://localhost:4567/poi/" + user + "/actions";
}

function getUsersUrl() 
{
    return "http://localhost:4567/poi/users";
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

function removeFilter(row)
{
	row.parentElement.parentElement.remove();	
}

function ddlUsersOnChange()
{
	event.preventDefault();
	
	if ($("#ddlUsers").val() != -1)
	{
		$("#ddlUsers").attr('disabled', 'disabled');
		$("#actionsError").css("visibility", "hidden");
		$("#actionsError").html("Error Default");
		
		$("#actionsTable").hide();
		$("#actionsTable").empty();
		
		var data = "";
		data = data.concat("{\"user\":\"", userName,"\",\"token\":\"", guid,"\"}");

		$.ajax({
			type: "POST",
			url: getUserActionsUrl($("#ddlUsers").val()),
			data: data,
		})
		.done(function(response) 
		{
			if (response != "")
			{	
				var tableHeader = "<tr>" +
									"<th colspan=\"2\">Acción</th>" +
								"</tr>";
							
				$("#actionsTable").append(tableHeader);
					
				$.each(response.actions, function (i, action) {
					var newRow = "<tr>" +
									"<td style=\"width:95%; font-size:14px;\">" + action.name + "</td>" +
									"<td style=\"width:5%\">" +
										"<input type=\"button\" value=\"X\" style=\"padding:1px 10px;\" onClick=\"javascript:removeAction(this);\">" +
									"</td>" +
								"</tr>";

					$("#actionsTable").append(newRow);
				});
				
				$("#actionsTable").show();
			}
			else
			{
				$("#actionsError").html("No se encontraron usuarios. Intentelo nuevamente haciendo refresh de la pagina.");
				$("#actionsError").css("visibility", "visible");	
			}
		})
		.fail(function(response) 
		{
			$("#actionsError").html("No se pudieron obtener los usuarios. Intentelo nuevamente haciendo refresh de la pagina.");
			$("#actionsError").css("visibility", "visible");	
		});
	}
	else
	{
		$("#actionsTable").hide();
		$("#actionsTable").empty();
	}
}

function removeAction(row)
{
	var rowQty = $("#actionsTable tr").length;
	
	if (rowQty == 2)
	{
		$("#actionsError").html("No puede dejar al usuario sin ninguna acción asociada.");
		$("#actionsError").css("visibility", "visible");		
	}
	else
	{
		row.parentElement.parentElement.remove();	
	}
}

function openDetail(index) {
    indexFlag = index;
    $(".poiDetail")[index].style.display = "block";
}

function openHistoricalDetail(index) {
    indexFlag = index;
    $(".historicalDetail")[index].style.display = "block";
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

Handlebars.registerHelper('formatTime', function(hours, minutes) 
{
	time = hours + ":";
	
	if (minutes == 0)
	{
			time = time + "00";
	}
	else
	{
		time = time + minutes;
	}
	
    return time;
});

Handlebars.registerHelper('dayOfWeekString', function(dayOfWeek) {
	if (dayOfWeek == 1)
	{
		return "Lunes";
	}
	else if (dayOfWeek == 2)
	{
		return "Martes";
	}
	else if (dayOfWeek == 3)
	{
		return "Miercoles";
	}
	else if (dayOfWeek == 4)
	{
		return "Jueves";
	}
	else if (dayOfWeek == 5)
	{
		return "Viernes";
	}
	else if (dayOfWeek == 5)
	{
		return "Sabado";
	}
	else
	{
		return "Domingo";
	}
});

$(document).ready(init);
