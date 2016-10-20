package ar.edu.utn.dds.poi.controller;

import ar.edu.utn.dds.poi.dto.HistoricalSearchDTO;
import ar.edu.utn.dds.poi.service.Historical;
import ar.edu.utn.dds.poi.service.historical.HistoricalManager;
import ar.edu.utn.dds.poi.utils.Formatter;
import ar.edu.utn.dds.poi.utils.JsonFactory;

import java.util.List;

import static spark.Spark.*;

public class PoiController {

    private static Historical searcher;
    private static JsonFactory jsonFactory;
    private static HistoricalManager historicalManager;

    public static void start() {
        searcher = new Historical();
        jsonFactory = new JsonFactory();
        historicalManager = HistoricalManager.getInstance();

        get("/poi/search/:keyword", (request, response) -> {
            //TODO: colocar user de login y voletear esto de abajo
            String userName = "lala";
            response.cookie("user", userName);

            response.type("application/json");

            return jsonFactory.toJson(searcher.search(
                    Formatter.keywordToFilter(request.params("keyword")),
                    userName));
        });

        get("/poi/historical/:user", (request, response) -> {
            response.type("application/json");

            return jsonFactory.toJson(Formatter.historicalSearchToDTO(
                    historicalManager.getSearches(
                            request.params("user"))));
        });

        get("/poi/historical/:dateFrom/:dateTo", (request, response) -> {
            response.type("application/json");
            return jsonFactory.toJson(Formatter.historicalSearchToDTO(
                    historicalManager.getSearches(
                            Formatter.stringToDateTime(request.params("dateFrom")),
                            Formatter.stringToDateTime(request.params("dateTo")))));
        });

    }
}
