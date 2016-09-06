package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.utils.*;

public class Audit implements Searcher 
{	
	private Historical historical;
	
	public Audit() 
	{
		historical = new Historical();
	}
	
	@Override
	public SearchResult search(String filter, String userName) 
	{
		SearchResult searchResult = historical.search(filter, userName);
		
		if (searchResult.getTime() > (Constant.MAX_SEARCH_TIME * 1000)) 
		{
			EmailSender emailSender = new EmailSender(Constant.MAX_SEARCH_TIME_MAILTO);
			new Thread(emailSender).start();
		}
		
		return searchResult;
	}
}