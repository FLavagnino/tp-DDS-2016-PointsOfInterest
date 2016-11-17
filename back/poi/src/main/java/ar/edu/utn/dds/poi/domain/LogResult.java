package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class LogResult implements Serializable
{
	private Long id;
	private String poiName;
	private Log historicalSearch;
	
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
    //@JoinColumn(name="historical_search_id", referencedColumnName = "id")
	public Log getHistoricalSearch()
	{
		return historicalSearch;
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
	
	public void setHistoricalSearch(Log historicalSearch)
	{
		this.historicalSearch = historicalSearch;
	}
}
