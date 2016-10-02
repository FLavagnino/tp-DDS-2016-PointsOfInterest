package ar.edu.utn.dds.poi.test;

import org.quartz.SchedulerException;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.dds.poi.service.*;


public class Entrega4Test 
{	
	private POIService poiService;

	@Before
	public void setUp()
	{
		this.poiService = new POIService();
	}
	
	@Test
	public void updateShopTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.updateShopProcess();
	}
}