package ar.edu.utn.dds.poi.domain;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public abstract class POI
{
	protected String name;
	protected Integer unit;
	protected Coordenate coordenate;
	protected Address address;
	protected List<OpeningHour> openingHours;
	protected String tags;
	
	protected POI()
	{
		this.openingHours = new ArrayList<OpeningHour>();
	}
	
	protected POI(String name, Coordenate coordenate, String tags) 
	{
		this.name = name;
		this.coordenate = coordenate;
		this.openingHours = new ArrayList<OpeningHour>();
		this.tags = tags;
	}

	protected void setAddress(String street, Integer number, String streetOne, String streetTwo, Integer floor, 
			String apartment, String postalCode, String locality, String neighborhood, String province, String country) 
	{	
		this.address = new Address(street, number, streetOne, streetTwo, floor, apartment, 
				postalCode, locality, neighborhood, province, country);
	}
	
	public boolean isCloserTo(int meters, POI poiFrom)
	{
		return true;
	}
	
	public void setCoordenate(Coordenate coordenate)
	{
		this.coordenate = coordenate;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addOpeningHour(OpeningHour openingHour)
	{
		this.openingHours.add(openingHour);
	}

	public List<OpeningHour> getOpeningHours()
	{
		return this.openingHours;
	}
	
	public boolean isAvailable(DateTime dateTime, String service)
	{
		return true;
	}
	
	public boolean matchFilter(String filter)
	{
		return true;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setUnit(Integer unit)
	{
		this.unit = unit;
	}
	
	public Integer getUnit()
	{
		return unit;
	}
	
	public Coordenate getCoordenate() 
	{
		return coordenate;
	}
	
	public Address getAddress() 
	{
		return address;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}	
	
}
