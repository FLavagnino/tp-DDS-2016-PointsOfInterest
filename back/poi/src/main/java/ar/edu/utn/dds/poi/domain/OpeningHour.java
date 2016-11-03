package ar.edu.utn.dds.poi.domain;

public class OpeningHour 
{
	private String service;
	private int dayOfWeek;
	private int hourFrom;
	private int minutesFrom;
	private int hourTo;
	private int minutesTo;
	
	
	public OpeningHour(String service, int dayOfWeek, int hourFrom, int minutesFrom,
						int hourTo, int minutesTo) 
	{
		this.service = service;
		this.dayOfWeek = dayOfWeek;
		this.hourFrom = hourFrom;
		this.minutesFrom = minutesFrom;
		this.hourTo = hourTo;
		this.minutesTo = minutesTo;
	}
	
	public String getService()
	{
		return this.service;
	}
	
	public int getDayOfWeek()
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