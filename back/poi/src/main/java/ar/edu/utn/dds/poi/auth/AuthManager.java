package ar.edu.utn.dds.poi.auth;

import java.util.List;
import java.util.UUID;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.repository.UserRepository;

public class AuthManager 
{
	private static AuthManager instance = null;

	private UserRepository userRepository;

	public static AuthManager getInstance() 
	{
		if(instance == null) 
		{
			instance = new AuthManager();
		}
		
		return instance;
	}
	
	private AuthManager()
	{
		userRepository = new UserRepository();
	}
	
	public String login(String userName, String password)
	{
		String token = "";
		
		User user = userRepository.getUserWithPassword(userName, password);
		
		if (user != null)
		{
			token = UUID.randomUUID().toString();
			user.setToken(token);
			
			userRepository.update(user);
		}
		
		return token;
	}
	
	public boolean validate(String userName, String token)
	{
		return getUser(userName, token) != null;
	}
	
	public User getUser(String userName, String token)
	{
		return userRepository.getUserWithToken(userName, token);
	}
	
	public String getMailOf(String userName) 
	{
		String email = null;

		User user = userRepository.getUser(userName);

		if (user != null)
		{
			email = user.getEmail();
		}

		return email;
	}
		
	public List<User> getUserList()
	{
		return userRepository.getAll();
	}
}
