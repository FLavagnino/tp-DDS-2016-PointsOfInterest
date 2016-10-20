package ar.edu.utn.dds.poi.service.historical;

import ar.edu.utn.dds.poi.domain.POI;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class HistoricalManager 
{
	private static HistoricalManager instance = null;
	private List<HistoricalSearch> searches = new ArrayList<HistoricalSearch>();
	
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
	
	public void saveSearch(HistoricalSearch historicalSearch) 
	{
		this.searches.add(historicalSearch);
	}
	
	public List<HistoricalSearch> getSearches()
	{
		return this.searches;
	}
	
	public void setSearches(List<HistoricalSearch> searches)
	{
		this.searches = searches;
	}

	public List<HistoricalSearch> getSearches(String user) {
		List<HistoricalSearch> searchesFiltered = new ArrayList<>();
		for (HistoricalSearch historicalSearch : getSearches()) {
			if (historicalSearch.getUserName().equals(user)) {
				searchesFiltered.add(historicalSearch);
			}
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
		for (HistoricalSearch historicalSearch : getSearches()) {
			if (historicalSearch.getDate().isAfter(from) && historicalSearch.getDate().isBefore(to)) {
				searchesFiltered.add(historicalSearch);
			}
		}
		return searchesFiltered;
	}

    private List<HistoricalSearch> getSearchesFrom(DateTime from) {
        List<HistoricalSearch> searchesFiltered = new ArrayList<>();
        for (HistoricalSearch historicalSearch : getSearches()) {
            if (historicalSearch.getDate().isAfter(from)) {
                searchesFiltered.add(historicalSearch);
            }
        }
        return searchesFiltered;
    }

    private List<HistoricalSearch> getSearchesTo(DateTime to) {
        List<HistoricalSearch> searchesFiltered = new ArrayList<>();
        for (HistoricalSearch historicalSearch : getSearches()) {
            if (historicalSearch.getDate().isBefore(to)) {
                searchesFiltered.add(historicalSearch);
            }
        }
        return searchesFiltered;
    }
}
