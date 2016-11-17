package ar.edu.utn.dds.poi.service;

import java.util.List;

import org.eclipse.collections.impl.list.mutable.ListAdapter;
import org.joda.time.DateTime;

import ar.edu.utn.dds.poi.domain.HistoricalSearch;
import ar.edu.utn.dds.poi.service.historical.HistoricalManager;

public class ReportService 
{
	private HistoricalManager historicalManager;
	
	public ReportService()
	{
		this.historicalManager = HistoricalManager.getInstance();
	}
	
	public void totalSearchQtyByDate()
	{
		System.out.println("\n---- Total Search Qty By Date ----\n");
		
		List<HistoricalSearch> searchList = this.historicalManager.getSearches();
		
		ListAdapter.adapt(searchList).sortThisBy(HistoricalSearch::getDate);
			
		DateTime currentDate = searchList.get(0).getDate();
		int searchQty = 0; 
		
		for (HistoricalSearch histSearch : searchList)
		{	
			if (histSearch.getDate().equals(currentDate)) 
			{
				searchQty = searchQty + histSearch.getResultsNumber();
			}
			else
			{
				System.out.println(currentDate.toString("dd/MM/yyyy") + " " + searchQty);
				currentDate = histSearch.getDate();
				searchQty = histSearch.getResultsNumber();
			}
		}
		
		System.out.println(currentDate.toString("dd/MM/yyyy") + " " + searchQty);
	}
	
	public void partialSearchQtyByUser()
	{
		System.out.println("\n---- Total Search Qty By User ----\n");
		
		List<HistoricalSearch> searchList = this.historicalManager.getSearches();
	
		String currentUser = searchList.get(0).getUserName();
		boolean printed = false;
		
		for (HistoricalSearch histSearch : searchList)
		{	
			if (histSearch.getUserName().equals(currentUser) && !printed) 
			{
				System.out.println("Usuario: " + histSearch.getUserName() + "\n");
				printed = true;
			}
			else if (!histSearch.getUserName().equals(currentUser))
			{
				currentUser = histSearch.getUserName();
				System.out.println("\nUsuario: " + histSearch.getUserName() + "\n");
			}
			
			System.out.println(histSearch.getResultsNumber());
		}
	}

	public void totalSearchQtyByUser()
	{
		System.out.println("\n---- Total Search Qty By User ----\n");
		
		List<HistoricalSearch> searchList = this.historicalManager.getSearches();
			
		String currentUser = searchList.get(0).getUserName();
		int searchQty = 0; 
		
		for(int i = 0; i < searchList.size(); i++)
		{	
			if (searchList.get(i).getUserName().equals(currentUser)) 
			{
				searchQty = searchQty + searchList.get(i).getResultsNumber();
			}
			else
			{
				System.out.println(currentUser + " " + searchQty);
				currentUser = searchList.get(i).getUserName();
				searchQty = searchList.get(i).getResultsNumber();
			}
		}
		
		System.out.println(currentUser + " " + searchQty);
	}
	
	public void setSearchResults(List<HistoricalSearch> searches)
	{
		this.historicalManager.setSearches(searches);
	}
}
