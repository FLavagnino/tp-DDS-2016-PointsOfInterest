package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="historical_search_result")
public class HistoricalSearchResult implements Serializable
{
	private Long id;
	private String poiName;
	private HistoricalSearch historicalSearch;
	
	public HistoricalSearchResult()
	{
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
	public HistoricalSearch getHistoricalSearch()
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
	
	public void setHistoricalSearch(HistoricalSearch historicalSearch)
	{
		this.historicalSearch = historicalSearch;
	}
}
