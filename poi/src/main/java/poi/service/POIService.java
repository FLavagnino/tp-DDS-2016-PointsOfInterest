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
	
	public int metersFromTo(POI poiFrom, POI poiTo) 
	{
		return distanceService.metersFromTo(poiFrom.getCoordenate(), poiTo.getCoordenate());
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
	
	public List<POI> search(String filter)
	{
		return new ArrayList<POI>();
	}
	
	public void searchPOI(ArrayList<POI> ListPOI, POI onePoi)
	{
		for(int i=0; i< ListPOI.size() ; i++){
			if ( ListPOI.get(i).equals(onePoi))
			{
				System.out.print(i);
			}
		}
	}
}
