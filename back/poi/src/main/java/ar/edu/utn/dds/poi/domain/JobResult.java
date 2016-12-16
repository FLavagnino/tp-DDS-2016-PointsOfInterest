package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
public class JobResult implements Serializable
{
	private Long id;
	private String startDate;
	private String endDate;
	private String jobName;
	private String userName;
	private String resultCode;
	private String resultMsg;
	
	public JobResult()
	{
	}
	
	public JobResult(String startDate, String endDate, String jobName, String userName, String resultCode, String resultMsg)
	{
		this.startDate = startDate;
		this.endDate = endDate;
		this.jobName = jobName;
		this.userName = userName;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	@Id
	@GeneratedValue
	public Long getId()
	{
		return id;
	}
	
	public String getStartDate()
	{
		return startDate;
	}
	
	public String getEndDate()
	{
		return endDate;
	}
	
	public String getJobName()
	{
		return jobName;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getResultCode()
	{
		return resultCode;
	}
	
	public String getResultMsg()
	{
		return resultMsg;
	}
		
	// Setters
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}
	
	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setResultCode(String resultCode)
	{
		this.resultCode = resultCode;
	}
	
	public void setResultMsg(String resultMsg)
	{
		this.resultMsg = resultMsg;
	}
}
