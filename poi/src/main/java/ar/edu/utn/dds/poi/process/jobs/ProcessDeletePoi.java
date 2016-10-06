package ar.edu.utn.dds.poi.process.jobs;

import ar.edu.utn.dds.poi.service.POIService;
import ar.edu.utn.dds.poi.service.historical.SearchResult;
import ar.edu.utn.dds.poi.constant.Constant;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.process.ProcessPoi;
import ar.edu.utn.dds.poi.utils.Mock;
import org.joda.time.DateTime;
import org.joda.time.format.*;
import org.json.*;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class ProcessDeletePoi extends ProcessPoi{

	protected Integer type; // El tipo de proceso es el numero de proceso (1,2,3 o 4)
	
	POIService poiservice = new POIService();
	Mock mockList = new Mock();
	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
	
	public ProcessDeletePoi()
	{
		// setSiguienteProceso(ProcessAddActionToUsers.class);
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException 
	{	
		JSONObject obj = new JSONObject(Constant.DELETE_POI_FILE_PATH);
		JSONArray arr = obj.getJSONArray("Pois");
		for (int i = 0; i < arr.length(); i++)
		{
		    String value = arr.getJSONObject(i).getString("Valor");
		    String date = arr.getJSONObject(i).getString("Fecha");
		    DateTime dateTimeJson = fmt.parseDateTime(date);
		    DateTime dateToday = new DateTime();
		    
		    SearchResult searchResult = poiservice.search(value);
			//El problema que el search buscar por nombre o por string, y el GCBA da como resultado el valor de busqueda y fecha.
		    //supongamos que valor es el nombre del poi a buscar.
			if ((searchResult != null && searchResult.getPois().size() > 0) && (dateToday.compareTo(dateTimeJson) > 0))
			{
				POI poi = (POI) searchResult.getPois().get(0);
				poiservice.deletePoi(poi.getUnit());		
			}
		}
	}
}
