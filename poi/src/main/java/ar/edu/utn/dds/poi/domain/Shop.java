package ar.edu.utn.dds.poi.domain;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import ar.edu.utn.dds.poi.constant.Category;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.utils.LevenshteinDistance;

public class Shop extends POI
{
	protected Category category;
	
	public Shop(String name, Coordenate coordenate, Category category, String tags) 
	{
		super(name, coordenate, tags);
		this.category = category;	
	}
	
	public Shop(String name, String tags) 
	{
		super(name, null, tags);
	}
	
	public Category getShopCategory()
	{
		return this.category;
	}
	
	public boolean isAvailable(DateTime dateTime, String service)
	{
		int dayOfWeek = dateTime.getDayOfWeek();
		
		for (OpeningHour openingHour : openingHours) 
		{
			if (openingHour.getDayOfWeek() == dayOfWeek)
			{
				DateTime from = new DateTime(Constant.JODATIME_COMPARE_YEAR, Constant.JODATIME_COMPARE_MONTH, 
						Constant.JODATIME_COMPARE_DAY, openingHour.getHoursFrom(), 
						openingHour.getMinutesFrom());

				DateTime to = new DateTime(Constant.JODATIME_COMPARE_YEAR, Constant.JODATIME_COMPARE_MONTH, 
										Constant.JODATIME_COMPARE_DAY, openingHour.getHoursTo(), 
										openingHour.getMinutesTo());

				Interval interval = new Interval(from, to);
				DateTime avaTime = new DateTime (Constant.JODATIME_COMPARE_YEAR, Constant.JODATIME_COMPARE_MONTH,
													Constant.JODATIME_COMPARE_DAY, dateTime.getHourOfDay(), dateTime.getMinuteOfHour());

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
		
		if (distance < Constant.LEVENSHTEIN_ACCEPTED_DIST)
		{
			return true;
		}
		
		// Now we will try with Levenshtein for the name
		distance = LevenshteinDistance.distance(this.name.toLowerCase(), filter.toLowerCase());
		
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
		int radius = this.category.getDistance();
		return (meters < radius);
	}
}