package poi.domain;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

import poi.constant.Service;

public abstract class POI
{
	protected String name;
	protected Integer unit;
	protected Coordenate coordenate;
	protected Address address;
	protected List<OpeningHour> openingHours;
	
	protected POI(String name, Coordenate coordenate) 
	{
		this.name = name;
		this.coordenate = coordenate;
		this.openingHours = new ArrayList<OpeningHour>();
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
	
	public void addOpeningHour(OpeningHour openingHour)
	{
		this.openingHours.add(openingHour);
	}

	public List<OpeningHour> getOpeningHours()
	{
		return this.openingHours;
	}
	
	public boolean isAvailable(DateTime dateTime, Service service)
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

	private class Address {
		
		private String street;
		private Integer number;
		private String streetOne;
		private String streetTwo;
		private Integer floor;
		private String apartment;
		private String postalCode;
		private String locality;
		private String neighborhood;
		private String province;
		private String country;
		
		public Address(String street, Integer number, String streetOne, String streetTwo, Integer floor, 
				String apartment, String postalCode, String locality, String neighborhood, String province, String country) {
		
			this.street = street;
			this.number = number;
			this.streetOne = streetOne;
			this.streetTwo = streetTwo;
			this.floor = floor;
			this.apartment = apartment;
			this.postalCode = postalCode;
			this.locality = locality;
			this.neighborhood = neighborhood;
			this.province = province;
			this.country = country;
		}

		public String getStreet() {
			return street;
		}
		public Integer getNumber() {
			return number;
		}
		public String getStreetOne() {
			return streetOne;
		}
		public String getStreetTwo() {
			return streetTwo;
		}
		public Integer getFloor() {
			return floor;
		}
		public String getApartment() {
			return apartment;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public String getLocality() {
			return locality;
		}
		public String getNeighborhood() {
			return neighborhood;
		}
		public String getProvince() {
			return province;
		}
		public String getCountry() {
			return country;
		}	
	}	
}
