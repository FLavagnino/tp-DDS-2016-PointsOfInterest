package ar.edu.utn.dds.poi.test;

import static org.junit.Assert.*;
import org.junit.Test;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.Shop;
import ar.edu.utn.dds.poi.service.POIService;

public class Entrega0BTest {

	@Test
	// Prueba para comparar el metodo tradicional Haversine vs el calculo que hace google
	// google siempre deberia dar una distancia mayor, ya que calcula por cuadras.
	public void haversineVSGoogleTest() 
	{
		POIService poiService = new POIService();

		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate, "muebleria", 1000, "tag1,tag2");
		Shop poiCampus = new Shop("Campus", campusCoordenate, "muebleria", 1000, "tag1,tag2");
		
		int google = poiService.metersFromTo(poiMedrano, poiCampus);
		int haversine = poiService.metersFromToHaversine(poiMedrano, poiCampus);
		
		assertTrue(google >= haversine);
	}
	
	@Test
	// Controla que el POI sea valido, por lo tanto que tenga los datos minimos para poder
	// persistirlo
	public void isValidMedranoTest() 
	{
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Shop poiMedrano = new Shop("Medrano", medranoCoordenate, "muebleria", 1000, "tag1,tag2");
		
		POIService poiService = new POIService();
		assertTrue(poiService.isValid(poiMedrano));
	}
	
	@Test
	// Controla que el POI sea valido, por lo tanto que tenga los datos minimos para poder
	// persistirlo
	public void isValidCampusTest() 
	{
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);
		Shop poiCampus = new Shop("Campus", campusCoordenate, "muebleria", 1000, "tag1,tag2");
		
		POIService poiService = new POIService();
		assertTrue(poiService.isValid(poiCampus));
	}
	
	@Test
	// Controla que la distancia entre los pois sea mayor a la ingresada en la prueba
	// En este caso 4000 mts
	public void metersFromToMoreThan4000mtsTest() 
	{
		POIService poiService = new POIService();
		int expected = 4000;
		
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate, "muebleria", 1000, "tag1,tag2");
		Shop poiCampus = new Shop("Campus", campusCoordenate, "muebleria", 1000, "tag1,tag2");
		
		assertTrue(expected <= poiService.metersFromTo(poiMedrano, poiCampus));
	}
	
	@Test
	// Controla que la distancia entre los pois sea menor a la ingresada en la prueba
	// En este caso 80000 mts
	public void metersFromToLessThan80000mtsTest() 
	{
		POIService poiService = new POIService();
		int expected = 80000;
		Coordenate medranoCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate campusCoordenate = new Coordenate(-34.659706, -58.467852);

		Shop poiMedrano = new Shop("Medrano", medranoCoordenate, "muebleria", 1000, "tag1,tag2");
		Shop poiCampus = new Shop("Campus", campusCoordenate, "muebleria", 1000, "tag1,tag2");
		
		assertFalse(expected <= poiService.metersFromTo(poiMedrano, poiCampus));
	}
}
