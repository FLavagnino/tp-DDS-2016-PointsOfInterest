package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.service.historical.*;

public class Historical implements Searcher 
{
	private Timer timer;
	private HistoricalManager historicalManager;

	private int searchKey;

	public Historical() 
	{
		this.timer = new Timer();
		this.historicalManager = HistoricalManager.getInstance();
	}

	@Override
	public SearchResult search(String filter, String userName) 
	{
		SearchResult searchResult = timer.search(filter, userName);

		HistoricalSearch historicalSearch = new HistoricalSearch(
				userName, filter, searchResult.getPois().size(), searchResult.getTime());
		historicalSearch.setSearchResult(searchResult);
		historicalSearch.setSearchKey(searchKey);

		historicalManager.saveSearch(historicalSearch);

		return searchResult;
	}

	public void setSearchKey(int searchKey) {
		this.searchKey = searchKey;
	}
}
