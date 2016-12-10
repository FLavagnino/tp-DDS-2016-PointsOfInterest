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
	// Prueba sobre el proceso para actualizar los locales comerciales.
	// Imprime por consola el resultado.
	public void updateShopTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.updateShopProcess();
	}
	
	@Test
	// Prueba sobre el proceso para borrar POIs
	// Imprime por consola el resultado.
	public void deletePoiTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.deletePOIProcess();
	}

	@Test	
	// Prueba sobre el proceso para agregar acciones sobre los usuarios.
	// Imprime por consola el resultado.
	public void addActionToUsersTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.addActionToUsersProcess();
	}
	
	@Test	
	// Prueba sobre el proceso que invoca multiples procesos (todos)
	// Imprime por consola el resultado.
	public void multiProcessTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		this.poiService.multiProcess();
	}
}