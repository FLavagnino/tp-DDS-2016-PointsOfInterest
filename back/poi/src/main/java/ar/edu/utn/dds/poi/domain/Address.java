package ar.edu.utn.dds.poi.domain;

public class Address 
{	
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
			String apartment, String postalCode, String locality, String neighborhood, String province, String country) 
	{
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

	public String getStreet() 
	{
		return street;
	}
	
	public Integer getNumber() 
	{
		return number;
	}
	
	public String getStreetOne() 
	{
		return streetOne;
	}
	
	public String getStreetTwo() 
	{
		return streetTwo;
	}
	
	public Integer getFloor() 
	{
		return floor;
	}
	
	public String getApartment() 
	{
		return apartment;
	}
	
	public String getPostalCode() 
	{
		return postalCode;
	}
	
	public String getLocality() 
	{
		return locality;
	}
	
	public String getNeighborhood() 
	{
		return neighborhood;
	}
	
	public String getProvince() 
	{
		return province;
	}
	
	public String getCountry() 
	{
		return country;
	}	
}
