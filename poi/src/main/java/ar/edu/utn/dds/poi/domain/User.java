package ar.edu.utn.dds.poi.domain;

public abstract class User 
{
	private String userName;
	private String password;
	private String token;
	private Boolean auditMode;
	
	protected User()
	{
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getToken()
	{
		return this.token;
	}
	
	public Boolean getAuditMode()
	{
		return this.auditMode;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public void setAuditMode(Boolean auditMode)
	{
		this.auditMode = auditMode;
	}
}
