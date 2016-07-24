package ar.edu.utn.dds.poi.service;

import java.util.ArrayList;

import com.fasterxml.jackson.core.type.TypeReference;

import ar.edu.utn.dds.poi.dto.CGPServDTO;
import ar.edu.utn.dds.poi.factory.JsonFactory;
import ar.edu.utn.dds.poi.utils.HttpService;

public class CGPSearchService {
	
	private final static String URL = "http://trimatek.org/Consultas/centro"; 
	
	private HttpService httpService = new HttpService();
	private JsonFactory jsonFactory = new JsonFactory();
	
	public ArrayList<CGPServDTO> getCGPs(String area) {
		String url = URL;
		if(area != null) {
			url += "?zona=" + area;
		}
		System.out.println(url);
		return jsonFactory.fromJson(httpService.getInputStreamReaderOf(url), new TypeReference<ArrayList<CGPServDTO>>() {});
	}
}
