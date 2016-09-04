package ar.edu.utn.dds.poi.service.historical;

import org.joda.time.DateTime;

public class HistoricalSearch {
	
	private Integer userId;
	private String filter;
	private Integer resultsNumber;
	private long time;
	private DateTime date;
	
	public HistoricalSearch(Integer userId, String filter, Integer resultsNumber, long time) {
		this.userId = userId;
		this.filter = filter;
		this.resultsNumber = resultsNumber;
		this.time = time;
		this.date = new DateTime();
	}
	
	public Integer getUserId() {
		return userId;
	}

	public String getFilter() {
		return filter;
	}

	public Integer getResultsNumber() {
		return resultsNumber;
	}

	public long getTime() {
		return time;
	}
	
	public DateTime getDate() {
		return date;
	}
	
	

}