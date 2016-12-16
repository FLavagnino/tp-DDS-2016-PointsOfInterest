package ar.edu.utn.dds.poi.web.controller;

import ar.edu.utn.dds.poi.auth.AuthManager;
import ar.edu.utn.dds.poi.repository.UserRepository;
import ar.edu.utn.dds.poi.service.ActionService;
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
    private static UserRepository userRepository;
    private static ActionService actionService;

    @SuppressWarnings("unchecked")
	public static void start() 
    {
        searcher = new Historical();
        jsonFactory = new JsonFactory();
        historicalManager = HistoricalManager.getInstance();
        userRepository = new UserRepository();
        actionService = new ActionService();

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
            
            return jsonFactory.toJson(historicalManager.getSearches(
        								request.params("user")));
        });

        post("/poi/historical/:dateFrom/:dateTo", (request, response) -> 
        {
            response.type("application/json");
            
            return jsonFactory.toJson(historicalManager.getSearches(
                                        Formatter.stringToDateTime(request.params("dateFrom")),
                                        Formatter.stringToDateTime(request.params("dateTo"))));
        });

        post("/poi/login", (request, response) -> 
        {
            HashMap<String,String> user = new ObjectMapper().readValue(request.body(), HashMap.class);
            return AuthManager.getInstance().login(user.get("user"), user.get("pass"));
        });

        post("/poi/users", (request, response) ->
        {
            response.type("application/json");
            HashMap<String,String> user = new ObjectMapper().readValue(request.body(), HashMap.class);
            if(AuthManager.getInstance().validate(user.get("user"), user.get("token"))) {
                return jsonFactory.toJson(Formatter.usersNameDto(userRepository.getAll()));
            }
            return null;
        });

        post("/poi/:user/actions", (request, response) -> {
            response.type("application/json");
            HashMap<String,String> user = new ObjectMapper().readValue(request.body(), HashMap.class);
            if(AuthManager.getInstance().validate(user.get("user"), user.get("token"))) {
                return jsonFactory.toJson(Formatter.userActionsDto(userRepository.getUser(request.params("user"))));
            }
            return null;
        });

//        post("/poi/:user/actions/save", (request, response) -> {
//            response.type("application/json");
//            HashMap<String,String> user = new ObjectMapper().readValue(request.body(), HashMap.class);
//            if(AuthManager.getInstance().validate(user.get("user"), user.get("token"))) {
//                User u = userRepository.getUser(request.params("user"));
//                return jsonFactory.toJson(actionService
//                        .updateActionsToUser(u, Formatter.toActions(request.params("actions"), u))));
//            }
//            return null;
//        });

    }
}
