package poi;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import poi.constant.*;
import poi.domain.*;
import poi.service.*;
import org.joda.time.*;

public class Entrega1Test 
{
	@Test
	public void butStopAvailabilityTest() 
	{
		// Vars
		DateTime avaTime = new DateTime();
		POIService poiService = new POIService();
		boolean result = false;
		
		// We create the busStop POI
		Coordenate busCoord = new Coordenate(-34.619160, -58.425443);
		BusStop busPOI = new BusStop("Parada 84", busCoord, 114, "tag1,tag2");
		
		// Test using the current time
		result = poiService.isAvailable(busPOI, avaTime, Service.NA);
		assertTrue(result);
		
		// Test using time 3 days from now.
		result = poiService.isAvailable(busPOI, avaTime.plusDays(3), Service.NA);
		assertTrue(result);	
	}
	
	@Test
	public void bankAvailabilityTest() 
	{
		// Vars
		POIService poiService = new POIService();
		boolean result = false;
		
		// We create the Bank POI
		Coordenate bankCoord = new Coordenate(-34.618191, -58.428769);		
		Bank bankPOI = new Bank("Banco Galicia Av. La Plata", bankCoord, "tag1,tag2");
		
		OpeningHour monday = new OpeningHour(Service.NA, DateTimeConstants.MONDAY, 10, 0, 15, 0);
		OpeningHour tuesday = new OpeningHour(Service.NA, DateTimeConstants.TUESDAY, 10, 0, 15, 0);
		OpeningHour wednesday = new OpeningHour(Service.NA, DateTimeConstants.WEDNESDAY, 10, 0, 15, 0);
		OpeningHour thursday = new OpeningHour(Service.NA, DateTimeConstants.THURSDAY, 10, 0, 15, 0);
		OpeningHour friday = new OpeningHour(Service.NA, DateTimeConstants.FRIDAY, 10, 0, 15, 0);
		
		bankPOI.addOpeningHour(monday);
		bankPOI.addOpeningHour(tuesday);
		bankPOI.addOpeningHour(wednesday);
		bankPOI.addOpeningHour(thursday);
		bankPOI.addOpeningHour(friday);
		
		// Test using Sunday 11am
		DateTime avaTime = new DateTime(2016, 6, 11, 11, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, Service.NA);
		assertFalse(result);
		
		// Test using Sunday 16pm
		avaTime = new DateTime(2016, 6, 11, 16, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, Service.NA);
		assertFalse(result);
		
		// Test using Monday 10am
		avaTime = new DateTime(2016, 6, 13, 10, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, Service.NA);
		assertTrue(result);
		
		// Test using Monday 13pm
		avaTime = new DateTime(2016, 6, 13, 13, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, Service.NA);
		assertTrue(result);
		
		// Test using Monday 16pm,
		avaTime = new DateTime(2016, 6, 13, 16, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, Service.NA);
		assertFalse(result);
	}
	
