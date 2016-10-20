package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.dds.poi.service.historical.HistoricalManager;
import org.joda.time.DateTime;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import ar.edu.utn.dds.poi.constant.*;
import ar.edu.utn.dds.poi.domain.*;
import ar.edu.utn.dds.poi.exception.*;
import ar.edu.utn.dds.poi.process.*;
import ar.edu.utn.dds.poi.process.jobs.*;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.utils.MetersDistance;

public class POIService implements Searcher
{
	private MetersDistance distanceService;
	private ExternalPOIService externalPOIService;
	private AuthService authService;
	
	private List<POI> poiList;
	
	public POIService() 
	{
		this.distanceService = new MetersDistance();
		this.externalPOIService = new ExternalPOIService();
		this.authService = new AuthService();
		this.poiList = new ArrayList<POI>();
		
		// Agrego algunos fake, despues deberian salir de la base de datos
		Coordenate coordenate = new Coordenate(-34.619160, -58.425443);
		Shop shopPOI = new Shop("muebleria", coordenate, Category.FURNITURE, "tag1,tag2");
		shopPOI.setUnit(1);
		poiList.add(shopPOI);

		shopPOI = new Shop("libreria", coordenate, Category.LIBRARY, "tag1,tag2");
		shopPOI.setUnit(2);
		poiList.add(shopPOI);		
		
		Coordenate bankCoord = new Coordenate(-34.618191, -58.428769);
		Bank bankPoi = new Bank("Banco Ciudad",bankCoord, "tags");
		bankPoi.setUnit(3);
		poiList.add(bankPoi);
		
	}
	
	public int metersFromToHaversine(POI poiFrom, POI poiTo) 
	{
		return distanceService.metersFromToHaversine(poiFrom.getCoordenate(), poiTo.getCoordenate());
	}
	
	public int metersFromTo(POI poiFrom, POI poiTo) 
	{
		return distanceService.metersFromToHaversine(poiFrom.getCoordenate(), poiTo.getCoordenate());
	}
		
	public boolean isValid(POI poi) 
	{
		return poi.getCoordenate() != null && poi.getName() != null;
	}
	
	public boolean isCloserTo(POI poiFrom, POI poiTo)
	{
		int meters = this.metersFromTo(poiFrom, poiTo);	
		return poiTo.isCloserTo(meters, poiFrom);
	}
	
	public boolean isAvailable(POI poi, DateTime dateTime, String service)
	{
		return poi.isAvailable(dateTime, service);
	}
	
	// Solo para pruebas, despues lo tenemos que borrar.
	public void listPOIs()
	{
		for(POI poi : poiList)
		{
			System.out.println("Nombre: " + poi.getName() + ", tags: " + poi.getTags());
		}		
	}
	
	public SearchResult search(String filter)
	{
		List<POI> result = new ArrayList<POI>();
		
		// Se agregan los pois externos.
		//poiList.addAll(externalPOIService.getExternalPois(filter));
						
		for(POI poi : poiList)
		{
			if (poi.matchFilter(filter))
			{
				result.add(poi);
			}
		}
		result.addAll(externalPOIService.getExternalPois(filter));
		
		return new SearchResult(result);
	}
	
	public SearchResult search(String filter, String userName)
	{
		return this.search(filter);
	}
	
	public SearchResult search(String filter, String userName, String token) throws InvalidUserException 
	{
		User user = this.authService.getUser(userName, token);
		
		if (user != null)
		{	
			if (user.getAuditMode())
			{
				Audit auditSearch = new Audit();
				return auditSearch.search(filter, userName);
			}
			else
			{
				return this.search(filter);
			}
		}
		else
		{
			throw new InvalidUserException(Constant.POISERVICE_AUTH_ERROR_MSG); 
		}
	}

	public void addPoi(POI poi) throws InvalidPoiException 
	{
//		if (this.isValid(poi))
//		{
			poiList.add(poi);
//		}
//		else
//		{
//			throw new InvalidPoiException(Constant.POISERVICE_INVALID_POI_MSG);
//		}
	}
	
	public void deletePoi(Integer unit)
	{
		poiList.removeIf( poi -> poi.getUnit() == unit );
	}
	
	public void updatePoi(POI poi) throws InvalidPoiException
	{
		if (this.isValid(poi))
		{
			deletePoi(poi.getUnit());
			addPoi(poi);
		}
		else
		{
			throw new InvalidPoiException(Constant.POISERVICE_INVALID_POI_MSG);
		}
	}
		
	// Process 1: Update Shops from FILE.
	public void updateShopProcess() throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		// Crea una instancia del planificador
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// Inicia el planificador
		scheduler.start();
		
		// Identificador del job
		JobKey key = new JobKey(ProcessUpdateShop.class.getSimpleName());

		// Crea una instancia del proceso y con la opción requestRecovery(true) se fuerzan reintentos en caso de fallas
		JobDetail job = JobBuilder.newJob(ProcessUpdateShop.class).withIdentity(key).requestRecovery(true)
				.build();

