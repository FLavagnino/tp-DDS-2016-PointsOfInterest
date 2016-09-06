package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.service.historical.SearchResult;

public class Timer implements Searcher 
{	
	private POIService poiService;
	
	public Timer() 
	{
		poiService = new POIService();
	}

	@Override
	public SearchResult search(String filter, int userId) 
	{
		long startingTime = System.currentTimeMillis();
		SearchResult searchResult = poiService.search(filter);		
		long endingTime = System.currentTimeMillis();
		
		searchResult.setTime(endingTime - startingTime);
		return searchResult;
	}	
}