package ar.edu.utn.dds.poi.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@NamedQueries({
	@NamedQuery(
	name = "all",
	query = "from User"
	)
})
@Entity
public class User implements Serializable
{
    private Long id;   
	private String userName;
	private String password;
	private String email;
	private String token;
	private Boolean auditMode;
	private List<Action> actions;
	
	public User()
	{
	}
	
    @Id @GeneratedValue
    public Long getId()
    {
    	return id;
    }
    
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    @JsonManagedReference
    public List<Action> getActions() 
    { 
    	return actions; 
    }
    
    public void setId(Long id)
    {
    	this.id = id;
    }
       
    public void setActions(List<Action> actions) 
    { 
    	this.actions = actions; 
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

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
}
