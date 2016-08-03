package ar.edu.utn.dds.poi.test;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import ar.edu.utn.dds.poi.domain.BusStop;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.exceptions.InvalidPoiException;
import ar.edu.utn.dds.poi.service.POIService;

public class Entrega2Test 
{
	
	private POIService poiService;
	private String filter;
	private List<POI> result;
	private POI poi;
	private Coordenate coordenate;

	@Before
	public void setUp()
	{
		poiService = new POIService();
		coordenate = new Coordenate(-34.619160, -58.425443);
	}
	
	@Test
	public void bankServiceSearchByNameTest()
	{
		filter = "Galicia";		
		result = poiService.search(filter);
		
		poi = result.get(0);
		assertEquals(1, result.size());
		assertEquals("Galicia", poi.getName());
	}
	
	@Test
	public void bankServiceSearchByNameAndTagTest()
	{
		filter = "Moneda Extranjera";		
		result = poiService.search(filter);

		poi = result.get(0);
		assertEquals(1, result.size());
		assertTrue(poi.getTags().toLowerCase().contains("moneda extranjera"));
	}
	
	@Test
	public void cgpServiceSearchByNameTest()
	{
		filter = "comuna 3";	
		result = poiService.search(filter);
		
		assertEquals(10, result.size());
	}
	
	@Test
	public void cgpServiceSearchByTagTest()
	{
		filter = "Villa Devoto";	
		result = poiService.search(filter);
		
		assertEquals(1, result.size());
	}
	
	@Test (expected = InvalidPoiException.class)
	public void addInvalidPOITest() throws InvalidPoiException
	{
		// We create the busStop POI
		poi = new BusStop(null, coordenate, 114, "tag1,tag2");
		
		poiService.addPoi(poi);
	}
	
	@Test
	public void addPOITest() throws InvalidPoiException
	{
		// We create the busStop POI
		poi = new BusStop("Parada 114", coordenate, 114, "tag1,tag2");
		
		poiService.addPoi(poi);
		
		result = poiService.search("114");
		assertEquals(1, result.size());
	}
	
	@Test
	public void deletePOITest() throws InvalidPoiException
	{
		// We create the busStop POI
		poi = new BusStop("Parada 114", coordenate, 114, "tag1,tag2");
		poi.setUnit(1);
		
		poiService.addPoi(poi);
		
		result = poiService.search("114");
		assertEquals(1, result.size());	
		
		poiService.deletePoi(poi.getUnit());
		
		result = poiService.search("114");
		assertEquals(0, result.size());	
	}
	
	@Test(expected = InvalidPoiException.class)
	public void updateInvalidPOITest() throws InvalidPoiException
	{
		// We create the busStop POI
		
		BusStop poi = new BusStop("Parada 114", coordenate, 114, "tag1,tag2");
		poi.setUnit(1);
		
		poiService.addPoi(poi);
		
		result = poiService.search("114");
		assertEquals(1, result.size());	
		
		poi.setName(null);
		poiService.updatePoi(poi);
	}
	
	@Test
	public void updatePOITest() throws InvalidPoiException
	{
		// We create the busStop POI
		poi = new BusStop("Parada 114", coordenate, 114, "tag1,tag2");
		poi.setUnit(1);
		
		poiService.addPoi(poi);
		
		result = poiService.search("114");
		assertEquals(1, result.size());	
		
		poi.setName("Parada del 115");
		((BusStop) poi).setBusLine(115);
		poiService.updatePoi(poi);

		result = poiService.search("114");
		assertEquals(0, result.size());	
		
		result = poiService.search("115");
		assertEquals(1, result.size());	
	}
}
