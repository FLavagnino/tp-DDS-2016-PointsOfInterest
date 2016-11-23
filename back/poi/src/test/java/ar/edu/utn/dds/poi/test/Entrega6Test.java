package ar.edu.utn.dds.poi.test;

import org.junit.Before;
import org.junit.Test;
import org.joda.time.DateTimeConstants;
import org.junit.Assert;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.exception.InvalidUserException;
import ar.edu.utn.dds.poi.service.AuthService;
import ar.edu.utn.dds.poi.service.POIService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Entrega6Test 
{	
	private AuthService authService;
	private POIService poiService;
	private String userName;
	private String password;
	private String token;
	
	@Before
	public void setUp()
	{
		this.authService = new AuthService();
		this.poiService = new POIService();
		
		List<User> userList = new ArrayList<User>();
			
		User tCaballito = new Terminal();	
		tCaballito.setUserName("t_caballito");
		tCaballito.setPassword("5555");
		tCaballito.setToken("");
		tCaballito.setAuditMode(true);
		tCaballito.setEmail("luiskahrs@gmail.com");
		userList.add(tCaballito);
	
		this.authService.setUsers(userList);
	}

	@Test
	// Prueba numero 1 Enunciado
	public void insertBankTest()
	{
		// Obtengo un usuario para crear en la DB
		Bank bank = this.getNewBank();
        
        // Grabo el POI
        Serializable bankID = poiService.saveBank(bank);
        System.out.println("Persisti el Bank: " + bank.getName() + " con el ID: [" + bank.getId() + "]");
        
        // Obtengo el poi nuevamente de la DB
        Bank dbBank = poiService.getBank(bankID);
        System.out.println("Obtuve al poi: " + dbBank.getName() + " con el ID: [" + dbBank.getId() + "]");
        
        // Le cambio las coordenadas
        Coordenate coords = new Coordenate(-34.0, -58.0);
        dbBank.setCoordenate(coords);
        
        System.out.println("Persisti el poi: " + dbBank.getName() + " con el ID: [" + dbBank.getId() + "]");
        poiService.updateBank(dbBank);
        
        // Lo obtengo de nuevo y me fijo que coincida el nombre
        Bank resultBank = poiService.getBank(bankID);
        System.out.println("Obtuve al poi: " + resultBank.getName() + " con el ID: [" + resultBank.getId() + "]");
        
        Assert.assertEquals(coords.getLatitude(), resultBank.getCoordenate().getLatitude());
	}
	
	@Test
	// Prueba numero 1 Enunciado
	public void insertShopTest()
	{
		// Obtengo un usuario para crear en la DB
		Shop shop = this.getNewShop();
        
        // Grabo el POI
        Serializable shopID = poiService.saveShop(shop);
        System.out.println("Persisti el Shop: " + shop.getName() + " con el ID: [" + shop.getId() + "]");
        
        Assert.assertNotNull(shopID);
	}
	
	@Test
	// Prueba numero 1 Enunciado
	public void insertCGPTest()
	{
		// Obtengo un usuario para crear en la DB
		CGP cgp = this.getNewCGP();
        
        // Grabo el POI
        Serializable cgpID = poiService.saveCGP(cgp);
        System.out.println("Persisti el CGP: " + cgp.getName() + " con el ID: [" + cgp.getId() + "]");
        
        Assert.assertNotNull(cgpID);
	}
	
	@Test
	// Prueba numero 2 Enunciado
	public void deleteBankTest()
	{
		// Obtengo un usuario para crear en la DB
		Bank bank = this.getNewBank();
        
        // Grabo el POI
        Serializable bankID = poiService.saveBank(bank);
        System.out.println("Persisti el Bank: " + bank.getName() + " con el ID: [" + bank.getId() + "]");
        
        // Obtengo el poi nuevamente de la DB
        Bank dbBank = poiService.getBank(bankID);
        System.out.println("Obtuve al poi: " + dbBank.getName() + " con el ID: [" + dbBank.getId() + "]");
        
        // Le cambio las coordenadas
        System.out.println("Lo borro...");
        poiService.deleteBank(dbBank);
        
        // Lo obtengo de nuevo y me fijo que coincida el nombre
        Bank resultBank = poiService.getBank(dbBank.getId());

        // Me fijo si es null
        Assert.assertNull(resultBank);
	}
	
	@Test
	// Prueba numero 3 Enunciado
	public void searchTest() throws InvalidUserException
	{
		userName = "t_caballito";
		password = "5555";
		token = "";
		
		token = this.authService.login(userName, password);
		this.poiService.search("Santander Rio", userName, token);	
		
		List<Log> logs = this.poiService.getLogByUserName(userName);
		
		Log lastLog = logs.get(logs.size() - 1);
        System.out.println("Ultimo log para el usuario: " + userName + ":\n" +
        		"Fecha: " + lastLog.getDate() + "\n" +
        		"Filtro: " + lastLog.getFilter() + "\n" +  
        		"Results Number: " + lastLog.getResultsNumber());
	}
	
	@Test
	// Prueba numero 4 Enunciado
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
        
        Assert.assertEquals(newUserName, resultUser.getUserName());
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
		
		busStop.setAddress(new Address());
		busStop.setUnit(100);
		
		return busStop;
	}
	
	public Bank getNewBank()
	{
		Coordenate coords = new Coordenate(-34.619109, -58.425454);
		Bank bank = new Bank("Banco Galicia", coords, "Banco,Galicia,Dinero");
		
		bank.setAddress(new Address());
		bank.setUnit(100);
		
		return bank;
	}
	
	public Shop getNewShop()
	{
		Coordenate coords = new Coordenate(-34.619109, -58.425454);
		Shop shop = new Shop("Muebleria Tito", coords, "muebleria", 1000, "muebleria,muebles,tito");
		
		shop.setAddress(new Address());
		shop.setUnit(101);
		
		return shop;
	}
	
	public CGP getNewCGP()
	{
		String services = "Rentas,ABL";
		List<ZoneCoordenate> zoneCoord = new ArrayList<ZoneCoordenate>();
		
		// We create the Bank POI
		Coordenate CGPCoord = new Coordenate(-34.608828, -58.430982);	
		
		ZoneCoordenate zone1 = new ZoneCoordenate(-34.618194, -58.427373);
		ZoneCoordenate zone2 = new ZoneCoordenate(-34.618706, -58.423339);
		ZoneCoordenate zone3 = new ZoneCoordenate(-34.620949, -58.424058);
		ZoneCoordenate zone4 = new ZoneCoordenate(-34.620887, -58.426236);
		ZoneCoordenate zone5 = new ZoneCoordenate(-34.619704, -58.427845);
		ZoneCoordenate zone6 = new ZoneCoordenate(-34.618194, -58.427373);
		
		zoneCoord.add(zone1);
		zoneCoord.add(zone2);
		zoneCoord.add(zone3);
		zoneCoord.add(zone4);
		zoneCoord.add(zone5);
		zoneCoord.add(zone6);
				
		CGP cgp = new CGP("CGP Parque Centenario", CGPCoord, zoneCoord, services, "tag1,tag2");
		
		cgp.setAddress(new Address("Mozart", 3000, "Dellepiane", "Castañares", 1, 
							"A", "1234", "CABA", "Lugano", "Buenos Aires", "Argentina"));
		
		cgp.setUnit(101);
		
		OpeningHour mondayIncomes = new OpeningHour("Rentas", DateTimeConstants.MONDAY, 10, 0, 14, 0);
		OpeningHour tuesdayIncomes = new OpeningHour("No aplica", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		OpeningHour wednesdayIncomes = new OpeningHour("No aplica", DateTimeConstants.WEDNESDAY, 10, 0, 14, 0);
		OpeningHour thursdayIncomes = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 10, 0, 14, 0);
		OpeningHour fridayIncomes = new OpeningHour("No aplica", DateTimeConstants.FRIDAY, 10, 0, 14, 0);
		OpeningHour tuesdayABL = new OpeningHour("ABL", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		
		cgp.addOpeningHour(mondayIncomes);
		cgp.addOpeningHour(tuesdayIncomes);
		cgp.addOpeningHour(wednesdayIncomes);
		cgp.addOpeningHour(thursdayIncomes);
		cgp.addOpeningHour(fridayIncomes);
		cgp.addOpeningHour(tuesdayABL);
		
		return cgp;
	}
}