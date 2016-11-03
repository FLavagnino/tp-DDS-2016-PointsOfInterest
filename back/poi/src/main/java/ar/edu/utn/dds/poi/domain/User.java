package ar.edu.utn.dds.poi.domain;

import java.util.ArrayList;
import ar.edu.utn.dds.poi.constant.Actions;
import javax.persistence.*;

@Entity
@Table
public abstract class User 
{
    @Id
    @GeneratedValue
    private Long id;
    
	private int userId;
	private String userName;
	private String password;
	private String email;
	private String token;
	private Boolean auditMode;
	private ArrayList<Actions> actionList;
	
	protected User()
	{
		this.actionList = new ArrayList<Actions>();
		actionList.add(Actions.SEARCH);
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Actions> getActionList()
	{
		return this.actionList;
	}
	
	public void setActionList(ArrayList<Actions> actionList)
	{	
		this.actionList = actionList;	
	}
}
