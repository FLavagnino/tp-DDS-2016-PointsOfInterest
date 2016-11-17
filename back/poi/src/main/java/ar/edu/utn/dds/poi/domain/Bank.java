package ar.edu.utn.dds.poi.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.joda.time.*;
import ar.edu.utn.dds.poi.constant.*;
import ar.edu.utn.dds.poi.utils.LevenshteinDistance;

@Entity
@PrimaryKeyJoinColumn(name="poi_id")
public class Bank extends POI
{
	private String type;

	public Bank()
	{
		this.type = "bank";
	}
	
	public Bank(String name, Coordenate coordenate, String tags) 
	{
		super(name, coordenate, tags);
		this.type = "bank";
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public boolean isAvailable(DateTime dateTime, String service)
	{
		int dayOfWeek = dateTime.getDayOfWeek();
		
		for (OpeningHour openingHour : openingHours) 
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
		
		return false;
	}
	
	public boolean matchFilter(String filter)
	{		
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
		return (meters < Constant.BANK_ISCLOSERTO_DIST);
	}
}
