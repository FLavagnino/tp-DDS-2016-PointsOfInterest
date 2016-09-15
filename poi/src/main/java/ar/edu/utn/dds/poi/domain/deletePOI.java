package ar.edu.utn.dds.poi.domain;
import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.service.POIService;

public class deletePOI extends Process{
	
	protected POI poi;
	protected POIService service;
	String ValueOfService; // este es el valor del servicio REST.
	
	public void delete(){
		//service.search(ValueOfService); seguir.
	}
}
