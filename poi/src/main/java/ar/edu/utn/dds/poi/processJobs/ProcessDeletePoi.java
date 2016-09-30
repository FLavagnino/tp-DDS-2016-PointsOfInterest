package ar.edu.utn.dds.poi.processJobs;

import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.processJobs.ProcessAddActionToUsers;
import ar.edu.utn.dds.poi.processService.ProcessPoi;

import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class ProcessDeletePoi extends ProcessPoi{

	protected Integer type; // El tipo de proceso es el numero de proceso (1,2,3 o 4)
	protected String filter;
	protected DateTime date;
	POIService poiservice = new POIService();
	
	public ProcessDeletePoi(){
		setSiguienteProceso(ProcessAddActionToUsers.class);
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		DateTime dateToday = new DateTime();
		SearchResult poisSearched = poiservice.search(filter);	
		POI poi = poisSearched.getPois().get(1);
		
		if (dateToday.compareTo(date) > 0){ 
			poiservice.deletePoi(poi.getUnit()); //toma el primer poi de la lista de pois encontrados.
		}

	}
	
	
	
}
