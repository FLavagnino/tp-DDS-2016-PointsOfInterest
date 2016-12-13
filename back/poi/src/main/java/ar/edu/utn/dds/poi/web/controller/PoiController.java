package ar.edu.utn.dds.poi.web.controller;

import ar.edu.utn.dds.poi.auth.AuthManager;
import ar.edu.utn.dds.poi.service.Historical;
import ar.edu.utn.dds.poi.service.historical.HistoricalManager;
import ar.edu.utn.dds.poi.utils.Formatter;
import ar.edu.utn.dds.poi.utils.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import static spark.Spark.*;

public class PoiController {

    private static Historical searcher;
    private static JsonFactory jsonFactory;
    private static HistoricalManager historicalManager;

    @SuppressWarnings("unchecked")
	public static void start() 
    {
        searcher = new Historical();
        jsonFactory = new JsonFactory();
        historicalManager = HistoricalManager.getInstance();

        post("/poi/search/:keyword", (request, response) -> 
        {
            HashMap<String,String> userName = new ObjectMapper().readValue(request.body(), HashMap.class);

            response.type("application/json");

            return jsonFactory.toJson(searcher.search(
                    						Formatter.keywordToFilter(request.params("keyword")),
                							userName.get("user")));
        });

        post("/poi/historical/:user", (request, response) -> 
        {
            response.type("application/json");
            
            return jsonFactory.toJson(Formatter.historicalSearchToDTO(
                    					historicalManager.getSearches(
        								request.params("user"))));
        });

        post("/poi/historical/:dateFrom/:dateTo", (request, response) -> 
        {
            response.type("application/json");
            
            return jsonFactory.toJson(Formatter.historicalSearchToDTO(
                    					historicalManager.getSearches(
				                            Formatter.stringToDateTime(request.params("dateFrom")),
				                            Formatter.stringToDateTime(request.params("dateTo")))));
        });

        post("/poi/login", (request, response) -> 
        {
            HashMap<String,String> user = new ObjectMapper().readValue(request.body(), HashMap.class);
            return AuthManager.getInstance().login(user.get("user"), user.get("pass"));
        });
    }
}