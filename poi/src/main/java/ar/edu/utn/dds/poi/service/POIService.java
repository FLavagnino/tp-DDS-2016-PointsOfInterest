package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.exception.InvalidPoiException;
import ar.edu.utn.dds.poi.utils.MetersDistance;

public class POIService implements Searcher
{
	private MetersDistance distanceService;
	private ExternalPOIService externalPOIService;
	
	private List<POI> poiList;
	
	public POIService() 
	{
		this.distanceService = new MetersDistance();
		externalPOIService = new ExternalPOIService();
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
	
	public boolean isAvailable(POI poi, DateTime dateTime, String service)
	{
		return poi.isAvailable(dateTime, service);
	}
	
	public SearchResult search(String filter)
	{
		List<POI> result = new ArrayList<POI>();
		result.addAll(externalPOIService.getExternalPois(filter));
				
		for(POI poi : poiList)
		{
			if (poi.matchFilter(filter))
			{
				result.add(poi);
			}
		}
		
		return new SearchResult(result);
	}
	
	public void addPoi(POI poi) throws InvalidPoiException 
	{
		if (this.isValid(poi))
		{
			poiList.add(poi);
		}
		else
		{
			throw new InvalidPoiException("Invalid POI. Please check all the coodenates and the name.");
		}
	}
	
	public void deletePoi(Integer unit)
	{
		poiList.removeIf( poi -> poi.getUnit() == unit );
	}
	
	public void updatePoi(POI poi) throws InvalidPoiException
	{
		if (this.isValid(poi))
		{
			deletePoi(poi.getUnit());
			addPoi(poi);
		}
		else
		{
			throw new InvalidPoiException("Invalid POI. Please check all the coodenates and the name.");
		}
	}
	
	public List<POI> getAllPois()
	{
		List<POI> allPois = new ArrayList<POI>();
		allPois.addAll(poiList);
		allPois.addAll(externalPOIService.getExternalPois(null));
		return allPois;
	}
}
