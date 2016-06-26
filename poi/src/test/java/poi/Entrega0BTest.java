package poi;

import static org.junit.Assert.*;
import org.junit.Test;
import poi.domain.*;
import poi.service.*;

public class Entrega0BTest {

	@Test
	public void isValidMedranoTest() 
	{
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Shop poiMedrano = new Shop("Medrano", medranoCoordenate);
		
		POIService poiService = new POIService();
		assertTrue(poiService.isValid(poiMedrano));
	}
	
	@Test
	public void isValidCampusTest() 
	{
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);
		Shop poiCampus = new Shop("Campus", campusCoordenate);
		
		POIService poiService = new POIService();
		assertTrue(poiService.isValid(poiCampus));
	}
	
	@Test
	public void metersFromToMoreThan4000mtsTest() 
	{
		POIService poiService = new POIService();
		int expected = 4000;
		
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate);
		Shop poiCampus = new Shop("Campus", campusCoordenate);
		
		assertTrue(expected <= poiService.metersFromTo(poiMedrano, poiCampus));
	}
	
	@Test
	public void metersFromToLessThan80000mtsTest() 
	{
		POIService poiService = new POIService();
		int expected = 80000;
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate);
		Shop poiCampus = new Shop("Campus", campusCoordenate);
		
		assertFalse(expected <= poiService.metersFromTo(poiMedrano, poiCampus));
	}
}
