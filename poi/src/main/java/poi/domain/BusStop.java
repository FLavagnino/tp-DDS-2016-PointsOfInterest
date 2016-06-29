package poi.domain;

import poi.search.*;
import poi.constant.*;
import org.joda.time.*;

public class BusStop extends POI
{
	protected int busLine;
		
	public BusStop(String name, Coordenate coordenate, int busLine, String tags) 
	{
		super(name, coordenate, tags);	
		this.busLine = busLine;
	}
	
	public boolean isAvailable(DateTime dateTime, Service service)
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
		
		if (distance < 2)
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
				
				if (distance < 2)
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
		if(meters < 100)
			
			return true;
		else
			
			return false;
	}
}