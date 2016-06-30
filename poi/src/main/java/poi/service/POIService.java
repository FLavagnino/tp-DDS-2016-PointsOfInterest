package poi.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import poi.constant.Service;
import poi.distance.service.DistanceService;
import poi.domain.*;

public class POIService 
{
	private DistanceService distanceService;
	
	public POIService() 
	{
		this.distanceService = new DistanceService();
	}
	
	public int metersFromToHaversine(POI poiFrom, POI poiTo) 
	{
		return distanceService.metersFromToHaversine(poiFrom.getCoordenate(), poiTo.getCoordenate());
	}
	
	public int metersFromTo(POI poiFrom, POI poiTo) 
	{
		//return distanceService.metersFromTo(poiFrom.getCoordenate(), poiTo.getCoordenate());
		return distanceService.metersFromToHaversine(poiFrom.getCoordenate(), poiTo.getCoordenate());
	}
		
	public boolean isValid(POI poi) 
	{
		return poi.getCoordenate() != null && poi.getName() != null;
	}
	
	public boolean isCloserTo(POI poiFrom, POI poiTo)
	{
		int meters = this.metersFromTo(poiFrom, poiTo);	
		return poiTo.isCloserTo(meters, poiFrom);
	}
	
	public boolean isAvailable(POI poi, DateTime dateTime, Service service)
	{
		return poi.isAvailable(dateTime, service);
	}
	
	public List<POI> search(String filter, List<POI> poiList)
	{
		List<POI> result = new ArrayList<POI>();
		
		for(int i=0; i< poiList.size() ; i++)
		{
			POI poi = poiList.get(i);
			if (poi.matchFilter(filter))
			{
				result.add(poi);
			}
		}
		
		return result;
	}
}
