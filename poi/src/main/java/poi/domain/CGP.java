package poi.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import poi.constant.Service;

public class CGP extends POI 
{
	List<CGP> CGPpoi = new ArrayList<CGP>();
	protected String services;
	
	public CGP(String name, Coordenate coordenate, String tags) 
	{
		super(name, coordenate, tags);
	}
	public String getCGPservices(){
		return this.services;
	}
	protected void setCGPservices(String services){
		this.services = services;
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
		// se busca por servicio / etiqueta
		if(CGPpoi.contains(this.services)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isCloserTo(int meters, POI poiFrom)
	{
		// TODO: Completar este metodo y borrar el comentario.
		return true;
	}
}
