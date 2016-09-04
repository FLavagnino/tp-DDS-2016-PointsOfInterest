package ar.edu.utn.dds.poi.service;


import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.poi.service.historical.HistoricalManager;
import ar.edu.utn.dds.poi.service.historical.HistoricalSearch;
import ar.edu.utn.dds.poi.service.historical.SearchResult;

public class Historical implements Searcher {
	
	private Timer timer;
	private HistoricalManager historicalManager;
	
	private List<HistoricalSearch> searches = new ArrayList<HistoricalSearch>();
	
	public Historical() {
		timer = new Timer();
		historicalManager = HistoricalManager.getInstance();
	}

	@Override
	public SearchResult search(String filter) {
		SearchResult searchResult = timer.search(filter);
		//TODO El 1 representa el userID que va a haber que pasarle al constructor cuando este desarrollado lo de usuarios
		historicalManager.saveSearch(new HistoricalSearch(1, filter, searchResult.getPois().size(), searchResult.getTime()));
		return searchResult;
	}

	public List<HistoricalSearch> getSearches() {
		return searches;
	}
	
 

}
