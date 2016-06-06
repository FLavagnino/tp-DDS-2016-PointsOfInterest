package pointsOfInterest.pointOfInterest.types;

import pointsOfInterest.pointOfInterest.domain.POI;
import pointsOfInterest.pointOfInterest.service.POIService;
import pointsOfInterest.pointOfInterest.types.ManagementAndParticipationCenter;
import pointsOfInterest.pointOfInterest.types.TypeOfPOI;
import pointsOfInterest.position.Coordenate;

public class BusStop implements TypeOfPOI {
	
	Coordenate coordenateorigin = new Coordenate(-35.14152, -32.1223);
	private static final String NAME = "BusStop";

	public String getName() {
		return BusStop.NAME;
	}
	
	private boolean isCloserTo (Integer metersDistance, POI poiA, POI poiB) {
		if (metersDistance < 500)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}