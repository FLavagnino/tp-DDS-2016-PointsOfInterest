package ar.edu.utn.dds.poi.domain;

import org.joda.time.*;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.utils.LevenshteinDistance;

public class BusStop extends POI
{
	protected final static String TYPE = "bus_stop";

	protected int busLine;
	
	public BusStop(String name, Coordenate coordenate, int busLine, String tags) 
	{
		super(name, coordenate, tags);	
		this.busLine = busLine;
	}
	
	public int getBusLine()
	{
		return this.busLine;
	}
	
	public void setBusLine(Integer busLine)
	{
		this.busLine = busLine;
	}
	
	public boolean isAvailable(DateTime dateTime, String service)
	{
		return true;
	}
	
	public boolean matchFilter(String filter)
	{	
		try 
		{
			// First we will try to find the bus line
			int busLine = Integer.parseInt(filter);

			if (this.busLine == busLine)
			{
				return true;
			}
		} 
		catch (NumberFormatException nfe) { }
		
		// Now we will try with Levenshtein for the name
		int distance = LevenshteinDistance.distance(this.name.toLowerCase(), filter.toLowerCase());
		
		if (distance < Constant.LEVENSHTEIN_ACCEPTED_DIST)
		{
			return true;
		}

		// Now we will try with Levenshtein for the tags
		if (!tags.equals(null) && !tags.equals(""))
		{
			String[] tagList = tags.split(",");
			
			for (int i=0; i < tagList.length; i++)
			{
				distance = LevenshteinDistance.distance(tagList[i].toLowerCase(), filter.toLowerCase());
				
				if (distance < Constant.LEVENSHTEIN_ACCEPTED_DIST)
				{
					return true;
				}
			}		
		}

		// Doesn't match anything
		return false;
	}
	
	public boolean isCloserTo(int meters, POI poiFrom)
	{
		return (meters < Constant.BUSSTOP_ISCLOSERTO_DIST);
	}

	public String getType() {
		return TYPE;
	}
}