package pointsOfInterest;

import pointsOfInterest.pointOfInterest.domain.POI;
import pointsOfInterest.pointOfInterest.service.POIService;
import pointsOfInterest.pointOfInterest.types.ManagementAndParticipationCenter;
import pointsOfInterest.pointOfInterest.types.TypeOfPOI;
import pointsOfInterest.position.Coordenate;

public class App {

	public static void main(String[] args) {
		
		//PRUEBA
		Coordenate originCoordenate = new Coordenate(-34.598533, -58.420084);
		Coordenate destinationCoordenate = new Coordenate(-34.659706, -58.467852);
		TypeOfPOI typeMPC = new ManagementAndParticipationCenter();

		POI poiMedrano = new POI("Medrano", originCoordenate, typeMPC);
		POI poiCampus = new POI("Campus", destinationCoordenate, typeMPC);
		
		POIService poiService = new POIService();
		
		System.out.println(poiService.isValid(poiMedrano));
		System.out.println(poiService.isValid(poiCampus));
		System.out.println(poiService.isCloserTo(4000, poiMedrano, poiCampus));
		System.out.println(poiService.isCloserTo(80000, poiCampus, poiMedrano));
	}

}
