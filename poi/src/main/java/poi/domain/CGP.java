package poi.domain;

import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import poi.constant.Service;
import poi.search.LevenshteinDistance;

public class CGP extends POI 
{
	protected List<Service> services;
	
	public CGP(String name, Coordenate coordenate, List<Service> services, String tags) 
	{
		super(name, coordenate, tags);
		this.services = services;
	}
	
	protected List<Service> getCGPservices()
	{
		return this.services;
	}
	
	public boolean isAvailable(DateTime dateTime, Service service)
	{
		int dayOfWeek = dateTime.getDayOfWeek();
		
		for (OpeningHour openingHour : openingHours) 
		{
			if (service == openingHour.getService())
			{				
				if (openingHour.getDayOfWeek() == dayOfWeek)
				{
					DateTime from = new DateTime(1, 1, 1, openingHour.getHoursFrom(), openingHour.getMinutesFrom(), 0, 0);
					DateTime to = new DateTime(1, 1, 1, openingHour.getHoursTo(), openingHour.getMinutesTo(), 0, 0);
					Interval interval = new Interval(from, to);
					
					DateTime avaTime = new DateTime (1, 1, 1, dateTime.getHourOfDay(), dateTime.getMinuteOfHour());
					if (interval.contains(avaTime))
					{
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean matchFilter(String filter)
	{		
		// Now we will try with Categories
		for(int i=0; i< this.services.size() ; i++)
		{
			if (this.services.get(i).getName().toLowerCase().contains(filter.toLowerCase()))
			{
				return true;	
			}
		}


		
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
		// TODO: Completar este metodo y borrar el comentario.
		return true;
	}
}
