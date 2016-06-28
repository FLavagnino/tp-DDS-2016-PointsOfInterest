package poi;

import static org.junit.Assert.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import poi.constant.Service;
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
		BusStop busPOI = new BusStop("Parada 84", busCoord);
		
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
		Bank bankPOI = new Bank("Banco Galicia Av. La Plata", bankCoord);
		
		OpeningHour monday = new OpeningHour(Service.NA, DayOfWeek.MONDAY, 10, 0, 15, 0);
		OpeningHour tuesday = new OpeningHour(Service.NA, DayOfWeek.TUESDAY, 10, 0, 15, 0);
		OpeningHour wednesday = new OpeningHour(Service.NA, DayOfWeek.WEDNESDAY, 10, 0, 15, 0);
		OpeningHour thursday = new OpeningHour(Service.NA, DayOfWeek.THURSDAY, 10, 0, 15, 0);
		OpeningHour friday = new OpeningHour(Service.NA, DayOfWeek.FRIDAY, 10, 0, 15, 0);
		
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
		
		// We create the Bank POI
		Coordenate CGPCoord = new Coordenate(-34.608828, -58.430982);		
		CGP CGPPOI = new CGP("CGP Parque Centenario", CGPCoord);
		
		OpeningHour mondayIncomes = new OpeningHour(Service.INCOMES, DayOfWeek.MONDAY, 10, 0, 14, 0);
		OpeningHour tuesdayIncomes = new OpeningHour(Service.INCOMES, DayOfWeek.TUESDAY, 10, 0, 14, 0);
		OpeningHour wednesdayIncomes = new OpeningHour(Service.INCOMES, DayOfWeek.WEDNESDAY, 10, 0, 14, 0);
		OpeningHour thursdayIncomes = new OpeningHour(Service.INCOMES, DayOfWeek.THURSDAY, 10, 0, 14, 0);
		OpeningHour fridayIncomes = new OpeningHour(Service.INCOMES, DayOfWeek.FRIDAY, 10, 0, 14, 0);
		OpeningHour tuesdayABL = new OpeningHour(Service.ABL, DayOfWeek.TUESDAY, 10, 0, 14, 0);
		
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
		
		// We create the Bank POI
		Coordenate shopCoord = new Coordenate(-34.616325, -58.428837);		
		Shop shopPOI = new Shop("Cinemark Av. La Plata", shopCoord);
		
		OpeningHour mondayMon = new OpeningHour(Service.NA, DayOfWeek.MONDAY, 10, 0, 13, 0);
		OpeningHour mondayAft = new OpeningHour(Service.NA, DayOfWeek.MONDAY, 17, 0, 22, 30);
		OpeningHour tuesdayMon = new OpeningHour(Service.NA, DayOfWeek.TUESDAY, 10, 0, 13, 0);
		OpeningHour tuesdayAft = new OpeningHour(Service.NA, DayOfWeek.TUESDAY, 17, 0, 20, 30);
		OpeningHour wednesdayMon = new OpeningHour(Service.NA, DayOfWeek.WEDNESDAY, 10, 0, 13, 0);
		OpeningHour wednesdayAft = new OpeningHour(Service.NA, DayOfWeek.WEDNESDAY, 17, 0, 20, 30);
		OpeningHour thursdayMon = new OpeningHour(Service.NA, DayOfWeek.THURSDAY, 10, 0, 13, 0);
		OpeningHour thursdayAft = new OpeningHour(Service.NA, DayOfWeek.THURSDAY, 17, 0, 20, 30);
		OpeningHour fridayMon = new OpeningHour(Service.NA, DayOfWeek.FRIDAY, 10, 0, 13, 0);
		OpeningHour fridayAft = new OpeningHour(Service.NA, DayOfWeek.FRIDAY, 17, 0, 20, 30);
		
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
	public void searchTest()
	{
		String filter = "114";
		POIService poiService = new POIService();
		
		List<POI> result = poiService.search(filter, new ArrayList<POI>());
		assertTrue(result.isEmpty());
	}
}
