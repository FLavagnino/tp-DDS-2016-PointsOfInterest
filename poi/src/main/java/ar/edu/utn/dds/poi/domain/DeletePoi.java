package ar.edu.utn.dds.poi.domain;

import ar.edu.utn.dds.poi.service.POIService;
import org.joda.time.DateTime;


public class DeletePoi extends Thread{

	protected Integer type; // El tipo de proceso es el numero de proceso (1,2,3 o 4)
	protected String filter;
	protected DateTime date;
	POIService poiservice = new POIService();
	
	public void run(){
		poiservice.deletePOIOfProcess2(filter, date);
	}
	
}
