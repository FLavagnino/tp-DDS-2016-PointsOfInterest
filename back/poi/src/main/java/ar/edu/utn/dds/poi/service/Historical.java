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
	public SearchResult search(String filter, String userName) 
	{
		SearchResult searchResult = timer.search(filter, userName);
		historicalManager.saveSearch(new HistoricalSearch(userName, filter, searchResult.getPois().size(), searchResult.getTime()));
		return searchResult;
	}

}
