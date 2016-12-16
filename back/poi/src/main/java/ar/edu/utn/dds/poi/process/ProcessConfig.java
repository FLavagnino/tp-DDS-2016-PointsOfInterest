package ar.edu.utn.dds.poi.process;

public class ProcessConfig 
{
	private String userName;
	private String userMail;
	private boolean sendMail;
	private int refireCount;
	private int sleepTime;
	
	public ProcessConfig()
	{
	}
	
	public String getUserName()
	{
		return userName;
	}

	public String getUserMail()
	{
		return userMail;
	}
	
	public boolean getSendMail()
	{
		return sendMail;
	}
	
	public int getRefireCount()
	{
		return refireCount;
	}
	
	public int getSleepTime()
	{
		return sleepTime;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setUserMail(String userMail)
	{
		this.userMail = userMail;
	}
	
	public void setSendMail(boolean sendMail)
	{
		this.sendMail = sendMail;
	}
	
	public void setRefireCount(int refireCount)
	{
		this.refireCount = refireCount;
	}
	
	public void setSleepTime(int sleepTime)
	{
		this.sleepTime = sleepTime;
	}
}
