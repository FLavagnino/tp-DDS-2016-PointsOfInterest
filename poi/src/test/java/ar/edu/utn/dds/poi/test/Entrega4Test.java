package ar.edu.utn.dds.poi.test;

import org.quartz.SchedulerException;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.dds.poi.constant.Category;
import ar.edu.utn.dds.poi.domain.Coordenate;
import ar.edu.utn.dds.poi.domain.Shop;
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
	public void multiProcessTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.multiProcess();
	}
}