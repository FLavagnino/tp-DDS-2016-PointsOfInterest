package ar.edu.utn.dds.poi.service;

public class HistoricalSearch {
	
	private String filter;
	private Integer resultsNumber;
	private long time;
	
	public HistoricalSearch(String filter, Integer resultsNumber, long time) {
		this.filter = filter;
		this.resultsNumber = resultsNumber;
		this.time = time;
	}

}
