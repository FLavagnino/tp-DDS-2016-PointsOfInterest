package ar.edu.utn.dds.poi.test;

import org.quartz.SchedulerException;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.dds.poi.exception.InvalidPoiException;
import ar.edu.utn.dds.poi.process.ProcessConfig;
import ar.edu.utn.dds.poi.service.*;

public class Entrega4Test 
{	
	private POIService poiService;

	@Before
	public void setUp() throws InvalidPoiException
	{
		poiService = new POIService();
	}
	
	@Test
	// Prueba sobre el proceso para actualizar los locales comerciales.
	// Imprime por consola el resultado.
	public void updateShopTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		ProcessConfig config = new ProcessConfig();
		config.setUserName("luisk");
		config.setUserMail("luiskahrs@gmail.com");
		config.setSendMail(true);
		config.setRefireCount(2);
		config.setSleepTime(1000000);
		
		poiService.updateShopProcess(config);
	}
	
	@Test
	// Prueba sobre el proceso para borrar POIs
	// Imprime por consola el resultado.
	public void deletePoiTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		ProcessConfig config = new ProcessConfig();
		config.setUserName("luisk");
		config.setUserMail("luiskahrs@gmail.com");
		config.setSendMail(true);
		config.setRefireCount(2);
		config.setSleepTime(1000000);
		
		poiService.deletePOIProcess(config);
	}

	@Test	
	// Prueba sobre el proceso para agregar acciones sobre los usuarios.
	// Imprime por consola el resultado.
	public void addActionToUsersTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		ProcessConfig config = new ProcessConfig();
		config.setUserName("luisk");
		config.setUserMail("luiskahrs@gmail.com");
		config.setSendMail(true);
		config.setRefireCount(2);
		config.setSleepTime(10000000);
		
		poiService.addActionToUsersProcess(config);
	}
	
	@Test	
	// Prueba sobre el proceso para agregar acciones sobre los usuarios.
	// Imprime por consola el resultado.
	public void addActionToUsersRollbackTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		ProcessConfig config = new ProcessConfig();
		config.setUserName("luisk");
		config.setUserMail("luiskahrs@gmail.com");
		config.setSendMail(true);
		config.setRefireCount(2);
		config.setSleepTime(10000000);
		
		poiService.addActionToUsersRollbackProcess(config);
	}
	
	@Test	
	// Prueba sobre el proceso que invoca multiples procesos (todos)
	// Imprime por consola el resultado.
	public void multiProcessTest() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SchedulerException, InterruptedException
	{
		ProcessConfig config = new ProcessConfig();
		config.setUserName("luisk");
		config.setUserMail("luiskahrs@gmail.com");
		config.setSendMail(true);
		config.setRefireCount(2);
		config.setSleepTime(10000);
		
		poiService.multiProcess(config);
	}
}