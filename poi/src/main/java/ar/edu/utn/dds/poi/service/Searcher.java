package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.service.historical.SearchResult;

public interface Searcher 
{
	public SearchResult search(String filter);
}
