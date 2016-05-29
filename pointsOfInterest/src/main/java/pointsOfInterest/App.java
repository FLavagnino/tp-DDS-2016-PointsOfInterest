package pointsOfInterest;

import pointsOfInterest.pointOfInterest.domain.POI;
import pointsOfInterest.pointOfInterest.service.POIService;
import pointsOfInterest.pointOfInterest.types.ManagementAndParticipationCenter;
import pointsOfInterest.pointOfInterest.types.TypeOfPOI;
import pointsOfInterest.position.Coordenate;

public class App 
{
	public static void main(String[] args) 
	{	
		//PRUEBA
		Coordenate originCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate destinationCoordenate = new Coordenate(-34.659706, -58.467852);
		TypeOfPOI typeMPC = new ManagementAndParticipationCenter();

		System.out.println("E0B - POC");
		
		System.out.println("Creamos el POI Medrano");
		POI poiMedrano = new POI("Medrano", originCoordenate, typeMPC);
		
		System.out.println("Creamos el POI Campus");
		POI poiCampus = new POI("Campus", destinationCoordenate, typeMPC);
		
		POIService poiService = new POIService();
		
		System.out.println("Es valido [" + poiMedrano.getName() + "]: " + poiService.isValid(poiMedrano));
		System.out.println("Es valido [" + poiCampus.getName() + "]: " + poiService.isValid(poiCampus));
		System.out.println("Esta a 4000mts [" + poiMedrano.getName() + "] de [" + poiCampus.getName() + "]: " + poiService.isCloserTo(4000, poiMedrano, poiCampus));
		System.out.println("Esta a 80000mts [" + poiCampus.getName() + "] de [" + poiMedrano.getName() + "]: " + poiService.isCloserTo(80000, poiCampus, poiMedrano));
	}
}
