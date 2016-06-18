package poi;

import poi.domain.*;
import poi.service.POIService;

public class App 
{
	public static void main(String[] args) 
	{	
		//PRUEBA
		Coordenate originCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate destinationCoordenate = new Coordenate(-34.659706, -58.467852);

		System.out.println("E0B - POC - Curso K3051");
		
		System.out.println("Creamos el POI Medrano");
		Shop poiMedrano = new Shop("Medrano", originCoordenate);
		
		System.out.println("Creamos el POI Campus");
		Shop poiCampus = new Shop("Campus", destinationCoordenate);
		
		POIService poiService = new POIService();
		
		System.out.println("Es valido [" + poiMedrano.getName() + "]: " + poiService.isValid(poiMedrano));
		System.out.println("Es valido [" + poiCampus.getName() + "]: " + poiService.isValid(poiCampus));
		System.out.println("Esta a menos de 4000mts [" + poiMedrano.getName() + "] de [" + poiCampus.getName() + "]: " + poiService.isCloserTo(4000, poiMedrano, poiCampus));
		System.out.println("Esta a menos de 8000mts [" + poiCampus.getName() + "] de [" + poiMedrano.getName() + "]: " + poiService.isCloserTo(8000, poiCampus, poiMedrano));
	}
}
