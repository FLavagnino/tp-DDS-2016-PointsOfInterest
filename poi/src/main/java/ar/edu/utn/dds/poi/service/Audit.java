package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.auth.AuthManager;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.utils.*;

public class Audit implements Searcher 
{	
	private Historical historical;
	private AuthManager authManager;
	
	public Audit() 
	{
		historical = new Historical();
		authManager = new AuthManager();
	}
	
	@Override
	public SearchResult search(String filter, int userId) 
	{
		SearchResult searchResult = historical.search(filter, userId);
		
		if (searchResult.getTime() > (Constant.MAX_SEARCH_TIME * 1000)) 
		{
			String email = authManager.getMailOf(userId);
			EmailSender emailSender = new EmailSender(email);
			new Thread(emailSender).start();
		}
		
		return searchResult;
	}
}