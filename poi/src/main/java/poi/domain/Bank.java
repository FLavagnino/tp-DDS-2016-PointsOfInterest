package poi.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.*;
import poi.constant.Service;

public class Bank extends POI 
{	
	List<Bank> BankPoi = new ArrayList<Bank>();
		
	
	public Bank(String name, Coordenate coordenate) 
	{
		super(name, coordenate);
	}
	
	public boolean isAvailable(DateTime dateTime, Service service)
	{
		int dayOfWeek = dateTime.getDayOfWeek();
		
		for (OpeningHour openingHour : openingHours) 
		{
			if (openingHour.getDayOfWeek().getValue() == dayOfWeek)
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
		// Se busca por etiqueta (name de banco).
		if (BankPoi.contains(this.name))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean isCloserTo(int meters, POI poiFrom)
	{
		if(meters < 500)
			
			return true;
		else
			
			return false;
	}
}
