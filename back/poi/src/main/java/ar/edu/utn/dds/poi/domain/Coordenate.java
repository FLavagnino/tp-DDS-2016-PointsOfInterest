package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Coordenate implements Serializable
{
	private Long id;
	private POI poi;
	private Double latitude;
	private Double longitude;
	
	public Coordenate()
	{
	}
	
	public Coordenate(Double latitude, Double longitude) 
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Id
	@GeneratedValue
	public Long getId()
	{
		return this.id;
	}
	
	@OneToOne(mappedBy = "coordenate")
	@JsonBackReference
	public POI getPoi()
	{
		return this.poi;
	}
	
	public Double getLatitude() 
	{
		return this.latitude;
	}

	public Double getLongitude() 
	{
		return longitude;
	}
	
	public void setLatitude(Double latitude) 
	{
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) 
	{
		this.longitude = longitude;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setPoi(POI poi)
	{
		this.poi = poi;
	}
}
