package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.domain.User;
import ar.edu.utn.dds.poi.exception.*;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.utils.MetersDistance;

public class POIService implements Searcher
{
	private MetersDistance distanceService;
	private ExternalPOIService externalPOIService;
	private AuthService authService;
	
	private List<POI> poiList;
	
	public POIService() 
	{
		this.distanceService = new MetersDistance();
		this.externalPOIService = new ExternalPOIService();
		this.authService = new AuthService();
		this.poiList = new ArrayList<POI>();
	}
	
	public int metersFromToHaversine(POI poiFrom, POI poiTo) 
	{
		return distanceService.metersFromToHaversine(poiFrom.getCoordenate(), poiTo.getCoordenate());
	}
	
	public int metersFromTo(POI poiFrom, POI poiTo) 
	{
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
		poiList.addAll(externalPOIService.getExternalPois(filter));
				
		for(POI poi : poiList)
		{
			if (poi.matchFilter(filter))
			{
				result.add(poi);
			}
		}
		
		return new SearchResult(result);
	}
	
	public SearchResult search(String filter, String userName)
	{
		List<POI> result = new ArrayList<POI>();
		poiList.addAll(externalPOIService.getExternalPois(filter));
				
		for(POI poi : poiList)
		{
			if (poi.matchFilter(filter))
			{
				result.add(poi);
			}
		}
		
		return new SearchResult(result);
	}
	
	public SearchResult search(String filter, String userName, String token) throws InvalidUserException 
	{
		User user = this.authService.getUser(userName, token);
		
		if (user != null)
		{	
			if (user.getAuditMode())
			{
				Audit auditSearch = new Audit();
				
				return auditSearch.search(filter, userName);
			}
			else
			{
				return this.search(filter);
			}
		}
		else
		{
			throw new InvalidUserException(Constant.POISERVICE_AUTH_ERROR_MSG); 
		}
	}
	
	public void addPoi(POI poi) throws InvalidPoiException 
	{
		if (this.isValid(poi))
		{
			poiList.add(poi);
		}
		else
		{
			throw new InvalidPoiException(Constant.POISERVICE_INVALID_POI_MSG);
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
			throw new InvalidPoiException(Constant.POISERVICE_INVALID_POI_MSG);
		}
	}
}
