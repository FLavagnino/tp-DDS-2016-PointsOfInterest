package poi;

import static org.junit.Assert.*;

import org.junit.Test;

import poi.domain.Coordenate;
import poi.domain.Shop;
import poi.service.POIService;

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
	public void isNotCloserTo4000mtsMedranoFromCampusTest() 
	{
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate);
		Shop poiCampus = new Shop("Campus", campusCoordenate);
		
		POIService poiService = new POIService();
		assertFalse(poiService.isCloserTo(4000, poiMedrano, poiCampus));
	}
	
	@Test
	public void isCloserTo80000mtsMedranoFromCampusTest() 
	{
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate);
		Shop poiCampus = new Shop("Campus", campusCoordenate);
		
		POIService poiService = new POIService();
		assertTrue(poiService.isCloserTo(80000, poiMedrano, poiCampus));
	}
}
