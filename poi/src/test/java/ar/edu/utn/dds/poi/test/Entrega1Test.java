package ar.edu.utn.dds.poi.test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import ar.edu.utn.dds.poi.constant.Category;
import ar.edu.utn.dds.poi.domain.Bank;
import ar.edu.utn.dds.poi.domain.BusStop;
import ar.edu.utn.dds.poi.domain.CGP;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.OpeningHour;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.domain.Shop;
import ar.edu.utn.dds.poi.exception.InvalidPoiException;
import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.service.SearchResult;

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
		result = poiService.isAvailable(busPOI, avaTime, "No aplica");
		assertTrue(result);
		
		// Test using time 3 days from now.
		result = poiService.isAvailable(busPOI, avaTime.plusDays(3), "No aplica");
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
		
		OpeningHour monday = new OpeningHour("No aplica", DateTimeConstants.MONDAY, 10, 0, 15, 0);
		OpeningHour tuesday = new OpeningHour("No aplica", DateTimeConstants.TUESDAY, 10, 0, 15, 0);
		OpeningHour wednesday = new OpeningHour("No aplica", DateTimeConstants.WEDNESDAY, 10, 0, 15, 0);
		OpeningHour thursday = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 10, 0, 15, 0);
		OpeningHour friday = new OpeningHour("No aplica", DateTimeConstants.FRIDAY, 10, 0, 15, 0);
		
		bankPOI.addOpeningHour(monday);
		bankPOI.addOpeningHour(tuesday);
		bankPOI.addOpeningHour(wednesday);
		bankPOI.addOpeningHour(thursday);
		bankPOI.addOpeningHour(friday);
		
		// Test using Sunday 11am
		DateTime avaTime = new DateTime(2016, 6, 11, 11, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, "No aplica");
		assertFalse(result);
		
		// Test using Sunday 16pm
		avaTime = new DateTime(2016, 6, 11, 16, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, "No aplica");
		assertFalse(result);
		
		// Test using Monday 10am
		avaTime = new DateTime(2016, 6, 13, 10, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, "No aplica");
		assertTrue(result);
		
		// Test using Monday 13pm
		avaTime = new DateTime(2016, 6, 13, 13, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, "No aplica");
		assertTrue(result);
		
		// Test using Monday 16pm,
		avaTime = new DateTime(2016, 6, 13, 16, 0, 0, 0);
		result = poiService.isAvailable(bankPOI, avaTime, "No aplica");
		assertFalse(result);
	}
	
	@Test
	public void CGPAvailabilityTest() 
	{
		// Vars
		POIService poiService = new POIService();
		boolean result = false;
		List<String> services = new ArrayList<String>();
		
		// We create the Bank POI
		Coordenate CGPCoord = new Coordenate(-34.608828, -58.430982);	
		
		services.add("Rentas");
		services.add("ABL");
		
		CGP CGPPOI = new CGP("CGP Parque Centenario", CGPCoord, services, "tag1,tag2");
		
		OpeningHour mondayIncomes = new OpeningHour("Rentas", DateTimeConstants.MONDAY, 10, 0, 14, 0);
		OpeningHour tuesdayIncomes = new OpeningHour("No aplica", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		OpeningHour wednesdayIncomes = new OpeningHour("No aplica", DateTimeConstants.WEDNESDAY, 10, 0, 14, 0);
		OpeningHour thursdayIncomes = new OpeningHour("No aplica", DateTimeConstants.THURSDAY, 10, 0, 14, 0);
		OpeningHour fridayIncomes = new OpeningHour("No aplica", DateTimeConstants.FRIDAY, 10, 0, 14, 0);
		OpeningHour tuesdayABL = new OpeningHour("ABL", DateTimeConstants.TUESDAY, 10, 0, 14, 0);
		
		CGPPOI.addOpeningHour(mondayIncomes);
		CGPPOI.addOpeningHour(tuesdayIncomes);
		CGPPOI.addOpeningHour(wednesdayIncomes);
		CGPPOI.addOpeningHour(thursdayIncomes);
		CGPPOI.addOpeningHour(fridayIncomes);
		CGPPOI.addOpeningHour(tuesdayABL);
		
		// Test using INCOMES Sunday 11am
		DateTime avaTime = new DateTime(2016, 6, 11, 11, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, "Rentas");
		assertFalse(result);
		
		// Test using INCOMES Sunday 16pm
		avaTime = new DateTime(2016, 6, 11, 16, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, "Rentas");
		assertFalse(result);
		
		// Test using INCOMES Monday 10am
		avaTime = new DateTime(2016, 6, 13, 10, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, "Rentas");
		assertTrue(result);
		
		// Test using INCOMES Monday 13pm
		avaTime = new DateTime(2016, 6, 13, 13, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, "Rentas");
		assertTrue(result);
		
		// Test using INCOMES Monday 16pm,
		avaTime = new DateTime(2016, 6, 13, 16, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, "Rentas");
		assertFalse(result);
		
		// Test using ABL Monday 13pm
		avaTime = new DateTime(2016, 6, 13, 13, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, "ABL");
		assertFalse(result);
		
		// Test using ABL Tuesday 13pm
		avaTime = new DateTime(2016, 6, 14, 13, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, "ABL");
		assertTrue(result);
		
		// Test using ABL Tuesday 15pm
		avaTime = new DateTime(2016, 6, 14, 15, 0, 0, 0);
		result = poiService.isAvailable(CGPPOI, avaTime, "ABL");
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
		result = poiService.isAvailable(shopPOI, avaTime, "No aplica");
		assertFalse(result);
		
		// Test using Sunday 16pm
		avaTime = new DateTime(2016, 6, 11, 16, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, "No aplica");
		assertFalse(result);
		
		// Test using Monday 10am
		avaTime = new DateTime(2016, 6, 13, 10, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, "No aplica");
		assertTrue(result);
		
		// Test using Monday 14pm
		avaTime = new DateTime(2016, 6, 13, 14, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, "No aplica");
		assertFalse(result);
		
		// Test using Monday 18pm
		avaTime = new DateTime(2016, 6, 13, 18, 0, 0, 0);
		result = poiService.isAvailable(shopPOI, avaTime, "No aplica");
		assertTrue(result);
	}
	
	@Test
	public void searchBusStopByBusLineTest() throws InvalidPoiException
	{
		String filter = "114";
		Coordenate busCoord = new Coordenate(-34.619160, -58.425443);
		BusStop busPOI84 = new BusStop("Parada 84", busCoord, 84, "84");
		BusStop busPOI114 = new BusStop("Parada 114", busCoord, 114, "114");
		BusStop busPOI132 = new BusStop("Parada 132", busCoord, 132, "132");
		
		POIService poiService = new POIService();
		
		poiService.addPoi(busPOI84);
		poiService.addPoi(busPOI114);
		poiService.addPoi(busPOI132);
		
		SearchResult result = poiService.search(filter);
		
		assertTrue(result.getPois().size() == 1);
		assertTrue(((BusStop)result.getPois().get(0)).getBusLine() == 114);
	}
	
	@Test
	public void searchShopByCategoryTest() throws InvalidPoiException
	{
		String filter = "muebleria";
		Coordenate shopCoord = new Coordenate(-34.616325, -58.428837);		
		Shop shopPOIFurniture = new Shop("Muebles Tito", shopCoord, Category.FURNITURE, "tag1,tag2");
		Shop shopPOIHardstore = new Shop("El Clavo", shopCoord, Category.HARDSTORE, "tag1,tag2");
		Shop shopPOILIbrary = new Shop("El Clavo", shopCoord, Category.LIBRARY, "tag1,tag2");

		POIService poiService = new POIService();
		
		poiService.addPoi(shopPOIFurniture);
		poiService.addPoi(shopPOIHardstore);
		poiService.addPoi(shopPOILIbrary);
		
		SearchResult result = poiService.search(filter);
		
		assertTrue(result.getPois().size() == 1);
		assertTrue(((Shop)result.getPois().get(0)).getShopCategory().getName().equals("muebleria"));
	}
	
	@Test
	public void searchBankByNameTest() throws InvalidPoiException
	{
		String filter = "Rio";
		Coordenate bankCoord = new Coordenate(-34.616325, -58.428837);		
		Bank bankPOIRio = new Bank("Rio", bankCoord, "tag1,tag2");
		Bank bankPOIGalicia = new Bank("Galicia", bankCoord, "tag1,tag2");
		Bank bankPOIFrances = new Bank("Frances", bankCoord, "tag1,tag2");

		POIService poiService = new POIService();
		poiService.addPoi(bankPOIRio);
		poiService.addPoi(bankPOIGalicia);
		poiService.addPoi(bankPOIFrances);
		
		SearchResult result = poiService.search(filter);
		
		assertTrue(result.getPois().size() == 1);
		assertTrue(((Bank)result.getPois().get(0)).getName().equals("Rio"));
	}
	
	@Test
	public void isCloserToTestBank()
	{
		POIService poiService = new POIService();
		boolean result = false;
		Coordenate bankCoord = new Coordenate(-34.616325, -58.428837);
		Bank bankPOIRio = new Bank("Rio", bankCoord, "tag1,tag2");
		Coordenate bankCoord2 = new Coordenate(-34.619574, -58.429606);
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
		Coordenate busCoord2 = new Coordenate(-34.619186, -58.425894);
		BusStop busPOI90 = new BusStop("Parada 90", busCoord2, 90, "90");
		
		result = poiService.isCloserTo(busPOI84, busPOI90);
		assertTrue(result);
	}
	
	@Test
	public void isCloserToTestShop()
	{
		POIService poiService = new POIService();
		boolean result = false;
		//Coordenate shopCoord = new Coordenate(-34.616325, -58.428837);
		Coordenate shopCoord = new Coordenate(-34.608515, -58.372282);
		Shop shopPOIFurniture = new Shop("Muebles Tito", shopCoord, Category.FURNITURE, "tag1,tag2");
		//Coordenate shopCoord2 = new Coordenate(-34.616325, -58.428837);		
		Coordenate shopCoord2 = new Coordenate(-34.608242, -58.370029);
		Shop shopPOITeatro = new Shop("Teatro Metropolitan", shopCoord2, Category.FURNITURE, "tag1,tag2");
		
		result = poiService.isCloserTo(shopPOIFurniture, shopPOITeatro);
		assertTrue(result);
	}
	
	@Test
	public void searchCGPByServiceTest() throws InvalidPoiException
	{
		String filter = "Asesoramiento";
		Coordenate cgpCoord = new Coordenate(-34.616325, -58.428837);	
		
		List<String> cgpPOIPalermoServ = new ArrayList<String>();
		cgpPOIPalermoServ.add("ABL");
		cgpPOIPalermoServ.add("Asesoramiento Legal");
		cgpPOIPalermoServ.add("Asesoramiento Contable");
	
		List<String> cgpPOICentenarioServ = new ArrayList<String>();
		cgpPOICentenarioServ.add("Bodas");
		cgpPOICentenarioServ.add("Asesoramiento Legal");
	
		List<String> cgpPOICaballitoServ = new ArrayList<String>();
		cgpPOICaballitoServ.add("Patentes");
		cgpPOICaballitoServ.add("Rentas");
		
		CGP cgpPOIPalermo = new CGP("CGP Palermo", cgpCoord, cgpPOIPalermoServ, "tag1,tag2");
		CGP cgpPOICentenario = new CGP("CGP Centenario", cgpCoord, cgpPOICentenarioServ, "tag1,tag2");
		CGP cgpPOICaballito = new CGP("CGP Caballito", cgpCoord, cgpPOICaballitoServ, "tag1,tag2");

		POIService poiService = new POIService();
		
		poiService.addPoi(cgpPOIPalermo);
		poiService.addPoi(cgpPOICentenario);
		poiService.addPoi(cgpPOICaballito);
		
		SearchResult result = poiService.search(filter);
		
		assertTrue(result.getPois().size() == 2);
	}
	
	@Test
	public void isCloserToCGPOKTest()
	{
		POIService poiService = new POIService();
		boolean result = false;
		
		List<String> services = new ArrayList<String>();
		List<Coordenate> zoneCoord = new ArrayList<Coordenate>();
		
		// We create the Bank POI
		Coordenate CGPCoord = new Coordenate(-34.608828, -58.430982);	
		
		Coordenate zone1 = new Coordenate(-34.618194, -58.427373);
		Coordenate zone2 = new Coordenate(-34.618706, -58.423339);
		Coordenate zone3 = new Coordenate(-34.620949, -58.424058);
		Coordenate zone4 = new Coordenate(-34.620887, -58.426236);
		Coordenate zone5 = new Coordenate(-34.619704, -58.427845);
		Coordenate zone6 = new Coordenate(-34.618194, -58.427373);
		
		zoneCoord.add(zone1);
		zoneCoord.add(zone2);
		zoneCoord.add(zone3);
		zoneCoord.add(zone4);
		zoneCoord.add(zone5);
		zoneCoord.add(zone6);
		
		services.add("Rentas");
		services.add("ABL");
		
		CGP CGPPOI = new CGP("CGP Parque Centenario", CGPCoord, zoneCoord, services, "tag1,tag2");
		
		// We create the busStop POI
		Coordenate busCoord = new Coordenate(-34.619572, -58.425753);
		BusStop busPOI = new BusStop("Parada 84", busCoord, 114, "tag1,tag2");
		
		result = poiService.isCloserTo(busPOI, CGPPOI);
		assertTrue(result);
	}
	
	@Test
	public void isCloserToCGPFAILTest()
	{
		POIService poiService = new POIService();
		boolean result = false;
		
		List<String> services = new ArrayList<String>();
		List<Coordenate> zoneCoord = new ArrayList<Coordenate>();
		
		// We create the Bank POI
		Coordenate CGPCoord = new Coordenate(-34.608828, -58.430982);	
		
		Coordenate zone1 = new Coordenate(-34.618194, -58.427373);
		Coordenate zone2 = new Coordenate(-34.618706, -58.423339);
		Coordenate zone3 = new Coordenate(-34.620949, -58.424058);
		Coordenate zone4 = new Coordenate(-34.620887, -58.426236);
		Coordenate zone5 = new Coordenate(-34.619704, -58.427845);
		Coordenate zone6 = new Coordenate(-34.618194, -58.427373);
		
		zoneCoord.add(zone1);
		zoneCoord.add(zone2);
		zoneCoord.add(zone3);
		zoneCoord.add(zone4);
		zoneCoord.add(zone5);
		zoneCoord.add(zone6);
		
		services.add("Rentas");
		services.add("ABL");
		
		CGP CGPPOI = new CGP("CGP Parque Centenario", CGPCoord, zoneCoord, services, "tag1,tag2");
		
		// We create the busStop POI
		Coordenate busCoord = new Coordenate(-34.615793, -58.422781);
		BusStop busPOI = new BusStop("Parada 84", busCoord, 114, "tag1,tag2");
		
		result = poiService.isCloserTo(busPOI, CGPPOI);
		assertFalse(result);	
	}
}

