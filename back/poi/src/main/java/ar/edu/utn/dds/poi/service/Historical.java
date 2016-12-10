package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.poi.domain.Log;
import ar.edu.utn.dds.poi.domain.LogResult;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.service.historical.*;

public class Historical implements Searcher 
{
	private Timer timer;
	private HistoricalManager historicalManager;

	public Historical()
	{
		timer = new Timer();
		historicalManager = HistoricalManager.getInstance();
	}

	@Override
	public SearchResult search(String filter, String userName) 
	{
		SearchResult searchResult = timer.search(filter, userName);
		
		List<LogResult> results = new ArrayList<>();
		
		for(POI poi : searchResult.getPois())
		{
			results.add(new LogResult(poi.getName()));
		}
		
		historicalManager.saveSearch(new Log(userName, results, filter, searchResult.getPois().size(), searchResult.getTime()));
		return searchResult;
	}

}
