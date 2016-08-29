package ar.edu.utn.dds.poi.users;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

public class terminal {
	
	String name;
	String password;
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName() 
	{
		return name;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	
}
