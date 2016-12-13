package ar.edu.utn.dds.poi.service.historical;

import ar.edu.utn.dds.poi.domain.Log;
import ar.edu.utn.dds.poi.dto.HistoricalSearchDTO;
import ar.edu.utn.dds.poi.dto.HistoricalSearchResponseDTO;
import ar.edu.utn.dds.poi.repository.LogRepository;
import ar.edu.utn.dds.poi.repository.MongoManager;
import ar.edu.utn.dds.poi.utils.Formatter;
import ar.edu.utn.dds.poi.utils.JsonFactory;
import org.bson.Document;
import org.joda.time.DateTime;
import java.util.ArrayList;
import java.util.List;

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
        for(Log search : searches) 
        {
            documents.add(Document.parse(jsonFactory.toJson(Formatter.logDTO(search))));
        }
        
		MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches")
				.insertMany(documents);
	}

	public HistoricalSearchResponseDTO getSearches(String user) 
	{
		List<HistoricalSearchDTO> searchesFiltered = new ArrayList<>();
		
		for (Document document : MongoManager.getInstance().getMongoDatabase()
				.getCollection("historical_searches").find(new Document("user_name", user))) 
		{
            searchesFiltered.add(createHistoricalSearch(document));
		}
		
		return new HistoricalSearchResponseDTO(searchesFiltered);
	}

	public HistoricalSearchResponseDTO getSearches(DateTime from, DateTime to) 
	{
        if(to == null) 
        {
            return getSearchesFrom(from);
        }
        else if(from == null) 
        {
            return getSearchesTo(to);
        }
        else
        {
			List<HistoricalSearchDTO> searchesFiltered = new ArrayList<>();
			String jsonQuery = "{date: {$gte:\"" + from.toString("dd/MM/yyyy") + "\",$lt:\"" + to.toString("dd/MM/yyyy") + "\"}}";
			
			for (Document document : MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches")
					.find(Document.parse(jsonQuery))) 
			{
				searchesFiltered.add(createHistoricalSearch(document));
			}
			
			return new HistoricalSearchResponseDTO(searchesFiltered);
        }
	}

    private HistoricalSearchResponseDTO getSearchesFrom(DateTime from) 
    {
		List<HistoricalSearchDTO> searchesFiltered = new ArrayList<>();
		
		String jsonQuery = "{date: {$gte:\"" + from.toString("dd/MM/yyyy") + "\"}}";
				
		for (Document document : MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse(jsonQuery))) 
		{
			searchesFiltered.add(createHistoricalSearch(document));
		}
		
		return new HistoricalSearchResponseDTO(searchesFiltered);
    }

    private HistoricalSearchResponseDTO getSearchesTo(DateTime to) 
    {
		List<HistoricalSearchDTO> searchesFiltered = new ArrayList<>();
		
		String jsonQuery = "{date: {$lt:\"" + to.toString("dd/MM/yyyy") + "\"}}";
				
		for (Document document : MongoManager.getInstance().getMongoDatabase().getCollection("historical_searches")
				.find(Document.parse(jsonQuery))) 
		{
			searchesFiltered.add(createHistoricalSearch(document));
		}
		
		return new HistoricalSearchResponseDTO(searchesFiltered);
    }

    @SuppressWarnings("unchecked")
	private HistoricalSearchDTO createHistoricalSearch(Document document) 
    {
		return new HistoricalSearchDTO(
				document.getString("user_name"),
				document.getString("filter"),
				Integer.valueOf(document.get("results_number").toString()),
				Long.valueOf(document.get("time").toString()),
				document.getString("date"),
				(List<String>) document.get("results_name"));
	}
}
