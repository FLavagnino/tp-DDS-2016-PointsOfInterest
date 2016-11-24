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

public class EntregaFinalTest 
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
	}

	@Test
	// Prueba numero 1 Enunciado
	public void insertBankTest()
	{
	}
	
	@Test
	public void insertUserTest()
	{
		List<User> users = this.getUsers();
		
		for (User user : users)
		{
			poiService.saveUser(user);
		}
	}
	
	@Test
	public void insertShopTest()
	{
	}
	
	@Test
	public void insertCGPTest()
	{
		//CGP cgp = this.getNewCGP();
        //poiService.saveCGP(cgp);
	}
		
	// Metodos de ayuda.
	public List<User> getUsers()
	{
		List<User> users = new ArrayList<User>();
		
        User luis = new User();
        luis.setUserName("luisk");
        luis.setPassword("1234");
        luis.setEmail("luis.kahrs@hotmail.com");
        luis.setToken(UUID.randomUUID().toString());
        luis.setAuditMode(true);
        luis.setActions(null);
  
        Action actionLogLuis = new Action();
        actionLogLuis.setName("Generar Log");
        
        Action actionTotFechaLuis = new Action();
        actionTotFechaLuis.setName("Totalizar por Fecha");

        List<Action> actionsLuis = new ArrayList<Action>();
        actionLogLuis.setUser(luis);
        actionTotFechaLuis.setUser(luis);
        
        actionsLuis.add(actionTotFechaLuis);
        actionsLuis.add(actionLogLuis);
        
        luis.setActions(actionsLuis);
        
        users.add(luis);
        
        User facul = new User();
        facul.setUserName("facul");
        facul.setPassword("1234");
        facul.setEmail("faculavag@gmail.com");
        facul.setToken(UUID.randomUUID().toString());
        facul.setAuditMode(true);
        facul.setActions(null);
        
        Action actionTotXUserFacuL = new Action();
        actionTotXUserFacuL.setName("Totalizar por Usuario");
        
        Action actionLogFacuL = new Action();
        actionLogFacuL.setName("Generar Log");

        List<Action> actionsFacuL = new ArrayList<Action>();
        actionLogFacuL.setUser(facul);
        actionTotXUserFacuL.setUser(facul);
        
        actionsFacuL.add(actionTotXUserFacuL);
        actionsFacuL.add(actionLogFacuL);
        
        facul.setActions(actionsFacuL);
        
        users.add(facul);
        
        User facub = new User();
        facub.setUserName("facub");
        facub.setPassword("1234");
        facub.setEmail("faku.kpg@gmail.com");
        facub.setToken(UUID.randomUUID().toString());
        facub.setAuditMode(true);
        facub.setActions(null);
        
        Action actionLogFacuB = new Action();
        actionLogFacuB.setName("Generar Log");

        List<Action> actionsFacuB = new ArrayList<Action>();
        actionLogFacuB.setUser(facub);
        
        actionsFacuB.add(actionLogFacuB);

        facub.setActions(actionsFacuB);
        
        users.add(facub);
        
        User juan = new User();
        juan.setUserName("juanc");
        juan.setPassword("1234");
        juan.setEmail("juanmanuelcarucci@gmail.com");
        juan.setToken(UUID.randomUUID().toString());
        juan.setAuditMode(true);
        juan.setActions(null);
        
        Action actionLogJuanC = new Action();
        actionLogJuanC.setName("Generar Log");

        List<Action> actionsJuanC = new ArrayList<Action>();
        actionLogJuanC.setUser(juan);
        
        actionsJuanC.add(actionLogJuanC);

        juan.setActions(actionsJuanC);
        
        users.add(juan);
        
        return users;
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
	
	public List<CGP> getCGPs()
	{
		List<CGP> cgps = new ArrayList<CGP>();
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
		
		return cgps;
	}
}