package ar.edu.utn.dds.poi.test;

import org.junit.Before;
import org.junit.Test;

import com.vividsolutions.jts.util.Assert;

import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.utils.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.OneToMany;

import org.hibernate.*;

public class Entrega6Test 
{	
	private POIService poiService;
	
	@Before
	public void setUp()
	{
		this.poiService = new POIService();
	}
	
	@Test
	public void insertUserTest()
	{
		// Obtengo un usuario para crear en la DB
		User user = this.getNewUser();
        
        // Grabo el usuario
        Serializable userID = poiService.saveUser(user);
        System.out.println("Persisti el usuario: " + user.getUserName() + " con el ID: [" + user.getId() + "]");
        // Obtengo el usuario nuevamente de la DB
        User dbUser = poiService.getUser(userID);
        System.out.println("Obtuve al usuario: " + dbUser.getUserName() + " con el ID: [" + dbUser.getId() + "]");
        
        // Le cambio el nombre
        String newUserName = "FacundoL";

        dbUser.setUserName(newUserName);
        System.out.println("Persisti el usuario: " + dbUser.getUserName() + " con el ID: [" + dbUser.getId() + "]");
        poiService.updateUser(dbUser);
        
        // Lo obtengo de nuevo y me fijo que coincida el nombre
        User resultUser = poiService.getUser(userID);
        System.out.println("Obtuve al usuario: " + resultUser.getUserName() + " con el ID: [" + resultUser.getId() + "]");
        
        Assert.equals(newUserName, resultUser.getUserName());
	}
	
	@Test
	public void insertBusStopTest()
	{
		// Obtengo un usuario para crear en la DB
		 BusStop busStop = this.getNewBusStop();
        
        // Grabo el POI
        Serializable busID = poiService.saveBusStop(busStop);
        System.out.println("Persisti el BusStop: " + busStop.getName() + " con el ID: [" + busStop.getId() + "]");
        
//        // Obtengo el usuario nuevamente de la DB
//        User dbUser = poiService.getUser(userID);
//        System.out.println("Obtuve al usuario: " + dbUser.getUserName() + " con el ID: [" + dbUser.getId() + "]");
//        
//        // Le cambio el nombre
//        String newUserName = "FacundoL";
//
//        dbUser.setUserName(newUserName);
//       busStop System.out.println("Persisti el usuario: " + dbUser.getUserName() + " con el ID: [" + dbUser.getId() + "]");
//        poiService.updateUser(dbUser);
//        
//        // Lo obtengo de nuevo y me fijo que coincida el nombre
//        User resultUser = poiService.getUser(userID);
//        System.out.println("Obtuve al usuario: " + resultUser.getUserName() + " con el ID: [" + resultUser.getId() + "]");
//        
//        Assert.equals(newUserName, resultUser.getUserName());
	}
	
	// Metodos de ayuda.
	public User getNewUser()
	{
        User user = new User();
        user.setUserName("luisk");
        user.setPassword("1234");
        user.setEmail("luis.kahrs@hotmail.com");
        user.setToken(UUID.randomUUID().toString());
        user.setAuditMode(true);
        user.setActions(null);
  
        Action actionLog = new Action();
        actionLog.setName("Log");
        
        Action actionSearch = new Action();
        actionSearch.setName("Search");

        List<Action> actions = new ArrayList<Action>();
        actionLog.setUser(user);
        actionSearch.setUser(user);
        
        actions.add(actionSearch);
        actions.add(actionLog);
        
        user.setActions(actions);
        
        return user;
	}
	
	public BusStop getNewBusStop()
	{
		Coordenate coords = new Coordenate(-34.619109, -58.425454);
		BusStop busStop = new BusStop("Linea 84", coords, 84, "Colectivo,Linea84,84,Parada");
		
		return busStop;
	}
}