package pointsOfInterest.pointOfInterest.service;

import pointsOfInterest.distanceService.DistanceService;
import pointsOfInterest.pointOfInterest.domain.POI;

public class POIService {

	private DistanceService distanceService;
	
	public POIService() {
		this.distanceService = new DistanceService();
	}
	
	public boolean isCloserTo(Integer metersDistance, POI poiA, POI poiB) {
		return metersDistance > distanceService.metersFromTo(poiA.getCoordenate(), poiB.getCoordenate());
	}
	
	public boolean isValid(POI poi) {
		return poi.getCoordenate() == null && poi.getName() == null;
	}
	
	
}
