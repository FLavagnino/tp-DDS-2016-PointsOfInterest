package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class ZoneCoordenate implements Serializable
{
	private Long id;
	private CGP cgp;
	private Double latitude;
	private Double longitude;
	
	public ZoneCoordenate()
	{
	}
	
	public ZoneCoordenate(Double latitude, Double longitude) 
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
	
	@ManyToOne(cascade = CascadeType.ALL)
	public CGP getCgp()
	{
		return this.cgp;
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
	
	public void setCgp(CGP cgp)
	{
		this.cgp = cgp;
	}
}
