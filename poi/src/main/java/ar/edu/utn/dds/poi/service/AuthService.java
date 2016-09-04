package ar.edu.utn.dds.poi.service;

import ar.edu.utn.dds.poi.auth.*;

public class AuthService 
{
	private AuthManager authManager;
	
	public AuthService()
	{
		this.authManager = new AuthManager();
	}
	
	public String login(String userName, String password)
	{
		return this.authManager.login(userName, password);
	}
	
	public boolean validate(String userName, String token)
	{
		return this.authManager.validate(userName, token);
	}
}
