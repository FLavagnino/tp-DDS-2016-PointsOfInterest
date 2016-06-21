package poi.domain;

import org.joda.time.*;

import poi.constant.Service;

public class BusStop extends POI
{
	public BusStop(String name, Coordenate coordenate) 
	{
		super(name, coordenate);
	}

	private boolean isCloserTo(Integer metersDistance, POI poiA, POI poiB) 
	{
		if (metersDistance < 500)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isAvailable(DateTime dateTime, Service service)
	{
		return true;
	}
}