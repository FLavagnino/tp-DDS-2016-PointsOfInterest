package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.utn.dds.poi.constant.Service;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.utils.MetersDistance;

public class POIService 
{
	private MetersDistance distanceService;
	
	private List<POI> poiList;
	
	public POIService() 
	{
		this.distanceService = new MetersDistance();
		poiList = new ArrayList<POI>();
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
		List<POI> allPois = getPois();
				
		for(int i=0; i< allPois.size() ; i++)
		{
			POI poi = poiList.get(i);
			if (poi.matchFilter(filter))
			{
				result.add(poi);
			}
		}
		
		return result;
	}
	
	public void addPoi(POI poi) 
	{
		poiList.add(poi);
	}
	
	public void deletePoi(Integer unit)
{
		poiList.removeIf( poi -> poi.getUnit() == unit );
	}
	
	public void updatePoi(POI poi)
	{
		deletePoi(poi.getUnit());
		addPoi(poi);
	}
	
	public List<POI> getPois()
	{
		List<POI> allPois = new ArrayList<POI>();
		allPois.addAll(poiList);
		ExternalPOIService externalPOIService = new ExternalPOIService();
		allPois.addAll(externalPOIService.getExternalPois());
		return allPois;
	}
}
