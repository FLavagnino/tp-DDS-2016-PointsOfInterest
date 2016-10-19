package ar.edu.utn.dds.poi.controller;

import ar.edu.utn.dds.poi.dto.PoiResultDTO;
import ar.edu.utn.dds.poi.service.Historical;
import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.service.Searcher;
import ar.edu.utn.dds.poi.utils.Formatter;
import ar.edu.utn.dds.poi.utils.JsonFactory;

import static spark.Spark.*;

public class PoiController {

    private static Integer searchKeySeed;

    private static Historical searcher;
    private static JsonFactory jsonFactory;
    private static Formatter formatter;
    private static POIService poiService;

    public static void start() {
        searchKeySeed = 0;

        searcher = new Historical();
        jsonFactory = new JsonFactory();
        formatter = new Formatter();
        poiService = new POIService();

        get("/poi/search/all/:keyword", (request, response) -> {
            //TODO: colocar user de login y voletear esto de abajo
            String userName = "lala";
            response.cookie("user", userName);

            response.type("application/json");

            searchKeySeed++;
            searcher.setSearchKey(searchKeySeed);


            return jsonFactory.toJson(new PoiResultDTO(
                    searcher.search(
                            formatter.keywordToFilter(request.params("keyword")),
                            userName),
                    searchKeySeed));
        });

        get("/poi/search/:poi/:searchKey", (request, response) -> {
            response.type("application/json");
            return jsonFactory.toJson(poiService.getPoi(
                    Integer.valueOf(request.params("poi")), Integer.valueOf(request.params("searchKey"))));
        });

    }
}
