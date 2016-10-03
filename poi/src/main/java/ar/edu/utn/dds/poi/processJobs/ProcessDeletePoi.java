package ar.edu.utn.dds.poi.processJobs;

import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.processJobs.ProcessAddActionToUsers;
import ar.edu.utn.dds.poi.processService.ProcessPoi;
import ar.edu.utn.dds.poi.utils.Mock;
import org.json.*;

import java.io.FileNotFoundException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class ProcessDeletePoi extends ProcessPoi{

	protected Integer type; // El tipo de proceso es el numero de proceso (1,2,3 o 4)
	
	POIService poiservice = new POIService();
	Mock mockList = new Mock();
	
	public ProcessDeletePoi()
	{
		// setSiguienteProceso(ProcessAddActionToUsers.class);
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{	
		
		JSONObject obj = new JSONObject(Constant.DELETE_POI_FILE_PATH);
		// String pageName = obj.getJSONObject("pageInfo").getString("pageName");
		JSONArray arr = obj.getJSONArray("Pois");
		for (int i = 0; i < arr.length(); i++)
		{
		    String value = arr.getJSONObject(i).getString("Valor");
		    String date = arr.getJSONObject(i).getString("Fecha");
		    

		    	for (POI poi : mockList.getPoisToDelete()) {
		    		if (poi.getUnit().toString() == value){
		    			poiservice.deletePoi(poi.getUnit());
		    		}
		    		
		    		//SearchResult searchResult = poiservice.search(poi.getName()); 
					//aca buscaria por nombre, habria que ver para que busque por UNIT. (asumo que Valor es el nombre)
				}

//				if (dateToday.compareTo(date) > 0)
//				{ 
//					poiservice.deletePoi(poi.getUnit()); //toma el primer poi de la lista de pois encontrados.
//				}
				
			}	
		//SearchResult poisSearched = poiservice.search("");
		//POI poi = poisSearched.getPois().get(1);

	}
}
