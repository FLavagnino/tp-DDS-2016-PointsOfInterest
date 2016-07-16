package ar.edu.utn.dds.poi.test;

import static org.junit.Assert.*;
import org.junit.Test;

import ar.edu.utn.dds.poi.constant.Category;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.service.*;

public class Entrega0BTest {

	@Test
	public void haversineVSGoogleTest() 
	{
		POIService poiService = new POIService();

		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate, Category.FURNITURE, "tag1,tag2");
		Shop poiCampus = new Shop("Campus", campusCoordenate, Category.FURNITURE, "tag1,tag2");
		
		int google = poiService.metersFromTo(poiMedrano, poiCampus);
		int haversine = poiService.metersFromToHaversine(poiMedrano, poiCampus);
		
		assertTrue(google >= haversine);
	}
	
	@Test
	public void isValidMedranoTest() 
	{
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Shop poiMedrano = new Shop("Medrano", medranoCoordenate, Category.FURNITURE, "tag1,tag2");
		
		POIService poiService = new POIService();
		assertTrue(poiService.isValid(poiMedrano));
	}
	
	@Test
	public void isValidCampusTest() 
	{
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);
		Shop poiCampus = new Shop("Campus", campusCoordenate, Category.FURNITURE, "tag1,tag2");
		
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

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate, Category.FURNITURE, "tag1,tag2");
		Shop poiCampus = new Shop("Campus", campusCoordenate, Category.FURNITURE, "tag1,tag2");
		
		assertTrue(expected <= poiService.metersFromTo(poiMedrano, poiCampus));
	}
	
	@Test
	public void metersFromToLessThan80000mtsTest() 
	{
		POIService poiService = new POIService();
		int expected = 80000;
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate, Category.FURNITURE, "tag1,tag2");
		Shop poiCampus = new Shop("Campus", campusCoordenate, Category.FURNITURE, "tag1,tag2");
		
		assertFalse(expected <= poiService.metersFromTo(poiMedrano, poiCampus));
	}
}
