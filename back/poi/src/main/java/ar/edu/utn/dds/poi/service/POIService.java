package ar.edu.utn.dds.poi.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
import ar.edu.utn.dds.poi.repository.LogRepository;
import ar.edu.utn.dds.poi.repository.POIRepository;
import ar.edu.utn.dds.poi.repository.UserRepository;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.utils.MetersDistance;

public class POIService implements Searcher
{
	private MetersDistance distanceService;
	private ExternalPOIService externalPOIService;
	private AuthService authService;
	private POIRepository poiRepository;
	
	private List<POI> poiList;
	
	public POIService() 
	{
		distanceService = new MetersDistance();
		externalPOIService = new ExternalPOIService();
		authService = new AuthService();
		poiList = new ArrayList<POI>();
		poiRepository = new POIRepository();
		
		// Agrego algunos fake, despues deberian salir de la base de datos
		Coordenate coordenate = new Coordenate(-34.619160, -58.425443);
		Shop shopPOI = new Shop("muebleria", coordenate, "muebleria", 1000, "tag1,tag2");
		shopPOI.setUnit(1);
		poiList.add(shopPOI);

		shopPOI = new Shop("libreria", coordenate, "libreria", 1200, "tag1,tag2");
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
		if (poi.getCoordenate() != null && poi.getName() != null) {
			return true;
		} else {
			throw new InvalidPoiException(Constant.POISERVICE_INVALID_POI_MSG);
		}
	}
	
	public boolean isCloserTo(POI poiFrom, POI poiTo)
	{
		int meters = metersFromTo(poiFrom, poiTo);	
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
		List<POI> result = filterPois(filter, poiRepository.getAllPois());

		result.addAll(externalPOIService.getExternalPoisCached(filter));
		
		return new SearchResult(result);
	}

	public List<POI> filterPois(String filter, List<POI> pois) {
		String[] filters = filter.split(" ");

		List<POI> result = new ArrayList<>();

		for(POI poi : pois)
		{
			for(String f : filters) {
				if (poi.matchFilter(f))
				{
					result.add(poi);
				}
			}
		}

		return result;
	}
	
	public SearchResult search(String filter, String userName)
	{
		return search(filter);
	}

	public SearchResult search(String filter, String userName, String token)
	{
		SearchResult searchResult = null;
		User user = authService.getUser(userName, token);
		
		if (user != null)
		{	
			if (user.getAuditMode())
			{
				Audit auditSearch = new Audit();
				searchResult = auditSearch.search(filter, userName);
			}
			else
			{
				searchResult = search(filter);
			}
		}
		return searchResult;
	}

	public void addPoi(POI poi) 
	{
		if (isValid(poi)) 
		{
			poiRepository.save(poi);
		}
	}
	
	public void deletePoi(POI poi)
	{
		poiRepository.delete(poi);
	}
	
	public void updatePoi(POI poi) throws InvalidPoiException
	{
		if (isValid(poi))
		{
			poiRepository.update(poi);
		}
	}
		
	// Process 1: Update Shops from FILE.
	public void updateShopProcess(ProcessConfig config) throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		ProcessPoi process = new ProcessUpdateShop();		
		launchJob(process, config);
	}
	
	// Process 2: Delete POIs	
	public void deletePOIProcess(ProcessConfig config) throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		ProcessPoi process = new ProcessDeletePoi();
		launchJob(process, config);
	}
	
	// Process 3: Add actions to user
	public void addActionToUsersProcess(ProcessConfig config) throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		ProcessPoi process = new ProcessAddActionToUsers();
		launchJob(process, config);
	}
	
	// Process 3: Add actions to user
	public void addActionToUsersRollbackProcess(ProcessConfig config) throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		ProcessPoi process = new ProcessAddActionToUsersRollback();
		launchJob(process, config);
	}
	
	// Process 4: Multiprocess
	public void multiProcess(ProcessConfig config) throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		ProcessPoi process = new MultiProcessDeletePoi();	
		launchJob(process, config);
	}
	
	public Serializable saveUser(User user)
	{
		UserRepository userRep = new UserRepository();
		return userRep.save(user);
	}
	
	public void updateUser(User user)
	{
		UserRepository userRep = new UserRepository();
		userRep.update(user);
	}
	
	public User getUser(Serializable userID)
	{
		UserRepository userRep = new UserRepository();
		return userRep.get(userID);
	}
	
	public Log getLog(Serializable logID)
	{
		LogRepository logRep = new LogRepository();
		return logRep.getLog(logID);
	}
	
	public List<Log> getLogByUserName(String userName)
	{
		LogRepository logRep = new LogRepository();
		return logRep.getLogByUserName(userName);
	}
	
	public void launchJob(ProcessPoi process, ProcessConfig config) throws SchedulerException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		// Crea una instancia del planificador
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		// Pasamos la configuracion
		scheduler.getContext().put("config", config);
		
		// Inicia el planificador
		scheduler.start();
		
		// Identificador del job
		JobKey key = new JobKey(process.getClass().getSimpleName());

		// Crea una instancia del proceso y con la opción requestRecovery(true) se fuerzan reintentos en caso de fallas
		JobDetail job = JobBuilder.newJob(process.getClass()).withIdentity(key).requestRecovery(config.getRefireCount() != 0)
				.build();

		// Crea una instancia del disparador (trigger) de procesos
		Trigger trigger = TriggerBuilder.newTrigger()
							.withIdentity("trigger")
							.startNow().build();

		// Crea una instancia del proceso inicial y su listener
		ProcessPoi procesoInicial = process;	
		ProcessListener procesoInicialListener = procesoInicial.getProcesoListener();
		
		// Asocia el listener al planificador
		scheduler.getListenerManager().addJobListener((JobListener)procesoInicialListener,
				KeyMatcher.keyEquals(key));

		// Agrega el proceso al planificador junto con su disparador (trigger)
		StdSchedulerFactory.getDefaultScheduler().scheduleJob(job, trigger);
		
		// Para darle tiempo al planificador que se puedea inicializar y
		// ejecutar los procesos
		Thread.sleep(config.getSleepTime());

		// Finaliza el planificador
		scheduler.shutdown();
	}
}
