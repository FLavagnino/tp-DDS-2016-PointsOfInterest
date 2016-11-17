package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class OpeningHour implements Serializable
{
	private Long id;
	private String service;
	private int dayOfWeek;
	private int hourFrom;
	private int minutesFrom;
	private int hourTo;
	private int minutesTo;
	private POI poi;
	
	public OpeningHour()
	{
	}
	
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
	
    @Id 
    @GeneratedValue
	public Long getId()
	{
		return this.id;
	}
	
    @ManyToOne(cascade = CascadeType.ALL)
	public POI getPoi()
	{
		return this.poi;
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
	
	public void setService(String service)
	{
		this.service = service;
	}
	
	public void setDayOfWeek(int dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}
	
	public void setHoursFrom(int hourFrom)
	{
		this.hourFrom = hourFrom;
	}
	
	public void setMinutesFrom(int minutesFrom)
	{
		this.minutesFrom = minutesFrom;
	}
	
	public void setHoursTo(int hourTo)
	{
		this.hourTo = hourTo;
	}
	
	public void setMinutesTo(int minutesTo)
	{
		this.minutesTo = minutesTo;
	}
	
	public void setPoi(POI poi)
	{
		this.poi = poi;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
}