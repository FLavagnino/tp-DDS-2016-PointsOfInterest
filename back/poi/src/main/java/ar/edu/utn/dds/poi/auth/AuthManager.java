package ar.edu.utn.dds.poi.auth;

import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.repository.UserRepository;

public class AuthManager 
{
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
	}
	
	public String login(String userName, String password)
	{
		String token = "";
		
		List<User> users = getUserList().stream()
							.filter(item -> item.getUserName() == userName && 
										item.getPassword().equals(password))
							.collect(Collectors.toList());
		
		if (!users.isEmpty())
		{
			User currentUser = users.get(0);
			
			token = UUID.randomUUID().toString();
			currentUser.setToken(token);
			
			UserRepository userRep = new UserRepository();
			userRep.update(currentUser);
		}
		
		return token;
	}
	
	public boolean validate(String userName, String token)
	{
		List<User> users = getUserList().stream()
						.filter(item -> item.getUserName() == userName && 
										item.getToken().equals(token))
						.collect(Collectors.toList());
		
		return !users.isEmpty();
	}
	
	public User getUser(String userName, String token)
	{
		List<User> users = getUserList().stream()
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
		List<User> users = getUserList().stream()
				.filter(item -> item.getUserName() == userName)
				.collect(Collectors.toList());

		if (!users.isEmpty())
		{
			return users.get(0).getEmail();
		}
		else
		{
			return null;
		}
	}
		
	public List<User> getUserList()
	{
		UserRepository userRep = new UserRepository();
		return userRep.getAll();
	}
}
