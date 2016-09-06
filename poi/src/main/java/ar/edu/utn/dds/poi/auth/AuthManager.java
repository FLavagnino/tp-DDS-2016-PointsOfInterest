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
		aLuis.setAuditMode(false);
		userList.add(aLuis);
		
		User aJuan = new Admin();	
		aJuan.setUserName("juanc");
		aJuan.setPassword("1111");
		aJuan.setToken("");
		aJuan.setAuditMode(false);
		userList.add(aJuan);
		
		User aFacuL = new Admin();
		aFacuL.setUserName("facul");
		aFacuL.setPassword("2222");
		aFacuL.setToken("");
		aFacuL.setAuditMode(false);
		userList.add(aFacuL);
		
		User aFacuB = new Admin();
		aFacuB.setUserName("facub");
		aFacuB.setPassword("3333");
		aFacuB.setToken("");
		aFacuB.setAuditMode(false);
		userList.add(aFacuB);
		
		User tAbasto = new Terminal();
		tAbasto.setUserName("t_abasto");
		tAbasto.setPassword("4444");
		tAbasto.setToken("");
		tAbasto.setAuditMode(true);
		userList.add(tAbasto);
		
		User tCaballito = new Terminal();	
		tCaballito.setUserName("t_caballito");
		tCaballito.setPassword("5555");
		tCaballito.setToken("");
		tCaballito.setAuditMode(true);
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
	
	public User getUser(int userId, String token)
	{
		List<User> users = userList.stream()
						.filter(item -> item.getUserId() == (userId) && 
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
	
	public String getMailOf(int userId) {
		for (User user : userList) {
			if(user.getUserId() == userId) {
				return user.getEmail();
			}
		}
		return null;
	}
}
