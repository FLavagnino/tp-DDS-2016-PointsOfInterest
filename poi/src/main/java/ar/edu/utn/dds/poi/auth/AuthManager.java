package ar.edu.utn.dds.poi.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import ar.edu.utn.dds.poi.domain.*;

public class AuthManager 
{
	private List<User> userList;
	private static AuthManager instance = null;
	
	public static AuthManager getInstance() 
	{
		if(instance == null) 
		{
			instance = new AuthManager();
		}
		
		return instance;
	}
	
	public AuthManager()
	{
		this.userList = new ArrayList<User>();
	}
	
	public String login(String userName, String password)
	{
		String token = "";
		
		List<User> users = userList.stream()
							.filter(item -> item.getUserName() == userName && 
										item.getPassword().equals(password))
							.collect(Collectors.toList());
		
		if (!users.isEmpty())
		{
			User currentUser = users.get(0);
			
			token = UUID.randomUUID().toString();
			currentUser.setToken(token);
		}
		
		return token;
	}
	
	public boolean validate(String userName, String token)
	{
		List<User> users = userList.stream()
						.filter(item -> item.getUserName() == userName && 
										item.getToken().equals(token))
						.collect(Collectors.toList());
		
		return !users.isEmpty();
	}
	
	public User getUser(String userName, String token)
	{
		List<User> users = userList.stream()
						.filter(item -> item.getUserName() == userName && 
										item.getToken().equals(token))
						.collect(Collectors.toList());
		
		if (!users.isEmpty())
		{
			return users.get(0);
		}
		else
		{
			return null;
		}
	}
	
	public String getMailOf(String userName) 
	{
		for (User user : userList) 
		{
			if(user.getUserName().equals(userName)) 
			{
				return user.getEmail();
			}
		}
		
		return null;
	}
	
	public void setUserList(List<User> users)
	{
		this.userList = users;
	}
}
