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
		userList = new ArrayList<User>();
		
		User aLuis = new Admin();
		aLuis.setUserName("luisk");
		aLuis.setPassword("1234");
		aLuis.setToken("");
		userList.add(aLuis);
		
		User aJuan = new Admin();	
		aJuan.setUserName("juanc");
		aJuan.setPassword("1111");
		aJuan.setToken("");
		userList.add(aJuan);
		
		User aFacuL = new Admin();
		aFacuL.setUserName("facul");
		aFacuL.setPassword("2222");
		aFacuL.setToken("");
		userList.add(aFacuL);
		
		User aFacuB = new Admin();
		aFacuB.setUserName("facub");
		aFacuB.setPassword("3333");
		aFacuB.setToken("");
		userList.add(aFacuB);
		
		User tAbasto = new Terminal();
		tAbasto.setUserName("t_abasto");
		tAbasto.setPassword("4444");
		tAbasto.setToken("");
		userList.add(tAbasto);
		
		User tCaballito = new Terminal();	
		tCaballito.setUserName("t_caballito");
		tCaballito.setPassword("5555");
		tCaballito.setToken("");
		userList.add(tCaballito);
	}
	
	public String login(String userName, String password)
	{
		String token = "";
		
		List<User> users = userList.stream()
							.filter(item -> item.getUserName().equals(userName) && 
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
						.filter(item -> item.getUserName().equals(userName) && 
										item.getToken().equals(token))
						.collect(Collectors.toList());
		
		return !users.isEmpty();
	}
	
	public User getUser(String userName, String token)
	{
		List<User> users = userList.stream()
						.filter(item -> item.getUserName().equals(userName) && 
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
}
