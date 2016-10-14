package ar.edu.utn.dds.poi.controller;

import ar.edu.utn.dds.poi.service.Historical;
import ar.edu.utn.dds.poi.service.Searcher;
import ar.edu.utn.dds.poi.utils.Formatter;
import ar.edu.utn.dds.poi.utils.JsonFactory;

import static spark.Spark.*;

public class PoiController {

    private static Searcher searcher;
    private static JsonFactory jsonFactory;
    private static Formatter formatter;

    public static void start() {
        searcher = new Historical();
        jsonFactory = new JsonFactory();
        formatter = new Formatter();

        get("/poi/search/:keyword", (request, response) -> {
            String filter = formatter.keywordToFilter(request.params("keyword"));
            response.type("application/json");
            return jsonFactory.toJson(searcher.search(filter, ""));
        });
    }
}
