package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.joda.time.DateTime;

@Entity
public class Log implements Serializable
{
	private Long id;
	private List<LogResult> results;
	private String userName;
	private String filter;
	private Integer resultsNumber;
	private long time;
	private String date;

	public Log()
	{
	}
	
	public Log(String userName, List<LogResult> results, String filter, Integer resultsNumber, long time) 
	{
		this.userName = userName;
		this.results = results;
		this.filter = filter;
		this.resultsNumber = resultsNumber;
		this.time = time;
		this.date = new DateTime().toString("dd/MM/yyyy");
	}
	
	public Log(String userName, String filter, Integer resultsNumber, long time, DateTime date) 
	{
		this.userName = userName;
		this.filter = filter;
		this.resultsNumber = resultsNumber;
		this.time = time;
		this.date = date.toString("dd/MM/yyyy");
	}
	
	@Id
	@GeneratedValue
	public Long getId()
	{
		return id;
	}
	
    @OneToMany(mappedBy="historicalSearch", cascade = CascadeType.ALL)
	public List<LogResult> getResults()
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

	public String getDate()
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

	public void setDate(String date)
	{
		this.date = date;
	}
	
	public void setResults(List<LogResult> results)
	{
		this.results = results;
	}
}
