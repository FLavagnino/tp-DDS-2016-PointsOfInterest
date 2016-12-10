package ar.edu.utn.dds.poi.test;

import org.junit.Before;
import org.junit.Test;
import org.joda.time.DateTimeConstants;
import org.junit.After;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.repository.POIRepository;
import ar.edu.utn.dds.poi.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntregaFinalTest 
{	
	private POIRepository poiRepository;
	private UserRepository userRepository;
	
	@Before
	public void setUp()
	{
		poiRepository = new POIRepository();
		userRepository = new UserRepository();
	}
	
	@After
	public void cleanUp()
	{
	}

	@Test
	// Utilizamos este test para llenar la entidad correspondiente a User
	public void insertUserTest()
	{
		List<User> users = getUsers();
		
		for (User user : users)
		{
			userRepository.save(user);
		}
	}
	
	@Test
	// Utilizamos este test para llenar la entidad correspondiente a CGP -> POI
	public void insertCGPTest()
	{
		List<CGP> cgps = getCGPs();
		
		for (CGP cgp : cgps)
		{
			poiRepository.save(cgp);
		}
	}
	
	@Test
	// Utilizamos este test para llenar la entidad correspondiente a Bank -> POI
	public void insertBankTest()
	{
		List<Bank> banks = getBanks();
		
		for (Bank bank : banks)
		{
			poiRepository.save(bank);
		}
	}
	
	@Test
	// Utilizamos este test para llenar la entidad correspondiente a Shop -> POI
	public void insertShopTest()
	{
		List<Shop> shops = getShops();
		
		for (Shop shop : shops)
		{
			poiRepository.save(shop);
		}
	}
	
	@Test
	// Utilizamos este test para llenar la entidad correspondiente a BusStop -> POI
	public void insertBusStopTest()
	{
		List<BusStop> busStops = getBusStops();
		
		for (BusStop busStop : busStops)
		{
			poiRepository.save(busStop);
		}
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
	
	
	public List<BusStop> getBusStops()
	{
		List<BusStop> busStops = new ArrayList<BusStop>();
		
		Coordenate coords84 = new Coordenate(-34.619109, -58.425454);
		BusStop busStop84 = new BusStop("Linea 84", coords84, 84, "Colectivo,Linea84,84,Parada");
		
		busStop84.setAddress(new Address("Agrelo", 4151, "Jose Marmol", "33 Orientales", 0, 
				"PB", "1236", "CABA", "Almagro", "Buenos Aires", "Argentina"));
		
		busStop84.setUnit(100);
		busStops.add(busStop84);
		
		Coordenate coords132 = new Coordenate(-34.617670, -58.427310);
		BusStop busStop132 = new BusStop("Linea 132", coords132, 132, "Colectivo,Linea132,132,Parada");
		
		busStop132.setAddress(new Address("Muñiz", 461, "Venezuela", "Quito", 0, 
				"PB", "1236", "CABA", "Almagro", "Buenos Aires", "Argentina"));
		
		busStop132.setUnit(110);
		busStops.add(busStop132);
		
		Coordenate coords105 = new Coordenate(-34.614588, -58.428136);
		BusStop busStop105 = new BusStop("Linea 105", coords105, 105, "Colectivo,Linea105,105,Parada");
		
		busStop105.setAddress(new Address("Rivadavia", 4337, "Yatay", "Av La Plata", 0, 
				"PB", "1205", "CABA", "Almagro", "Buenos Aires", "Argentina"));
		
		busStop105.setUnit(120);
		busStops.add(busStop105);
		
		return busStops;
	}
	
	public List<Bank> getBanks()
	{
		List<Bank> banks = new ArrayList<Bank>();
		
		Coordenate coordsGalicia = new Coordenate(-34.618209, -58.428791);
		Bank bankGalicia = new Bank("Banco Galicia", coordsGalicia, "Banco,Galicia,Dinero");
		
		OpeningHour monday = new OpeningHour("No aplica", DateTimeConstants.MONDAY, 10, 0, 15, 0);
		OpeningHour tuesday = new OpeningHour("No aplica", DateTimeConstants.TUESDAY, 10, 0, 15, 0);
		OpeningHour wednesday = new OpeningHour("No aplica", DateTimeConstants.WEDNESDAY, 10, 0, 15, 0);
		OpeningHour thursday = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 10, 0, 15, 0);
		OpeningHour friday = new OpeningHour("No aplica", DateTimeConstants.FRIDAY, 10, 0, 15, 0);
		
		bankGalicia.addOpeningHour(monday);
		bankGalicia.addOpeningHour(tuesday);
		bankGalicia.addOpeningHour(wednesday);
		bankGalicia.addOpeningHour(thursday);
		bankGalicia.addOpeningHour(friday);
		
		bankGalicia.setAddress(new Address("Av La Plata", 277, "Rosario", "Quito", 0, 
				"PB", "1184", "CABA", "Caballito", "Buenos Aires", "Argentina"));
		
		bankGalicia.setUnit(70);
		banks.add(bankGalicia);
		
		Coordenate coordsSantander = new Coordenate(-34.616646, -58.428791);
		Bank bankRio = new Bank("Banco Santander Rio", coordsSantander, "Banco,Rio,Dinero");
		
		bankRio.addOpeningHour(monday);
		bankRio.addOpeningHour(tuesday);
		bankRio.addOpeningHour(wednesday);
		bankRio.addOpeningHour(thursday);
		bankRio.addOpeningHour(friday);
		
		bankRio.setAddress(new Address("Av La Plata", 154, "Rivadavia", "Quito", 0, 
				"PB", "1184", "CABA", "Caballito", "Buenos Aires", "Argentina"));
		
		bankRio.setUnit(80);
		banks.add(bankRio);
		
		Coordenate coordsCiudad = new Coordenate(-34.617264, -58.436827);
		Bank bankCiudad = new Bank("Banco Ciudad", coordsCiudad, "Banco,Ciudad,Dinero");
		
		bankCiudad.addOpeningHour(monday);
		bankCiudad.addOpeningHour(tuesday);
		bankCiudad.addOpeningHour(wednesday);
		bankCiudad.addOpeningHour(thursday);
		bankCiudad.addOpeningHour(friday);
		
		bankCiudad.setAddress(new Address("Acoyte", 91, "Rivadavia", "Yerbal", 0, 
				"PB", "1405", "CABA", "Caballito", "Buenos Aires", "Argentina"));
		
		bankCiudad.setUnit(90);
		banks.add(bankCiudad);
		
		return banks;
	}
	
	public List<Shop> getShops()
	{
		List<Shop> shops = new ArrayList<Shop>();
		
		Coordenate coordsMuebleria = new Coordenate(-34.619109, -58.425454);
		Shop shopMuebleria = new Shop("Muebleria Tito", coordsMuebleria, "muebleria", 1000, "muebleria,muebles,tito");
		
		OpeningHour mondayMon = new OpeningHour("No aplica", DateTimeConstants.MONDAY, 10, 0, 13, 0);
		OpeningHour mondayAft = new OpeningHour("No aplica", DateTimeConstants.MONDAY, 17, 0, 22, 30);
		OpeningHour tuesdayMon = new OpeningHour("No aplica", DateTimeConstants.TUESDAY, 10, 0, 13, 0);
		OpeningHour tuesdayAft = new OpeningHour("No aplica", DateTimeConstants.TUESDAY, 17, 0, 20, 30);
		OpeningHour wednesdayMon = new OpeningHour("No aplica", DateTimeConstants.WEDNESDAY, 10, 0, 13, 0);
		OpeningHour wednesdayAft = new OpeningHour("No aplica", DateTimeConstants.WEDNESDAY, 17, 0, 20, 30);
		OpeningHour thursdayMon = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 10, 0, 13, 0);
		OpeningHour thursdayAft = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 17, 0, 20, 30);
		OpeningHour fridayMon = new OpeningHour("No aplica", DateTimeConstants.FRIDAY, 10, 0, 13, 0);
		OpeningHour fridayAft = new OpeningHour("No aplica", DateTimeConstants.FRIDAY, 17, 0, 20, 30);
		
		shopMuebleria.addOpeningHour(mondayMon);
		shopMuebleria.addOpeningHour(mondayAft);
		shopMuebleria.addOpeningHour(tuesdayMon);
		shopMuebleria.addOpeningHour(tuesdayAft);
		shopMuebleria.addOpeningHour(wednesdayMon);
		shopMuebleria.addOpeningHour(wednesdayAft);
		shopMuebleria.addOpeningHour(thursdayMon);
		shopMuebleria.addOpeningHour(thursdayAft);
		shopMuebleria.addOpeningHour(fridayMon);
		shopMuebleria.addOpeningHour(fridayAft);
		
		shopMuebleria.setAddress(new Address("Agrelo", 4151, "Jose Marmol", "33 Orientales", 4, 
				"B", "1211", "CABA", "Almagro", "Buenos Aires", "Argentina"));
		
		shopMuebleria.setUnit(40);
		
		shops.add(shopMuebleria);
		
		Coordenate coordsLibreria = new Coordenate(-34.616902, -58.423823);
		Shop shopLibreria = new Shop("Libreria Escuadra", coordsLibreria, "libreria", 1500, "libreria,libros,escuadra");
				
		shopLibreria.addOpeningHour(mondayMon);
		shopLibreria.addOpeningHour(mondayAft);
		shopLibreria.addOpeningHour(tuesdayMon);
		shopLibreria.addOpeningHour(tuesdayAft);
		shopLibreria.addOpeningHour(wednesdayMon);
		shopLibreria.addOpeningHour(wednesdayAft);
		shopLibreria.addOpeningHour(thursdayMon);
		shopLibreria.addOpeningHour(thursdayAft);
		shopLibreria.addOpeningHour(fridayMon);
		shopLibreria.addOpeningHour(fridayAft);
		
		shopLibreria.setAddress(new Address("Quito", 4054, "33 Orientales", "Quintino Bocayuba", 0, 
				"PB", "1211", "CABA", "Almagro", "Buenos Aires", "Argentina"));
		
		shopLibreria.setUnit(50);
		
		shops.add(shopLibreria);
		
		Coordenate coordsFerreteria = new Coordenate(-34.614774, -58.423641);
		Shop shopFerreteria = new Shop("Ferreteria El Tornillo", coordsFerreteria, "ferreteria", 200, "ferreteria,herramientas,tornillo");
				
		shopFerreteria.addOpeningHour(mondayMon);
		shopFerreteria.addOpeningHour(mondayAft);
		shopFerreteria.addOpeningHour(tuesdayMon);
		shopFerreteria.addOpeningHour(tuesdayAft);
		shopFerreteria.addOpeningHour(wednesdayMon);
		shopFerreteria.addOpeningHour(wednesdayAft);
		shopFerreteria.addOpeningHour(thursdayMon);
		shopFerreteria.addOpeningHour(thursdayAft);
		shopFerreteria.addOpeningHour(fridayMon);
		shopFerreteria.addOpeningHour(fridayAft);
		
		shopFerreteria.setAddress(new Address("Hipolito Yrigoyen", 4013, "33 Orientales", "Quintino Bocayuba", 0, 
				"PB", "1208", "CABA", "Almagro", "Buenos Aires", "Argentina"));
		
		shopFerreteria.setUnit(60);
		
		shops.add(shopFerreteria);
		
		return shops;
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
		cgpCent.setZone("Parque Centenario");
		
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
		cgpAcoyte.setZone("Caballito");
		
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
		cgpLaPlata.setZone("Almagro");
		
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