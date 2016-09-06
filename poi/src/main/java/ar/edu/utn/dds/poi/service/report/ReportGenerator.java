package ar.edu.utn.dds.poi.service.report;

import java.util.List;
import org.joda.time.DateTime;
import ar.edu.utn.dds.poi.service.historical.HistoricalManager;
import ar.edu.utn.dds.poi.service.historical.HistoricalSearch;

public class ReportGenerator 
{
	private HistoricalManager historicalManager;
	
	public ReportGenerator()
	{
		this.historicalManager = HistoricalManager.getInstance();
	}
	
	public void totalSearchQtyByDate(DateTime date)
	{
		System.out.println("---- Total Search Qty By Date ----\n\n");
		
		List<HistoricalSearch> searchList = this.historicalManager.getSearches();
		
		for (HistoricalSearch histSearch : searchList)
		{
			System.out.println(histSearch.getUserId() + " " + histSearch.getResultsNumber());
		}
		
//		List<User> users = userList.stream()
//				.filter(item -> item.getUserName().equals(userName) && 
//								item.getToken().equals(token))
//				.collect(Collectors.toList());
	}
	
	public void partialSearchQtyByUser(String userName)
	{
		System.out.println("hello system out");
	}
	
	public void totalSearchQtyByUser(String userName)
	{
		System.out.println("hello system out");
	}
}
