package ar.edu.utn.dds.poi.test;

import org.junit.Before;
import org.junit.Test;
import org.joda.time.DateTimeConstants;
import org.junit.After;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.repository.HibernateManager;
import ar.edu.utn.dds.poi.service.POIService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntregaFinalTest 
{	
	private POIService poiService;
	
	@Before
	public void setUp()
	{
		this.poiService = new POIService();
	}
	
	@After
	public void cleanUp()
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
	public void insertCGPTest()
	{
		List<CGP> cgps = this.getCGPs();
		
		for (CGP cgp : cgps)
		{
			poiService.saveCGP(cgp);
		}
	}
	
	@Test
	public void insertBankTest()
	{
	}
	
	@Test
	public void insertShopTest()
	{
	}
	
	@Test
	public void insertBusStopTest()
	{
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
		
		String servicesCent = "Rentas,ABL";
		List<ZoneCoordenate> zoneCoordCent = new ArrayList<ZoneCoordenate>();
		
		Coordenate CGPCoordCent = new Coordenate(-34.608828, -58.430982);	
		
		ZoneCoordenate zone1Cent = new ZoneCoordenate(-34.618194, -58.427373);
		ZoneCoordenate zone2Cent = new ZoneCoordenate(-34.618706, -58.423339);
		ZoneCoordenate zone3Cent = new ZoneCoordenate(-34.620949, -58.424058);
		ZoneCoordenate zone4Cent = new ZoneCoordenate(-34.620887, -58.426236);
		ZoneCoordenate zone5Cent = new ZoneCoordenate(-34.619704, -58.427845);
		ZoneCoordenate zone6Cent = new ZoneCoordenate(-34.618194, -58.427373);
		
		zoneCoordCent.add(zone1Cent);
		zoneCoordCent.add(zone2Cent);
		zoneCoordCent.add(zone3Cent);
		zoneCoordCent.add(zone4Cent);
		zoneCoordCent.add(zone5Cent);
		zoneCoordCent.add(zone6Cent);
				
		CGP cgpCent = new CGP("CGP Parque Centenario", CGPCoordCent, zoneCoordCent, servicesCent, "Parque,Centenario,CGP");
		
		cgpCent.setAddress(new Address("Mozart", 3000, "Dellepiane", "Castañares", 1, 
							"A", "1234", "CABA", "Lugano", "Buenos Aires", "Argentina"));
		
		cgpCent.setUnit(10);
		
		OpeningHour CentMondayIncomes = new OpeningHour("Rentas", DateTimeConstants.MONDAY, 10, 0, 14, 0);
		OpeningHour CentTuesdayIncomes = new OpeningHour("No aplica", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		OpeningHour CentWednesdayIncomes = new OpeningHour("No aplica", DateTimeConstants.WEDNESDAY, 10, 0, 14, 0);
		OpeningHour CentThursdayIncomes = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 10, 0, 14, 0);
		OpeningHour CentFridayIncomes = new OpeningHour("No aplica", DateTimeConstants.FRIDAY, 10, 0, 14, 0);
		OpeningHour CentTuesdayABL = new OpeningHour("ABL", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		
		cgpCent.addOpeningHour(CentMondayIncomes);
		cgpCent.addOpeningHour(CentTuesdayIncomes);
		cgpCent.addOpeningHour(CentWednesdayIncomes);
		cgpCent.addOpeningHour(CentThursdayIncomes);
		cgpCent.addOpeningHour(CentFridayIncomes);
		cgpCent.addOpeningHour(CentTuesdayABL);
		
		cgps.add(cgpCent);
		
		String servicesAcoyte = "ABL,Asesoramiento Legal,Asesoramiento Contable";
		
		List<ZoneCoordenate> zoneCoordAcoyte = new ArrayList<ZoneCoordenate>();
		
		Coordenate CGPCoordAcoyte = new Coordenate(-34.618327, -58.436408);	
		
		ZoneCoordenate zone1Acoyte = new ZoneCoordenate(-34.617232, -58.434037);
		ZoneCoordenate zone2Acoyte = new ZoneCoordenate(-34.619227, -58.434885);
		ZoneCoordenate zone3Acoyte = new ZoneCoordenate(-34.618953, -58.437653);
		ZoneCoordenate zone4Acoyte = new ZoneCoordenate(-34.616993, -58.437846);
		ZoneCoordenate zone5Acoyte = new ZoneCoordenate(-34.616428, -58.435775);
		ZoneCoordenate zone6Acoyte = new ZoneCoordenate(-34.617232, -58.434037);
		
		zoneCoordAcoyte.add(zone1Acoyte);
		zoneCoordAcoyte.add(zone2Acoyte);
		zoneCoordAcoyte.add(zone3Acoyte);
		zoneCoordAcoyte.add(zone4Acoyte);
		zoneCoordAcoyte.add(zone5Acoyte);
		zoneCoordAcoyte.add(zone6Acoyte);
				
		CGP cgpAcoyte = new CGP("CGP Acoyte", CGPCoordAcoyte, zoneCoordAcoyte, servicesAcoyte, "Acoyte,CGP");
		
		cgpAcoyte.setAddress(new Address("Acoyte", 2450, "Rivadavia", "Rosario", 2, 
							"C", "1405", "CABA", "Caballito", "Buenos Aires", "Argentina"));
		
		cgpAcoyte.setUnit(20);
		
		OpeningHour AcoyteMondayIncomes = new OpeningHour("Asesoramiento Legal", DateTimeConstants.MONDAY, 10, 0, 14, 0);
		OpeningHour AcoyteTuesdayIncomes = new OpeningHour("No aplica", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		OpeningHour AcoyteWednesdayIncomes = new OpeningHour("Asesoramiento Contable", DateTimeConstants.WEDNESDAY, 10, 0, 14, 0);
		OpeningHour AcoyteThursdayIncomes = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 10, 0, 14, 0);
		OpeningHour AcoyteFridayIncomes = new OpeningHour("Asesoramiento Legal", DateTimeConstants.FRIDAY, 10, 0, 14, 0);
		OpeningHour AcoyteTuesdayABL = new OpeningHour("ABL", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		
		cgpAcoyte.addOpeningHour(AcoyteMondayIncomes);
		cgpAcoyte.addOpeningHour(AcoyteTuesdayIncomes);
		cgpAcoyte.addOpeningHour(AcoyteWednesdayIncomes);
		cgpAcoyte.addOpeningHour(AcoyteThursdayIncomes);
		cgpAcoyte.addOpeningHour(AcoyteFridayIncomes);
		cgpAcoyte.addOpeningHour(AcoyteTuesdayABL);
		
		cgps.add(cgpAcoyte);
		
		String servicesLaPlata = "Bodas,Patentes";

		List<ZoneCoordenate> zoneCoordLaPlata = new ArrayList<ZoneCoordenate>();
		
		Coordenate CGPCoordLaPlata = new Coordenate(-34.615015, -58.429166);	
		
		ZoneCoordenate zone1LaPlata = new ZoneCoordenate(-34.614026, -58.426613);
		ZoneCoordenate zone2LaPlata = new ZoneCoordenate(-34.615909, -58.427063);
		ZoneCoordenate zone3LaPlata = new ZoneCoordenate(-34.617286, -58.430099);
		ZoneCoordenate zone4LaPlata = new ZoneCoordenate(-34.615644, -58.431859);
		ZoneCoordenate zone5LaPlata = new ZoneCoordenate(-34.613401, -58.429756);
		ZoneCoordenate zone6LaPlata = new ZoneCoordenate(-34.614026, -58.426613);
		
		zoneCoordLaPlata.add(zone1LaPlata);
		zoneCoordLaPlata.add(zone2LaPlata);
		zoneCoordLaPlata.add(zone3LaPlata);
		zoneCoordLaPlata.add(zone4LaPlata);
		zoneCoordLaPlata.add(zone5LaPlata);
		zoneCoordLaPlata.add(zone6LaPlata);
				
		CGP cgpLaPlata = new CGP("CGP La Plata", CGPCoordLaPlata, zoneCoordLaPlata, servicesLaPlata, "La Plata,CGP");
		
		cgpLaPlata.setAddress(new Address("Av. La Plata", 152, "Rivadavia", "Chaco", 3, 
							"D", "1411", "CABA", "Almagro", "Buenos Aires", "Argentina"));
		
		cgpLaPlata.setUnit(30);
		
		OpeningHour LaPlataMondayIncomes = new OpeningHour("No aplica", DateTimeConstants.MONDAY, 10, 0, 14, 0);
		OpeningHour LaPlataTuesdayIncomes = new OpeningHour("Patentes", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		OpeningHour LaPlataWednesdayIncomes = new OpeningHour("No aplica", DateTimeConstants.WEDNESDAY, 10, 0, 14, 0);
		OpeningHour LaPlataThursdayIncomes = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 10, 0, 14, 0);
		OpeningHour LaPlataFridayIncomes = new OpeningHour("No aplica", DateTimeConstants.FRIDAY, 10, 0, 14, 0);
		OpeningHour LaPlataTuesdayABL = new OpeningHour("Bodas", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		
		cgpLaPlata.addOpeningHour(LaPlataMondayIncomes);
		cgpLaPlata.addOpeningHour(LaPlataTuesdayIncomes);
		cgpLaPlata.addOpeningHour(LaPlataWednesdayIncomes);
		cgpLaPlata.addOpeningHour(LaPlataThursdayIncomes);
		cgpLaPlata.addOpeningHour(LaPlataFridayIncomes);
		cgpLaPlata.addOpeningHour(LaPlataTuesdayABL);
		
		cgps.add(cgpLaPlata);
		
		return cgps;
	}
}