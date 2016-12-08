package ar.edu.utn.dds.poi.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Address 
{	
	private Long id;
	private POI poi;
	private String street;
	private int number;
	private String streetOne;
	private String streetTwo;
	private int floor;
	private String apartment;
	private String postalCode;
	private String locality;
	private String neighborhood;
	private String province;
	private String country;
	
	public Address()
	{
	}
	
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

	// Getters
	@Id
	@GeneratedValue
	public Long getId()
	{
		return this.id;
	}
	
	@OneToOne(mappedBy = "address")
	@JsonManagedReference
	public POI getPoi()
	{
		return this.poi;
	}
	
	public String getStreet() 
	{
		return street;
	}
	
	public int getNumber() 
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
	
	public int getFloor() 
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
	
	// Setters
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setPoi(POI poi)
	{
		this.poi = poi;
	}
	
	public void setStreet(String street) 
	{
		this.street = street;
	}
	
	public void setNumber(int number) 
	{
		this.number = number;
	}
	
	public void setStreetOne(String streetOne) 
	{
		this.streetOne = streetOne;
	}
	
	public void setStreetTwo(String streetTwo) 
	{
		this.streetTwo = streetTwo;
	}
	
	public void setFloor(int floor) 
	{
		this.floor = floor;
	}
	
	public void setApartment(String apartment) 
	{
		this.apartment = apartment;
	}
	
	public void setPostalCode(String postalCode) 
	{
		this.postalCode = postalCode;
	}
	
	public void setLocality(String locality) 
	{
		this.locality = locality;
	}
	
	public void setNeighborhood(String neighborhood) 
	{
		this.neighborhood = neighborhood;
	}
	
	public void setProvince(String province) 
	{
		this.province = province;
	}
	
	public void setCountry(String country) 
	{
		this.country = country;
	}
}