		// Crea una instancia del disparador (trigger) de procesos
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").startNow().build();

		// Crea una instancia del proceso inicial y su listener
		ProcessPoi procesoInicial = new ProcessUpdateShop();		
		ProcessListener procesoInicialListener = procesoInicial.getProcesoListener();
		
		// Asocia el listener al planificador
		scheduler.getListenerManager().addJobListener((JobListener)procesoInicialListener,
				KeyMatcher.keyEquals(key));
	
		// Agrega el proceso al planificador junto con su disparador (trigger)
		StdSchedulerFactory.getDefaultScheduler().scheduleJob(job, trigger);
		
		// Para darle tiempo al planificador que se puedea inicializar y
		// ejecutar los procesos
		Thread.sleep(10000);

		// Finaliza el planificador
		scheduler.shutdown();
	}
	
	// Process 2: Delete POIs	
	public void deletePOIProcess() throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		// Crea una instancia del planificador
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// Inicia el planificador
		scheduler.start();
		
		// Identificador del job
		JobKey key = new JobKey(ProcessDeletePoi.class.getSimpleName());

		// Crea una instancia del proceso y con la opción requestRecovery(true) se fuerzan reintentos en caso de fallas
		JobDetail job = JobBuilder.newJob(ProcessDeletePoi.class).withIdentity(key).requestRecovery(true)
				.build();

		// Crea una instancia del disparador (trigger) de procesos
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").startNow().build();

		// Crea una instancia del proceso inicial y su listener
		ProcessPoi procesoInicial = new ProcessDeletePoi();		
		ProcessListener procesoInicialListener = procesoInicial.getProcesoListener();
		
		// Asocia el listener al planificador
		scheduler.getListenerManager().addJobListener((JobListener)procesoInicialListener,
				KeyMatcher.keyEquals(key));
	
		// Agrega el proceso al planificador junto con su disparador (trigger)
		StdSchedulerFactory.getDefaultScheduler().scheduleJob(job, trigger);
		
		// Para darle tiempo al planificador que se puedea inicializar y
		// ejecutar los procesos
		Thread.sleep(30000);

		// Finaliza el planificador
		scheduler.shutdown();
	}
	
	// Process 3: Add actions to user
	public void addActionToUsersProcess () throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		// Crea una instancia del planificador
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// Inicia el planificador
		scheduler.start();
		
		// Identificador del job
		JobKey key = new JobKey(ProcessAddActionToUsers.class.getSimpleName());

		// Crea una instancia del proceso y con la opción requestRecovery(true) se fuerzan reintentos en caso de fallas
		JobDetail job = JobBuilder.newJob(ProcessAddActionToUsers.class).withIdentity(key).requestRecovery(true)
				.build();

		// Crea una instancia del disparador (trigger) de procesos
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").startNow().build();

		// Crea una instancia del proceso inicial y su listener
		ProcessPoi procesoInicial = new ProcessAddActionToUsers();		
		ProcessListener procesoInicialListener = procesoInicial.getProcesoListener();
		
		// Asocia el listener al planificador
		scheduler.getListenerManager().addJobListener((JobListener)procesoInicialListener,
				KeyMatcher.keyEquals(key));
	
		// Agrega el proceso al planificador junto con su disparador (trigger)
		StdSchedulerFactory.getDefaultScheduler().scheduleJob(job, trigger);
		
		// Para darle tiempo al planificador que se puedea inicializar y
		// ejecutar los procesos
		Thread.sleep(10000);

		// Finaliza el planificador
		scheduler.shutdown();		
	}
	
	// Process 4: Multiprocess
	public void multiProcess() throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		// Crea una instancia del planificador
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// Inicia el planificador
		scheduler.start();
		
		// Identificador del job
		JobKey key = new JobKey(MultiProcessDeletePoi.class.getSimpleName());

		// Crea una instancia del proceso y con la opción requestRecovery(true) se fuerzan reintentos en caso de fallas
		JobDetail job = JobBuilder.newJob(MultiProcessDeletePoi.class).withIdentity(key).requestRecovery(true)
				.build();

		// Crea una instancia del disparador (trigger) de procesos
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger").startNow().build();

		// Crea una instancia del proceso inicial y su listener
		ProcessPoi procesoInicial = new MultiProcessDeletePoi();	
		ProcessListener procesoInicialListener = procesoInicial.getProcesoListener();
		
		// Asocia el listener al planificador
		scheduler.getListenerManager().addJobListener((JobListener)procesoInicialListener,
				KeyMatcher.keyEquals(key));

		// Agrega el proceso al planificador junto con su disparador (trigger)
		StdSchedulerFactory.getDefaultScheduler().scheduleJob(job, trigger);
		
		// Para darle tiempo al planificador que se puedea inicializar y
		// ejecutar los procesos
		Thread.sleep(10000);

		// Finaliza el planificador
		scheduler.shutdown();
	}
}
