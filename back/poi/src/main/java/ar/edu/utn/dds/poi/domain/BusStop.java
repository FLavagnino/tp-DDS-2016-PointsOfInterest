package ar.edu.utn.dds.poi.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.joda.time.*;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.utils.LevenshteinDistance;

@Entity
@PrimaryKeyJoinColumn(name="poi_id")
public class BusStop extends POI
{
	private String type;
	private int busLine;
	
	public BusStop()
	{
		type = "bus_stop";
	}
	
	public BusStop(String name, Coordenate coordenate, int busLine, String tags) 
	{
		super(name, coordenate, tags);	
		this.busLine = busLine;
		type = "bus_stop";
	}
	
	public int getBusLine()
	{
		return busLine;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
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
		int distance = LevenshteinDistance.distance(name.toLowerCase(), filter.toLowerCase());
		
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
}