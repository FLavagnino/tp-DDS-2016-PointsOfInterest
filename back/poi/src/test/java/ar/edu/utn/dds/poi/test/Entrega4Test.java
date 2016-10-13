package ar.edu.utn.dds.poi.test;

import org.quartz.SchedulerException;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.dds.poi.exception.InvalidPoiException;
import ar.edu.utn.dds.poi.service.*;

public class Entrega4Test 
{	
	private POIService poiService;

	@Before
	public void setUp() throws InvalidPoiException
	{
		this.poiService = new POIService();
	}
	
	@Test
	public void updateShopTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.updateShopProcess();
	}
	
	@Test
	public void deletePoiTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.deletePOIProcess();
	}

	@Test	
	public void addActionToUsersTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.addActionToUsersProcess();
	}
	
	@Test	
	public void multiProcessTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.multiProcess();
	}
}