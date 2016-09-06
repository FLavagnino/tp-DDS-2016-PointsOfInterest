package ar.edu.utn.dds.poi.service.historical;

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
}
