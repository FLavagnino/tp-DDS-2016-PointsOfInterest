package ar.edu.utn.dds.poi.test;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import ar.edu.utn.dds.poi.domain.Bank;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.service.POIService;

public class Entrega2Test 
{
	@Test
	public void bankServiceSearchByNameTest()
	{
		String filter = "Galicia";		
		POIService poiService = new POIService();		
		List<POI> result = poiService.search(filter);
		
		assertTrue(result.size() == 1);
		assertTrue(((Bank)result.get(0)).getName().equals("Galicia"));
	}
	
	@Test
	public void bankServiceSearchByNameAndTagTest()
	{
		String filter = "Galicia";		
		POIService poiService = new POIService();		
		List<POI> result = poiService.search(filter);
		
		assertTrue(result.size() == 1);
		assertTrue(((Bank)result.get(0)).getName().equals("Galicia"));
		assertTrue(((Bank)result.get(0)).getTags().contains("Moneda Extrangera"));
	}
	
}
