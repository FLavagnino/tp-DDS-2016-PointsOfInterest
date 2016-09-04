package ar.edu.utn.dds.poi.test;

import static org.junit.Assert.*;
import org.junit.Before;

import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.service.historical.SearchResult;

public class Entrega3Test 
{	
	private POIService poiService;
	private String filter;
	private SearchResult result;
	private POI poi;
	private Coordenate coordenate;

	@Before
	public void setUp()
	{
		poiService = new POIService();
		coordenate = new Coordenate(-34.619160, -58.425443);
	}
}
