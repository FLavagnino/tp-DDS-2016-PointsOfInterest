package ar.edu.utn.dds.poi.domain;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.utn.dds.poi.constant.Category;
import ar.edu.utn.dds.poi.constant.Service;
import ar.edu.utn.dds.poi.utils.LevenshteinDistance;

public class Shop extends POI
{
	protected Category category;
	public int maxLengthString = 2;
	
	public Shop(String name, Coordenate coordenate, Category category, String tags) 
	{
		super(name, coordenate, tags);
		this.category = category;	
	}
	
	public Category getShopCategory()
	{
		return this.category;
	}
	
	public boolean isAvailable(DateTime dateTime, Service service)
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
		// First will try with Levenshtein for the category
		int distance = LevenshteinDistance.distance(this.category.getName().toLowerCase(), filter.toLowerCase());
		
		if (distance < maxLengthString)
		{
			return true;
		}
		
		// Now we will try with Levenshtein for the name
		distance = LevenshteinDistance.distance(this.name.toLowerCase(), filter.toLowerCase());
		
		if (distance < maxLengthString)
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
				
				if (distance < maxLengthString)
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
		int radius = this.category.getDistance();
		return (meters < radius);
	}
}