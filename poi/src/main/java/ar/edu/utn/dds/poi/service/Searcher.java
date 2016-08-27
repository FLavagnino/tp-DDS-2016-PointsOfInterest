package ar.edu.utn.dds.poi.service;

import java.util.List;

import ar.edu.utn.dds.poi.domain.POI;

public interface Searcher {

	public List<POI> search(String filter);
}
