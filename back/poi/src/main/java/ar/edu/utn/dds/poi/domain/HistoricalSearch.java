package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;

@Entity
@Table(name="historical_search")
public class HistoricalSearch implements Serializable
{
	private Long id;
	private List<HistoricalSearchResult> results;
	private String userName;
	private String filter;
	private Integer resultsNumber;
	private long time;
	private DateTime date;

	public HistoricalSearch()
	{
	}
	
	public HistoricalSearch(String userName, String filter, Integer resultsNumber, long time) 
	{
		this.userName = userName;
		this.filter = filter;
		this.resultsNumber = resultsNumber;
		this.time = time;
		this.date = new DateTime();
	}
	
	public HistoricalSearch(String userName, String filter, Integer resultsNumber, long time, DateTime date) 
	{
		this.userName = userName;
		this.filter = filter;
		this.resultsNumber = resultsNumber;
		this.time = time;
		this.date = date;
	}
	
	@Id
	@GeneratedValue
	public Long getId()
	{
		return id;
	}
	
    @OneToMany(mappedBy="historical_search", cascade = CascadeType.ALL)
	public List<HistoricalSearchResult> getResults()
	{
		return results;
	}
	
	public String getUserName()
	{
		return userName;
	}

	public String getFilter()
	{
		return filter;
	}

	public Integer getResultsNumber()
	{
		return resultsNumber;
	}

	public long getTime()
	{
		return time;
	}

	public DateTime getDate()
	{
		return date;
	}
	
	// Setters
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public void setFilter(String filter)
	{
		this.filter = filter;
	}

	public void setResultsNumber(int resultsNumber)
	{
		this.resultsNumber = resultsNumber;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public void setDate(DateTime date)
	{
		this.date = date;
	}
	
	public void setResults(List<HistoricalSearchResult> results)
	{
		this.results = results;
	}
}
