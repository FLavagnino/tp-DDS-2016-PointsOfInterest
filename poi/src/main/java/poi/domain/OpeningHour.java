package poi.domain;

import java.time.DayOfWeek;

import org.joda.time.*;
import poi.constant.Service;

public class OpeningHour 
{
	private Service service;
	private DayOfWeek dayOfWeek;
	private int hourFrom;
	private int minutesFrom;
	private int hourTo;
	private int minutesTo;
	
	
	public OpeningHour(Service service, DayOfWeek dayOfWeek, int hourFrom, int minutesFrom,
						int hourTo, int minutesTo) 
	{
		this.service = service;
		this.dayOfWeek = dayOfWeek;
		this.hourFrom = hourFrom;
		this.minutesFrom = minutesFrom;
		this.hourTo = hourTo;
		this.minutesTo = minutesTo;
	}
	
	public Service getService()
	{
		return this.service;
	}
	
	public DayOfWeek getDayOfWeek()
	{
		return this.dayOfWeek;
	}
	
	public int getHoursFrom()
	{
		return this.hourFrom;
	}
	
	public int getMinutesFrom()
	{
		return this.minutesFrom;
	}
	
	public int getHoursTo()
	{
		return this.hourTo;
	}
	
	public int getMinutesTo()
	{
		return this.minutesTo;
	}
}