package ar.edu.utn.dds.poi.service.historical;

import ar.edu.utn.dds.poi.domain.POI;

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

	public POI getPoi(int key, int searchKey) {
		for (HistoricalSearch historicalSearch : this.getSearches()) {
			if(historicalSearch.getSearchKey() == searchKey) {
				for(POI poi : historicalSearch.getSearchResult().getPois()) {
					if(poi.getKey() == key) {
						return poi;
					}
				}
			}
		}
		return null;
	}
}
