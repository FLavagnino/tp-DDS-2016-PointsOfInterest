package ar.edu.utn.dds.poi.service.historical;

import ar.edu.utn.dds.poi.domain.Log;
import ar.edu.utn.dds.poi.repository.LogRepository;
import ar.edu.utn.dds.poi.repository.MongoManager;

import ar.edu.utn.dds.poi.utils.Formatter;
import ar.edu.utn.dds.poi.utils.JsonFactory;
import org.bson.Document;
import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoricalManager 
{
	private static HistoricalManager instance = null;
	private List<Log> searches = new ArrayList<Log>();

	JsonFactory jsonFactory;
	
	private HistoricalManager() 
	{
		jsonFactory = new JsonFactory();
	}
	
	public static HistoricalManager getInstance() 
	{
		if(instance == null) 
		{
			instance = new HistoricalManager();
		}
		
		return instance;
	}
	
	public void saveSearch(Log historicalSearch) 
	{
		searches.add(historicalSearch);
		
		// Grabo en la db
		LogRepository logRep = new LogRepository();
		logRep.saveHistoricalSearch(historicalSearch);
		
		MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches").insertOne(
				Document.parse(jsonFactory.toJson(Formatter.logDTO(historicalSearch)))
		);
	}
	
	public List<Log> getSearches()
	{
		return searches;
	}
	
	public void setSearches(List<Log> searches)
	{
		this.searches = searches;

        List<Document> documents = new ArrayList<>();
        for(Log search : searches) {
            documents.add(Document.parse(jsonFactory.toJson(Formatter.logDTO(search))));
        }
		MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches")
				.insertMany(documents);
	}

	public List<Log> getSearches(String user) {
		List<Log> searchesFiltered = new ArrayList<>();
		for (Document document : MongoManager.getInstance().getMongoDatabase()
				.getCollection("historical_searches").find(new Document("user_name", user))) {

            searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
	}

	public List<Log> getSearches(DateTime from, DateTime to) {
        if(to == null) {
            return getSearchesFrom(from);
        }
        if(from == null) {
            return getSearchesTo(to);
        }

		List<Log> searchesFiltered = new ArrayList<>();
		for (Document document : MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse("{date: {$gte:" + from + ",$lt:" + to + "}}"))) {

			searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
	}

    private List<Log> getSearchesFrom(DateTime from) {
		List<Log> searchesFiltered = new ArrayList<>();
		for (Document document : MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse("{date: {$gte:" + from + "}}"))) {

			searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
    }

    private List<Log> getSearchesTo(DateTime to) {
		List<Log> searchesFiltered = new ArrayList<>();
		for (Document document : MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse("{date: {$lt:" + to + "}}"))) {

			searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
    }

    private Log createHistoricalSearch(Document document) {
		return new Log(
				document.getString("user_name"),
				document.getString("filter"),
				Integer.valueOf(document.get("results_number").toString()),
				Long.valueOf(document.get("time").toString()),
				document.getString("date"));
	}
}
