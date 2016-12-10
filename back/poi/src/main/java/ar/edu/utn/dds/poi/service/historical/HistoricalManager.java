package ar.edu.utn.dds.poi.service.historical;

import ar.edu.utn.dds.poi.domain.Log;
import ar.edu.utn.dds.poi.repository.LogRepository;
import ar.edu.utn.dds.poi.utils.MongoDB;
import org.bson.Document;
import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoricalManager 
{
	private static HistoricalManager instance = null;
	private List<Log> searches = new ArrayList<Log>();
	
	private HistoricalManager() 
	{
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
		this.searches.add(historicalSearch);
		
		// Grabo en la db
		LogRepository logRep = new LogRepository();
		logRep.saveHistoricalSearch(historicalSearch);
		
		MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches").insertOne(
				Document.parse(historicalSearch.toJson())
		);
	}
	
	public List<Log> getSearches()
	{
		return this.searches;
	}
	
	public void setSearches(List<Log> searches)
	{
		this.searches = searches;

        List<Document> documents = new ArrayList<>();
        for(Log search : searches) {
            documents.add(Document.parse(search.toJson()));
        }
		MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches")
				.insertMany(documents);
	}

	public List<Log> getSearches(String user) {
		List<Log> searchesFiltered = new ArrayList<>();
		for (Document document : MongoDB.getInstance().getMongoDatabase()
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
		for (Document document : MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse("{date: {$gte:" + from + ",$lt:" + to + "}}"))) {

			searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
	}

    private List<Log> getSearchesFrom(DateTime from) {
		List<Log> searchesFiltered = new ArrayList<>();
		for (Document document : MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse("{date: {$gte:" + from + "}}"))) {

			searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
    }

    private List<Log> getSearchesTo(DateTime to) {
		List<Log> searchesFiltered = new ArrayList<>();
		for (Document document : MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches")
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
