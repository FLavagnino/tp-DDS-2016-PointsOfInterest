package ar.edu.utn.dds.poi.service.historical;

import ar.edu.utn.dds.poi.domain.HistoricalSearch;
import ar.edu.utn.dds.poi.utils.JsonFactory;
import ar.edu.utn.dds.poi.utils.MongoDB;
import org.bson.Document;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class HistoricalManager 
{
	private static HistoricalManager instance = null;

	private JsonFactory jsonFactory;

	private List<HistoricalSearch> searches = new ArrayList<HistoricalSearch>();
	
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
	
	public void saveSearch(HistoricalSearch historicalSearch) 
	{
		this.searches.add(historicalSearch);
		MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches").insertOne(
				Document.parse(jsonFactory.toJson(historicalSearch))
		);
	}
	
	public List<HistoricalSearch> getSearches()
	{
		return this.searches;
	}
	
	public void setSearches(List<HistoricalSearch> searches)
	{
		this.searches = searches;

        List<Document> documents = new ArrayList<>();
        for(HistoricalSearch search : searches) {
            documents.add(Document.parse(jsonFactory.toJson(search)));
        }
		MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches").insertMany(documents);
	}

	public List<HistoricalSearch> getSearches(String user) {
		List<HistoricalSearch> searchesFiltered = new ArrayList<>();
		for (Document document : MongoDB.getInstance().getMongoDatabase()
				.getCollection("historical_searches").find(new Document("user_name", user))) {

            searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
	}

	public List<HistoricalSearch> getSearches(DateTime from, DateTime to) {
        if(to == null) {
            return getSearchesFrom(from);
        }
        if(from == null) {
            return getSearchesTo(to);
        }

		List<HistoricalSearch> searchesFiltered = new ArrayList<>();
		for (Document document : MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse("{date: {$gte:" + from + ",$lt:" + to + "}}"))) {

			searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
	}

    private List<HistoricalSearch> getSearchesFrom(DateTime from) {
		List<HistoricalSearch> searchesFiltered = new ArrayList<>();
		for (Document document : MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse("{date: {$gte:" + from + "}}"))) {

			searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
    }

    private List<HistoricalSearch> getSearchesTo(DateTime to) {
		List<HistoricalSearch> searchesFiltered = new ArrayList<>();
		for (Document document : MongoDB.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse("{date: {$lt:" + to + "}}"))) {

			searchesFiltered.add(createHistoricalSearch(document));
		}
		return searchesFiltered;
    }

    private HistoricalSearch createHistoricalSearch(Document document) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		return new HistoricalSearch(
				document.getString("user_name"),
				document.getString("filter"),
				document.getInteger("results_number"),
				document.getLong("time"),
				formatter.parseDateTime(document.getString("date")));
	}
}
