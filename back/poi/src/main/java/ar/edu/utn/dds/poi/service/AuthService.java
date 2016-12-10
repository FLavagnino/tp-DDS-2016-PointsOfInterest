package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.auth.*;
import ar.edu.utn.dds.poi.domain.*;

public class AuthService 
{
	private AuthManager authManager;
	
	public AuthService()
	{
		authManager = AuthManager.getInstance();
	}
	
	public String login(String userName, String password)
	{
		return authManager.login(userName, password);
	}
	
	public boolean validate(String userName, String token)
	{
		return authManager.validate(userName, token);
	}
	
	public User getUser(String userName, String token)
	{
		return authManager.getUser(userName, token);
	}
		
	public String getMailOf(String userName)
	{
		return authManager.getMailOf(userName);
	}
}
