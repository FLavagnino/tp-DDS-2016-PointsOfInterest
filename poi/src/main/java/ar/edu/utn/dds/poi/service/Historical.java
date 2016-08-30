package ar.edu.utn.dds.poi.service;


import java.util.ArrayList;
import java.util.List;

public class Historical implements Searcher {
	
	private Timer timer;
	
	private List<HistoricalSearch> searches = new ArrayList<HistoricalSearch>();
	
	public Historical() {
		timer = new Timer();
	}

	@Override
	public SearchResult search(String filter) {
		SearchResult searchResult = timer.search(filter);
		searches.add(new HistoricalSearch(filter, searchResult.getPois().size(), searchResult.getTime()));
		return searchResult;
	}

}
