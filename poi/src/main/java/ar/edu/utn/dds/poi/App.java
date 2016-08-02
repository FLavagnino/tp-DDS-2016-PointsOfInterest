package ar.edu.utn.dds.poi;

import java.util.List;

import ar.edu.utn.dds.poi.domain.POI;
import ar.edu.utn.dds.poi.service.POIService;

public class App {
public static void main(String[] args) {
	POIService poiService = new POIService();
	List<POI> pois = poiService.getPois();
	for (POI poi : pois) {
		System.out.println(poi.getName());
		System.out.println(poi.getCoordenate().getLatitude());
		System.out.println(poi.getCoordenate().getLongitude());
		System.out.println(poi.getTags());
	}
}
}
