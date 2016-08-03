package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.utn.dds.poi.constant.Service;
import ar.edu.utn.dds.poi.domain.POI;

public class POIService 
{
	private DistanceService distanceService;
	
	private List<POI> pois;
	
	public POIService() 
	{
		this.distanceService = new DistanceService();
		pois = new ArrayList<POI>();
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
	
	public List<POI> search(String filter)
	{
		List<POI> result = new ArrayList<POI>();
		
		List<POI> poiList = getPois();
		
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
	
	public void addPoi(POI poi) {
		pois.add(poi);
	}
	
	public void deletePoi(Integer unit) {
		pois.removeIf( poi -> poi.getUnit() == unit );
	}
	
	public void updatePoi(POI poi) {
		deletePoi(poi.getUnit());
		addPoi(poi);
	}
	
	public List<POI> getPois() {
		List<POI> allPois = new ArrayList<POI>();
		allPois.addAll(pois);
		ExternalPOIService externalPOIService = new ExternalPOIService();
		allPois.addAll(externalPOIService.getExternalPois());
		return allPois;
	}
	
	
}
