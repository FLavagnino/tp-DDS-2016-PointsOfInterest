package ar.edu.utn.dds.poi.service;

import java.util.List;

import ar.edu.utn.dds.poi.domain.POI;

public class SearchResult {
	
	private List<POI> pois;
	private long time;
	
	public SearchResult(List<POI> pois) {
		this.pois = pois;
	}

	public List<POI> getPois() {
		return pois;
	}
	
	public void setTime(long time) {
		this.time = time;
	}

	public long getTime() {
		return time;
	}

}
