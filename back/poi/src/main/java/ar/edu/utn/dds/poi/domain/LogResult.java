package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class LogResult implements Serializable
{
	private Long id;
	private String poiName;
	private Log log;
	
	public LogResult()
	{
	}
	
	public LogResult(String poiName)
	{
		this.poiName = poiName;
	}

	@Id
	@GeneratedValue
	public Long getId()
	{
		return id;
	}
	
	public String getPoiName()
	{
		return poiName;
	}
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
	public Log getLog()
	{
		return log;
	}
	
	// Setters
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setPoiName(String poiName)
	{
		this.poiName = poiName;
	}
	
	public void setLog(Log log)
	{
		this.log = log;
	}
}
