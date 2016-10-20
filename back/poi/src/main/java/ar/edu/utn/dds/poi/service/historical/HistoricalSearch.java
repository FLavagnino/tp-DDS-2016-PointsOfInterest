package ar.edu.utn.dds.poi.service.historical;

import org.joda.time.DateTime;

public class HistoricalSearch 
{
	private String userName;
	private String filter;
	private Integer resultsNumber;
	private long time;
	private DateTime date;

	public HistoricalSearch(String userName, String filter, Integer resultsNumber, long time) 
	{
		this.userName = userName;
		this.filter = filter;
		this.resultsNumber = resultsNumber;
		this.time = time;
		this.date = new DateTime();
	}
	
	public HistoricalSearch(String userName, String filter, Integer resultsNumber, long time, DateTime date) 
	{
		this.userName = userName;
		this.filter = filter;
		this.resultsNumber = resultsNumber;
		this.time = time;
		this.date = date;
	}
	
	public String getUserName() 
	{
		return userName;
	}

	public String getFilter() 
	{
		return filter;
	}

	public Integer getResultsNumber() 
	{
		return resultsNumber;
	}

	public long getTime() 
	{
		return time;
	}
	
	public DateTime getDate() 
	{
		return date;
	}
}