	@Test
	public void CGPAvailabilityTest() 
	{
		// Vars
		POIService poiService = new POIService();
		boolean result = false;
		List<Service> services = new ArrayList<Service>();
		
		// We create the Bank POI
		Coordenate CGPCoord = new Coordenate(-34.608828, -58.430982);	
		
		services.add(Service.INCOMES);
		services.add(Service.ABL);
		
		CGP CGPPOI = new CGP("CGP Parque Centenario", CGPCoord, services, "tag1,tag2");
		
		OpeningHour mondayIncomes = new OpeningHour(Service.INCOMES, DateTimeConstants.MONDAY, 10, 0, 14, 0);
		OpeningHour tuesdayIncomes = new OpeningHour(Service.INCOMES, DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		OpeningHour wednesdayIncomes = new OpeningHour(Service.INCOMES, DateTimeConstants.WEDNESDAY, 10, 0, 14, 0);
		OpeningHour thursdayIncomes = new OpeningHour(Service.INCOMES, DateTimeConstants.THURSDAY, 10, 0, 14, 0);
		OpeningHour fridayIncomes = new OpeningHour(Service.INCOMES, DateTimeConstants.FRIDAY, 10, 0, 14, 0);
		OpeningHour tuesdayABL = new OpeningHour(Service.ABL, DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		
		CGPPOI.addOpeningHour(mondayIncomes);
		CGPPOI.addOpeningHour(tuesdayIncomes);
		CGPPOI.addOpeningHour(wednesdayIncomes);
		CGPPOI.addOpeningHour(thursdayIncomes);
		CGPPOI.addOpeningHour(fridayIncomes);
		CGPPOI.addOpeningHour(tuesdayABL);
		
		// Test using INCOMES Sunday 11am
		DateTime avaTime = new DateTime(2016, 6, 11, 11, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, Service.INCOMES);
		assertFalse(result);
		
		// Test using INCOMES Sunday 16pm
		avaTime = new DateTime(2016, 6, 11, 16, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, Service.INCOMES);
		assertFalse(result);
		
		// Test using INCOMES Monday 10am
		avaTime = new DateTime(2016, 6, 13, 10, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, Service.INCOMES);
		assertTrue(result);
		
		// Test using INCOMES Monday 13pm
		avaTime = new DateTime(2016, 6, 13, 13, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, Service.INCOMES);
		assertTrue(result);
		
		// Test using INCOMES Monday 16pm,
		avaTime = new DateTime(2016, 6, 13, 16, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, Service.INCOMES);
		assertFalse(result);
		
		// Test using ABL Monday 13pm
		avaTime = new DateTime(2016, 6, 13, 13, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, Service.ABL);
		assertFalse(result);
		
		// Test using ABL Tuesday 13pm
		avaTime = new DateTime(2016, 6, 14, 13, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, Service.ABL);
		assertTrue(result);
		
		// Test using ABL Tuesday 15pm
		avaTime = new DateTime(2016, 6, 14, 15, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, Service.ABL);
		assertFalse(result);
	}
	
	@Test
	public void shopAvailabilityTest() 
	{
		// Vars
		POIService poiService = new POIService();
		boolean result = false;
		
		// We create the Shop POI
		Coordenate shopCoord = new Coordenate(-34.616325, -58.428837);		
		Shop shopPOI = new Shop("Cinemark Av. La Plata", shopCoord, Category.FURNITURE, "tag1,tag2");
		
		OpeningHour mondayMon = new OpeningHour(Service.NA, DateTimeConstants.MONDAY, 10, 0, 13, 0);
		OpeningHour mondayAft = new OpeningHour(Service.NA, DateTimeConstants.MONDAY, 17, 0, 22, 30);
		OpeningHour tuesdayMon = new OpeningHour(Service.NA, DateTimeConstants.TUESDAY, 10, 0, 13, 0);
		OpeningHour tuesdayAft = new OpeningHour(Service.NA, DateTimeConstants.TUESDAY, 17, 0, 20, 30);
		OpeningHour wednesdayMon = new OpeningHour(Service.NA, DateTimeConstants.WEDNESDAY, 10, 0, 13, 0);
		OpeningHour wednesdayAft = new OpeningHour(Service.NA, DateTimeConstants.WEDNESDAY, 17, 0, 20, 30);
		OpeningHour thursdayMon = new OpeningHour(Service.NA, DateTimeConstants.THURSDAY, 10, 0, 13, 0);
		OpeningHour thursdayAft = new OpeningHour(Service.NA, DateTimeConstants.THURSDAY, 17, 0, 20, 30);
		OpeningHour fridayMon = new OpeningHour(Service.NA, DateTimeConstants.FRIDAY, 10, 0, 13, 0);
		OpeningHour fridayAft = new OpeningHour(Service.NA, DateTimeConstants.FRIDAY, 17, 0, 20, 30);
		
		shopPOI.addOpeningHour(mondayMon);
		shopPOI.addOpeningHour(mondayAft);
		shopPOI.addOpeningHour(tuesdayMon);
		shopPOI.addOpeningHour(tuesdayAft);
		shopPOI.addOpeningHour(wednesdayMon);
		shopPOI.addOpeningHour(wednesdayAft);
		shopPOI.addOpeningHour(thursdayMon);
		shopPOI.addOpeningHour(thursdayAft);
		shopPOI.addOpeningHour(fridayMon);
		shopPOI.addOpeningHour(fridayAft);
		
		// Test using Sunday 11am
		DateTime avaTime = new DateTime(2016, 6, 11, 11, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, Service.NA);
		assertFalse(result);
		
		// Test using Sunday 16pm
		avaTime = new DateTime(2016, 6, 11, 16, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, Service.NA);
		assertFalse(result);
		
		// Test using Monday 10am
		avaTime = new DateTime(2016, 6, 13, 10, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, Service.NA);
		assertTrue(result);
		
		// Test using Monday 14pm
		avaTime = new DateTime(2016, 6, 13, 14, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, Service.NA);
		assertFalse(result);
		
		// Test using Monday 18pm
		avaTime = new DateTime(2016, 6, 13, 18, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, Service.NA);
		assertTrue(result);
	}
	
	@Test
	public void searchBusStopByBusLineTest()
	{
		String filter = "114";
		Coordenate busCoord = new Coordenate(-34.619160, -58.425443);
		BusStop busPOI84 = new BusStop("Parada 84", busCoord, 84, "84");
		BusStop busPOI114 = new BusStop("Parada 114", busCoord, 114, "114");
		BusStop busPOI132 = new BusStop("Parada 132", busCoord, 132, "132");
		List<POI> searchList = new ArrayList<POI>();
		
		searchList.add(busPOI84);
		searchList.add(busPOI114);
		searchList.add(busPOI132);
		
		POIService poiService = new POIService();
		List<POI> result = poiService.search(filter, searchList);
		
		assertTrue(result.size() == 1);
		assertTrue(((BusStop)result.get(0)).getBusLine() == 114);
	}
	
	@Test
	public void searchShopByCategoryTest()
	{
		String filter = "muebleria";
		Coordenate shopCoord = new Coordenate(-34.616325, -58.428837);		
		Shop shopPOIFurniture = new Shop("Muebles Tito", shopCoord, Category.FURNITURE, "tag1,tag2");
		Shop shopPOIHardstore = new Shop("El Clavo", shopCoord, Category.HARDSTORE, "tag1,tag2");
		Shop shopPOILIbrary = new Shop("El Clavo", shopCoord, Category.LIBRARY, "tag1,tag2");

		List<POI> searchList = new ArrayList<POI>();
		
		searchList.add(shopPOIFurniture);
		searchList.add(shopPOIHardstore);
		searchList.add(shopPOILIbrary);
		
		POIService poiService = new POIService();
		List<POI> result = poiService.search(filter, searchList);
		
		assertTrue(result.size() == 1);
		assertTrue(((Shop)result.get(0)).getShopCategory().getName() == "muebleria");
	}
	
	@Test
	public void searchBankByNameTest()
	{
		String filter = "Rio";
		Coordenate bankCoord = new Coordenate(-34.616325, -58.428837);		
		Bank bankPOIRio = new Bank("Rio", bankCoord, "tag1,tag2");
		Bank bankPOIGalicia = new Bank("Galicia", bankCoord, "tag1,tag2");
		Bank bankPOIFrances = new Bank("Frances", bankCoord, "tag1,tag2");

		List<POI> searchList = new ArrayList<POI>();
		
		searchList.add(bankPOIRio);
		searchList.add(bankPOIGalicia);
		searchList.add(bankPOIFrances);
		
		POIService poiService = new POIService();
		List<POI> result = poiService.search(filter, searchList);
		
		assertTrue(result.size() == 1);
		assertTrue(((Bank)result.get(0)).getName() == "Rio");
	}
	
	@Test
	public void isCloserToTestBank()
	{
		POIService poiService = new POIService();
		boolean result = false;
		Coordenate bankCoord = new Coordenate(-34.616325, -58.428837);
		Bank bankPOIRio = new Bank("Rio", bankCoord, "tag1,tag2");
		Coordenate bankCoord2 = new Coordenate(-14.616325, -38.428837);
		Bank bankPOIGalicia = new Bank("Rio", bankCoord2, "tag1,tag2");
		
		result = poiService.isCloserTo(bankPOIRio, bankPOIGalicia);
		assertTrue(result);
		
	}
	
	@Test
	public void isCloserToTestBusStop()
	{
		POIService poiService = new POIService();
		boolean result = false;
		Coordenate busCoord = new Coordenate(-34.619160, -58.425443);
		BusStop busPOI84 = new BusStop("Parada 84", busCoord, 84, "84");
		Coordenate busCoord2 = new Coordenate(-10.619160, -70.425443);
		BusStop busPOI90 = new BusStop("Parada 90", busCoord2, 90, "90");
		
		result = poiService.isCloserTo(busPOI84, busPOI90);
		assertTrue(result);
	}
	
	@Test
	public void isCloserToTestShop()
	{
		POIService poiService = new POIService();
		boolean result = false;
		Coordenate shopCoord = new Coordenate(-34.616325, -58.428837);		
		Shop shopPOIFurniture = new Shop("Muebles Tito", shopCoord, Category.FURNITURE, "tag1,tag2");
		Coordenate shopCoord2 = new Coordenate(-34.616325, -58.428837);		
		Shop shopPOITeatro = new Shop("Teatro Metropolitan", shopCoord2, Category.FURNITURE, "tag1,tag2");
		
		result = poiService.isCloserTo(shopPOIFurniture, shopPOITeatro);
		assertTrue(result);
	}
	
	@Test
	public void searchCGPByServiceTest()
	{
		String filter = "asesoramiento";
		Coordenate cgpCoord = new Coordenate(-34.616325, -58.428837);	
		
		List<Service> cgpPOIPalermoServ = new ArrayList<Service>();
		cgpPOIPalermoServ.add(Service.ABL);
		cgpPOIPalermoServ.add(Service.LEGAL_ADVICE);
		cgpPOIPalermoServ.add(Service.ACCOUNTING_ADVICE);
	
		List<Service> cgpPOICentenarioServ = new ArrayList<Service>();
		cgpPOICentenarioServ.add(Service.WEDDINGS);
		cgpPOICentenarioServ.add(Service.LEGAL_ADVICE);
	
		List<Service> cgpPOICaballitoServ = new ArrayList<Service>();
		cgpPOICaballitoServ.add(Service.CAR_PLATES);
		cgpPOICaballitoServ.add(Service.INCOMES);
		
		CGP cgpPOIPalermo = new CGP("CGP Palermo", cgpCoord, cgpPOIPalermoServ, "tag1,tag2");
		CGP cgpPOICentenario = new CGP("CGP Centenario", cgpCoord, cgpPOICentenarioServ, "tag1,tag2");
		CGP cgpPOICaballito = new CGP("CGP Caballito", cgpCoord, cgpPOICaballitoServ, "tag1,tag2");

		List<POI> searchList = new ArrayList<POI>();
		
		searchList.add(cgpPOIPalermo);
		searchList.add(cgpPOICentenario);
		searchList.add(cgpPOICaballito);
		
		POIService poiService = new POIService();
		List<POI> result = poiService.search(filter, searchList);
		
		assertTrue(result.size() == 2);
	}
}

