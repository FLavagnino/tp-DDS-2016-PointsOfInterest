package ar.edu.utn.dds.poi.controller;

import ar.edu.utn.dds.poi.service.Historical;
import ar.edu.utn.dds.poi.utils.Formatter;
import ar.edu.utn.dds.poi.utils.JsonFactory;

import static spark.Spark.*;

public class PoiController {

    private static Historical searcher;
    private static JsonFactory jsonFactory;
    private static Formatter formatter;

    public static void start() {
        searcher = new Historical();
        jsonFactory = new JsonFactory();
        formatter = new Formatter();

        get("/poi/search/:keyword", (request, response) -> {
            //TODO: colocar user de login y voletear esto de abajo
            String userName = "lala";
            response.cookie("user", userName);

            response.type("application/json");

            return jsonFactory.toJson(searcher.search(
                            formatter.keywordToFilter(request.params("keyword")),
                            userName));
        });

    }
}
