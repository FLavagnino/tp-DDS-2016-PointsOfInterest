package poi.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import poi.constant.Service;
import poi.distance.service.DistanceService;
import poi.domain.OpeningHour;
import poi.domain.POI;

public class POIService 
{
	private DistanceService distanceService;
	
	public POIService() 
	{
		this.distanceService = new DistanceService();
	}
	
	public boolean isCloserTo(Integer metersDistance, POI poiA, POI poiB) 
	{
		return metersDistance > distanceService.metersFromTo(poiA.getCoordenate(), poiB.getCoordenate());
	}
	
	public boolean isValid(POI poi) 
	{
		return poi.getCoordenate() != null && poi.getName() != null;
	}	
	
	public boolean isAvailable(POI poi, DateTime dateTime, Service service)
	{
		return poi.isAvailable(dateTime, service);
	}
	
	public List<POI> search(String filter)
	{
		return new ArrayList<POI>();
	}
}
