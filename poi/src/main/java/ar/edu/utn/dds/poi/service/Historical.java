package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.service.historical.*;

public class Historical implements Searcher 
{
	private Timer timer;
	private HistoricalManager historicalManager;

	public Historical() 
	{
		this.timer = new Timer();
		this.historicalManager = HistoricalManager.getInstance();
	}

	@Override
	public SearchResult search(String filter, int userId) 
	{
		SearchResult searchResult = timer.search(filter, userId);
		
		historicalManager.saveSearch(
							new HistoricalSearch(userId, 
													filter, 
													searchResult.getPois().size(), 
													searchResult.getTime())
							);
		
		return searchResult;
	}
}
